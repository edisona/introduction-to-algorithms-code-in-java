package vol1.ch3.exercises.ex33_deque;

import java.util.Iterator;

/**
 * 1.3.33 Deque. A double-ended queue or deque is like a stack or a queue but supports adding and removing items at both
 * ends. A deque stores a collection of items and supports the following API:<br/>
 * <br/>
 * public class Deque<Item> implements Iterable<Item> <br/>
 * Deque() create an empty deque <br/>
 * boolean isEmpty() is the deque empty? <br/>
 * int size() number of items in the deque <br/>
 * void pushLeft(Item item) add an item to the left end <br/>
 * void pushRight(Item item) add an item to the right end <br/>
 * Item popLeft() remove an item from the left end <br/>
 * Item popRight() remove an item from the right end <br/>
 * <br/>
 * 
 * Write a class Deque that uses a doubly-linked list to implement this API and a class ResizingArrayDeque that uses a
 * resizing array.
 * 
 * 
 * @author libojuve
 * 
 */
public class Deque<E> implements Iterable<E> {
    private static class Node<E> {
        E item;
        Node<E> prec;
        Node<E> next;
    }


    private int size;
    private Node<E> first;
    private Node<E> last;


    /**
     * create an empty deque
     */
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * is the deque empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * number of items in the deque
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add an item to the left end
     * 
     * @param item
     */
    public void pushLeft(E item) {
        Node<E> newNode = new Node<E>();
        newNode.item = item;

        if (size == 0) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.prec = newNode;
            first = newNode;
        }

        ++size;
    }

    /**
     * add an item to the right end
     * 
     * @param item
     */
    public void pushRight(E item) {
        Node<E> newNode = new Node<E>();
        newNode.item = item;

        if (size == 0) {
            last = first = newNode;
        } else {
            last.next = newNode;
            newNode.prec = last;
            last = newNode;
        }

        ++size;
    }

    /**
     * remove an item from the left end
     * 
     * @return
     */
    public E popLeft() {
        E item = null;
        if (size > 0) {
            item = first.item;
            if (size == 1) {
                first = last = null;
            } else {
                first = first.next;
                first.prec = null;
            }
            --size;
        }
        return item;
    }


    /**
     * remove an item from the right end
     * 
     * @return
     */
    public E popRight() {
        E item = null;
        if (size > 0) {
            item = last.item;
            if (size == 1) {
                first = last = null;
            } else {
                last = last.prec;
                last.next = null;
            }
            --size;
        }
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = first;


            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void state() {
        System.out.println("------------Deque state------------");
        System.out.println("Order: ");
        for (Node<E> node = first; node != null; node = node.next) {
            System.out.print(node.item + ", ");
        }
        System.out.println();

        System.out.println("Reverse order:");
        for (Node<E> node = last; node != null; node = node.prec) {
            System.out.print(node.item + ", ");
        }
        System.out.println();
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.state();

        System.out.println("Pop left : " + deque.popLeft());
        System.out.println("Pop right: " + deque.popRight());

        deque.pushLeft("1");
        deque.state();

        deque.pushLeft("2");
        deque.state();

        deque.pushLeft("3");
        deque.state();

        System.out.println("Pop left : " + deque.popLeft());
        System.out.println("Pop left : " + deque.popLeft());
        System.out.println("Pop left : " + deque.popLeft());
        System.out.println("Pop left : " + deque.popLeft());

        deque.pushRight("1");
        deque.state();

        deque.pushRight("2");
        deque.state();

        deque.pushRight("3");
        deque.state();

        System.out.println("Test foreach (iterator) begin");
        for (String s : deque) {
            System.out.println(s);
        }
        System.out.println("Test foreach (iterator) end");

        System.out.println("Pop right: " + deque.popRight());
        System.out.println("Pop right: " + deque.popRight());
        System.out.println("Pop right: " + deque.popRight());
        System.out.println("Pop right: " + deque.popRight());

        System.out.println("Test foreach (iterator) begin");
        for (String s : deque) {
            System.out.println(s);
        }
        System.out.println("Test foreach (iterator) end");
    }
}
