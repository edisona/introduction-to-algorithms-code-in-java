package vol1.ch3;

import java.util.Scanner;

/**
 * 和MyFixedCapacityStackOfStrings一样，区别仅是这个是generic的
 * 
 * @author libojuve
 * 
 */
public class MyFixedCapacityStack<Item> {
    private Object[] elements;
    private int size;


    /**
     * 
     * @param cap
     *            capacity of the stack
     */
    public MyFixedCapacityStack(int cap) {
        elements = new Object[cap];
        size = 0;
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
     */
    public void push(Item item) {
        if (size < elements.length) {
            elements[size] = item;
            ++size;
        }
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
            return elt;
        }
        return null;
    }

    /**
     * Test client for the implementation
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        MyFixedCapacityStack<String> stack = new MyFixedCapacityStack<String>(5);

        System.out.println("Enter now: ");
        while (in.hasNext()) {
            String input = in.next();
            // 如果输入"-"，pop stack，并且输出
            if ("-".equals(input)) {
                System.out.print(stack.pop() + " ");
            } else {
                stack.push(input);
            }
        }

        System.out.println("(" + stack.size() + " left on stack)");
    }
}
