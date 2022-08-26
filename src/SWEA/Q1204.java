package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class Q1204 {
    static int maxValue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            int max = 0;
            sc.nextInt();
            int[] numbers = new int[1000];
            for(int j = 0; j < 1000; j++){
                numbers[j] = sc.nextInt();
            }

            Arrays.sort(numbers);

            int value = -1; int count = 0;

            for(int index = 0; index < 1000; index++){
                if(numbers[index] != value){
                    if(count >= max){
                        maxValue = value;
                        max = count;
                    }
                    value = numbers[index];
                    count = 1;
                }else{
                    count++;
                }
            }

            System.out.printf("#%d %d\n", test_case, maxValue);
        }
    }
}