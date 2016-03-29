package com.nng;

/**
 * Created by Zsu
 */
class RegularOuter {
    private int outerVariable = 22;

    public void makeInnerFromInside_And_SeeOuter() {
        RegularInner in = new RegularInner(); // make an inner instance
        System.out.println("Inner class instantained.");
        in.seeOuter();
    }

    class RegularInner {
        public void seeOuter() {
            System.out.println("Inner class can read RegularOuter's private variable: " + outerVariable);
            System.out.println("Inner class ref is " + this);
            System.out.println("Outer class ref is " + RegularOuter.this);
            System.out.println("----------------------------");
        }
    }

    public int shadow = 0;
    class FirstLevel {
        public int shadow = 1;
        void methodInFirstLevel(int shadow) {
            System.out.println("shadowParam = " + shadow);
            System.out.println("this.shadow = " + this.shadow);
            System.out.println("RegularOuter.this.shadow = " + RegularOuter.this.shadow);
            System.out.println("----------------------------");
        }
    }
}
