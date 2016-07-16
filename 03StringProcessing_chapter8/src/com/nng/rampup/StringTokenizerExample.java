package com.nng.rampup;

import java.util.Date;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		performanceCompare();
	}
	
	public static void performanceCompare() {
		StringJoiner sb = new StringJoiner(" ");
		for(int i = 0; i<=10000000; i++) {
			sb.add(String.valueOf(i));
		}
		long s1 = new Date().getTime();
		StringTokenizer st = new StringTokenizer(sb.toString(), " ");
		System.out.println("tokenizer: " + (new Date().getTime() - s1));
		
		long s2 = new Date().getTime();
		String[] split = sb.toString().split(" ");
		System.out.println("split: " + (new Date().getTime() - s2));
	}
	
}
