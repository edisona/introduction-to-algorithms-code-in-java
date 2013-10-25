package vol1.ch3.linked_list;

import java.util.Iterator;
import java.util.Scanner;

import vol1.ch3.interfaces.Queue;

/**
 * FIFO queue
 * 
 * @author libojuve
 * 
 * @param <E>
 */
public class MyLinkedListQueue<E> implements Queue<E> {
    private static class Node<E> {
        private E item;
        private Node<E> next;
    }


    private Node<E> first;
    private Node<E> last;
    private int size;


    public MyLinkedListQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(E item) {
        Node<E> newNode = new Node<E>();
        newNode.item = item;

        if (last != null) {
            last.next = newNode;
        }

        last = newNode;
        ++size;
        if (size == 1) {
            first = last;
        }
    }

    @Override
    public E dequeue() {
        if (first != null) {
            E item = first.item;
            first = first.next;
            --size;
            if (size == 0) {
                last = null;
            }
            return item;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty()) {
            for (E item : this) {
                sb.append(item);
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
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


    public static void main(String[] args) { // Create a stack and push/pop
        // strings as directed on StdIn.
        MyLinkedListQueue<String> q = new MyLinkedListQueue<String>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String item = in.next();
            if ("bye".equalsIgnoreCase(item)) {
                break;
            } else if (!item.equals("-")) {
                q.enqueue(item);
            } else if (!q.isEmpty()) {
                System.out.println(q.dequeue() + " ");
            }
        }
        System.out.println("(" + q.size() + " left on stack) : " + q);
    }
}
