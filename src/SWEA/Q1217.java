package SWEA;

import java.util.Scanner;

public class Q1217 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            int n = sc.nextInt();
            int r = sc.nextInt();
            System.out.printf("#%d %d\n",test_case,pow(n,r));
        }
    }
    static int pow(int n, int r){
        return (r == 1) ? n : n * pow(n,r-1);
    }
}
