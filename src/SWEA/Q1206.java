package SWEA;

import java.util.Scanner;

public class Q1206 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 10;

        for(int test_case = 0; test_case < T; test_case++){
            int cases = sc.nextInt();
            int[] buildings = new int[cases];
            for(int i = 0; i < cases; i++){
                buildings[i] = sc.nextInt();
            }

            int sum = 0;

            for(int i = 2; i < cases-2; i++){
                int h = maxHeight(buildings,i-2,i+2);
                if(buildings[i] > h){
                    sum += (buildings[i] - h);
                }
            }

            System.out.printf("#%d %d\n",test_case, sum);
        }
    }
    static int maxHeight(int[] buildings, int start, int end){
        int before = Math.max(buildings[start], buildings[start+1]);
        int after = Math.max(buildings[end-1], buildings[end]);
        return Math.max(before,after);
    }
}
