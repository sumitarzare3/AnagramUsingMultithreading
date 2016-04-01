package com.kaufland.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnagramResourceForLargeDataset {

	private static final int MYTHREADS = 2;

	public static void main(String args[]) throws Exception {

		// Lets take any number of threads to run
		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);

		/// read file to get value.
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("D:/file/Kaufland_Test/sample.txt"));
		List<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] anagramslist = lines.toArray(new String[0]);
		int size = anagramslist.length;

		Map<String, ArrayList<String>> map = new ConcurrentHashMap<>();

		FindAnagramUsingMultiThread f1 = new FindAnagramUsingMultiThread("Thread 1 to add", anagramslist, 0, size / 2,
				map);
		executor.execute(f1);

		FindAnagramUsingMultiThread f2 = new FindAnagramUsingMultiThread("Thread 2 to add", anagramslist, size / 2,
				size, map);
		executor.execute(f2);

		executor.shutdown();

		while (!executor.isTerminated()) {
		}
		printResult(map);
	}

	private static void printResult(Map<String, ArrayList<String>> map) {
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