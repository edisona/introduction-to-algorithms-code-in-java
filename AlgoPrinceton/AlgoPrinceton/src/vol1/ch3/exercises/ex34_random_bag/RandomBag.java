package vol1.ch3.exercises.ex34_random_bag;

import java.util.Iterator;
import java.util.Random;

/**
 * 
 * 1.3.34 Random bag. A random bag stores a collection of items and supports the following API: <br/>
 * 
 * <pre>
 * public class RandomBag<Item> implements Iterable<Item>
 * RandomBag() create an empty random bag
 * boolean isEmpty() is the bag empty?
 * int size() number of items in the bag
 * void add(Item item) add an item
 * </pre>
 * 
 * Write a class RandomBag that implements this API. Note that this API is the same as for Bag, except for the <br/>
 * adjective random, which indicates that the iteration should provide the items in random order (all N ! <br/>
 * permutations equally likely, for each iterator). Hint : Put the items in an array and randomize their order <br/>
 * in the iterator’s constructor. <br/>
 * 
 * @author libojuve
 * 
 * @param <E>
 */

public class RandomBag<E> implements Iterable<E> {
    private Object[] elements;
    private int size;


    /**
     * create an empty random bag
     */
    public RandomBag() {
        elements = new Object[2];
        size = 0;
    }

    /**
     * is the bag empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * number of items in the bag
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add an item
     * 
     * @param item
     */
    public void add(E item) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        ++size;
    }

    private void resize(int length) {
        Object[] newArray = new Object[length];
        // copy all elements
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void state() {
        System.out.println("========= Bag state ==========");
        System.out.println("Size = " + size);
        for (Object o : elements) {
            System.out.print(o + ", ");
        }
        System.out.println();
        System.out.println("------------------------------");
    }

    /**
     * Random order
     * 
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = 0;
            private int[] orderArray = initOrderArray();


            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            private int[] initOrderArray() {
                int[] array = new int[size];
                boolean[] generated = new boolean[size];

                Random random = new Random();
                int i = 0;
                while (i < size) {
                    int j = random.nextInt(size);
                    if (!generated[j]) {
                        array[i] = j;
                        generated[j] = true;
                        ++i;
                    }
                }

                return array;
            }

            @Override
            public E next() {
                E nextElt = (E) elements[orderArray[currentIndex]];
                ++currentIndex;
                return nextElt;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static void testIteration(RandomBag<?> bag) {
        System.out.println("Test Iteration");
        for (Object s : bag) {
            System.out.print(s + ", ");
        }
        System.out.println("End!");
    }

    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();
        bag.state();

        bag.add("1");
        bag.state();

        bag.add("2");
        bag.state();

        bag.add("3");
        bag.state();

        bag.add("4");
        bag.state();

        bag.add("5");
        bag.state();

        bag.add("6");
        bag.state();

        testIteration(bag);
        testIteration(bag);
        testIteration(bag);
        testIteration(bag);
        testIteration(bag);
        testIteration(bag);
        testIteration(bag);
        testIteration(bag);
    }

}
