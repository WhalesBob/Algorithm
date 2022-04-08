package AdvancedPS_Class.week4th;

import java.util.Scanner;

public class P4_2_PaintFence {
    static int[] fence;
    public static void main(String[] args) {
        // 변형 피보나치!
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        fence = new int[n];
        System.out.println(paint(n,k));
    }
    static int paint(int n, int k){
        for(int i = 0; i < n; i++) {
            if (i == 0) {
                fence[i] = k;
            } else if (i == 1) {
                fence[i] = k * k;
            } else {
                fence[i] = (k - 1) * (fence[i - 2] + fence[i - 1]);
            }
        }
        return fence[n-1];
    }
}
