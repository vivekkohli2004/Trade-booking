package com.jpm.interview.kohli.vivek.process;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReportGeneratorHelper {

	public static Map<String, BigDecimal> sortMapOnKeys(
			Map<String, BigDecimal> map) {
		List<Entry<String, BigDecimal>> list = new LinkedList<Entry<String, BigDecimal>>(
				map.entrySet());
		// Sort the Entity objects on the basis of values. This Map.Entity will
		// also have keys with it.
		Collections.sort(list, new Comparator<Entry<String, BigDecimal>>() {
			public int compare(Entry<String, BigDecimal> entity1,
					Entry<String, BigDecimal> entity2) {
				//WE NEED DESCENDING ORDER
				return entity2.getValue().compareTo(entity1.getValue());
			}
		});

		Map<String, BigDecimal> sortedMap = new LinkedHashMap<String, BigDecimal>();
		for (Entry<String, BigDecimal> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
