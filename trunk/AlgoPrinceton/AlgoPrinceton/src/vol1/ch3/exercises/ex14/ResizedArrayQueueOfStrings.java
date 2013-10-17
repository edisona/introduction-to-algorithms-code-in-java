package vol1.ch3.exercises.ex14;


/**
 * 1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue
 * abstraction with a fixed-size array, and then extend your implementation to
 * use array resizing to remove the size restriction.
 * 
 * @author libojuve
 * 
 */
/*
 * 思路是这样的：用first和last来记录这个queue的头和尾，然后围着转圈圈 如果需要增加储存空间，则把数组从新排列一下（把first弄成0）
 */
public class ResizedArrayQueueOfStrings {
    private String[] elements;
    private int size;
    private int first;
    private int last;


    public ResizedArrayQueueOfStrings() {
        this(2);
    }

    public ResizedArrayQueueOfStrings(int capacity) {
        elements = new String[capacity];
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

    public void enqueue(String item) {
        // 先检查queue中是否还有足够的空间
        if (size == elements.length) {
            resize(elements.length * 2);
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

    /**
     * newCapacity一定要大于等于size
     * 
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        // System.out.println("Resizing to new capacity " + newCapacity +
        // "...");
        String[] newArray = new String[newCapacity];
        // 把elements中的内容拷贝到newArray中，并把first元素排到0号位

        // 如果first在last的左边
        // 0...first...last...(length-1)
        if (first <= last) {
            System.arraycopy(elements, first, newArray, 0, size);
        }
        // 如果first在last的右边
        // 0...last...first...(length-1)
        else {
            // 先拷贝从first到elements.length - 1的元素
            // System.out.println("first = " + first);
            int firstPartLength = elements.length - first;
            // System.out.println("firstPartLength = " + firstPartLength);
            System.arraycopy(elements, first, newArray, 0, firstPartLength);
            // 再拷贝从0到last的元素
            System.arraycopy(elements, 0, newArray, firstPartLength, last + 1);
        }
        elements = newArray;
        first = 0;
        last = size - 1;
        // System.out.println("Resizing finished");
    }

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
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }

        // 删除掉第一个元素之后（first所指的那个），如果queue为空，则恢复初始状态
        if (size == 0) {
            first = last = -1;
        }
        return item;
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
        ResizedArrayQueueOfStrings queue = new ResizedArrayQueueOfStrings();
        queue.state();

        testDequeue(queue);

        testEnqueue(queue, "1");

        testEnqueue(queue, "2");

        testDequeue(queue);

        testEnqueue(queue, "3");

        testEnqueue(queue, "4");

        testEnqueue(queue, "5");

        testDequeue(queue);

        testEnqueue(queue, "6");

        testDequeue(queue);

        testEnqueue(queue, "7");

        testDequeue(queue);
        testDequeue(queue);
        testDequeue(queue);

        for (int i = 0; i < 7; ++i) {
            testEnqueue(queue, "" + i);
        }

        for (int i = 0; i < 5; ++i) {
            testDequeue(queue);
        }

        testEnqueue(queue, "7");
        testDequeue(queue);
        testDequeue(queue);
    }

    private static void testEnqueue(ResizedArrayQueueOfStrings queue, String elt) {
        System.out.println("Enquete " + elt);
        queue.enqueue(elt);
        queue.state();
    }

    private static void testDequeue(ResizedArrayQueueOfStrings queue) {
        System.out.println("Dequeue: " + queue.dequeue());
        queue.state();
    }
}
