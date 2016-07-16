package com.nng.rampup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;

public class ScannerVsBufferExample {
	
	private static final String FILE_PATH = "src/file/loremipsum.txt";
	
	private static void scanFromFile() {
		long start = new Date().getTime();
		try(Scanner scanner = new Scanner(new File(FILE_PATH))) {
			while(scanner.hasNextLine()) {
				scanner.nextLine();
				//System.out.println(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("scanner speed: " + (new Date().getTime() - start));
	}
	
	private static void readFromFile() {
		long start = new Date().getTime();
		String  thisLine = null;
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
	         while ((thisLine = br.readLine()) != null) {
	            //System.out.println(thisLine);
	         }       
	      }catch(Exception e){
	         e.printStackTrace();
	      }
		System.out.println("bufferedreader speed: " + (new Date().getTime() - start));
	}
	
	public static void main(String[] args) {
		readFromFile();
		scanFromFile();
	}

}
