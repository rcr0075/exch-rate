/**
 * 
 */
package com.app.hsbc.excrates.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

/**
 * @author Rejin Chandran R
 *
 */
@Data
public class RatesDTO {
	
	@JsonAlias({"USD", "HKD" , "GBP"})
	private String currencyCode;
	private BigDecimal rate;

}
