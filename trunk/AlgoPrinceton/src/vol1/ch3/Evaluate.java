package vol1.ch3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <b>Dijkstra’s Two-Stack Algorithm for Expression Evaluation</b><br/>
 * 
 * 这个程序用来这是一个<u>fully parenthesized</u>的表达式，其定义如下：<br/>
 * 表达式是一个数字，或者是左括号、表达式、运算符、表达式再加右括号。<br/>
 * 
 * 这个程序仅是运用stack的一个演示程序，所以简化如下： 仅支持+、-、*、/、sqrt、(、)这些符号，和数字，而且它们中间用空格分隔开，如：<br/>
 * 
 * <pre>
 * (1 + (2 * 3.5))
 * </pre>
 * 
 * 此算法的原理是这样的 <br/>
 * 从左到右读取表达式，<br/>
 * - 如果遇到左括号则忽略，遇到操作数(operand)，则把操作数存在操作数stack中，<br/>
 * - 如果遇到运算符(operator)，则把运算符储存到运算符stack中， <br/>
 * - 如果遇到右括号，则从运算符stack中取出一个运算符，再看这个运算符有几个操作数，就从操作数stack中取出几个操作数，
 * 然后把运算结果再保存到操作数的stack中。<br/>
 * 
 * 
 * @author libojuve
 * 
 */
public class Evaluate {
	public static void main(String[] args) {
		DequeStack<Double> operandStack = new DequeStack<Double>();
		DequeStack<Operator> operatorStack = new DequeStack<Operator>();
		Scanner scanner = new Scanner(System.in);
		// lire une expression, on suppose que son format est bon.
		System.out.println("Enter an expression: ");
		String expression = scanner.nextLine();

		// Test avec ( 5 + ( ( 6 + ( ( 8 * 3 ) + 10 ) ) * 9 ) ) = 365 !!!

		String[] expressionPart = expression.split("\\s+");
		for (String part : expressionPart) {
			// On néglige parenthèse ouvrante "("
			if (!"(".equals(part)) {
				// Addition
				if ("+".equals(part)) {
					operatorStack.push(Operator.ADD);
				}
				// Soustraction
				else if ("-".equals(part)) {
					operatorStack.push(Operator.SUBTRACT);
				}
				// Multiplication
				else if ("*".equals(part)) {
					operatorStack.push(Operator.MULTIFY);
				}
				// Division
				else if ("/".equals(part)) {
					operatorStack.push(Operator.DIVIDE);
				}
				// Racine carrée
				else if ("sqrt".equals(part)) {
					operatorStack.push(Operator.SQUARE_ROOT);
				}
				// Parenthèse fermante ")"
				else if (")".equals(part)) {
					// Retirer opérateur, et nb requis d'opérandes
					Operator op = operatorStack.pop();
					double[] operandArray = new double[op.getNbOperands()];
					for (int i = 0; i < operandArray.length; ++i) {
						operandArray[i] = operandStack.pop();
					}
					operandStack.push(op.getResult(operandArray));
				} else {
					operandStack.push(Double.valueOf(part));
				}
			}
		}
		System.out.println(expression + " = " + operandStack.pop());
	}

	public static void parseExpression(String expression) {

	}
}


/**
 * 表示运算符的enum
 * 
 * @author libojuve
 * 
 */
enum Operator {
	//@formatter:off
	/**
	 * 加, addition
	 */
	ADD("+", 2, new Calculator() {
		@Override
		public double getResult(double[] operandArray) {
			return operandArray[0] + operandArray[1];
		}
	}),
	/**
	 * 减, soustraction
	 */
	SUBTRACT("-", 2, new Calculator() {
		@Override
		public double getResult(double[] operandArray) {
			return operandArray[0] - operandArray[1];
		}
		
	}),
	/**
	 * 乘, multiplication
	 */
	MULTIFY("*", 2, new Calculator() {
		@Override
		public double getResult(double[] operandArray) {
			return operandArray[0] * operandArray[1];
		}
	}),
	/**
	 * 除, division
	 */
	DIVIDE("/", 2, new Calculator() {
		@Override
		public double getResult(double[] operandArray) {
			return operandArray[0] / operandArray[1];
		}
	}),
	/**
	 * 平方根
	 * Racine carrée
	 */
	SQUARE_ROOT("sqrt", 1, new Calculator() {
		@Override
		public double getResult(double[] operandArray) {
			return Math.sqrt(operandArray[0]);
		}
	})
	;
	//@formatter:on

	interface Calculator {
		double getResult(double[] operandArray);
	}


	/**
	 * 操作数的个数<br/>
	 * Nombre d'opérands requis
	 */
	private int nbOperands;
	/**
	 * 操作符符号，如+、-等
	 */
	private String operator;

	private Calculator calc;

	private static Map<String, Operator> mapOperator = initOperatorMapping();


	private static Map<String, Operator> initOperatorMapping() {
		Operator[] operatorArray = values();
		Map<String, Operator> mapOperator = new HashMap<String, Operator>(
				operatorArray.length);
		for (Operator op : operatorArray) {
			mapOperator.put(op.operator, op);
		}
		return mapOperator;
	}

	private Operator(String operator, int nbOperands, Calculator calc) {
		this.operator = operator;
		this.nbOperands = nbOperands;
		this.calc = calc;
	}

	public int getNbOperands() {
		return nbOperands;
	}

	public String getOperator() {
		return operator;
	}

	public double getResult(double[] operands) {
		return calc.getResult(operands);
	}
}


class DequeStack<E> {
	private Deque<E> deque;


	public DequeStack() {
		deque = new ArrayDeque<E>();
	}

	public void push(E elt) {
		deque.offerLast(elt);
	}

	public E pop() {
		return deque.pollLast();
	}
}