package com.jpm.interview.kohli.vivek.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpm.interview.kohli.vivek.entity.Direction;
import com.jpm.interview.kohli.vivek.entity.TradeInstruction;
import com.jpm.interview.kohli.vivek.file.FileParser;

public class ReportGenerator {
	
	public static void main(String[] args) {
		File file = new File("src\\main\\resources\\trades.txt");
		FileParser fileParser = new FileParser();
		try {
			List<TradeInstruction> trades = fileParser.parseFile(file);
			System.out.println("THE TRADES WITH EFFECTIVE SETTLEMENT DATE "
					+ "(and considered for further calculations) ARE:\n"
					+ trades);
			interpretParsedData(trades);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void interpretParsedData(List<TradeInstruction> trades) {
		Map<String, BigDecimal> dayIncomingTradesMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> dayOutGoingTradesMap = new HashMap<String, BigDecimal>();

		Map<String, BigDecimal> entityIncomingTradesMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> entityOutGoingTradesMap = new HashMap<String, BigDecimal>();

		for (TradeInstruction trade : trades) {
			BigDecimal usdAmt = getUsdAmountOfTrade(trade);
			if (isIncomingTrade(trade)) {
				populateDateTradesMap(dayIncomingTradesMap, trade, usdAmt);
				populateEntityTradesMap(entityIncomingTradesMap, trade, usdAmt);
			} else {
				populateDateTradesMap(dayOutGoingTradesMap, trade, usdAmt);
				populateEntityTradesMap(entityOutGoingTradesMap, trade, usdAmt);
			}
		}

		System.out.println("TOTAL TRADES RECEIVED IN FILE:" + trades.size());

		System.out.println("\nDATEWISE AMOUNT IN USD SETTLED ON INCOMING TRADES (CLIENT SIDE SELL):"
				+ dayIncomingTradesMap);
		System.out.println("DATEWISE AMOUNT IN USD SETTLED ON OUTGOING TRADES (CLIENT SIDE BUY):"
				+ dayOutGoingTradesMap);

		System.out
				.println("\nRANKING OF ENTITIES BASED ON INCOMING TRADES (CLIENT SIDE SELL):"
						+ ReportGeneratorHelper
								.sortMapOnKeys(entityIncomingTradesMap));

		System.out
				.println("RANKING OF ENTITIES BASED ON OUTGOING TRADES CLIENT SIDE BUY):"
						+ ReportGeneratorHelper
								.sortMapOnKeys(entityOutGoingTradesMap));

		// Collections.s
	}

	private static void populateEntityTradesMap(Map<String, BigDecimal> map,
			TradeInstruction trade, BigDecimal usdAmt) {
		String keyForEntity = trade.getEntity();
		if (map.containsKey(keyForEntity)) {
			BigDecimal valueForEntity = map.get(keyForEntity);
			map.put(keyForEntity, valueForEntity.add(usdAmt));
		} else {
			map.put(keyForEntity, usdAmt);
		}

	}

	private static void populateDateTradesMap(Map<String, BigDecimal> map,
			TradeInstruction trade, BigDecimal usdAmt) {
		String keyForDate = trade.getEffectiveSettDate();
		if (map.containsKey(keyForDate)) {
			BigDecimal valueForDate = map.get(keyForDate);
			map.put(keyForDate, valueForDate.add(usdAmt));
		} else {
			map.put(keyForDate, usdAmt);
		}
	}

	private static BigDecimal getUsdAmountOfTrade(TradeInstruction trade) {
		return trade.getPricePerUnit().multiply(trade.getFxRate())
				.multiply(new BigDecimal(trade.getUnits()));

	}

	private static boolean isIncomingTrade(TradeInstruction trade) {
		if (trade.getDirection().equals(Direction.SELL)) {
			return true;
		}
		return false;
	}
}
