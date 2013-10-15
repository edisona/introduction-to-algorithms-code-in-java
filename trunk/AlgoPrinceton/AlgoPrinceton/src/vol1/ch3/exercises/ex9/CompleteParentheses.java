package vol1.ch3.exercises.ex9;

import java.util.Scanner;

import vol1.ch3.linked_list.MyLinkedListStack;

import com.libojuve.utils.StringUtils;

/**
 * <b>1.3.9</b> Write a program that takes from standard input an expression
 * without left parentheses and prints the equivalent infix expression with the
 * parentheses inserted. For example, given the input:</br>
 * <code>1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) <code><br/>
 * your program should print<br/>
 * <code>( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )</code>
 * 
 * 每个数字、括号和运算符号之间都有空格隔开，方便程序分析输入的表达式
 * 
 * @author libojuve
 * 
 */
public class CompleteParentheses {
    public static void main(String[] args) {
        MyLinkedListStack<String> stack = new MyLinkedListStack<String>();

        System.out.println("请输入一个只有右括号的表达式：");
        Scanner in = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String input = in.next();
            // 如果输入end，则输入结束
            if ("end".equalsIgnoreCase(input)) {
                end = true;
            }
            // 如果输入的是)，则取出至少两个元素，并且根据第二个元素（运算符）来决定是否取出更多的元素，
            // 然后把第三个元素、第二个元素（运算符）以及第一个元素用括号括起来
            else if (")".equals(input)) {
                String operand2 = StringUtils.getStringNonNull(stack.pop());
                String operator = StringUtils.getStringNonNull(stack.pop());
                String operand1 = StringUtils.getStringNonNull(stack.pop());
                String term = ("( " + getTerm(operand1) + getTerm(operator)
                        + getTerm(operand2) + ")");
                stack.push(term);
            } else {
                stack.push(input);
            }
        }
        System.out.println("带括号的表达式为：" + stack.peek());
    }

    /**
     * 如果op为空，则返回空；否则，返回op + " "。 注意，假设op不为null。
     * 
     * @param op
     * @return
     */
    public static String getTerm(String op) {
        return op.isEmpty() ? "" : (op + " ");
    }
}
