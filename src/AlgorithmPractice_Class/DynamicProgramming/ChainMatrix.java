package AlgorithmPractice_Class.DynamicProgramming;

import java.util.Scanner;

public class ChainMatrix {
    static int[][] M;
    static int[][] P;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] d = inputArray(scanner, n);

        M = new int[n][n]; P = new int[n][n];
        fillOptimal(n,d);
        printArrays(M); printArrays(P);
        System.out.println(M[0][n-1]);
        print(1,n);
    }
    static int[] inputArray(Scanner scanner,int n){
        int[] d = new int[n+1];
        for(int i = 0; i <= n; i++){
            d[i] = scanner.nextInt();
        }
        return d;
    }
    static void fillOptimal(int size, int[] d){
        for(int going = 1; going < size; going++){
            for(int i = 0; i + going < size; i++){
                M[i][i+going] = fillOne(i,i+going,d);
            }
        }
    }
    static int fillOne(int row, int col, int[] d){
        int min = Integer.MAX_VALUE;
        for(int k = 0; k < col-row; k++){
            int value = M[row][row+k] + M[row+k+1][col] + d[row] * d[row+k+1] * d[col+1];
            if(value < min){
                min = value;
                P[row][col] = row + k + 1;
            }
        }
        return min;
    }
    static void printArrays(int[][] arrays){
        for(int i = 0; i < arrays.length; i++){
            for(int j = i; j < arrays.length; j++){
                System.out.print(arrays[i][j]);
                if(j < arrays.length - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    static void print(int row, int col){

        System.out.print("(");
        if(P[row-1][col-1] == 0){
            System.out.printf("A%d",row);
        }else{
            print(row,P[row-1][col-1]);
            print(P[row-1][col-1] + 1, col);
        }
        System.out.print(")");
    }
}
