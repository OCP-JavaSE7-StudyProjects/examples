package com.nng;

/**
 * Created by Zsu
 */

class InnerBase {
    public void writeInfo() {
        System.out.println("Inner Base");
        System.out.println("------------------");
    }
}

class AnonymousOuter {

    InnerBase anonymousInner = new InnerBase() {
        public void writeInfo() {
            System.out.println("anonymous Inner Base");
            System.out.println("------------------");
        }

        public void doSomethingElse() {   //not available from outside: InnerBase does not have this method(polymorphism)
            System.out.println("anonymous does something else");
            System.out.println("------------------");
        }
    };

    public void writeInnerInfo() {
        anonymousInner.writeInfo();
    }

    InnerBaseCompletable completableAnonymousInner = new InnerBaseCompletable() {//this can be replaced by lambda expr.
        @Override
        public void writeCompletableInfo() {
            System.out.println("anonymous completable implementer just in time");
            System.out.println("------------------");
        }
    };

    public void writeInnerCompletableInfo() {
        completableAnonymousInner.writeCompletableInfo();
    }
}

interface InnerBaseCompletable {
    public void writeCompletableInfo();
}