package SWEA;

import java.util.Scanner;
import java.util.Stack;

public class Q1224 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            String input = sc.next();
            System.out.printf("#%d %d\n",test_case,calculator(input));
        }
    }
    static int calculator(String input){
        String postOrder = postOrder(input);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < postOrder.length(); i++){
            char c = postOrder.charAt(i);
            if (whatIsThis(c) == 0) {
                stack.push(Integer.parseInt(Character.toString(c)));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                if (c == '+') {
                    stack.push(a + b);
                } else {
                    stack.push(a * b);
                }
            }
        }
        return stack.pop();
    }
    static String postOrder(String input){
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            switch(whatIsThis(c)){
                case 0:
                    builder.append(c);
                    break;
                case 1:
                    while(!stack.isEmpty() && (stack.peek() == '+' || stack.peek() == '*')){
                        builder.append(stack.pop());
                    }
                    stack.push(c);
                    break;
                case 2:
                    while(!stack.isEmpty() && stack.peek() == '*'){
                        builder.append(stack.pop());
                    }
                    stack.push(c);
                    break;
                case 3:
                    stack.push(c);
                    break;
                case 4:
                    while(true){
                        if(stack.peek() != '('){
                            builder.append(stack.pop());
                        }else{
                            stack.pop();
                            break;
                        }
                    }

            }
        }
        while(!stack.isEmpty()){
            builder.append(stack.pop());
        }
        return builder.toString();
    }
    static int whatIsThis(char c){
        char[] matrix = {'/','+','*','(',')'};
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i] == c){
                return i;
            }
        }
        return 0;
    }
}