package AdvancedPS_Class;

import java.util.Arrays;
import java.util.Scanner;

public class P2_4_PascalTriangle2 {

    static int[][] triangle;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        makeTriangle(n);
        System.out.println(Arrays.toString(pascalRow(n)));
    }
    static int pascalNumber(int i, int j){
        if(triangle[i][j] != 0){
            // 바로 return 으로 넘어가면 되어서 비워놓았습니다.
        }else if((j == 0) || (i == j)){
            triangle[i][j] = 1;
        }else{
            triangle[i][j] = pascalNumber(i-1,j-1)+pascalNumber(i-1,j);
        }
        return triangle[i][j];
    }
    static int[] pascalRow(int n){
        int[] row = new int[n+1];
        for(int i = 0; i <= n; i++){
            row[i] = pascalNumber(n,i);
        }
        return row;
    }
    static void makeTriangle(int n){
        triangle = new int[n+1][];
        for(int i = 0; i <= n ; i++){
            triangle[i] = new int[i+1];
        }
    }
}
