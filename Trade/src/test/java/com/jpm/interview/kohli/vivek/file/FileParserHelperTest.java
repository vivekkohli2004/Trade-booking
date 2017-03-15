package com.jpm.interview.kohli.vivek.file;

import java.util.Currency;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FileParserHelperTest {

	@Test
	public void testGetEffectiveSettlementdateForSpecialCurr_NoRollOver(){
		String date="12-03-2017"; //Sunday which is a working day for SAR.
		Currency curr= Currency.getInstance("SAR");
		String result=FileParserHelper.getEffectiveSettlementdate(curr, date);
		assertEquals(date, result);//SAME AS INPUT DATE
	}
	
	@Test
	public void testGetEffectiveSettlementdateForSpecialCurr_withRollOver(){
		String date="10-03-2017"; //Friday which is NOT a working day for AED.
		Currency curr= Currency.getInstance("SAR");
		String result=FileParserHelper.getEffectiveSettlementdate(curr, date);
		assertEquals("12-03-2017", result);//Next Sunday after 10-03-2017
	}
	
	@Test
	public void testGetEffectiveSettlementdateForNormalCurr_NoRollOver(){
		String date="10-03-2017"; //Friday which is a working day for SEK.
		Currency curr= Currency.getInstance("SEK");
		String result=FileParserHelper.getEffectiveSettlementdate(curr, date);
		assertEquals(date, result);//SAME AS INPUT DATE
	}
	
	@Test
	public void testGetEffectiveSettlementdateForNormalCurr_WithRollOver(){
		String date="11-03-2017"; //Saturday which is NOT a working day for INR.
		Currency curr= Currency.getInstance("INR");
		String result=FileParserHelper.getEffectiveSettlementdate(curr, date);
		assertEquals("13-03-2017", result);//Next Monday after 10-03-2017
	}
	
}
