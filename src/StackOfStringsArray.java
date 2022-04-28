
public class StackOfStringsArray {

    private String[] stack;
    private int position;
    
    public StackOfStringsArray() {
        stack = new String[1];
        position = 0;
    }
    
    public String pop() {
        //This way removes the returned element (prevents loitering)
        String item = stack[--position];
        stack[position] = null;
        // resizing down to 1/2 size when array is 1/4 in size.
        if (position > 0 && position == stack.length/4) {
            resize(stack.length/2);
        }
        return item;
    }
    
    public void push(String item) {
        if (position == stack.length) {
            resize(stack.length * 2);
        }
        stack[position++] = item;
    }
    
    public boolean isEmpty() {
        return position == 0;
    }
    
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < position; i++) {
            copy[i] = stack[i];
        }
        stack = copy;
    }
    
    public static void main(String[] args) {
        StackOfStringsArray linked_list =
                new StackOfStringsArray();
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
