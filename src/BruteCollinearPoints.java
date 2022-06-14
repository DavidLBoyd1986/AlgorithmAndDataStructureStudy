import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;



public class BruteCollinearPoints {

    private Point[] copyPoints;
    private LinkedListStack<LineSegment> stack;
    private LineSegment[] segments;
    private LinkedListStack<TupleLineSegments> linesUsed;

    // Finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument 'points' is null");
        }
        // This might need to be a resizing array
        stack = new LinkedListStack<LineSegment>();
        linesUsed = new LinkedListStack<TupleLineSegments>();
        copyPoints = new Point[points.length];
        
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("points[" + i + "] is null, it must be a Point");
            }
            copyPoints[i] = points[i];
        }
        
        Arrays.sort(copyPoints);
        
        // Check for duplicate points before processing copyPoints
        Point previousPoint = copyPoints[0];
        for (int i = 1; i < copyPoints.length; i++) {
            if (copyPoints[i].compareTo(previousPoint) == 0) {
                throw new IllegalArgumentException("Point[] points argument contains duplicate points.");
            }
            previousPoint = copyPoints[i];
        }
        
        // Loop four points in nested loops to compare every implementation
        for (int pointOne = 0; pointOne < copyPoints.length; pointOne++) {           
            for (int pointTwo = pointOne+1; pointTwo < copyPoints.length; pointTwo++) {
                for (int pointThree = pointTwo+1; pointThree < copyPoints.length; pointThree++) {
                    for (int pointFour = pointThree+1; pointFour < copyPoints.length; pointFour++) {
                        boolean duplicate = false;
                        // If the slope of all four points are equal it's a line
                        if (copyPoints[pointOne].slopeTo(copyPoints[pointTwo]) == copyPoints[pointOne].slopeTo(copyPoints[pointThree]) &&
                                copyPoints[pointOne].slopeTo(copyPoints[pointThree]) == copyPoints[pointOne].slopeTo(copyPoints[pointFour])) {
                            // Go through saved list of segments to verify you don't add duplicates
                            for (TupleLineSegments tuple : linesUsed) {
                                if ((tuple.getStart() == copyPoints[pointOne] || tuple.getStart() == copyPoints[pointFour]) &&
                                    (tuple.getEnd() == copyPoints[pointFour] || tuple.getEnd() == copyPoints[pointOne])) {
                                    duplicate = true;
                                    }
                                }
                            if (!duplicate) {
                                stack.push(new LineSegment(copyPoints[pointOne], copyPoints[pointFour]));
                                linesUsed.push(new TupleLineSegments(copyPoints[pointOne], copyPoints[pointFour]));
                                }
                            }
                        }
                    }
                }
            }    
        segments = new LineSegment[stack.size()];
        for (int i = 0; i < numberOfSegments(); i++) {
            segments[i] = stack.pop();
        }          
    }
    
    
    // Number of line segments
    public int numberOfSegments() {
        return segments.length;
    }
    
    
    // The line segments
    public LineSegment[] segments() {
        LineSegment[] segmentsCopy = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++) {
            segmentsCopy[i] = segments[i];
        }
        return segmentsCopy;
    }
    
    
    private class LinkedListStack<Item> implements Iterable<Item> {
        
        private Node first;
        private int size;
        
        private class Node {
            private Item item;
            private Node next;
        }
        
        
        public boolean isEmpty() {
            return first == null;
        }
        
        
        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
        }
        
        
        public Item pop() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            Item returnItem = first.item;
            first = first.next;
            return returnItem;
        }
        
        
        public int size() {
            return size;
        }
        
        
        public Iterator<Item> iterator() {
            return new StackIterator();
        }
        
        private class StackIterator implements Iterator<Item> {
            
            private Node current = first;
            
            
            public boolean hasNext() {
                return current != null;
            }
            
            
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in list");
                }
                Item returnItem = current.item;
                current = current.next;
                return returnItem;
            }
        }
    }

    
    private class TupleLineSegments {
        private final Point start;
        private final Point end;

        
        private TupleLineSegments(Point s, Point e) {
            start = s;
            end = e;
        }
        
        
        public Point getStart() {
            return start;
        }
        
        
        public Point getEnd() {
            return end;
        }
    }


    public static void main(String[] args) {

        // read the n points from a file
        String testFile = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\collinear\\grid4x4-MyGrid.txt";
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
