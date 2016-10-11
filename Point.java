/*
 * Maria Mueller
 * Professor Max Lieblich
 * Math 126 G, Journal Assignment 1
 * October 10, 2016
 * purpose: to not turn into a foul calculus monkey
 * class defines the Point objects, which have x, y, and z coordinates
 * Client can call accessor methods, create a Vector from two points,
 * or create new points from user input
 */

import java.util.Scanner;
public class Point {
    private double x, y, z;
    public Point(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }
    
    public Point() {
        this(0,0,0);
    }
    
    public double getX() {return this.x; }
    public double getY() {return this.y; }
    public double getZ() {return this.z; }
    
    public double distance(Point other) {
        double dX = this.getX() - other.getX();
        double dY = this.getY() - other.getY();
        double dZ = this.getZ() - other.getZ();
        double sum = Math.pow(dX, 2) + Math.pow(dY, 2) + Math.pow(dZ, 2);
        return Math.sqrt(sum);
    }
    
    public static Point fromUserInput() {
        Scanner r = new Scanner(System.in);
        print("x: "); double x = r.nextDouble();
        print("y: "); double y = r.nextDouble();
        print("z: "); double z = r.nextDouble();
        return new Point(x, y, z);
    }
    
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
    }

    private static void print(String s) {
        System.out.print(s);
    }
}