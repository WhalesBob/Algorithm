package AdvancedPS_Class.week2nd;

import java.util.Scanner;

public class P2_3_PascalTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        System.out.println(pascalNumber(i,j));
    }
    static int pascalNumber(int i, int j){
        if((j == 0) || (i == j)){
            return 1;
        }else{
            return (pascalNumber(i-1,j-1)+pascalNumber(i-1,j));
        }
    }
}
