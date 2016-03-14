package com.company;

import java.io.*;
import java.sql.SQLException;


public class Examples {

    public void oldMultipleExc() {
        try {
            // access the database and write to a file
            couldThrowAnException();
        } catch (SQLException e) {
            handleErrorCase(e);
        } catch (IOException e) {
            handleErrorCase(e);
        }
    }

    private void couldThrowAnException() throws IOException, SQLException {
        throw new IOException("Could throw An Exception");
    }

    private void handleErrorCase(Exception e) {
        System.out. println("Handle error case "+e.getMessage());
    }

    public void newMultipleExc() {
        try {
            // access the database and write to a file
            couldThrowAnException();
        } catch (SQLException | IOException e) { //  OR IOException|SQLException e
            //} catch(FileNotFoundException | IOException e ){
            handleErrorCase(e);
        }
    }

    public void oldRethrow() throws SQLException, IOException {
        try {
            couldThrowAnException();
        } catch (SQLException | IOException e) { //if sqlException has been removed, wont compile
            log(e);
            //e=new SQLException();
            throw e;
        }
    }

    public static void log(Exception e) {
        System.out.println("******LOG: " + e.getMessage());
    }

    public void newRethrow() throws SQLException, IOException// illetve, java 6 esetében a fordításhoz :Exception
    {
        try {
            couldThrowAnException();
        } catch (Exception e)//if sqlException has been removed, nothing collapsed at all,+not all exception subclasses catched
        {
            // e=new SQLException();
            log(e);
            throw e; // note: won't compile in Java 6
        }
    }

    public void oldSimpleRethrow() throws SQLException, IOException {
        try {
            couldThrowAnException();
        } catch (SQLException e) {
            log(e);
            e = new SQLException("Új exc");
            throw e;
        } catch (IOException e) {
            log(e);
            e = new IOException("Új exc");
            throw e;
        }
    }

    public void tryWITHOUTResources() throws IOException {
        Reader reader = null;
        try {
            readFromFile();
        } catch (IOException e) {
            log(e);
            throw e;
        } finally {                //HelperClass.close(reader);
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // ignore exceptions on closing file
                }
            }
        }
    }

    private void readFromFile() throws IOException {
        throw new IOException("Read from file");
    }

    public void tryWITHResources() throws IOException {
        File file = new File("C:/rampupTest.txt");
        try
                (Reader reader = new BufferedReader(new FileReader(file))) { // note the new syntax
            readFromFile();
        } catch (IOException e) {
            log(e);
            throw e;
        }
    }

    public void tryWITHMultipleResources() throws IOException {
        try (MyResource mr = MyResource.createResource(); // first resource
             MyResource.MyThingy mt = mr.createThingy()) { // second resource
            // do stuff
        }
    }

    private static class MyResource implements AutoCloseable {

        @Override
        public void close() throws IOException {
            throw new IOException("Closing my resource.");
        }

        public static MyResource createResource() {
            return new MyResource();
        }

        public MyThingy createThingy() {
            return new MyThingy();
        }

        public class MyThingy implements AutoCloseable {
            @Override
            public void close() throws IOException {

            }
        }
    }


    public void doSuppression() {

        try (MyResource mr = new MyResource()) {

            throw new Exception("Try");
            //mivel exc dobodott, itt zarni akarom "mr"-t +az az exc amit itt dobtam=2db exc

        } catch (Exception e) {

            System.err.println(e.getMessage());
            for (Throwable t : e.getSuppressed()) {

                System.err.println("suppressed:" + t);

            }

        }
    }

    public void doBADSuppression() {
        try (Bad b1 = new Bad("1"); Bad b2 = new Bad("2")) {
            // do stuff
        } catch (Exception e) {
            System.err.println(e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.err.println("suppressed:" + t);
            }
        }
    }

    private class Bad implements AutoCloseable {
        String name;

        Bad(String n) {
            name = n;
        }

        @Override
        public void close() throws IOException {
            throw new IOException("Closing - " + name);
        }
    }



    public void puzzle(){
            try {
                System.out.println("Hello world");
                System.exit(0);
            } finally {
                System.out.println("Goodbye world");
            }
        }

    }
