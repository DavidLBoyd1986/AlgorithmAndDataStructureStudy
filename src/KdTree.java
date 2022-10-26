import java.util.ArrayList;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree {

    private Node root;

    private static class Node implements Comparable{
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node parent;    // the parent Node of this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean axis;   // true = vertical ; false = horizontal
        
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
        
        public int compareTo(Object that) {
            Node other = (Node) that;
            return this.p.compareTo(other.p);
        }
        
        public Node(Point2D initP, boolean initAxis) {
            p = initP;
            axis = initAxis;
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
        
        root = insert(root, null, p, true);
    }

    private Node insert(Node x, Node parent, Point2D p, boolean currentAxis) {
        if (x == null) {
            Node newNode = new Node(p, currentAxis);
            newNode.parent = parent;
            createLine(newNode);
            kdTree.add(newNode); // Have to add Nodes to tree, even though the nodes link to themselves
            return newNode;
        }
        boolean nextAxis = !(currentAxis);
        int cmp = p.compareTo(x.p);
        if (cmp < 0) {
            x.lb  = insert(x.lb, x, p, nextAxis);
        } else if (cmp > 0) {
            x.rt = insert(x.rt, x, p, nextAxis);
        } else {
            x.p = p;
        }
        return x;
    }
    
    private void createLine(Node x) {
        // Determine two end points based on axis and parent nodes
        if (x.axis == true) {
            
        }
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return get(p) != null;
    }
    
    
    public Point2D get(Point2D p) {
        return get(root, p);
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
        root = range(rect, root, pointList);
        return pointList;
    }
        
    private Node range(RectHV rectangle, Node node, ArrayList<Point2D> pList) {
        if (node == null) {
            return null;
        }
        if (rectangle.contains(node.p)) {
            pList.add(node.p);
        }
        // Determine how to know which way to go.
        // ADD - If line intersects rectangle, check both branches
        // TODO - above
        // If left branch is closer to rectangle go that way
        if (rectangle.distanceTo(node.lb.p) < rectangle.distanceTo(node.rt.p)) {
            node = range(rectangle, node.lb, pList);
            return node;
        } else { // rectangle is down right branch, might add a check to be sure
            node = range(rectangle, node.rt, pList);
            return node;
        }
    }
            
    // a nearest neighbor in the set to point p; null if the set is empty 
//    public Point2D nearest(Point2D p) {
//        if (p == null) {
//            throw new IllegalArgumentException();
//        }
//        Point2D closest = kdTree.first();
//        for (Point2D iterPoint : kdTree) {
//            if (iterPoint.distanceTo(p) > closest.distanceTo(p)) {
//                continue;
//            } else {
//                closest = iterPoint;
//            }
//        }
//        return closest;
//    }
    
    public static void main(String[] args) {
        KdTree testTree = new KdTree();
        Double x = 0.372;
        Double y = 0.497;
        Point2D p = new Point2D(x, y);
        System.out.println(testTree.isEmpty());
        testTree.insert(p);
        x = 0.564;
        y = 0.413;
        p = new Point2D(x, y);
        testTree.insert(p);
        System.out.println(testTree.contains(p));
        System.out.println(testTree.size());
        System.out.println(testTree.get(p));
        System.out.println(testTree.isEmpty());
        System.out.println(testTree.size());
        x = 0.226;
        y = 0.577;
        p = new Point2D(x, y);
        System.out.println(testTree.contains(p));
        testTree.insert(p);
        System.out.println(testTree.contains(p));
        System.out.println(testTree.size());
        System.out.println(testTree.get(p));

    }

}
