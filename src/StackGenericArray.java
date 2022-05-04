
public class StackGenericArray<Item> {

    private Item[] stack;
    private int position;
    
    public StackGenericArray() {
        // Ugly typecast required because can't create a generic array
        stack = (Item[]) new Object[1];
        position = 0;
    }
    
    public Item pop() {
        //This way removes the returned element (prevents loitering)
        Item item = stack[--position];
        stack[position] = null;
        // resizing down to 1/2 size when array is 1/4 in size.
        if (position > 0 && position == stack.length/4) {
            resize(stack.length/2);
        }
        return item;
    }
    
    public void push(Item item) {
        if (position == stack.length) {
            resize(stack.length * 2);
        }
        stack[position++] = item;
    }
    
    public boolean isEmpty() {
        return position == 0;
    }
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < position; i++) {
            copy[i] = stack[i];
        }
        stack = copy;
    }
    
    public static void main(String[] args) {
    StackGenericArray<String> linked_list =
            new StackGenericArray<String>();
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
