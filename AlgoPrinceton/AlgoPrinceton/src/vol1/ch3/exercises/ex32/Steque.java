package vol1.ch3.exercises.ex32;

/**
 * 1.3.32 Steque. A stack-ended queue or stequeis a data type that supports push, pop, and
 * enqueue. Articulate an API for this ADT. Develop a linked-list-based implementation.
 * 
 * @author libojuve
 * 
 */
public class Steque<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
    }


    private int size;
    private Node<E> first;
    private Node<E> last;


    /**
     * Create an empty steque
     */
    public Steque() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Return the size of this steque
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Return if this steque is empty
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Enqueue the <code>item</code> to the end of the steque
     * 
     * @param item
     */
    public void enqueue(E item) {
        Node<E> newNode = new Node<E>();
        newNode.item = item;

        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        ++size;
    }

    /**
     * Remove the first element and return it. <br/>
     * If no element, return <code>null</code>
     * 
     * @return
     */
    public E pop() {
        E item = null;
        if (size > 0) {
            item = first.item;
            if (size == 1) {
                first = last = null;
            } else {
                first = first.next;
            }
            --size;
        }
        return item;
    }

    /**
     * Add <code>item</code> in the first of this steque
     * 
     * @param item
     */
    public void push(E item) {
        Node<E> newNode = new Node<E>();
        newNode.item = item;

        if (size == 0) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        ++size;
    }

    private void printState() {
        for (Node<E> node = first; node != null; node = node.next) {
            System.out.print(node.item + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Steque<String> steque = new Steque<String>();
        System.out.println("Pop: " + steque.pop());
        steque.enqueue("1");
        steque.enqueue("2");
        steque.printState();
        System.out.println("Pop: " + steque.pop());
        System.out.println("Pop: " + steque.pop());

        steque.push("1");
        steque.push("2");
        steque.printState();
        System.out.println("Pop: " + steque.pop());
        System.out.println("Pop: " + steque.pop());
    }
}
