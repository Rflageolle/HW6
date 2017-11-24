/*
 * Ryan Flageolle
 * Computer Science
 * November 21, 2017
 * HW6
 */

/** The HW6 class is where the user interface code is defined, where the random
    generators are set up, There are methods for the user to enter options to create
    random arrays as well as a set of tests to print to a file **/

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class HW6 {

    static Scanner in = new Scanner(System.in);
    long javaStartTime, javaStopTime, mergeStartTime, mergeStopTime;
    BufferedWriter out;
    String outStr = "";

    public static void main(String[] args){
        HW6 current = new HW6();
    }

    HW6(){

        integers();
        doubles();
        strings();
        setBufferedReader();
    }

    /** Method to create a two demensional Integer array to hold two copies of the
        same randomly generated Array of Integers **/
    public Integer[][] random(int size, int big) {
  	     Integer[] array = new Integer[size];
         Integer[] copy = new Integer[size];
         Integer[][] rtn = new Integer[2][];

  		   for(int i = 0; i < size; i++){
  		       array[i] = (int)(Math.random() * big + 1);
             copy[i] = array[i];
         }

         rtn[0] = array;
         rtn[1] = copy;

  		   return rtn;
  	}
    /** Method to create a two demensional Double array to hold two copies of the
        same randomly generated Array of Doubles **/
  	public Double[][] random(double size, double big) {
  		  Double[] array = new Double[(int)size];
        Double[] copy = new Double[(int)size];
        Double[][] rtn = new Double[2][];

  		  for(int i = 0; i < size; i++) {
  		      array[i] = (Math.random() * big + 1);
            copy[i] = array[i];
        }

        rtn[0] = array;
        rtn[1] = copy;

  		  return rtn;
  	}

    /** Method to create a two demensional String array to hold two copies of the
        same randomly generated Array of Strings **/
    public String[][] random(int size) {
        String letters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String[] array = new String[size];
        String[] copy = new String[size];
        String[][] rtn = new String[2][];

        for (int i = 0; i < size; i++) {
            String str = "";
            for (int j = 0; j < 6; j++){
                int letter = (int)(Math.random() * 36);
                if (letter < 11){
                    str += letters.charAt(letter);
                }
                else{
                  if ((int)(Math.random() * 100) % 2 == 0){
                      str += letters.charAt(letter += 26);
                  }
                  else {
                      str += letters.charAt(letter);
                  }
                }
            }
            array[i] = str;
            copy[i] = array[i];
        }
        rtn[0] = array;
        rtn[1] = copy;
        return rtn;
    }

    /** Method to allow user to create random Array of Integers **/
    public void intTest(){
        do {
            System.out.println("What size should the Integers be?");
        } while (in.hasNextInt());
        int size = in.nextInt();

        do {
            System.out.println("How many Integers?");
        } while (in.hasNextInt());
        int big = in.nextInt();

        Integer[][] test = random(size, big);

        System.out.println("\nBefore the Sort: ");
        for (Integer i : test[1]){
            System.out.println(i);
        }

        mergeStartTime = System.nanoTime();
        Mergesort.sort(test[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(test[1]);
        javaStopTime = System.nanoTime();

        System.out.println("\nAfter the Sort: ");
        for (Integer i : test[0]){
            System.out.println(i);
        }

    }

    /** Method to allow user to create random Array of Doubles **/
    public void doubTest(){
        do {
            in.next();
            System.out.println("How many Integers?");
        } while (in.hasNextDouble());
        Double size = in.nextDouble();

        do {
            in.next();
            System.out.println("What size should the Integers be?");
        } while (in.hasNextDouble());
        Double big = in.nextDouble();

        Double[][] test = random(size, big);

        System.out.println("\nBefore the Sort: ");
        for (Double d : test[0]){
            System.out.println(d);
        }

        mergeStartTime = System.nanoTime();
        Mergesort.sort(test[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(test[1]);
        javaStopTime = System.nanoTime();

        System.out.println("\nAfter the Sort: ");
        for (double d : test[0]){
            System.out.println(d);
        }

    }

    /** Method to allow user to create random Array of Strings **/
    public void stringTest(){
        do {
            in.next();
            System.out.println("How many Words?");
        } while (in.hasNextInt());
        int size = in.nextInt();

        String[][] test = random(size);

        System.out.println("\nBefore the Sort: ");
        for (String str : test[0]){
            System.out.println(str);
        }

        mergeStartTime = System.nanoTime();
        Mergesort.sort(test[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(test[1]);
        javaStopTime = System.nanoTime();

        System.out.println("\nAfter the Sort: ");
        for (String str : test[0]){
            System.out.println(str);
        }
    }

    /** Method to write the times collected during the 9 tests to the file
        HW6output.txt **/
    public void setBufferedReader(){
        try{
            out = new BufferedWriter(new FileWriter("HW6output.txt"));
            out.write(outStr);
            out.close();
        } catch (IOException i) {
            System.out.println("Something terrible is afoot");
        }
    }

    /** prints to file the duration of both Mergesort and Array.sort when applied
        to three different randomly generated Arrays of integers **/
    public void integers(){

        outStr += "INTEGER TEST";
        outStr += "\n\n  /** TEST ONE: SIZE = 10, BIG = 100 **/\n";

        Integer[][] randomOne = random(10, 100);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomOne[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomOne[1]);
        javaStopTime = System.nanoTime();

        outStr += "\n    Test One: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                    + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

        outStr += "\n\n  /** TEST TWO: SIZE = 20, BIG = 2500 **/\n";

        Integer[][] randomTwo = random(20, 2500);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomTwo[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomTwo[1]);
        javaStopTime = System.nanoTime();


        outStr += "\n    Test Two: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

        outStr += "\n\n  /** TEST THREE: SIZE = 150, BIG = 5000 **/\n";

        Integer[][] randomThree = random(150, 5000);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomThree[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomThree[1]);
        javaStopTime = System.nanoTime();

        outStr += "\n    Test Three: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

    }

    /** prints to file the duration of both Mergesort and Array.sort when applied
        to three different randomly generated Arrays of doubles **/
    public void doubles(){

        outStr += "\n\nDOUBLE TEST";
        outStr += "\n\n  /** TEST ONE: SIZE = 10.0, BIG = 100.0 **/\n";

        Double[][] randomOne = random(10.0, 100.0);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomOne[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomOne[1]);
        javaStopTime = System.nanoTime();

        outStr += "\n    Test One: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

        outStr += "\n\n  /** TEST TWO: SIZE = 20.0, BIG = 2500.0 **/\n";

        Double[][] randomTwo = random(20.0, 2500.0);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomTwo[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomTwo[1]);
        javaStopTime = System.nanoTime();


        outStr += "\n    Test Two: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

        outStr += "\n\n  /** TEST THREE: SIZE = 150.0, BIG = 5000.0 **/\n";

        Double[][] randomThree = random(150.0, 5000.0);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomThree[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomThree[1]);
        javaStopTime = System.nanoTime();

        outStr += "\n    Test Three: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";
    }

    /** prints to file the duration of both Mergesort and Array.sort when applied
        to three different randomly generated Arrays of strings **/
    public void strings(){
        outStr += "\n\nSTRING TEST";
        outStr += "\n\n  /** TEST ONE: SIZE = 10 **/\n";

        String[][] random = random(10);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(random[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(random[1]);
        javaStopTime = System.nanoTime();

        outStr += "\n    Test One: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

        outStr += "\n\n  /** TEST TWO: SIZE = 20 **/\n";

        String[][] randomTwo = random(20);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomTwo[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomTwo[1]);
        javaStopTime = System.nanoTime();


        outStr += "\n    Test Two: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";

        outStr += "\n\n  /** TEST THREE: SIZE = 150 **/\n";

        String[][] randomThree = random(150);

        mergeStartTime = System.nanoTime();
        Mergesort.sort(randomThree[0]);
        mergeStopTime = System.nanoTime();

        javaStartTime = System.nanoTime();
        Arrays.sort(randomThree[1]);
        javaStopTime = System.nanoTime();

        outStr += "\n    Test Three: " + "\n     - Merge Time: " + (mergeStopTime - mergeStartTime)
                  + " nanoseconds\n     - Java Time: " + (javaStopTime - javaStartTime) + " nanoseconds";
    }
}
