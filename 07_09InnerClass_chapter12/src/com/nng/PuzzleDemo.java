package com.nng;

/**
 * Brought from DZone page :  https://dzone.com/articles/java-quiz-nested-classes
 */
class PuzzleDemo {

    private int a = 12;

    private int b = 6;

    PuzzleDemo() {

        NestedA nestedA = new NestedA(4);

        a = a - 12;

    }

    class NestedA {

        NestedA(int y) {

            NestedB nestedB = new NestedB(y);

            nestedB.methodB(y, a);

            a = a + b - y;

        }

        private void methodA(int z) {

            System.out.print("-a" + a + z + "-b" + (b - z));

        }

        class NestedB {

            NestedB(int i) {

                a = a - i;
                b = b + i;

            }

            private void methodB(int x, int z) {

                if (x < 5) {

                    a = a + x + b;

                }

                b = b + z;

                System.out.print("-a" + a + "-b" + b);

            }

        }

    }

    /**
    *    What happens when you try to compile and run the program?
    *   Illegal at compile time
    *   Legal at compile time, but illegal at run time
    *   This program writes "-a22-b18-a42-b42" to the standard output.
    *   This program writes "-a22-b18-a44-b40" to the standard output.
    *   This program writes "-a22-b18-a24-b42" to the standard output.
    *   This program writes "-a22-b20-a46-b42" to the standard output.
    *   This program writes "-a22-b18-a44-b42" to the standard output.
    *   This program writes nothing to the standard output.
    */
    public static void main(String[] args) {

        NestedA nestedA = new PuzzleDemo().new NestedA(2);


    }

}