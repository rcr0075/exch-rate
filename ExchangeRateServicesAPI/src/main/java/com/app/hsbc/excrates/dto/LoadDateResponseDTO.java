/**
 * 
 */
package com.app.hsbc.excrates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Rejin Chandran R
 *
 */
@Data
public class LoadDateResponseDTO {
	@JsonProperty("success")
	private String status;
	@JsonProperty("date")
	private LocalDate exchrateDate;
	@JsonProperty("base")
	private String base;
	@JsonProperty("timestamp")
	private long timesStamp;
	@JsonProperty("rates")
	HashMap<String , BigDecimal> rateMap;
	//RatesDTO ratesList;

}
