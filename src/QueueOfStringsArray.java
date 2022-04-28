
public class QueueOfStringsArray {

    private String[] id_array;
    private int first_position;
    private int last_position;
    
    QueueOfStringsArray() {
        id_array = new String[1];
        first_position = 0;
        last_position = 0;
    }
    
    
    public boolean isEmpty() {
        return (first_position == last_position);
    }
    
    
    public void enqueue(String item) {
        id_array[last_position] = item;
        last_position++;
        if (last_position == id_array.length) {
            resize(last_position * 2);
        }   
    }
    
    
    public String dequeue() {
        //I know returning null is terrible design, but this was a just for fun assignment
        if (isEmpty()) {
            System.out.println("\nQueue is empty, nothing to dequeue, returning null...\n");
            return null;
        }
        String item = id_array[first_position];
        id_array[first_position] = null;
        first_position++;
        if ((last_position - first_position) <= id_array.length/4) {
            resize(id_array.length/2);
        }
        return item;
    }
    
    
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        int copy_position = 0;
        for (int i = first_position; i < last_position; i++) {
            copy[copy_position] = id_array[i];
            copy_position++;
        }
        id_array = copy;
        last_position = last_position - first_position;
        first_position = 0;
    }
    
    
    public static void main(String[] args) {
        QueueOfStringsArray test = new QueueOfStringsArray();
        test.enqueue("I");
        test.enqueue(" really ");
        test.enqueue("love");
        test.enqueue(" steak ");
        test.enqueue("that\'s");
        test.enqueue(" Rare!!! ");
        test.enqueue("It\'s");
        test.enqueue(" always ");
        test.enqueue("so");
        test.enqueue(" juicy ");
        test.enqueue("Yummy!!!!!!");     
        while (!test.isEmpty()) {
            System.out.print(test.dequeue());
        }
        System.out.println("");
        test.dequeue();
        test.enqueue("I");
        test.enqueue(" really ");
        System.out.println(test.dequeue());
        test.enqueue("love");
        test.enqueue(" steak ");
        test.enqueue("that\'s");
        System.out.println(test.dequeue());
        test.enqueue(" Rare!!! ");
        System.out.println(test.dequeue());
        test.enqueue("I");
        test.enqueue(" really ");
        test.enqueue("love");
        test.enqueue(" steak ");
        test.enqueue("that\'s");
        test.enqueue(" Rare!!! ");
        test.enqueue("It\'s");
        test.enqueue(" always ");
        test.enqueue("so");
        test.enqueue(" juicy ");
        test.enqueue("Yummy!!!!!!");
        while (!test.isEmpty()) {
            System.out.print(test.dequeue());
        }
    }
}
