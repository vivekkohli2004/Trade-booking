package com.jpm.interview.kohli.vivek.entity;

public enum Direction {
	BUY("B"), SELL("S"), NONE("ERROR");
	String dirString;

	public String getDirString() {
		return dirString;
	}

	public static Direction getDirection(String dirString) {
			if (dirString.equalsIgnoreCase("B")) {
				return BUY;
			} else if (dirString.equalsIgnoreCase("S")) {
				return SELL;
			} else {
				throw new RuntimeException(
						"Incorrect value received for Direction.");
			}
		//return NONE;
	}
	Direction(String dirString) {
		this.dirString = dirString;
	}
	
}
