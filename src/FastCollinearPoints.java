import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class FastCollinearPoints {

    private LinkedListStack<LineSegment> stack;
    private LineSegment[] segments;

    // Finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument 'points' is null");
        }
        stack = new LinkedListStack<LineSegment>();
                
        // Outer loop looks at every point in the Point[]     
        for (int point = 0; point < points.length; point++) {
            // This sort puts the array back in it's natural order after it was sorted by slope
            Arrays.sort(points);
            if (points[point] == null) {
                throw new IllegalArgumentException("point " + point + " in points array is null");
            }
            
            // Sort points array by SlopeComparator to this pivot point
            Point pivotPoint = points[point];
            Comparator<Point> pointComparator = pivotPoint.slopeOrder();
            Arrays.sort(points, pointComparator);
            
            // Set up base values for the inner loop to check for collinear points in the sorted array
            int collinearPoints = 0;
            int furthestCollinearPointPosition = 0;
            double previousSlopeValue = pivotPoint.slopeTo(points[0]);
            // Inner loop that tries to find four or more adjacent slope values that are equal
            for (int pos = 0; pos < points.length; pos++) {
                // If first value it's already listed as previousSlopeValue, make collinearPoints 1 and continue
                if (pos == 0) {
                    collinearPoints++;
                    continue;
                }
                // Always skip the pivotPoint to prevent lines with the same point on each end
                if (points[pos] == pivotPoint) {
                    continue;
                }
                // Calculate slopeValue
                double slopeValue = pivotPoint.slopeTo(points[pos]);
                // If this slopeValue equals previous, save the Point position, and continue
                if (slopeValue == previousSlopeValue) {
                    furthestCollinearPointPosition = pos;
                    previousSlopeValue = slopeValue;
                    collinearPoints++;
                    // If last element, then see if there are 4 or more collinear points to add before exiting loop
                    if (pos == points.length - 1 && collinearPoints >= 3) {
                        stack.push(new LineSegment(pivotPoint, points[furthestCollinearPointPosition]));
                    }
                    continue;
                }
                // If it doesn't equal, and a line can be made, save the line, and then continue
                if (previousSlopeValue != slopeValue && collinearPoints >= 3) {
                        stack.push(new LineSegment(pivotPoint, points[furthestCollinearPointPosition]));
                        collinearPoints = 1;
                        previousSlopeValue = slopeValue;
                        continue;
                // The slope values don't match, but a line can't be made, continue
                } else if (previousSlopeValue != slopeValue) {
                    collinearPoints = 1;
                    previousSlopeValue = slopeValue;
                }
            }
        }
        // Outer loop over, add all the LineSegments to segments array
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
        return segments;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
