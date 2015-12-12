package practice.mathematics;

import java.util.Stack;

public class InToPostfixEval {

    public static int inToPostfixEval(String input) {
        String postfix = InToPostfix.inToPostfix(input);
        return evalPostfix(postfix);
    }
    
    public static int evalPostfix(String input) {
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            switch (curr) {
                case '+':
                    int result = stack.pop() + stack.pop();
                    stack.push(result);
                    break;
                case '-':
                    int s2 = stack.pop();
                    int s1 = stack.pop();
                    stack.push(s1 - s2);
                    break;
                case '*':
                    result = stack.pop() * stack.pop();
                    stack.push(result);
                    break;
                case '/':
                    int d2 = stack.pop();
                    int d1 = stack.pop();
                    stack.push(d1 / d2);
                    break;
                default:
                    stack.push(new Integer(curr - '0'));
                    break;
            }
        }
        
        return stack.pop();
    }
    
    public static void main(String[] args) {
        String input = "(1+2)*(3+4)";
        System.out.println(inToPostfixEval(input));
    }
}
