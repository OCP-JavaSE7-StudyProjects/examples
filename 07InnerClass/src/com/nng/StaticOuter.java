package com.nng;

/**
 * Created by Zsu
 */
class StaticOuter {


    static class Nested {
        void goOuter() {
            System.out.println("Outer Nested Static function goOuter");
            System.out.println("----------------------");
            class Belso {
                public void doValami() {
                    System.out.println("Nohát");
                }

                public void doValamikulsobb() {
                    Belso belso = new Belso();
                    belso.doValami();
                }
            }
            new Belso().doValamikulsobb();
        }


    }

    }
