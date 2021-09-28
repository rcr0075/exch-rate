/**
 * 
 */
package com.app.hsbc.excrates.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.hsbc.excrates.dto.LoadDateResponseDTO;
import com.app.hsbc.excrates.exception.ApplicationFatalException;
import com.app.hsbc.excrates.services.ExchangeRateService;
import com.app.hsbc.excrates.services.ExchangeRateServiceImpl;

/**
 * @author Rejin Chandran R
 *
 */
@RestController
@RequestMapping("/exchangerate/api")
public class ExchangeRateController {
	
	@Autowired
	private ExchangeRateService exchangeRateServiceObj ;
	
	
	/**
	 * @param exchangeRateServiceObj
	 */
	public ExchangeRateController(ExchangeRateServiceImpl exchangeRateServiceImplObj) {
		super();
		this.exchangeRateServiceObj = exchangeRateServiceImplObj;
	}

	/**
	 * This API takes current Date as input and fetch last 365 days exchange rate details from the external API and inserts in to the database.
	 * ***/
	@GetMapping("/loaddata")
	public ResponseEntity<String> loadData(){
		LocalDate currentDate = LocalDate.now();
		try {
			this.exchangeRateServiceObj.loadExtData( currentDate);
			return new ResponseEntity<>("Data loaded succcessfully",HttpStatus.OK);
		} catch (ApplicationFatalException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() , HttpStatus.CONFLICT );
		}		
	}
	
	/**
	 * This API Returns Exchange Rate details to the user based on the requested date given 
	 * ***/
	@GetMapping("/getdata/{requestedDate}")
	public ResponseEntity<LoadDateResponseDTO> getRequestedExchangeRate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestedDate){
		LoadDateResponseDTO responseDTOObj = null;
		try {
			responseDTOObj = this.exchangeRateServiceObj.processRequestedExchangeRate( requestedDate);
			if(responseDTOObj != null ) {
				return new ResponseEntity<LoadDateResponseDTO> (responseDTOObj ,HttpStatus.FOUND);
			}else {
				throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Response is null");
			}
			
		} catch (ApplicationFatalException e) {
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}		
	}
	

	

}
