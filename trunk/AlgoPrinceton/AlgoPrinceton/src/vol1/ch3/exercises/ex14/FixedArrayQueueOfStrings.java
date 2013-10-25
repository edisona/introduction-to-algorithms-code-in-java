package vol1.ch3.exercises.ex14;

import java.util.Iterator;

import vol1.ch3.interfaces.Queue;

/**
 * 1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue
 * abstraction with a fixed-size array, and then extend your implementation to
 * use array resizing to remove the size restriction.
 * 
 * @author libojuve
 * 
 */
/*
 * 思路是这样的：用first和last来记录这个queue的头和尾，然后围着
 */
public class FixedArrayQueueOfStrings implements Queue<String> {
    private String[] elements;
    private int size;
    private int first;
    private int last;


    public FixedArrayQueueOfStrings(int capacity) {
        elements = new String[capacity];
        size = 0;
        first = -1;
        last = -1;
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
    public void enqueue(String item) {
        // 如果size为0，则一切从头开始，就如同新建一样
        // TODO: 应该在dequeue中做这个

        // 先检查queue中是否还有足够的空间
        if (size == elements.length) {
            return;
        }

        // Queue有足够空间再加入一个新元素
        // 确定插入位置
        ++last;
        if (last == elements.length) {
            last = 0;
        }
        elements[last] = item;
        ++size;

        // 加入元素后，如果queue中只有一个元素，则first和last相等。
        if (size == 1) {
            first = last;
        }
    }

    @Override
    public String dequeue() {
        // 如果queue没有元素，则返回null
        if (size == 0) {
            return null;
        }
        // queue有元素
        String item = elements[first];
        elements[first] = null;
        ++first;
        if (first == elements.length) {
            first = 0;
        }
        --size;
        // 删除掉第一个元素之后（first所指的那个），如果queue为空，则恢复初始状态
        if (size == 0) {
            first = last = -1;
        }
        return item;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = first;
            // first是一定<=lastIndex的，然后realIndex一定是在两者之间的
            private int realIndex = first;
            private int lastIndex = last >= first ? last : elements.length + last;


            @Override
            public boolean hasNext() {
                return realIndex != -1 && realIndex <= lastIndex;
            }

            @Override
            public String next() {
                String nextValue = elements[index];
                ++index;
                ++realIndex;
                if (index == elements.length) {
                    index = 0;
                }
                return nextValue;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("不支持删除操作！");
            }
        };
    }

    private void state() {
        String format = "%-8s";
        System.out.println("----------------------------------------------------------------");
        System.out.println("---- QUEUE STATE ----");
        System.out.println("Size = " + size + ", first = " + first + ", last = " + last);
        for (int i = 0; i < elements.length; ++i) {
            System.out.printf(format, elements[i]);
        }

        System.out.println();
        for (int i = 0; i < elements.length; ++i) {
            if (i == first) {
                if (first == last) {
                    System.out.printf(format, "^f(l)");
                } else {
                    System.out.printf(format, "^first");
                }
            } else if (i == last) {
                System.out.printf(format, "^last");
            } else {
                System.out.printf(format, " ");
            }
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }

    public static void main(String[] args) {
        FixedArrayQueueOfStrings queue = new FixedArrayQueueOfStrings(10);
        queue.state();

        System.out.println("Dequeue: " + queue.dequeue());

        System.out.println("Enqueue: 1");
        queue.enqueue("1");
        queue.state();

        System.out.println("Dequeue: " + queue.dequeue());
        queue.state();

        System.out.println("Enqueue: 1, 2");
        queue.enqueue("1");
        queue.enqueue("2");
        queue.state();

        System.out.println("Dequeue: " + queue.dequeue());
        queue.state();

        System.out.println("Enqueue: 3, 4, 5, 6");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.state();

        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.state();

        System.out.println("Enqueue: 7, 8, 9, 10, 11");
        for (int i = 7; i <= 11; ++i) {
            queue.enqueue("" + i);
        }
        queue.state();

        for (int i = 0; i < 7; ++i) {
            System.out.println("Dequeue: " + queue.dequeue());
        }
        queue.state();

        System.out.println("Enqueue: 12, 13");
        queue.enqueue("12");
        queue.enqueue("13");
        queue.state();

        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.state();

        System.out.println("Dequeue: " + queue.dequeue());
        queue.state();
    }
}
