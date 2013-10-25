package vol1.ch3.exercises.ex31;

/**
 * 1.3.31 Implement a nested class DoubleNode for building doubly-linked lists, where each node contains a reference
 * to the item preceding it and the item following it in the list (null if there is no such item). Then implement
 * static methods for the following tasks: insert at the beginning, insert at the end, remove from the beginning,
 * remove from the end, insert before a given node, insert after a given node, and remove a given node.
 * 
 * @author libojuve
 */
public class DoublyLinkedList<E> {
    private static class DoubleNode<E> {
        private E item;
        private DoubleNode<E> precedent;
        private DoubleNode<E> next;
    }


    private int size;
    private DoubleNode<E> first;
    private DoubleNode<E> last;


    public void addFirst(E item) {
        DoubleNode<E> newNode = new DoubleNode<E>();
        newNode.item = item;
        if (size == 0) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.precedent = newNode;
            first = newNode;
        }
        ++size;
    }

    public void addLast(E item) {
        DoubleNode<E> newNode = new DoubleNode<E>();
        newNode.item = item;
        if (size == 0) {
            first = last = newNode;
        } else {
            newNode.precedent = last;
            last.next = newNode;
            last = newNode;
        }
        ++size;
    }

    public E removeFirst() {
        E item = null;
        if (size > 0) {
            item = first.item;
            if (size == 1) {
                first = last = null;
            } else {
                first = first.next;
                first.precedent = null;
            }
            --size;
        }
        return item;
    }

    public E removeLast() {
        E item = null;
        if (size > 0) {
            item = last.item;
            if (size == 1) {
                first = last = null;
            } else {
                last = last.precedent;
                last.next = null;
            }
            --size;
        }
        return item;
    }

    private void insertAfter(DoubleNode<E> node, E item) {
        DoubleNode<E> newNode = new DoubleNode<E>();
        newNode.item = item;

        newNode.next = node.next;
        newNode.precedent = node;
        if (newNode.next != null) {
            newNode.next.precedent = newNode;
        }
        node.next = newNode;
        ++size;
    }

    private void insertBefore(DoubleNode<E> node, E item) {
        DoubleNode<E> newNode = new DoubleNode<E>();
        newNode.item = item;

        newNode.next = node;
        newNode.precedent = node.precedent;
        node.precedent = newNode;
        if (newNode.precedent != null) {
            newNode.precedent.next = newNode;
        }

        ++size;
    }

    private void remove(DoubleNode<E> node) {
        if (node.precedent != null) {
            node.precedent.next = node.next;
        }

        if (node.next != null) {
            node.next.precedent = node.precedent;
        }

        --size;
    }
}
