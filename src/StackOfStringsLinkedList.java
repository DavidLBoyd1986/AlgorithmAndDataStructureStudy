
public class StackOfStringsLinkedList {
    
    private Node first = null;

    // Access Modifiers don't matter on a private inner class
    private class Node {
        String item;
        Node next;
    }
    
    // Need to add a NullPointerException
    public String pop() {
        String return_item = first.item;
        first = first.next;
        return return_item;
    }
    
    public void push(String new_item) {
        Node old_node = first;
        first = new Node();
        first.item = new_item;
        first.next = old_node;
    }
    
    public boolean isEmpty() {
        return (first == null);
    }

    public static void main(String[] args) {
        StackOfStringsLinkedList linked_list =
                new StackOfStringsLinkedList();
        System.out.println(linked_list.isEmpty());
        linked_list.push("I");
        System.out.println(linked_list.isEmpty());
        linked_list.push(" really ");
        linked_list.push("want");
        linked_list.push(" to ");
        linked_list.push("learn");
        linked_list.push(" this ");
        linked_list.push("stuff");
        System.out.println(linked_list.pop());
        linked_list.push(". I");
        linked_list.push(" popped ");
        System.out.println(linked_list.pop());
        linked_list.push("stuff");
        linked_list.push("!");
        linked_list.push("Done!!!!");
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        System.out.println(linked_list.pop());
        // Below pops empty list. uncomment once exception handling has been added
        //System.out.println(linked_list.pop());
    }

}
