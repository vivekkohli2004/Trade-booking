package com.jpm.interview.kohli.vivek.file;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.StringTokenizer;

import org.joda.time.LocalDate;
// A helper class having static methods.
public class FileParserHelper {
	
	private static List<String> specialCurrencies;
	private static List<Integer> workingDays;
	private static List<Integer> specialCurrenciesWorkingDays;
	private static final String HYPHEN = "-";

	static {
		// working day values correspond to Joda's API definition: 1 (Monday)
		// through 7 (Sunday)
		// settDate is in ddmmYYYY format

		specialCurrencies = new ArrayList<String>();
		specialCurrencies.add("AED");
		specialCurrencies.add("SAR");

		workingDays = new ArrayList<Integer>();
		workingDays.add(1);
		workingDays.add(2);
		workingDays.add(3);
		workingDays.add(4);
		workingDays.add(5);

		specialCurrenciesWorkingDays = new ArrayList<Integer>();
		specialCurrenciesWorkingDays.add(7);
		specialCurrenciesWorkingDays.add(1);
		specialCurrenciesWorkingDays.add(2);
		specialCurrenciesWorkingDays.add(3);
		specialCurrenciesWorkingDays.add(4);
	}

	public static String getEffectiveSettlementdate(Currency currency,
			String settDate) {
		if (specialCurrencies.contains(currency.getCurrencyCode())) {
			if (isNotAWorkingDay(settDate, true)) {
				return getNextSunday(settDate);
			} else {
				return settDate;
			}
		} else {
			// non-Special currency
			if (isNotAWorkingDay(settDate, false)) {
				return getNextMonday(settDate);
			} else {
				return settDate;
			}
		}
	}

	private static String getNextSunday(String strDate) {
		LocalDate date = convertStringToDate(strDate);
		int currDay = date.getDayOfWeek();
		date=date.plusDays(7 - currDay);
		return convertDateToString(date);
	}
	
	private static String getNextMonday(String strDate) {
		LocalDate date = convertStringToDate(strDate);
		int currDay = date.getDayOfWeek();
		date=date.plusDays(8 - currDay);
		return convertDateToString(date);
	}

	private static String convertDateToString(LocalDate date) {
		DecimalFormat df = new DecimalFormat("00");
		return df.format(date.getDayOfMonth()) + HYPHEN + df.format(date.getMonthOfYear()) + HYPHEN
				+ date.getYear();
	}

	// settDate e.g 13-01-2017
	private static boolean isNotAWorkingDay(String settDate,
			boolean isSpecialCurr) {
		LocalDate date = convertStringToDate(settDate);
		if (isSpecialCurr) {
			if (!specialCurrenciesWorkingDays.contains(date.getDayOfWeek())) {
				return true;
			} else {
				return false;
			}
		} else {
			if (!workingDays.contains(date.getDayOfWeek())) {
				return true;
			} else {
				return false;
			}
		}
	}

	private static LocalDate convertStringToDate(String strDate) {
		StringTokenizer dateTokenizer = new StringTokenizer(strDate, HYPHEN);
		LocalDate date = null;
		while (dateTokenizer.hasMoreTokens()) {
			int dd = Integer.parseInt(dateTokenizer.nextToken());
			int mm = Integer.parseInt(dateTokenizer.nextToken());
			int yyyy = Integer.parseInt(dateTokenizer.nextToken());
			date = new LocalDate(yyyy, mm, dd);
		}
		return date;
	}

}
