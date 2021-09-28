/**
 * 
 */
package com.app.hsbc.excrates.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.app.hsbc.excrates.config.AppPropertyConfig;
import com.app.hsbc.excrates.config.PropertyMapper;
import com.app.hsbc.excrates.constants.ApplicationConstants;
import com.app.hsbc.excrates.dto.LoadDateResponseDTO;
import com.app.hsbc.excrates.dto.RatesDTO;
import com.app.hsbc.excrates.entity.BaseDataEntity;
import com.app.hsbc.excrates.entity.ExchangeRateDataEntity;
import com.app.hsbc.excrates.exception.ApplicationFatalException;
import com.app.hsbc.excrates.repository.ExchangeRateDataRepository;
import com.app.hsbc.excrates.util.CommonUtil;

/**
 * @author Rejin Chandran R
 *
 */

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	ExchangeRateDataRepository exRateRepoObj;
	@Autowired
	private PropertyMapper propsMap;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * @param exRateRepoObj
	 */
	public ExchangeRateServiceImpl(ExchangeRateDataRepository exRateRepoObj) {
		super();
		this.exRateRepoObj = exRateRepoObj;
	}

	@Override
	public void loadExtData(LocalDate currentDate) throws ApplicationFatalException {
		CommonServiceHelper serviceHelperObj = new CommonServiceHelper();
		List<LocalDate> dateList = CommonUtil.prepareDatesList(currentDate);
		String accessKey = propsMap.getApiAccessKey();
		String symbols = propsMap.getSymbols();
		String extAPIUrl = propsMap.getApiUrl();

		ResponseEntity<LoadDateResponseDTO> responseObj = null;
		List<BaseDataEntity> baseDataEntityListObj = new ArrayList<BaseDataEntity>();
		try {
			for (LocalDate date : dateList) {
				responseObj = restTemplate.getForEntity(extAPIUrl, LoadDateResponseDTO.class, date, accessKey, symbols);
				LoadDateResponseDTO dtoObj = responseObj.getBody();
				BaseDataEntity baseDataEntityObj = serviceHelperObj.prepareDataToPersist(dtoObj);
				if (baseDataEntityObj != null) {
					baseDataEntityListObj.add(baseDataEntityObj);
				}
			}

			saveBaseDataEntity(baseDataEntityListObj);
		} catch (ApplicationFatalException | RestClientException e) {
			e.printStackTrace();
			throw new ApplicationFatalException(e.getMessage(), e);
		}
	}

	@Transactional
	public void saveBaseDataEntity(List<BaseDataEntity> baseDataEntityListObj) throws ApplicationFatalException {

		try {
			baseDataEntityListObj.forEach(entityObj -> {
				this.exRateRepoObj.save(entityObj);
			});
		} catch (IllegalArgumentException ex) {
			throw new ApplicationFatalException(ex.getMessage(), ex);
		}

	}

	@Override
	public LoadDateResponseDTO processRequestedExchangeRate(LocalDate requestedDate) throws ApplicationFatalException {
		
		LoadDateResponseDTO responseDTOObj = null;
		Optional<BaseDataEntity> baseDataEntityOptObj =  Optional.empty(); 
		BaseDataEntity baseDataObj = null;
		HashMap <String , BigDecimal> rateMap= new HashMap<String , BigDecimal>();
		baseDataEntityOptObj = this.exRateRepoObj.findLoadedDataByRequestedDate(requestedDate);
		if(baseDataEntityOptObj.isPresent()) {
			baseDataObj = baseDataEntityOptObj.get();
			responseDTOObj = new LoadDateResponseDTO();
			responseDTOObj.setExchrateDate(baseDataObj.getExchrateDate());
			Set<ExchangeRateDataEntity> baseDataSet =  baseDataObj.getExchangeRateData();
			for(ExchangeRateDataEntity xchangeRate : baseDataSet) {
				rateMap.put(xchangeRate.getCurrencyCode(), xchangeRate.getExchangeRate());
			}
			responseDTOObj.setRateMap(rateMap);
		}else {
			throw new ApplicationFatalException ("Data Not Found");
		}
		return responseDTOObj;
	}

}
