/*
 * Maria Mueller
 * Professor Max Lieblich
 * Math 126 G, Journal Assignment 1
 * October 10, 2016
 * purpose: to not turn into a foul calculus monkey
 * class defines the Vector objects, which have x, y, and z components
 * and a magnitude
 * Client can call Vector methods to calculate dot product, cross product,
 * and projections between two Vector objects
 * Client can also create Vector objects from user input constructors
 */
import java.util.Scanner;
public class Vector
{
    private double x, y, z;
    private double mag; //magnitude
    
    /*CONSTRUCTORS*/
    public Vector() {
        this(0, 0, 0);
    }
    
    /*create Vector with given components <x,y,z>*/
    public Vector(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
        double x2 = Math.pow(this.getX(), 2);
        double y2 = Math.pow(this.getY(), 2);
        double z2 = Math.pow(this.getZ(), 2);
        this.mag = truncate(Math.sqrt((x2 + y2 + z2)));
    }
    
    /*create a Vector ab, where a and b are Point objects*/
    public Vector(Point a, Point b) {
        this(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }
    
    /*
     * not exactly a constructor, but it returns a new Vector object
     * from user input of two points
     */
    public static Vector fromUserInputTwoPoints() {
        print("Create point A(x, y, z)\n");
        Point p1 = Point.fromUserInput();
        print("Create point B(x, y, z)\n");
        Point p2 = Point.fromUserInput();
        return new Vector(p1, p2);
    }
    
    /*
     * another quazi-constructor, returns new Vector object
     * from user input of <x,y,z> componenets
     */
    public static Vector fromUserInputComponents() {
        Scanner r = new Scanner(System.in);
        print("x: "); double x = r.nextDouble();
        print("y: "); double y = r.nextDouble();
        print("z: "); double z = r.nextDouble();
        return new Vector(x, y, z);
    }
    
    /*basic accessor methods*/
    public double getX() {return this.x; }
    public double getY() {return this.y; }
    public double getZ() {return this.z; }
    public double getMag() {return this.mag; }
    
    /*returns new vector that is multiplied by the double given as a parameter*/
    public Vector multByScalar(double scalar) {
        return new Vector(scalar * this.getX(), scalar * this.getY(), scalar * this.getZ());
    }
        
    /*
     * returns a new Vector with the components of a vector with 
     * magnitude 1, in the same direction as the given vector
     */
    public Vector getUnitVector() {
        double mag = this.getMag();
        double newX = truncate(this.getX() / mag);
        double newY = truncate(this.getY() / mag);
        double newZ = truncate(this.getZ() / mag);
        return new Vector(newX, newY, newZ);
    }
    
    /*METHODS FOR TWO VECTOR OBJECTS*/
    /*returns dot product of two given vectors*/
    public double getDotProd(Vector other) {
        double xs = this.getX() * other.getX();
        double ys = this.getY() * other.getY();
        double zs = this.getZ() * other.getZ();
        return xs + ys + zs;
    }
    
    /*returns angle in between two vectors*/
    public double getAngleBetween(Vector other) {
        double dotProd = this.getDotProd(other);
        double answerInRadians = Math.acos(dotProd / (this.getMag() * other.getMag()));
        double answer = Math.toDegrees(answerInRadians);
        return truncate(answer);
    }
    
    /*
     * returns cross product of the two given vectors as a new Vector object
     * magnitude of the cross product can be found by calling the .getMag() 
     * method on the returned Vector object
     */
    public Vector getCrossProd(Vector other) {
        double c1, c2, c3;
        c1 = this.getY() * other.getZ() - this.getZ() * other.getY();
        c2 = this.getZ() * other.getX() - this.getX() * other.getZ();
        c3 = this.getX() * other.getY() - this.getY() * other.getX();
        return new Vector(c1, c2, c3);
    }
    
    /*
     * returns the scalar projection of "this" vector on the parameter vector "other"
     */
    public double getScalarProj(Vector other) {
        double dotProd = this.getDotProd(other);
        return truncate(dotProd / this.getMag());
    }
    
    /*
     * returns a Vector object for the vector projection of "this" vector on "other" Vector
     */
    public Vector getVectorProj(Vector other) {
        double numerator = this.getDotProd(other);
        double denom = Math.pow(this.getMag(), 2);
        return truncate(this.multByScalar(numerator / denom));
    }
    
    /*PRINTING METHODS*/
    /*default toString method listing vector components*/
    public String toString() {
        return "<" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ">";
    }
    
    /*
     * more detailed method with components, magnitude and unit vector components
     * about a single vector
     */
    public String fullString() {
        String str = this.toString();
        str += "\nMagnitude: " + this.getMag();
        str += "\nUnit Vector: " + this.getUnitVector().toString();
        return str;
    }
    
    /*full information with all possible calculations of two vectors*/
    public String fullString(Vector other) {
        String str = "A " + this.formatFull() + "B " + other.formatFull();
        str += "\nDot Product: " + this.getDotProd(other);
        str += "\nAngle Between: " + this.getAngleBetween(other);
        Vector crossProd = this.getCrossProd(other);
        str += "\nCross Product: \n\t" + crossProd.toString();
        str += "\n\tMagnitude: " + crossProd.getMag();
        str += "\n\tUnit Vector: " + crossProd.getUnitVector().toString();
        Vector AonB = this.getVectorProj(other); 
        double scalaB = this.getScalarProj(other);
        Vector BonA = other.getVectorProj(this);
        double scalbA = other.getScalarProj(this);
        str += "\nProjections - A onto B \n\t" + AonB.toString() + "\n\tScalar: " + scalaB; 
        str += "\nProjections - B onto A \n\t" + BonA.toString() + "\n\tScalar: " + scalbA;
        return str;
    }
    
    /*HELPER METHODS*/
    /*this method exists because it is faster than typing "System.out.print("*/
    private static void print(String x) {
        System.out.print(x);
    }
    /*truncates/rounds double to two decimal points*/
    private static double truncate(double d) {
        d = d * 100;
        d = (int) Math.round(d);
        d = (double) d / 100;
        return d;
    }
    /*truncates all values in vector, to make code cleaner when creating new vectors*/
    private static Vector truncate(Vector v) {
        return new Vector(truncate(v.getX()), truncate(v.getY()), truncate(v.getZ()));
    }
    /*returns string with vector components, magnitude, and unit vector components*/
    private String formatFull() {
        String str = "Vector: " + this.toString();
        str += "\n\tMagnitude: " + this.getMag();
        str += "\n\tUnit Vector: " + this.getUnitVector().toString() + "\n";
        return str;
    }
}
 