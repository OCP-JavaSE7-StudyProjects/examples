package com.nng;
 /*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 subnote: modifications was made by Zsu in it
 */

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.lang.Iterable;
import java.util.function.ToIntFunction;

import static java.util.stream.Collectors.toList;

class Person {

    public enum Sex {
        MALE, FEMALE
    }

    static int count = 0;
    String name;
    Sex gender;
    int age;
    String emailAddress;

    public Person(String name, Sex gender) {
        this.name = name;
        this.gender = gender;
        this.age = new Random().nextInt(40);
        emailAddress = "Person_00" + (count++) + "@nng.sajt";
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void printPerson() {
        System.out.println("Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", emailAddress='" + emailAddress + '\'' +
                '}');
    }

    public static List<Person> createPeople() {
        List<Person> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(new Person(Character.getName(i), i > 5 ? Sex.FEMALE : Sex.MALE));
        }
        return result;
    }
}


public class LambdaDemo {


    interface CheckPerson {
        boolean test(Person p);
    }

    // Approach 1: Create Methods that Search for Persons that Match One
    // Characteristic

    public static void printPersonsOlderThan(List<Person> people, int age) {
        for (Person p : people) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods

    public static void printPersonsWithinAgeRange(
            List<Person> people, int low, int high) {
        for (Person p : people) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    // Approach 5: Specify Search Criteria Code with a Lambda Expression

    public static void printPersons(
            List<Person> people, CheckPerson tester) {
        for (Person p : people) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions

    public static void printPersonsWithPredicate(
            List<Person> people, Predicate<Person> tester) {
        for (Person p : people) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    // Approach 7: Use Lambda Expressions Throughout Your Application

    public static void processPersons(
            List<Person> people,
            Predicate<Person> tester,
            Consumer<Person> block) {
        for (Person p : people) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    // Approach 7, second example

    public static void processPersonsWithFunction(
            List<Person> people,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {
        for (Person p : people) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    // Approach 8: Use Generics More Extensively

    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    // Util skeletons: generic with lambda and stream:
    // Lambdas are "we can say" kind of unnamed methods..this is why we can use them
    // instead of an "anonymous one-method owner inner classes"!
    // You can omit the data type of the parameters in a lambda expression,
    // In addition, you can omit the parentheses if there is only one parameter.
    // Person p->p.getSg()...=> p->p.getSg()...=> Person::getSg()

    //A: applying a single value returner function on each element

    public static <T, U> List<U> transform(Collection<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(toList());
    }

    //B: A(function)+ use a filter with predicate
    public static <T, U> List<U> transform(Collection<? extends T> from, Function<T, U> func, Predicate<? super U> predicate) {
        return from.stream().map(func).filter(predicate).collect(toList());
    }

    //C: if the default sum reduction operation(reduction operations are: average, sum, min, max, and count)
    // is is not available for the input class of list
    //default sum looks like:  list.stream.sum();
    //
    public static <T> int sumOf(Collection<? extends T> list, ToIntFunction<T> func) {
        return list.stream().mapToInt(func).sum();
    }

    //D: filter result collector (using a filter on a stream will not affect the input!!)
    public static <T> List<T> filterBy(Collection<T> list, Predicate<T> pred) {
        return list.stream().filter(pred).collect(toList());
    }

    //X: applying a COLLECTION returner function on each element+ filter empty lists ....monster
    public static <T, U> List<U> flat(Collection<? extends T> from, Function<T, Collection<? extends U>> func) {
        return from.stream().map(func).filter(Objects::nonNull).flatMap(Collection::stream).collect(toList());
    }


    public static void main(String... args) {

        List<Person> people = Person.createPeople();

        for (Person p : people) {
            p.printPerson();
        }

        // Approach 1: Create Methods that Search for Persons that Match One
        // Characteristic

        System.out.println("Persons older than 20:");
        printPersonsOlderThan(people, 20);
        System.out.println();

        // Approach 2: Create More Generalized Search Methods

        System.out.println("Persons between the ages of 14 and 30:");
        printPersonsWithinAgeRange(people, 14, 30);
        System.out.println();

        // Approach 3: Specify Search Criteria Code in a Local Class

        System.out.println("Persons who are eligible for Selective Service:");

        class CheckPersonEligibleForSelectiveService implements CheckPerson {
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        }

        printPersons(
                people, new CheckPersonEligibleForSelectiveService());


        System.out.println();

        // Approach 4: Specify Search Criteria Code in an Anonymous Class

        System.out.println("Persons who are eligible for Selective Service " +
                "(anonymous class):");

        printPersons(
                people,
                new CheckPerson() {
                    public boolean test(Person p) {
                        return p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25;
                    }
                }
        );

        System.out.println();

        // Approach 5: Specify Search Criteria Code with a Lambda Expression

        System.out.println("Persons who are eligible for Selective Service " +
                "(lambda expression):");

        printPersons(
                people,
                (Person p) -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );

        System.out.println();

        // Approach 6: Use Standard Functional Interfaces with Lambda
        // Expressions

        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate parameter):");

        printPersonsWithPredicate(
                people,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );

        System.out.println();

        // Approach 7: Use Lamba Expressions Throughout Your Application

        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate and Consumer parameters):");

        processPersons(
                people,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.printPerson()
        );

        System.out.println();

        // Approach 7, second example

        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate, Function, and Consumer parameters):");

        processPersonsWithFunction(
                people,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        System.out.println();

        // Approach 8: Use Generics More Extensively

        System.out.println("Persons who are eligible for Selective Service " +
                "(generic version):");

        processElements(
                people,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        System.out.println();

        // Approach 9: Use Bulk Data Operations That Accept Lambda Expressions
        // as Parameters

        System.out.println("Persons who are eligible for Selective Service " +
                "(with bulk data operations):");

        people
                .stream()
                .filter(
                        p -> p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));

//---------------------------------------------------------------------------------------------------------------------
        // Reading Lambda --advantage!
//Labda expressions:
//        (p->p.getAge())
//        (Person::getAge())


        System.out.println("Calculator example");
        Calculator myApp = new Calculator();
        Calculator.IntegerMath addition = new Calculator.IntegerMath() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };

        //        Calculator.IntegerMath addition = (a, b) -> a + b;
        Calculator.IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("40 + 2 = " +
                myApp.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " +
                myApp.operateBinary(20, 10, subtraction));
        //the shorter interface implementation here too
        System.out.println("Puzzle:  " + myApp.invoke(() -> "done"));

    }

}

class Calculator {

    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    interface First {
        void test();
    }

    interface Second<V> {
        V anotherTest();
    }

    void invoke(First f) {
        f.test();
    }

    <T> T invoke(Second<T> s) {
        return s.anotherTest();
    }
}






















