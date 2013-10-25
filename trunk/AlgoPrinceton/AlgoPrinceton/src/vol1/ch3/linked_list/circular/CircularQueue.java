package vol1.ch3.linked_list.circular;

import java.util.Iterator;

import vol1.ch3.interfaces.Queue;


/**
 * 1.3.29 Write a Queue implementation that uses a circular linked list, which is the same as a linked list except that
 * no links are null and the value of last.next is first whenever the list is not empty. Keep only one Node instance
 * variable (last).
 * 
 * @author libojuve
 * 
 */
public class CircularQueue<E> implements Queue<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
    }


    private Node<E> last;
    private int size;


    public CircularQueue() {
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

        // 新元素的next应该指向第一个元素，即last.next
        if (last != null) {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        // 这时queue为空，newNode应该指向自己
        else {
            newNode.next = newNode;
            last = newNode;
        }
        ++size;
    }

    @Override
    public E dequeue() {
        E item = null;
        if (last != null) {
            Node<E> first = last.next;
            item = first.item;

            if (size == 1) {
                last = null;
            } else {
                last.next = first.next;
            }
            --size;
        }
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = last == null ? null : last.next;
            private boolean started = false;


            @Override
            public boolean hasNext() {
                if (current == null) {
                    return false;
                } else {
                    // 遍历已经开始，且还没有再回到first；或者遍历还没开始，当前node在第一个元素上
                    return started && current != last.next || !started && current == last.next;
                }
            }

            @Override
            public E next() {
                if (!started) {
                    started = true;
                }
                E item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("不支持删除操作");
            }
        };
    }

    // --------------------- 测试用的方法 -----------------------
    private void printState() {
        System.out.println("---------------------------------");
        System.out.println("----------Queue State-----------");
        if (last != null) {
            Node<E> node = last.next;
            for (node = last.next; node != last; node = node.next) {
                System.out.print(node.item + " --> ");
            }
            // print last element
            System.out.print(node.item + " --> ");
            System.out.println("[" + last.next.item + "]");
        } else {
            System.out.println("null");
        }
        System.out.println("---------------------------------");
    }

    private static <E> void testIteration(CircularQueue<E> queue) {
        System.out.println("**********测试遍历开始**********");
        for (E str : queue) {
            System.out.print(str + ", ");
        }
        System.out.println();
        System.out.println("**********测试遍历结束**********");
    }

    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<String>();
        queue.printState();
        testIteration(queue);

        queue.enqueue("1");
        queue.printState();

        testIteration(queue);

        queue.enqueue("2");
        queue.printState();

        queue.enqueue("3");
        queue.printState();

        testIteration(queue);

        System.out.println("Dequeue: " + queue.dequeue());
        queue.printState();

        System.out.println("Dequeue: " + queue.dequeue());
        queue.printState();

        System.out.println("Dequeue: " + queue.dequeue());
        queue.printState();

        System.out.println("Dequeue: " + queue.dequeue());
        queue.printState();


    }
}
