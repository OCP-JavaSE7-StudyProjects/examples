package com.nng;

/**
 * Created by Zsu
 */
class StaticOuter {
    static class Nested {
        void goOuter() {
            System.out.println("Outer Nested Static function goOuter");
            System.out.println("----------------------");
        }
    }

}
