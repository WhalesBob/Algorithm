package SWEA;

import java.util.Scanner;

public class Q1234 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            StringBuilder builder = new StringBuilder(sc.next());

            int index = whereToStart(builder.toString());
            while(index != -1){
                builder.delete(index,index+2);
                index = whereToStart(builder.toString());
            }

            System.out.printf("#%d %s\n",test_case,builder);
        }
    }

    static int whereToStart(String input){
        int[] array = new int[input.length()];
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(input.substring(i,i+1));
        }

        for(int i = 0; i < array.length -1; i++){
            if(array[i] == array[i+1]){
                return i;
            }
        }
        return -1;
    }
}
