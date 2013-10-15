package vol1.ch3.linked_list;

import java.util.Iterator;
import java.util.Scanner;

public class MyLinkedListStack<E> implements Iterable<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
    }


    private int size;
    private Node<E> first;


    public MyLinkedListStack() {
        size = 0;
        first = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(E item) {
        // 新建一个值为item的newNode
        Node<E> newNode = new Node<E>();
        newNode.item = item;

        // 把newNode的next指向first |newNode|--> first element
        newNode.next = first;

        // 把链表的first指向新加的元素
        first = newNode;
        ++size;
    }

    /**
     * 返回stack顶端元素，并将其从中删除
     * 
     * @return stack顶端元素。如果stack为空，则返回null。
     */
    public E pop() {
        E item = null;
        if (size > 0) {
            item = first.item;
            first = first.next;
            --size;
        }
        return item;
    }

    /**
     * 返回stack顶端元素，并不从中删除
     * 
     * @return stack顶端元素。如果stack为空，则返回null。
     */
    public E peek() {
        if (!isEmpty()) {
            E item = first.item;
            return item;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> currentNode = first;


            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                E item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("这个Iterator不支持删除操作！");
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (E item : this) {
            sb.append(item.toString());
            sb.append(", ");
        }
        if (sb.length() >= 2) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) { // Create a stack and push/pop
                                             // strings as directed on StdIn.
        MyLinkedListStack<String> s = new MyLinkedListStack<String>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String item = in.next();
            if (!item.equals("-")) {
                s.push(item);
            }
            // else if ("bye".equalsIgnoreCase(item)) {
            // break;
            // }
            else if (!s.isEmpty()) {
                System.out.println(s.pop() + " ");
            }
        }
        System.out.println("(" + s.size() + " left on stack) : " + s);
    }
}
