package com.nng;

/**
 * Created by Zsu at long time ago
 * This Example module connected to the second half of Chapter 7
 */
public class Main {

    public static void main(String[] args) {

        try {
            Examples example = new Examples();
           //example.oldMultipleExc();
           // example.newMultipleExc();
           // example.oldRethrow();
           // example.newRethrow();
           // example.oldSimpleRethrow();
           // example.tryWITHOUTResources();
          // example.tryWITHResources();
           // example.tryWITHMultipleResources();
           // example.doSuppression();
           // example.doBADSuppression();
            //example.puzzle();

        } catch (Exception e) {
            System.out. print("Catched from main ");
            Examples.log(e);
        }
    }


}
