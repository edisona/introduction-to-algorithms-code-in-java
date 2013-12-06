package vol1.ch3.exercises.ex35_36_random_queue;

import java.util.Iterator;
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
 * 1.3.36 Random iterator. Write an iterator for RandomQueue<Item> from the previous exercise that returns the items in
 * random order.
 * 
 * @author libojuve@gmail.com
 * 
 */
public class RandomQueue<E> implements Iterable<E> {
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int nb = 0;
            private boolean[] iterated = new boolean[size];
            private Random random = new Random();


            @Override
            public boolean hasNext() {
                return nb < size;
            }

            @Override
            public E next() {
                int index;
                do {
                    index = random.nextInt(size);
                } while (iterated[index]);
                iterated[index] = true;
                E element = (E) elements[index];
                ++nb;
                return element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    public static void main(String[] args) {

        RandomQueue<Integer> queue = new RandomQueue<Integer>();
        queue.printState();
        System.out.println("=========== Iteration ===========");
        StringBuilder sb = new StringBuilder();
        for (Integer i : queue) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);

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

        System.out.println("=========== Iteration ===========");
        sb.setLength(0);
        for (Integer i : queue) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);

        System.out.println("=========== Iteration ===========");
        sb.setLength(0);
        for (Integer i : queue) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);

        System.out.println("=========== Iteration ===========");
        sb.setLength(0);
        for (Integer i : queue) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);

        System.out.println("=========== Iteration ===========");
        sb.setLength(0);
        for (Integer i : queue) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);

        System.out.println("=========== Iteration ===========");
        sb.setLength(0);
        for (Integer i : queue) {
            sb.append(i);
            sb.append(", ");
        }
        System.out.println(sb);

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
