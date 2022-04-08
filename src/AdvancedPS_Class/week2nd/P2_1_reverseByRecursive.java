package AdvancedPS_Class.week2nd;

import java.util.Scanner;

public class P2_1_reverseByRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        P2_1_reverseByRecursive forRecursive = new P2_1_reverseByRecursive();
        // static 으로 하면 문제가 생길 여지가 있을 것 같아 새로 객체선언 했습니다.
        String inputString = scanner.nextLine(); // 값 입력
        System.out.println(forRecursive.printReverse(inputString)); // 출력
    }
    String printReverse(String str){
        if(str.equals("")){
            return str;
        }else{
            return printReverse(str.substring(1)) + str.charAt(0);
        }
    }
}
