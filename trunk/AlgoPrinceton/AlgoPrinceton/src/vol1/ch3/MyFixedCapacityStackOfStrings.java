package vol1.ch3;

import java.util.Scanner;

/**
 * 我的FixedCapacityStackOfStrings的实现，其API如下：<br/>
 * <code>FixedCapacityStackOfStrings(int cap)</code> create an empty stack of
 * capacity <code>cap</code> <br/>
 * <code>void  push(String item)</code> add a string <br/>
 * <code>String  pop()</code> remove the most recently added string <br/>
 * <code>boolean  isEmpty()</code> is the stack empty? <br/>
 * <code>int  size()</code> number of strings on the stack <br/>
 * 
 * @author libojuve
 * 
 */

public class MyFixedCapacityStackOfStrings {
    private String[] elements;
    private int size;


    /**
     * 
     * @param cap
     *            capacity of the stack
     */
    public MyFixedCapacityStackOfStrings(int cap) {
        elements = new String[cap];
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

    public boolean isFull() {
        return size == elements.length;
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
    public void push(String item) {
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
    public String pop() {
        if (!isEmpty()) {
            --size;
            String elt = elements[size];
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
        MyFixedCapacityStackOfStrings stack = new MyFixedCapacityStackOfStrings(
                5);

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
