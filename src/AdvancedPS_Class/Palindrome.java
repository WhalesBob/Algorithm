package AdvancedPS_Class;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //long x = scanner.nextLong();
        //System.out.println(is_Palindrome_Number(x));
        String s = scanner.nextLine();
        System.out.println(is_Palindrome(s));
    }
    static boolean is_Palindrome_Number(long x){
        String reverseStr = new StringBuilder(Long.toString(x)).reverse().toString();

        return reverseStr.equals(Long.toString(x));
    }
    static boolean is_Palindrome(String s){

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            boolean isAlphabet =
                    (s.charAt(i) >= 65 && s.charAt(i) < 91) || (s.charAt(i) >= 97  && s.charAt(i) < 123);
            if(isAlphabet){
                builder.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        String reverse = new StringBuilder(builder.toString()).reverse().toString();
        return builder.toString().equals(reverse);
    }
}
