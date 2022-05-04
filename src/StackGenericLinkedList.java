
public class StackGenericLinkedList<Item> {

    private Node first;
    
    private class Node {
        Item item;
        Node next;
    }
    
    public Boolean isEmpty() {
        return (first == null);
    }
    
    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    public void push(Item item) {
        Node old_first = first;
        first = new Node();
        first.item = item;
        first.next = old_first;
    }
    
    
    public static void main(String[] args) {
        StackGenericLinkedList<String> linked_list =
                new StackGenericLinkedList<String>();
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
