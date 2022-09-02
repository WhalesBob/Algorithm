package SWEA;

import java.util.Scanner;
import java.util.Stack;

public class Q1222 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            int length = sc.nextInt();
            String input = sc.next();
            System.out.printf("#%d %d\n", test_case, calculator(input,length));
        }
    }
    static int calculator(String input, int length){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < length; i++){
            char c = input.charAt(i);
            if(isNumber(c)){
                stack.push(Integer.parseInt(Character.toString(c)));
            }else{
                int a = stack.pop();
                int b = Integer.parseInt(Character.toString(input.charAt(++i)));
                stack.push(a+b);
            }
        }
        return stack.pop();
    }
    static boolean isNumber(char c){
        return c != '+';
    }
}
