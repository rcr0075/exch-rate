/**
 * 
 */
package com.app.hsbc.excrates.services;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.app.hsbc.excrates.dto.LoadDateResponseDTO;
import com.app.hsbc.excrates.entity.BaseDataEntity;
import com.app.hsbc.excrates.entity.ExchangeRateDataEntity;

/**
 * @author Rejin Chandran R
 *
 */
@Component
public class CommonServiceHelper {


	
	protected BaseDataEntity prepareDataToPersist(LoadDateResponseDTO dtoObj) {
		if (dtoObj.getStatus() == "success") {
			BaseDataEntity baseDataEntityObj = new BaseDataEntity();
			ExchangeRateDataEntity exRateDataObj = null;
			baseDataEntityObj.setExchrateDate(dtoObj.getExchrateDate());
			baseDataEntityObj.setBase(dtoObj.getBase());
			for (Map.Entry<String,BigDecimal> entry : dtoObj.getRateMap().entrySet()) {
				exRateDataObj = new ExchangeRateDataEntity();
				exRateDataObj.setCurrencyCode(entry.getKey());
				exRateDataObj.setExchangeRate(entry.getValue());
				baseDataEntityObj.getExchangeRateData().add(exRateDataObj);
			}
			return baseDataEntityObj;
		}
		return null;
		
	}	


	

}
