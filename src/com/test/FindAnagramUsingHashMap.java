package com.kaufland.test;

import java.util.HashMap;

public class FindAnagramUsingHashMap {

	static boolean AnagramAnalyzer(String anagram1, String anagram2) {

		String anagram1Copy = anagram1.replaceAll("\\s", "").toLowerCase();
		String anagram2Copy = anagram2.replaceAll("\\s", "").toLowerCase();

		boolean status = true;

		if (anagram1Copy.length() != anagram2Copy.length()) {

			status = false;
		} else {

			HashMap<Character, Integer> map = new HashMap<Character, Integer>();

			for (int i = 0; i < anagram1Copy.length(); i++) {

				char mapKey = anagram1Copy.charAt(i);

				int countOfChar = 0;

				if (map.containsKey(mapKey)) {
					countOfChar = map.get(mapKey);
				}

				map.put(mapKey, ++countOfChar);
				mapKey = anagram2Copy.charAt(i);

				countOfChar = 0;
				if (map.containsKey(mapKey)) {
					countOfChar = map.get(mapKey);
				}

				map.put(mapKey, --countOfChar);
			}

			for (int value : map.values()) {
				if (value != 0) {
					status = false;
				}
			}
		}
		return status;
	}
}