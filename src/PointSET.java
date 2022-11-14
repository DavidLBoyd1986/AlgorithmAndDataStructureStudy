import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    
    private TreeSet<Point2D> bst;

    // construct an empty set of points
    public PointSET() {
        bst = new TreeSet<Point2D>();
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return bst.isEmpty();
    }
    
    // number of points in the set 
    public int size() {
        return bst.size();
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (!bst.contains(p)) {
            bst.add(p);
        }
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return bst.contains(p);
    }
    
    // draw all points to standard draw 
    public void draw() {
        for (Point2D p : bst) {
            p.draw();
        }
    }
    
    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> pointList = new ArrayList<Point2D>();
        for (Point2D p : bst) {
            // All the 'ifs' below can be condensed to rect.contains(p)
            if (p.x() < rect.xmin()) {
                continue;
            } else if (p.y() < rect.ymin()) {
                continue;
            } else if (p.x() > rect.xmax()) {
                continue;
            } else if (p.y() > rect.ymax()) {
                continue;
            } else {
                pointList.add(p);
            }
        }
        return pointList;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (bst.isEmpty()) {
            return null;
        }
        Point2D closest = bst.first();
        for (Point2D iterPoint : bst) {
            if (iterPoint.distanceSquaredTo(p) > closest.distanceSquaredTo(p)) {
                continue;
            } else {
                closest = iterPoint;
            }
        }
        return closest;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
