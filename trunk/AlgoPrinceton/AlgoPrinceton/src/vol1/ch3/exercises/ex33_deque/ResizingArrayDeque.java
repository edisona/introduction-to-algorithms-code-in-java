package vol1.ch3.exercises.ex33_deque;

import java.util.Iterator;

public class ResizingArrayDeque<E> implements Iterable<E> {

    private Object[] elements;
    private int size;
    private int first;
    private int last;


    public ResizingArrayDeque() {
        elements = new Object[2];
        size = 0;
        first = -1;
        last = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushLeft(E item) {
        // resizing elements array if necessary (the elements array is full)
        if (size == elements.length) {
            // double the array
            resize(elements.length * 2);
        }

        if (size == 0) {
            first = last = 0;
            elements[first] = item;
        } else {
            decrementFirst();
            elements[first] = item;
        }
        ++size;
    }

    public E popLeft() {
        E item = null;
        if (size > 0) {
            item = (E) elements[first];
            incrementFirst();
            --size;
            if (size > 0 && size == elements.length / 4) {
                resize(elements.length / 2);
            }
        }
        return item;
    }

    public E popRight() {
        E item = null;
        if (size > 0) {
            item = (E) elements[last];
            decrementLast();
            --size;
            if (size > 0 && size == elements.length / 4) {
                resize(elements.length / 2);
            }
        }
        return item;
    }

    public void pushRight(E item) {
        // double elements array if necessary (elements array is full)
        if (size == elements.length) {
            resize(elements.length * 2);
        }

        if (size == 0) {
            last = first = 0;
            elements[last] = item;
        } else {
            incrementLast();
            elements[last] = item;
        }
        ++size;
    }

    private void incrementFirst() {
        ++first;
        if (first == elements.length) {
            first = 0;
        }
    }

    private void decrementFirst() {
        --first;
        if (first < 0) {
            first = elements.length - 1;
        }
    }

    private void incrementLast() {
        ++last;
        if (last == elements.length) {
            last = 0;
        }
    }

    private void decrementLast() {
        --last;
        if (last < 0) {
            last = elements.length - 1;
        }
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];

        // if first <= last
        if (first <= last) {
            System.out.println("first < last");
            System.out.println("first = " + first);
            System.out.println("last = " + last);
            System.arraycopy(elements, first, newArray, 0, size);
        } else {
            System.out.println("first > last");
            System.out.println("first = " + first);
            System.out.println("last = " + last);

            int lengthFirst2End = elements.length - first;
            System.out.println("lengthFirst2End = " + lengthFirst2End);
            // copy first to end of elments to newArray
            System.arraycopy(elements, first, newArray, 0, elements.length - first);

            // copy 0 to last of elements to newArray
            System.arraycopy(elements, 0, newArray, lengthFirst2End, last + 1);
        }
        first = 0;
        last = size - 1;
        elements = newArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int lastIndex = last >= first ? last : last + elements.length;
            private int currentIndex = first;
            private int realIndex = first;


            @Override
            public boolean hasNext() {
                return size > 0 && currentIndex <= lastIndex;
            }

            @Override
            public E next() {
                E item = (E) elements[realIndex];
                ++realIndex;
                if (realIndex == elements.length) {
                    realIndex = 0;
                }
                ++currentIndex;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void state() {
        System.out.println("######################################");
        System.out.println("first = " + first + ", last = " + last + ", size = " + size);
        System.out.println("#State");
        for (int i = 0; i < elements.length; ++i) {
            System.out.print(elements[i] + "( " + i + "), ");
        }
        System.out.println();
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        ResizingArrayDeque<String> deque = new ResizingArrayDeque<String>();
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
