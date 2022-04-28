
public class QueueOfStringsLinkedList {

    private Node first;
    private Node last;
    
    
    private class Node {
        String item = null;
        Node next = null;
    }
    
    
    public boolean isEmpty() {
        return (first == null);
    }

    
    public void enqueue(String item) {
        Node old_last = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
        old_last.next = last;
        }
    }
    
    
    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    
    
    public static void main(String[] args) {
        QueueOfStringsLinkedList test = new QueueOfStringsLinkedList();
        test.enqueue("I");
        test.enqueue(" really ");
        test.enqueue("hate");
        test.enqueue(" managers ");
        test.enqueue("named");
        test.enqueue(" Pete!!! ");
        test.enqueue("They\'re");
        test.enqueue(" always ");
        test.enqueue("massive");
        test.enqueue(" corporate ");
        test.enqueue("bootlickers!!!!!!");
        
        while (!test.isEmpty()) {
            System.out.print(test.dequeue());
        }
    }

}
