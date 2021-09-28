package com.app.hsbc.excrates.services;

import java.time.LocalDate;
import java.util.List;

import com.app.hsbc.excrates.dto.LoadDateResponseDTO;
import com.app.hsbc.excrates.entity.BaseDataEntity;
import com.app.hsbc.excrates.exception.ApplicationFatalException;

/**
 * @author Rejin Chandran R
 *
 */
public interface ExchangeRateService {

	public void loadExtData(LocalDate currentDate) throws ApplicationFatalException;
	public void saveBaseDataEntity(List<BaseDataEntity> baseDataEntityListObj) throws ApplicationFatalException;
	public LoadDateResponseDTO processRequestedExchangeRate(LocalDate requestedDate) throws ApplicationFatalException;

}
