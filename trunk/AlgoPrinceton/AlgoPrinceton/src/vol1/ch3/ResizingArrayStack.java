package vol1.ch3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Object[] elements;
    private int size;


    /**
     * 
     * @param cap
     *            capacity of the stack
     */
    public ResizingArrayStack(int cap) {
        elements = new Object[cap];
        size = 0;
    }

    public ResizingArrayStack() {
        this(0);
    }

    /**
     * is the stack empty?
     * 
     * @return if the stack is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 把<code>item</code>放入stack中, 仅在stack容量不到时有效
     * 
     * @param item
     *            要放入stack中的元素
     * 
     */
    public void push(Item item) {
        // 检查数组大小，如果数组满了，则加倍数组的大小
        if (size >= elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        ++size;
    }

    /**
     * 返回stack顶端的元素，如果stack为空，则返回null
     * 
     * @return stack顶端的元素
     */
    @SuppressWarnings("unchecked")
    public Item pop() {
        if (!isEmpty()) {
            --size;
            Item elt = (Item) elements[size];
            elements[size] = null;
            // 如果size是array长度的1/4，则把array长度减半
            if (size > 0 && size <= elements.length / 4) {
                resize(elements.length / 2);
            }
            return elt;
        }
        return null;
    }

    /**
     * 把此stack的元素移动到另一个大小为max的数组中，<code>max >= capacity</code>
     * 
     * @param max
     *            新的stack的大小
     */
    private void resize(int max) {
        // Object[] newArray = new Object[max];
        // // 把此stack的内容移动到新的数组中
        // for (int i = 0; i < elements.length; ++i) {
        // newArray[i] = elements[i];
        // }
        //
        // elements = newArray;

        // 可以直接使用Arrays.copyOf来实现创建一个数组，并将另一个数组的内容复制进去
        elements = Arrays.copyOf(elements, max);
    }

    /**
     * Return an iterator for the stack <br/>
     * 
     */
    /*
     * (non-Javadoc) 注意：因为stack是个LIFO，所以iterator应该是反过来的。
     * 
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index = size;


            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @SuppressWarnings("unchecked")
            @Override
            public Item next() {
                return (Item) elements[--index];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "Remove is not supported for the stack iterator");
            }
        };
    }

    /**
     * Test client for the implementation
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>(5);

        System.out.println("Enter now: ");
        while (in.hasNext()) {
            String input = in.next();
            // 如果输入"-"，pop stack，并且输出
            if ("-".equals(input)) {
                System.out.print(stack.pop() + " ");
            } else if ("bye".equals(input)) {
                System.out.println("Bye!");
                break;
            } else {
                stack.push(input);
            }
        }
        System.out.println("(" + stack.size() + " left on stack)");

        stack.push("12");
        stack.push("blabla");
        for (String str : stack) {
            System.out.println(str);
        }

    }
}
