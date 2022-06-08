import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;



public class BruteCollinearPoints {

    // This would have to be a resizing array
    resizingSegmentArray segmentsResizingArray;
    LineSegment[] segments;
    int segmentArraySize;

    // Finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument 'points' is null");
        }
        // This might need to be a resizing array
        segmentsResizingArray = new resizingSegmentArray();
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
        
        //Loop four points in nested loops to compare every implementation
        for (int pointOne = 0; pointOne < points.length; pointOne++) {
            for (int pointTwo = pointOne+1; pointTwo < points.length; pointTwo++) {
                for (int pointThree = pointOne+2; pointThree < points.length; pointThree++) {
                    for (int pointFour = pointOne+3; pointFour < points.length; pointFour++) {
                        //troubleshooting step
                        System.out.println("Outerloop: " + pointOne);
                        System.out.println(copyPoints[pointOne].slopeTo(copyPoints[pointTwo]));
                        System.out.println(copyPoints[pointOne].slopeTo(copyPoints[pointThree]));
                        System.out.println(copyPoints[pointOne].slopeTo(copyPoints[pointFour]));
                        System.out.println("----------------");
                            
                        // If the slope of all four points are equal it's a line
                        if (copyPoints[pointOne].slopeTo(copyPoints[pointTwo]) == copyPoints[pointOne].slopeTo(copyPoints[pointThree]) &&
                                copyPoints[pointOne].slopeTo(copyPoints[pointThree]) == copyPoints[pointOne].slopeTo(copyPoints[pointFour])) {
                            segmentsResizingArray.add(new LineSegment(copyPoints[pointOne], copyPoints[pointFour]));
                            segmentArraySize++;
                            }
                        }
                    }
                }
            }
    
        segments = new LineSegment[segmentsResizingArray.size()];
        for (int i = 0; i < numberOfSegments(); i++) {
            segments[i] = segmentsResizingArray.remove();
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
    
    
    // resizing segment array
    private class resizingSegmentArray{
        
        private LineSegment[] array;
        private int size;
        
        
        resizingSegmentArray() {
            array = new LineSegment[1];
            size = 0;
        }
        
        
        private void add(LineSegment segment) {
            array[size++] = segment;
            if (size == array.length) {
                resize(array.length*2);
            }
        }
        
        
        private LineSegment remove() {
            LineSegment segment = array[--size];
            if (size <= array.length/4) {
                resize(array.length/2);
            }
            return segment;
        }
        
        
        private void resize(int newSize) {
            LineSegment[] newArray = new LineSegment[newSize];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        
        private int size() {
            return size;
        }
        
        
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
