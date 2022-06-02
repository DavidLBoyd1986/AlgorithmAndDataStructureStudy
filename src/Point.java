import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
   
   private final int x; // x-coordinate of this point
   private final int y; // y-coordinate of this point
  
    
   /**
    * Initializes a new point.
    *
    * @param  x the <em>x</em>-coordinate of the point
    * @param  y the <em>y</em>-coordinate of the point
    */ 
   public Point(int x, int y) {
       this.x = x;
       this.y = y;
       
   }
   
   
   /**
    * Draws this point to standard draw.
    */
   public void draw() {
       StdDraw.point(x, y);
   }
   
   
   /**
    * Draws the line segment between this point and the specified point
    * to standard draw.
    *
    * @param that the other point
    */
   public void drawTo(Point that) {
       StdDraw.line(this.x, this.y, that.x, that.y);
   }
   
   

   /**
    * Returns a string representation of this point.
    * This method is provide for debugging;
    * your program should not rely on the format of the string representation.
    *
    * @return a string representation of this point
    */
   public String toString() {
       return "(" + x + "," + y + ")";
   }

   
   /**
    * Compares two points by y-coordinate, breaking ties by x-coordinate.
    * Formally, the invoking point (x0, y0) is less than the argument point
    * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
    *
    * @param  that the other point
    * @return the value <tt>0</tt> if this point is equal to the argument
    *         point (x0 = x1 and y0 = y1);
    *         a negative integer if this point is less than the argument
    *         point; and a positive integer if this point is greater than the
    *         argument point
    */
   public int compareTo(Point that) {
       // Reference point to same object
       if (this == that) {
           return 0;
       } else if (this.y < that.y) {
           return -1;
       } else if (this.y > that.y) {
           return 1;
       } else if (this.y == that.y) {
           if (this.x < that.x) {
               return -1;
           } else if (this.x > that.x) {
               return 1;
           }
       }
       return 0;
   }
   
   
   /**
    * Returns the slope between this point and the specified point.
    * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
    * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
    * +0.0 if the line segment connecting the two points is horizontal;
    * Double.POSITIVE_INFINITY if the line segment is vertical;
    * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
    *
    * @param  that the other point
    * @return the slope between this point and the specified point
    */
   public double slopeTo(Point that) {
       // Corner cases of horizontal or vertical slopes, and if points in same spot.
       if (this.x == that.x) {
           return +0.0;
       } else if (this.y == that.y) {
           return Double.POSITIVE_INFINITY;
       } else if (this.compareTo(that) == 0) {
           return Double.NEGATIVE_INFINITY;
       }
       // Calculate slope and return it. Might need to change to use double in formula instead of int.
       double slope = (that.y - this.y) / (that.x - this.x);
       return slope;
   }
   
   

   /**
    * Compares two points by the slope they make with this point.
    * The slope is defined as in the slopeTo() method.
    *
    * @return the Comparator that defines this ordering on points
    */
   public Comparator<Point> slopeOrder() {
       return new SlopeOrder();
   }
   
   
   private class SlopeOrder implements Comparator<Point> {
       
       public int compare(Point a, Point b) {
           //The outer instance variable Point that calls this method is implied, so slopeTo() is in effect this.slopeTo()
           double aSlope = slopeTo(a);
           double bSlope = slopeTo(b);
           if (aSlope < bSlope) {
               return -1;
           } else if (aSlope > bSlope) {
               return 1;
           } else {
               return 0;
           }
       }
   }
   
   public static void main(String[] args) {
       //Test creation of Points
       Point test1 = new Point(0, 0);
       Point test2 = new Point(1, 5);
       Point test3 = new Point(2, 5);
       Point test4 = new Point(4, 2);
       Point test5 = new Point(2, 5);
       Point test8 = new Point(5, 5);
       System.out.println(test1);
       System.out.println(test2);
       System.out.println(test3);
       System.out.println(test4);
       System.out.println(test5);
       //Test Compare of points
       if (test1.compareTo(test4) < 0) {
           System.out.println("True");
       } else {
           System.out.println("False");
       }
       if (test3.compareTo(test4) > 0) {
           System.out.println("True");
       } else {
           System.out.println("False");
       }
       if (test2.compareTo(test3) < 0) {
           System.out.println("True");
       } else {
           System.out.println("False");
       }
       //Get slope of points from test1
       double slopeTest2 = test1.slopeTo(test2);
       double slopeTest3 = test1.slopeTo(test3);
       double slopeTest4 = test1.slopeTo(test4);
       double slopeTest8 = test1.slopeTo(test8);
       System.out.println("slopeTest2 = " + slopeTest2);
       System.out.println("slopeTest3 = " + slopeTest3);
       System.out.println("slopeTest4 = " + slopeTest4);
       System.out.println("slopeTest8 = " + slopeTest8);
       //Test slope comparator
       System.out.println(test1.slopeOrder().compare(test2, test3));
       System.out.println(test1.slopeOrder().compare(test2, test4));
       System.out.println(test1.slopeOrder().compare(test3, test4));
       System.out.println(test1.slopeOrder().compare(test4, test3));
       System.out.println(test1.slopeOrder().compare(test4, test2));
       System.out.println(test1.slopeOrder().compare(test3, test2));
       System.out.println(test1.slopeOrder().compare(test3, test5));

       //Need to research if this should be static class or not
       Comparator<Point> test1Comp = test1.slopeOrder();
       int test1TwoToThreeResult = test1Comp.compare(test2, test3);
       System.out.println("test1 Comparater: compare(test2, test3) result: " + test1TwoToThreeResult);
       
       //Testing StdDraw
       //Normal Scale is set to (0.0 : 1.0), so I adjusted it.
       StdDraw.setScale(0, 6);
       test1.draw();
       test2.draw();
       test3.draw();
       test4.draw();
       test1.drawTo(test2);
       test1.drawTo(test3);
       test1.drawTo(test4);
       Point test6 = new Point(1,1);
       Point test7 = new Point(2,1);
       test6.draw();
       test7.draw();
       test6.drawTo(test7);
   }


}