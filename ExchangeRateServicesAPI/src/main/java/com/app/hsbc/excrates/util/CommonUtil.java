/**
 * 
 */
package com.app.hsbc.excrates.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.hsbc.excrates.constants.ApplicationConstants;

/**
 * @author Rejin Chandran R
 *
 */
public class CommonUtil {
	
	public  static String prepareUrl(String apiUrl , String accessKey , String symbols , LocalDate date) {
	   
		StringBuilder urlBuilder = new StringBuilder();
		if( date != null ) {
			urlBuilder.append(apiUrl).append(ApplicationConstants.FORWARD_SLASH).append(date)
					.append(ApplicationConstants.QUESTION_MARK).append(ApplicationConstants.ACCESS_KEY)
					.append(ApplicationConstants.EQUAL_SYMBOL).append(accessKey)
					.append(ApplicationConstants.AMPERSAND_SYMBOL).append(ApplicationConstants.SYMBOLS_KEY)
					.append(ApplicationConstants.EQUAL_SYMBOL).append(symbols);
			}
		return urlBuilder.toString();
	}

		
	public static List<LocalDate> prepareDatesList(LocalDate today) {
		List<LocalDate> dateList = new ArrayList<LocalDate>();
		int itrCount = 0;
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		dateList.add(today);

		while (itrCount != 365)
			if (day > 1 || day <= 30) {
				if (day != 1) {
					day--;
					today = LocalDate.of(year, month, day);
				} else {
					month--;
					if (month != 2) {
						day = 30;
					} else {
						if (today.isLeapYear()) {
							day = 29;
						} else {
							day = 28;
						}
					}
					if (month <= 0) {

						month = 12;
						year--;
					}
					today = LocalDate.of(year, month, day);

				}

				dateList.add(today);

				itrCount++;
			}
		return dateList;

	}


}
