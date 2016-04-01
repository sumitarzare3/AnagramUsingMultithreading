package com.kaufland.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FindAnagramUsingMultiThread implements Runnable {

	String threadName;
	String[] anagramsList;
	int start = 0;
	int end = 0;
	Map<String, ArrayList<String>> map;

	FindAnagramUsingMultiThread(String threadname, String[] anagramslist, int start, int size,
			Map<String, ArrayList<String>> hashMap) {
		this.threadName = threadname;
		this.anagramsList = anagramslist;
		this.start = start;
		this.end = size;
		map = hashMap;
		System.out.println("Creating " + this.threadName + "Start " + this.start + " end " + size);
	}

	@Override
	public void run() {
		System.out.println("Start processing..." + this.threadName);
		for (int i = start; i < end; i++) {
			String anagram1 = anagramsList[i];
			boolean notFound = true;
			for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
				String anagram2 = entry.getKey();
				if (AnagramAnalyzer(anagram1, anagram2)) {
					ArrayList<String> ar = entry.getValue();
					ar.add(anagram1);
					notFound = false;
					break;
				}
			}
			if (notFound) {
				ArrayList<String> arr = new ArrayList<>();
				arr.add(anagram1);
				map.put(anagram1, arr);
			}
		}
		System.out.println("Finish processing..." + this.threadName);
	}

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
