package SWEA;

import java.util.Scanner;

public class Q1209 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 1;
        int length = 5;

        for(int test_case = 1; test_case <= T; test_case++){
            int[][] matrix = new int[length][length];
            sc.nextInt();

            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    matrix[i][j] = sc.nextInt();
                }
            }

            int maxHorizontal = compareAndWrite(matrix, true);
            int maxVertical = compareAndWrite(matrix, false);
            int maxOther = Math.max(otherSum(matrix, true), otherSum(matrix, false));

            int max = Math.max(maxOther, Math.max(maxHorizontal, maxVertical));

            System.out.printf("#%d %d\n", test_case, max);
        }
    }
    static int sumMatrix(int[][] matrix, boolean isHorizontal, int n){
        int s = 0;
        if(isHorizontal){
            for(int i = 0; i < matrix.length; i++){
                s += matrix[n][i];
            }
        }else{
            for(int i = 0; i < matrix.length; i++){
                s += matrix[i][n];
            }
        }
        return s;
    }
    static int compareAndWrite(int[][] matrix, boolean isHorizontal){
        int max = Integer.MIN_VALUE + 1;

        for(int i = 0; i < matrix.length; i++){
            int s = sumMatrix(matrix, isHorizontal, i);
            if(s > max){
                max = s;
            }
        }
        return max;
    }
    static int otherSum(int[][] matrix, boolean towardRight){
        int s = 0;
        if(towardRight){
            for(int i = 0; i < matrix.length; i++){
                s += matrix[i][i];
            }
        }else{
            for(int i = 0; i < matrix.length; i++){
                s += matrix[matrix.length - i - 1][i];
            }
        }
        return s;
    }
}

