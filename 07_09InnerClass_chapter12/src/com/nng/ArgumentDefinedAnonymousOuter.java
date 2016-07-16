package com.nng;

/**
 * Created by Zsu
 */
class ArgumentDefinedAnonymousOuter {
    void doBeautifulThings() {
        ArgumentBase base = new ArgumentBase();
        base.doStuff(new ArgumentCompletable() {
            public void muhaha() {
                System.out.println("Muhahahahahaha!");
                System.out.println("-----------------");
            } // end muhaha method
        }); // end inner class def, arg, and b.doStuff stmt.
    } // end doBeautifulThings()
} // end class

interface ArgumentCompletable {
    void muhaha();
}

class ArgumentBase {
    void doStuff(ArgumentCompletable f) {
        f.muhaha();
    }
}
