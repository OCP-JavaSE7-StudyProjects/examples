package com.company;

import java.io.IOException;
import java.sql.SQLException;

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
