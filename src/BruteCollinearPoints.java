import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;



public class BruteCollinearPoints {

    // This would have to be a resizing array
    LineSegment[] segments;
    int segmentArraySize;

    // Finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument 'points' is null");
        }
        // This might need to be a resizing array
        segments = new LineSegment[points.length];
        segmentArraySize = 0;
        
        //Copy array to make a 2nd array that can be sorted. So, not sorting an array that is being iterated upon.
        Point[] copyPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point " + i + " in points array is null");
            }
            // Normally would check for repeated points here using hashing, but I don't think that's allowed
            // Hashing to check for repeated points????
            copyPoints[i] = points[i];
        }
        
        //To find a line compare slopes. For a point, if the slope to 3 other points are equal, that's a line
        for (int point = 0; point < points.length; point++) {
            if (points[point] == null) {
                throw new IllegalArgumentException("point " + point + " in points array is null");
            }
            //Sort points array by SlopeComparator to this point
            Comparator<Point> pointComparator = points[point].slopeOrder();
            Arrays.sort(copyPoints, pointComparator);
            //troubleshooting step
            System.out.println("Outerloop: " + point);
            for (int i = 1; i < copyPoints.length-3; i++) {
                System.out.println(copyPoints[0].slopeTo(copyPoints[i]));
//                System.out.println(copyPoints[0].slopeTo(copyPoints[i+2]));
//                System.out.println(copyPoints[0].slopeTo(copyPoints[i+3]));
//                System.out.println("----------------");
            }
            
            // Should only use point[0] as reference since that's what it was sorted by
            for (int pos = 0; pos < 4; pos++) {
                if (copyPoints[0].slopeTo(copyPoints[pos+1]) == copyPoints[0].slopeTo(copyPoints[pos+2]) &&
                        copyPoints[0].slopeTo(copyPoints[pos+2]) == copyPoints[0].slopeTo(copyPoints[pos+3])) {
                    segments[segmentArraySize] = new LineSegment(copyPoints[pos], copyPoints[pos+3]);
                    segmentArraySize++;
                    
                }
            }
            
        }
    }
    
    
    // Number of line segments
    public int numberOfSegments() {
        return segments.length;
    }
    
    
    // The line segments
    public LineSegment[] segments() {
        return segments;
    }
    
    
    public static void main(String[] args) {

        // read the n points from a file
        String testFile = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\collinear\\grid4x4.txt";
        In in = new In(testFile);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
