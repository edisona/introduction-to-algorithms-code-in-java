package vol1.ch3.exercises.ex38_generalized_queue;

/**
 * 1.3.38 Delete kth element. Implement a class that supports the following API: <br/>
 * 
 * <pre>
 *     public class GeneralizedQueue<Item>
 *     GeneralizedQueue() create an empty queue
 *     boolean isEmpty() is the queue empty?
 *     void insert(Item x) add an item
 *     Item delete(int k) delete and return the kth least recently inserted item
 * </pre>
 * 
 * First, develop an implementation that uses an array implementation, and then develop
 * one that uses a linked-list implementation. Note: the algorithms and data structures
 * that we introduce in Chapter 3 make it possible to develop an implementation that
 * can guarantee that both insert() and delete() take time prortional to the logarithm
 * of the number of items in the queue—see Exercise 3.5.27.
 * 
 * @author libojuve@gmail.com
 * 
 * @param <E>
 */
public class GeneralizedQueueLinkedListImpl<E> {

    /**
     * Linked list的Node
     * 
     * @author libojuve@gmail.com
     * 
     * @param <E>
     */
    private static class Node<E> {
        private E value;
        private Node<E> next;
    }


    private Node<E> first;
    private Node<E> last;
    private int size;


    public GeneralizedQueueLinkedListImpl() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(E item) {
        Node<E> newNode = new Node<E>();
        newNode.value = item;

        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }

        ++size;
    }

    /**
     * The first element is 1.
     * 
     * @param k
     * @return
     */
    public E delete(int k) {
        E item = null;
        if (size >= k) {
            // 只有一个元素，改动first和last
            if (size == 1) {
                item = first.value;
                first = last = null;
            }
            // 删第一个元素，需要改动first
            else if (k == 1) {
                item = first.value;
                first = first.next;
            } else {
                // 找第k-1个元素的位置
                Node<E> k_1;
                int i;
                for (k_1 = first, i = 0; i < k - 1; ++i) {
                    k_1 = k_1.next;
                }

                item = k_1.next.value;
                k_1.next = k_1.next.next;

                // 删除最后一个元素，需要改动last
                if (k == size) {
                    last = k_1;
                }
            }

            --size;
        }
        return item;
    }
}
