/**
 * 
 */
package com.app.hsbc.excrates.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Rejin Chandran R
 *
 */

@Configuration
@PropertySource("classpath:application.properties")
public class AppPropertyConfig {
	
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
