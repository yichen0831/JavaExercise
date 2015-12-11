package practice.mathematics;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InToPostfix {

	public static final Map<Character, Integer> priorityMap = new HashMap<Character, Integer>() {
		private static final long serialVersionUID = 1L;

		{
			put('(', 9);
			put(')', 9);
			put('*', 8);
			put('/', 8);
			put('+', 7);
			put('-', 7);
		}
	};

	private InToPostfix() {
	}

	public static String inToPostfix(String input) {
		StringBuffer stringBuffer = new StringBuffer();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			switch (cur) {
				case '(':
					stack.push(cur);
					break;
				case ')':
					while (!stack.isEmpty() && stack.peek() != '(') {
						stringBuffer.append(stack.pop());
					}
					if (!stack.isEmpty()) {
						stack.pop();
					}
					break;
				case '*':
				case '/':
				case '+':
				case '-':
					while (!stack.isEmpty() && priorityMap.get(stack.peek()) >= priorityMap.get(cur)) {
						if (stack.peek() == '(') {
							break;
						} else {
							stringBuffer.append(stack.pop());
						}
					}
					stack.push(cur);
					break;
				default:
					stringBuffer.append(cur);
					break;
			}
		}

		while (!stack.isEmpty()) {
			stringBuffer.append(stack.pop());
		}

		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		String input = "a+b*d+c/d";
		String output = inToPostfix(input);
		System.out.println("input:  " + input);
		System.out.println("output: " + output);
		
		String input1 = "(a+b)*(c+d)";
		String output1 = inToPostfix(input1);
		System.out.println("input:  " + input1);
		System.out.println("output: " + output1);
	}
}
