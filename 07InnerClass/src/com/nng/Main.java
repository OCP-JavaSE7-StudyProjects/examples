package com.nng;

/**
 * Created by Zsu
 * This Example module connected to Chapter 12th
 */
public class Main {
    public static void main(String[] args) {
//REGULAR
        //RegularOuter regular=new RegularOuter();
        //regular.makeInnerFromInside_And_SeeOuter();

        //RegularOuter.RegularInner regularInner1 = regular.new RegularInner();// need an outer instance!
        //regularInner1.seeOuter();

        //RegularOuter.RegularInner regularInner2 = new RegularOuter().new RegularInner();
        //regularInner2.seeOuter();

        //RegularOuter.FirstLevel shadowTest = new RegularOuter().new FirstLevel();
        //shadowTest.methodInFirstLevel(23);
//METHOD LOCAL
        //MethodLocalOuter method=new MethodLocalOuter();
        //method.doStuff();

       // MethodLocalOuter.validatePhoneNumber("123-456-7890", "456-7890");
//ANONYMOUS
        //AnonymousOuter anonym=new AnonymousOuter();
        //anonym.writeInnerInfo();
        //anonym.doSomethongElse();
        //anonym.writeInnerCompletableInfo();

       // ArgumentDefinedAnonymousOuter anonymInArgument=new ArgumentDefinedAnonymousOuter();
       // anonymInArgument.doBeautifulThings();

//STATIC INNER
        //StaticOuter.Nested staticOuter = new StaticOuter.Nested(); // both class names
        //staticOuter.goOuter();

        //STATIC_INNER staticInner = new STATIC_INNER(); // access the enclosed class
        //staticInner.goInner();
    }


    static class STATIC_INNER {
        void goInner()
        {
            System.out.println("Inner Nested Static function goInner");
            System.out.println("-----------------");
        }
    }
}
