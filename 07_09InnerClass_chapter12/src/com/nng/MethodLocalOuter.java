package com.nng;

/**
 * Created by Zsu
 */
class MethodLocalOuter {
    private String outerVariable = "outer";

    void doStuff() {
       final String methodLocalVariable = "local";
        class MethodLocalInner {
            public void seeOuter() {
                System.out.println("The MethodLocalOuter's outerVariable: " + outerVariable);
                System.out.println("The methodLocalVariable: " + methodLocalVariable); //Valid in JDK 8 and later without final!
                System.out.println("------------------------");
            }
            public void seeOuter(String param) {
                System.out.println("The MethodLocalOuter's outerVariable: " + outerVariable);
                System.out.println("The methodLocalVariable: " + methodLocalVariable+ param); //Valid in JDK 8 and later without final!
                System.out.println("------------------------");
            }
            // close inner class method
        } // close inner class definition
        MethodLocalInner mi = new MethodLocalInner(); // HERE WE USE THE INNER CLASS
        mi.seeOuter();
        mi.seeOuter(methodLocalVariable);
    } // close outer class method doStuff()



    static String regularExpression = "[^0-9]";
    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
        final int numberLength = 10;
        // int numberLength = 10;  // Valid in JDK 8 and later

        class PhoneNumber {
            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber){
                // numberLength = 7;
                String currentNumber = phoneNumber.replaceAll(
                        regularExpression, "");
                if (currentNumber.length() == numberLength)
                    formattedPhoneNumber = currentNumber;
                else
                    formattedPhoneNumber = null;
            }
            public String getNumber() {
                return formattedPhoneNumber;
            }
//            public void printOriginalNumbers() {   // Valid in JDK 8 and later:
//                System.out.println("Original numbers are " + phoneNumber1 +
//                    " and " + phoneNumber2);
//            }
        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);
//        myNumber1.printOriginalNumbers();      // Valid in JDK 8 and later:
        if (myNumber1.getNumber() == null)
            System.out.println("First number is invalid");
        else
            System.out.println("First number is " + myNumber1.getNumber());
        if (myNumber2.getNumber() == null)
            System.out.println("Second number is invalid");
        else
            System.out.println("Second number is " + myNumber2.getNumber());
    }

} // close outer class
