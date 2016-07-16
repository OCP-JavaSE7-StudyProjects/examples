package com.nng.rampup;

public class StringBuilderExample {

	private static String reverseExample(String reverse) {
		String localReverse = new StringBuilder(reverse).reverse().toString();
		return new StringBuilder("In ")
				.append(reverse).append(" ")
				.append("reverse: ")
				.append(localReverse).toString();
	}

	private static String charAtExample(String str, int at) {
		StringBuilder sb = new StringBuilder(str);
		char character = sb.charAt(at);
		return new StringBuilder("In ")
				.append(str).append(" ")
				.append(at).append(". character: ")
				.append(character).toString();
	}

	private static String capacityExample(String str) {
		StringBuilder sb = new StringBuilder(str);
		return new StringBuilder("In ")
				.append(str).append(" ")
				.append("the capacity: ")
				.append(sb.capacity()).toString();
	}
	
	private static String codePointBeforeExample(String str, int at) {
		StringBuilder sb = new StringBuilder(str);
		return new StringBuilder("In ")
				.append(str).append(" ")
				.append(at).append(". char before: ")
				.append(sb.codePointBefore(at)).toString();
	}
	
	private static String deleteCharAtExample(String str, int at) {
		StringBuilder sb = new StringBuilder(str);
		return new StringBuilder("From ")
				.append(str).append(" delete char at: ")
				.append(at)
				.append(" ")
				.append(sb.deleteCharAt(at)).toString();
	}
	
	private static String deleteExample(String str, int from, int to) {
		StringBuilder sb = new StringBuilder(str);
		return new StringBuilder("From ")
				.append(str).append(" delete chars from ")
				.append(from)
				.append(" to: ")
				.append(to)
				.append(" ")
				.append(sb.delete(from, to)).toString();
	}

	public static void main(String[] args) {

		String APPLE = "APPLE";
		System.out.println(reverseExample(APPLE));
		System.out.println(charAtExample(APPLE, 1));
		System.out.println(capacityExample(APPLE));
		System.out.println(codePointBeforeExample(APPLE, 1));
		System.out.println(deleteCharAtExample(APPLE, 1));
		System.out.println(deleteExample(APPLE, 0, 2));
		
	}

}
