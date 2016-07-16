package com.nng.rampup;

public class BufferVsReaderThreadExample {

	 private static StringBuilder sb = new StringBuilder();
//	 private static final StringBuffer sb = new StringBuffer();

	    public static void addProperty(String name) {
	        sb.append(name);
	    }

	    public static void main(String[] args) throws InterruptedException {
	        Thread t1 = new Thread(() -> addProperty("aaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	        Thread t2 = new Thread(() -> addProperty("ccccccccccccccccccc"));
	        t1.start();
	        t2.start();
	        t1.join();
	        t2.join();
	        System.out.println(sb.toString());
	    }

}
