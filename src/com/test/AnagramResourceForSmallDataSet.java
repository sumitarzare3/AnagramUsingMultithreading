package com.kaufland.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class AnagramResourceForSmallDataSet {

	public static void main(String args[]) throws Exception {

		/// read file to get value.
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("D:/file/Kaufland_Test/sample.txt"));
		List<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] anagramslist = lines.toArray(new String[0]);

		Map<String, ArrayList<String>> map = new ConcurrentHashMap<>();

		for (int i = 0; i < anagramslist.length; i++) {
			String anagram1 = anagramslist[i];
			boolean notFound = true;
			for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
				String anagram2 = entry.getKey();
				if (FindAnagramUsingHashMap.AnagramAnalyzer(anagram1, anagram2)) {
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

		for (ArrayList<String> values : map.values()) {
			if (values.size() > 1) {
				for (int i = 0; i < values.size(); i++) {
					if (i != 0) {
						System.out.print(" ");
					}
					System.out.print(values.get(i));
				}
				System.out.println();
			}
			
		}
	}
}
