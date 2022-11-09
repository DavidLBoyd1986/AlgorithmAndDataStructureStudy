import java.util.ArrayList;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree {

    private Node root;
    private int size;

    private static class Node{
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
        
        public Node(Point2D initP, boolean initAxis) {
            p = initP;
            axis = initAxis;
        }
     }

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }
    
    // number of points in the set 
    public int size() {
        return size;
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        
        root = insert(root, null, p, true);
    }

    // Have to carry the parent's min and max for point's axis down
    private Node insert(Node x, Node parent, Point2D p, boolean currentAxis) {
        if (x == null) {
            Node newNode = new Node(p, currentAxis);
            newNode.parent = parent;
            createRect(newNode);
            draw();
            size++;
            return newNode;
        }
        boolean nextAxis = !(currentAxis);
        // Compare based on axis
        int cmp = pointCompare(x, p, currentAxis);
        
        if (cmp < 0) {
            x.lb  = insert(x.lb, x, p, nextAxis);
        } else {
            x.rt = insert(x.rt, x, p, nextAxis);
        }
        return x;
    }
    
    private int pointCompare(Node x, Point2D p, boolean axis) {
        int cmp = 0;
        if (axis == true) {
            if (p.x() < x.p.x()) {
                cmp = -1;
            } else if (p.x() > x.p.x()) {
                cmp = 1;
            } else {
                cmp = 0;
            }
        } else {
            if (p.y() < x.p.y()) {
                cmp = -1;
            } else if (p.y() > x.p.y()) {
                cmp = 1;
            } else {
                cmp = 0;
            }
        }
        return cmp;
    }

    private void createRect(Node x) {
        // Set rect to entire grid, then update edge points based on parent points
        x.rect = new RectHV(0, 0, 1, 1);
        Node iterNode = x; // gets set to parent at start of loop
        if (x.parent == null) { // if root return, its rect is entire grid
            return;
        }
        Double yMax = x.rect.ymax();
        Double yMin = x.rect.ymin();
        Double xMax = x.rect.xmax();
        Double xMin = x.rect.xmin();
        while (iterNode.parent != null) {
            iterNode = iterNode.parent;
            if (iterNode.axis) { // Vertical Orientation, update x-axis
                // checks if parent is on max or min side,
                // then checks if parent line is closer than current min or max
                if ((iterNode.p.x() > x.p.x()) &&
                    (xMax > iterNode.p.x())) {
                    xMax = iterNode.p.x();
                } else if ((iterNode.p.x() <= x.p.x()) &&
                        (xMin < iterNode.p.x())){
                    xMin = iterNode.p.x();
                }
            } else { // Horizontal Orientation, update y-axis
                // checks if parent is on max or min side,
                // then checks if parent line is closer than current min or max
                if ((iterNode.p.y() > x.p.y()) &&
                    (x.rect.ymax() > iterNode.p.y())) {
                    yMax = iterNode.p.y();
                } else if ((iterNode.p.y() <= x.p.y()) &&
                        (yMin < iterNode.p.y())){
                    yMin = iterNode.p.y();
                }
            }
        }
        // Create rect with its edges updated based on parents points.
        x.rect = new RectHV(xMin, yMin, xMax, yMax);
    }


    
    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return get(p) != null;
    }
    
    
    private Point2D get(Point2D p) {
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
        LinkedList<Node> nodes = getNodes();
        for (Node node : nodes) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            node.p.draw();
            // Don't need createLine, can just draw the axis based on orientation
            if (node.axis == true) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius();
                StdDraw.line(node.p.x(), node.rect.ymin(),
                             node.p.x(), node.rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius();
                StdDraw.line(node.rect.xmin(), node.p.y(),
                        node.rect.xmax(), node.p.y());
            }
        }
    }
    
    private LinkedList<Node> getNodes() {
        Node temp = root;
        LinkedList<Node> nodeList = new LinkedList<Node>();
        getNodes(temp, nodeList);
        return nodeList;
    }
    
    private void getNodes(Node node, LinkedList<Node> nodeList) {
        if (node == null) {
            return;
        }
        nodeList.add(node);
        getNodes(node.lb, nodeList);
        getNodes(node.rt, nodeList);
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
        if ((node.lb != null) && (node.lb.rect.intersects(rectangle))) {
            node = range(rectangle, node.lb, pList);
            return node;
        }
        if ((node.rt != null) && (node.rt.rect.intersects(rectangle))) {
            node = range(rectangle, node.rt, pList);
            return node;
        }
        return node;
    }
            
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node nearestNode = nearest(root, p, null);
        return nearestNode.p;
    }
    
    private Node nearest(Node node, Point2D p, Point2D nearestP) {
        if (node == null) {
            return null;
        }
        nearestP = node.p; // get node's point
        if (node.lb != null) {
            // if the current nearest point is closer than lb rectangle, prune lb
            if (nearestP.distanceTo(p) < node.lb.rect.distanceTo(p)) {
                return node;
            } else {
                node = nearest(node.lb, p, nearestP);
            }
        }
        if (node.rt != null) {
            // if the current nearest point is closer than rt rectangle, prune rt
            if (nearestP.distanceTo(p) < node.rt.rect.distanceTo(p)) {
                return node;
            } else {
                node = nearest(node.rt, p, nearestP);
            }
        }
        return node;
    }
    
    public static void main(String[] args) {
//        KdTree testTree = new KdTree();
//        Double xTest = 0.372;
//        Double yTest = 0.497;
//        Point2D pTest = new Point2D(xTest, yTest);
//        System.out.println(testTree.isEmpty());
//        testTree.insert(pTest);
//        xTest = 0.564;
//        yTest = 0.413;
//        pTest = new Point2D(xTest, yTest);
//        testTree.insert(pTest);
//        System.out.println(testTree.contains(pTest));
//        System.out.println(testTree.size());
//        System.out.println(testTree.get(pTest));
//        System.out.println(testTree.isEmpty());
//        System.out.println(testTree.size());
//        xTest = 0.226;
//        yTest = 0.577;
//        pTest = new Point2D(xTest, yTest);
//        System.out.println(testTree.contains(pTest));
//        testTree.insert(pTest);
//        System.out.println(testTree.contains(pTest));
//        System.out.println(testTree.size());
//        System.out.println(testTree.get(pTest));
//        testTree.draw();
        
        // Test Two
        KdTree testTwo = new KdTree();    
        String[] files = new String[1];
        files[0] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\kdtree\\input80k.txt";

        // for each command-line argument
        for (String filename : files) {
            // read in the board specified in the filename
            In in = new In(filename);
            while (!in.isEmpty()) {
                double x = in.readDouble();
                double y = in.readDouble();
                Point2D p = new Point2D(x, y);
                testTwo.insert(p);
            }
        }
        testTwo.draw();
        System.out.println(testTwo.size());
    }

}
