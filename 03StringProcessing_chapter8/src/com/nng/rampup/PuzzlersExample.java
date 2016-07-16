package com.nng.rampup;

public class PuzzlersExample {
	
	public static void main(String[] args) {
		stringConcatChar();
		stringBuilderCharAppend();
		internExample();
	}
	
	private static void stringConcatChar() {
		System.out.print("H" + "a");
		System.out.print('H' + 'a'); 
		System.out.println();
	}

	private static void stringBuilderCharAppend() {
		StringBuilder sb = new StringBuilder();
		sb.append('H').append('a').append('H').append('a');
		System.out.println(sb.toString());
	}
	
	private static void internExample() {
		String HEL = "HEL";
		String LO = "LO";
		System.out.println("HELLO" == (HEL + LO));
		System.out.println("HELLO" == (HEL + LO).intern());
	}

}
