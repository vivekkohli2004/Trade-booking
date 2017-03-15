package com.jpm.interview.kohli.vivek.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.StringTokenizer;

import com.jpm.interview.kohli.vivek.entity.Direction;
import com.jpm.interview.kohli.vivek.entity.TradeInstruction;

/*
 * Class responsible for parsing the input file.
 * 
 * Following the SINGLE RESPONSIILITY PRINCIPLE, this class just parses & returns a list of TradeInstruction
 * objects. It is on the client to ensure to modify the List of trade objects as needed. 
 * In this case ReportGenerator would create Maps as needed by it.
 * 
 * */
public class FileParser {

	private final String PIPE = "|";
	//private final String HYPHEN = "-";

	public List<TradeInstruction> parseFile(File file) throws FileNotFoundException {
		FileInputStream fileInputstream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(fileInputstream));
		List<TradeInstruction> trades=new ArrayList<TradeInstruction>();
		String strLine;
		try {
			while ((strLine = bufferedReader.readLine()) != null) {
				TradeInstruction tradeInstruction = parseLine(strLine);
				trades.add(tradeInstruction);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trades;
	}

	private TradeInstruction parseLine(String strLine) {
		TradeInstruction tradeInstruction = new TradeInstruction();
		StringTokenizer tokenizer = new StringTokenizer(strLine, PIPE);
		while (tokenizer.hasMoreTokens()) {
			tradeInstruction.setEntity(tokenizer.nextToken());
			tradeInstruction.setDirection(Direction.getDirection(tokenizer.nextToken()));
			tradeInstruction.setFxRate(new BigDecimal(tokenizer.nextToken()));
			tradeInstruction.setCurrency(Currency.getInstance(tokenizer
					.nextToken()));
			// String instDate=tokenizer.nextToken();
			// StringTokenizer instDateTokenizer= new StringTokenizer(instDate,
			// HYPHEN);
			/*
			 * while(instDateTokenizer.hasMoreTokens()){ int
			 * dd=Integer.parseInt(instDateTokenizer.nextToken()); int
			 * mm=Integer.parseInt(instDateTokenizer.nextToken()); int
			 * yyyy=Integer.parseInt(instDateTokenizer.nextToken());
			 * tradeInstruction.setInstDate(new LocalDate(yyyy, mm, dd)); }
			 */
			// EASIER to use date as String as it would be needed as a key of a
			// Map.
			tradeInstruction.setInstDate(tokenizer.nextToken());
			tradeInstruction.setSettDate(tokenizer.nextToken());
			tradeInstruction.setUnits(Integer.parseInt(tokenizer.nextToken()));
			tradeInstruction.setPricePerUnit(new BigDecimal(tokenizer.nextToken()));
			tradeInstruction
					.setEffectiveSettDate(FileParserHelper
					.getEffectiveSettlementdate(tradeInstruction.getCurrency(),tradeInstruction.getSettDate()));
		}
		return tradeInstruction;
	}

}
