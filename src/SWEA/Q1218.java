package SWEA;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Q1218 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            int length = sc.nextInt();
            String input = sc.next();

            boolean result = true;
            Stack<Character> stack = new Stack<>();

            try{
                for(int i = 0; i < length; i++){
                    if(isEnd(input.charAt(i))){
                        Character c = stack.pop();
                        if(!isMatch(c,input.charAt(i))){
                            result = false;
                            break;
                        }
                    }else{
                        stack.push(input.charAt(i));
                    }
                }
            }catch(EmptyStackException exception){
                result = false;
            }

            result = stack.empty() && result;
            System.out.printf("#%d %d\n", test_case, result ? 1 : 0);
        }
    }
    static boolean isEnd(Character c){
        return c.equals('>') || c.equals(')') || c.equals(']') || c.equals('}');
    }
    static boolean isMatch(Character before, Character after){
        return (before.equals('(') && after.equals(')')) ||
                (before.equals('<') && after.equals('>')) ||
                (before.equals('[') && after.equals(']')) ||
                (before.equals('{') && after.equals('}'));
    }
}
