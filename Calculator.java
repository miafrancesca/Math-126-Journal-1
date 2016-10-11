/*
 * Maria Mueller
 * Professor Max Lieblich
 * Math 126 G, Journal Assignment 1
 * October 10, 2016
 * purpose: to not turn into a foul calculus monkey
 * main method of calculator can be run to prompt the user into supplying information
 * for calculations! 
 * CAUSES ERRORS IF STRINGS ARE INPUTTED, ONLY ACCEPTS DOUBLES
 */
import java.util.Scanner;
public class Calculator{
    public static void main (String [] args) {
        Scanner r = new Scanner(System.in);
        /*menu of options for user, stored as string to avoid redundancy later*/
        String menu = "1. Vector from 2 points \n2. Single vector \n3. Two vectors\nSelect an option (999 to quit): ";
        print("Welcome to calculator\n" + menu); 
        double input = r.nextDouble();
        /*creates loop for user to perform as many calculations as they would like, until they enter sentinel 999*/
        while (input != 999) {
            /*asks for clarification if user inputs anything other than sentinel 999 or the three menus options*/
            while (input != 1 && input != 2 && input != 3 && input != 999) {
                print("Undecipherable, try again: ");
                input = r.nextDouble();
            }
            if (input == 999) {break; }
            /*three options from the menu*/
            if (input == 1) {
                print("\n");
                Vector v = Vector.fromUserInputTwoPoints();
                print("\nVector AB\n" + v.fullString());
            }
            else if (input == 2) {
                Vector vector = Vector.fromUserInputComponents();
                print("\n" + vector.fullString());
            }
            else if (input == 3) {
                print("\nCreate vector A <x, y, z>\n");
                Vector vA = Vector.fromUserInputComponents();
                print("\nCreate vector B<x, y, z>\n");
                Vector vB = Vector.fromUserInputComponents();
                print("\n" + vA.fullString(vB));
            }
            print("\n\nNew calculation\n" + menu); 
            input = r.nextDouble();
        }
        print("\nprogram terminated");
    }

    /*because writing System.out.print( over and over again is a pain*/
    private static void print(String x) {
        System.out.print(x);
    }

}