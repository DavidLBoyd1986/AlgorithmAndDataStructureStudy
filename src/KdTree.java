import java.util.ArrayList;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.BST.Node;

public class KdTree {

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean axis;   // true = x-axis ; false = y-axis
        
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            }
            if (!(this instanceof Node)) {
                return false;
            }
            Node other = (Node) that;
            if (this.p == other.p) {
                return true;
            }
            return false;
        }
     }

    private TreeSet<Node> kdTree;

    // construct an empty set of points
    public KdTree() {
        kdTree = new TreeSet<Node>();
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return kdTree.isEmpty();
    }
    
    // number of points in the set 
    public int size() {
        return kdTree.size();
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.p = p;
        if (kdTree.isEmpty()) {
            kdTree.add(node);
        }
        int level = 0;
        Node iterNode = kdTree.first();
        // Turn this into a private recursive function if this has issues
        while (iterNode != null) {
            if (iterNode.p == p) {
                return;
            }
            if (iterNode.axis == true) { // Even means it's a X-Axis check
                if (p.x() < iterNode.p.x()) {
                    if (iterNode.lb == null) {
                        iterNode.lb = node;
                        return;
                    } else {
                        iterNode = iterNode.lb;
                        level++;
                    }
                } else {
                    if (iterNode.rt == null) {
                        iterNode.rt = node;
                        return;
                    } else {
                        iterNode = iterNode.rt;
                        level++;
                    }
                }
            } else { // Must be odd, Odd means it's a Y-Axis check
                if (p.y() < iterNode.p.y()) {
                    if (iterNode.lb == null) {
                        iterNode.lb = node;
                        return;
                    } else {
                        iterNode = iterNode.lb;
                    }
                } else {
                    if (iterNode.rt == null) {
                        iterNode.rt = node;
                        return;
                    } else {
                        iterNode = iterNode.rt;
                    }
                }
            }
        }
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return get(p) != null;;
    }
    
    
    public Point2D get(Point2D p) {
        return get(kdTree.first(), p);
    }
    
    private Point2D get(Node x, Point2D p) {
        if (p == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        // Add if to check x or y depending on orientation
        int cmp = p.compareTo(x.p);
        if      (cmp < 0) return get(x.lb, p);
        else if (cmp > 0) return get(x.rt, p);
        else              return x.p;
    }
    
    // draw all points to standard draw 
    public void draw() {
        for (Node node : kdTree) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            node.p.draw();
            if (node.axis == true) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius();
                StdDraw.line(node.p.x(), node.p.y(), 0, 0);
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius();
                StdDraw.line(0, 0, 0, 0);
            }
        }
    }
    
    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> pointList = new ArrayList<Point2D>();
        for (Point2D p : kdTree) {
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
        Point2D closest = kdTree.first();
        for (Point2D iterPoint : kdTree) {
            if (iterPoint.distanceTo(p) > closest.distanceTo(p)) {
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
