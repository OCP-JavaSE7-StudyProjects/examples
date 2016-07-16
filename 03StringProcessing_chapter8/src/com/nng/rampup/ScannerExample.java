package com.nng.rampup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerExample {
	
	private static final String FILE_PATH = "src/file/test.txt";
	
	private static void scanFromSystemIn() {
		try(Scanner scanner = new Scanner(System.in)) {
			String scanString = scanner.next();
			System.out.println(scanString);
		}
	}
	
	private static void scanFromFile() {
		try(Scanner scanner = new Scanner(new File(FILE_PATH))) {
			while(scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		scanFromSystemIn();
		scanFromFile();
	}

}
