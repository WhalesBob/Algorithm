package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class Q1221 {
    static String[] output = {"ZRO","ONE","TWO","THR","FOR","FIV","SIX","SVN","EGT","NIN"};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            sc.next();
            int cases = sc.nextInt();
            int[] numbers = new int[cases];

            for(int i = 0; i < cases; i++){
                numbers[i] = convertToInt(sc.next());
            }

            Arrays.sort(numbers);

            System.out.printf("#%d\n", test_case);
            for(int i = 0; i < numbers.length; i++){
                System.out.printf("%s ",output[numbers[i]]);
            }
        }
    }
    static int convertToInt(String input){
        for(int i = 0; i < output.length; i++){
            if(input.equals(output[i])){
                return i;
            }
        }
        return -1;
    }
}
