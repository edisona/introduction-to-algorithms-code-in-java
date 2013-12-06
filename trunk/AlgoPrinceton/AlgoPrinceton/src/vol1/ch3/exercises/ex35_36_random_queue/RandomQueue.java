package vol1.ch3.exercises.ex35_36_random_queue;

import java.util.Random;

/**
 * 1.3.35 Random queue. A random queue stores a collection of items and supports the following API:
 * 
 * <pre>
 * public class RandomQueue<Item>
 * RandomQueue() create an empty random queue
 * boolean isEmpty() is the queue empty?
 * void enqueue(Item item) add an item
 * Item dequeue() remove and return a random item (sample without replacement)
 * Item sample() return a random item, but do not remove (sample with replacement)
 * </pre>
 * 
 * Write a class RandomQueuethat implements this API. Hint : Use an array representation (with resizing). To remove an
 * item, swap one at a random position (indexed 0through N-1) with the one at the last position (index N-1). Then delete
 * and return the last object, as in ResizingArrayStack. Write a client that deals bridge hands (13 cards each)
 * usingRandomQueue<Card>.
 * 
 * @author libojuve@gmail.com
 * 
 */
public class RandomQueue<E> {
    private Object[] elements;
    private int size;


    // private int last;


    /**
     * create an empty random queues
     */
    public RandomQueue() {
        elements = new Object[2];
        size = 0;
        // last = 0;
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
     * add an item
     * 
     * @param item
     */
    public void enqueue(E item) {
        if (elements.length == size) {
            resize(size * 2);
        }
        elements[size] = item;
        ++size;
    }

    /**
     * remove and return a random item (sample without replacement)
     * 
     * @return
     */
    public E dequeue() {
        if (size > 0) {
            Random random = new Random();
            int dequeueIndex = random.nextInt(size);
            // 用最后一个元素来填补deque的元素留下的空缺
            E item = (E) elements[dequeueIndex];
            elements[dequeueIndex] = elements[size - 1];
            elements[size - 1] = null;
            --size;
            if (size > 0 && size * 4 == elements.length) {
                resize(elements.length / 2);
            }
            return item;
        }
        return null;
    }

    /**
     * return a random item, but do not remove (sample with replacement)
     * 
     * @return
     */
    public E sample() {
        if (size > 0) {
            Random random = new Random();
            int dequeueIndex = random.nextInt(size);
            return (E) elements[dequeueIndex];
        }
        return null;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void printState() {
        System.out.println("========= State =========");
        for (Object elt : elements) {
            System.out.print(elt + ", ");
        }
        System.out.println();
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {

        RandomQueue<Integer> queue = new RandomQueue<Integer>();
        queue.printState();

        queue.enqueue(1);
        queue.printState();

        queue.enqueue(2);
        queue.printState();

        queue.enqueue(3);
        queue.printState();

        queue.enqueue(4);
        queue.printState();

        queue.enqueue(5);
        queue.printState();

        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());

        System.out.println(queue.dequeue());
        queue.printState();

        System.out.println(queue.dequeue());
        queue.printState();

        System.out.println(queue.dequeue());
        queue.printState();

        System.out.println(queue.dequeue());
        queue.printState();

        System.out.println(queue.dequeue());
        queue.printState();
    }
}
