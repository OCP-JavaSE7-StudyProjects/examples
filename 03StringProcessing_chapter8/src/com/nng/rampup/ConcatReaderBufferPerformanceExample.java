package com.nng.rampup;

import java.util.Date;
import java.util.StringJoiner;

public class ConcatReaderBufferPerformanceExample {
	
	private static final Integer max = 10000;
	
	private static void stringConcatExample() {
		String s = "";
		long start = new Date().getTime();
		for (int i = 0; i< max; i++) {
			s = s + String.valueOf(i);
		}
		System.out.println("Concat speed: " + (new Date().getTime() - start));
	}
	
	private static void stringConcat2Example() {
		String s = "";
		long start = new Date().getTime();
		for (int i = 0; i< max; i++) {
			s = s.concat(String.valueOf(i));
		}
		System.out.println("Concat2 speed: " + (new Date().getTime() - start));
	}
	
	private static void stringBuilderExample() {
		StringBuilder sb = new StringBuilder();
		long start = new Date().getTime();
		for (int i = 0; i< max; i++) {
			sb.append(String.valueOf(i));
		}
		System.out.println("StringBuilder speed: " + (new Date().getTime() - start));
	}
	
	private static void stringJoinerExample() {
		StringJoiner sj = new StringJoiner("");
		long start = new Date().getTime();
		for (int i = 0; i< max; i++) {
			sj.add(String.valueOf(i));
		}
		System.out.println("StringJoiner speed: " + (new Date().getTime() - start));
	}
	
	
	
	public static void main(String[] args) {
		stringConcatExample();
		stringConcat2Example();
		stringBuilderExample();
		stringJoinerExample();
	}

}
