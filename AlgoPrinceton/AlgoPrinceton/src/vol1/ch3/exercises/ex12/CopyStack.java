package vol1.ch3.exercises.ex12;

import vol1.ch3.linked_list.MyLinkedListStack;


/**
 * 1.3.12 Write an iterable Stack client that has a static method copy() that
 * takes a stack of strings as argument and returns a copy of the stack. Note:
 * This ability is a prime example of the value of having an iterator, because
 * it allows development of such functionality without changing the basic API.
 * 
 * @author libojuve
 * 
 */
public class CopyStack {
    public static MyLinkedListStack<String> copy(MyLinkedListStack<String> stack) {
        String[] values = new String[stack.size()];
        int i = values.length - 1;
        for (String value : stack) {
            values[i] = value;
            --i;
        }

        MyLinkedListStack<String> copied = new MyLinkedListStack<String>();
        for (String value : values) {
            copied.push(value);
        }
        return copied;
    }

    public static void main(String[] args) {
        MyLinkedListStack<String> stack = new MyLinkedListStack<String>();
        for (int i = 1; i <= 10; ++i) {
            stack.push(String.valueOf(i));
        }
        System.out.println("Stack: " + stack);

        MyLinkedListStack<String> copied = copy(stack);
        System.out.println("copied == stack : " + (stack == copied));
        System.out.println("Copied: " + copied);
    }
}
