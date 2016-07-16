package com.nng.rampup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternMatcherExample {
	
	private static String input = "Lorem ipsum dolor sit amet..";
	private static String input_2 = "aaa\\bbb";
	private static String input_3 = "a asldfksdéf lé .";
	
	private static void matcherFindExample(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bool = matcher.find();
		System.out.println("matcherFindExample: " + bool);
	}
	
	private static void quoteMatcherFindExample(String regex, String input) {
		String quote = Pattern.quote(regex);
		Pattern pattern = Pattern.compile(quote);
		Matcher matcher = pattern.matcher(input);
		boolean bool = matcher.find();
		System.out.println("quoteMatcherFindExample: " + bool);
	}
	
	private static void matcherMatchesExample(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bool = matcher.matches();
		System.out.println("matcherMatchesExample: " + bool);
	}
	
	private static void matcherGroupExample(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()) {
			String str = matcher.group(0);
			System.out.println("matcherGroupExample: " + str);
		} else {
			System.out.println("matcherGroupExample: not find");
		}
	}
	
	private static void matcherStartEndExample(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while(matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			System.out.println("matcherStartEndExample: " + start + " : " + end);
		}
	}
	
	public static void main(String[] args) {
		matcherFindExample("i?s?m", input);
		matcherFindExample("\\D", input);
		quoteMatcherFindExample("\\", input_2);
		matcherMatchesExample("[a]", input_3);
		matcherGroupExample("\\D", input);
		matcherStartEndExample(".m.[d,u]", input);
	}
	
	

}
