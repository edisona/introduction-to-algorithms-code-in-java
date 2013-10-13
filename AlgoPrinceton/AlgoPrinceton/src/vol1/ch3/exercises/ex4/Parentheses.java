package vol1.ch3.exercises.ex4;

import java.util.Scanner;

import vol1.ch3.linked_list.MyLinkedListStack;

/**
 * 1.3.4 Write a stack client Parentheses that reads in a text stream from
 * standard input and uses a stack to determine whether its parentheses are
 * properly balanced. For example, your program should print true for
 * [()]{}{[()()]()} and false for [(]).
 * 
 * @author libojuve
 * 
 */
public class Parentheses {

    public Parentheses() {
    }

    public boolean isBalanced(String input) {
        MyLinkedListStack<String> parentheseStack = new MyLinkedListStack<String>();
        String[] stringSplit = input.split("");
        for (String s : stringSplit) {
            // 遇到开括号，就加入stack
            if ("[".equals(s) || "(".equals(s) || "{".equals(s)) {
                parentheseStack.push(s);
            }
            // 遇到闭括号，则从stack中取出，并与之比较是否成对
            else if ("]".equals(s) || ")".equals(s) || "}".equals(s)) {
                String open = parentheseStack.pop();
                if (!isConform(open, s)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isConform(String parOpen, String parClose) {
        return ("(".equals(parOpen) && ")".equals(parClose))
                || ("[".equals(parOpen) && "]".equals(parClose))
                || ("{".equals(parOpen) && "}".equals(parClose));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Parentheses par = new Parentheses();
        while (!"bye".equalsIgnoreCase(input)) {
            System.out.println(par.isBalanced(input) + " for input : " + input);
            input = in.nextLine();
        }
    }
}
