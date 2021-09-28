package com.app.hsbc.excrates.config;
/**
 * 
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Rejin Chandran R
 *
 */

@Component
@Getter
@NoArgsConstructor
public class PropertyMapper {

	@Value("${exchrate.ext.apiurl}")
	private String apiUrl;
	@Value("${exchrate.ext.apiaccesskey}")
	private String apiAccessKey;
	@Value("${exchrate.ext.symbols}")
	private String symbols;
	
}
