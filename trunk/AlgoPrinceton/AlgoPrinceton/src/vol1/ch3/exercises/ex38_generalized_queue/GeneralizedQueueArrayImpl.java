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
public class GeneralizedQueueArrayImpl<E> {
    private Object[] elements;
    private int size;


    /**
     * create an empty queue
     */
    public GeneralizedQueueArrayImpl() {
        elements = new Object[2];
        size = 0;
    }

    /**
     * is the queue empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Resize the elements array to a new length
     * 
     * @param newLength
     */
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    /**
     * add an item
     * 
     * @param e
     */
    public void insert(E e) {
        if (size == elements.length) {
            resize(size * 2);
        }
        elements[size] = e;
        ++size;
    }

    /**
     * delete and return the kth least recently inserted item
     * 
     * @param index
     * @return
     */
    public E delete(int index) {
        E elt = (E) elements[index];
        // 把删除元素后面的元素往前移动
        for (int i = index + 1; i < size; ++i) {
            elements[i] = elements[i - 1];
        }
        --size;
        if (size > 0 && size * 4 < elements.length) {
            resize(elements.length / 2);
        }
        return elt;
    }
}
