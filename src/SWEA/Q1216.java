package SWEA;

import java.util.Scanner;

public class Q1216 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        int length = 100;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            char[][] matrix = new char[length][length];

            for(int i = 0; i < length; i++){
                String input = sc.next();
                for(int j = 0; j < length; j++){
                    matrix[i][j] = input.charAt(j);
                }
            }

            int horizontalMax = getMaximum(matrix, 0, true);
            int verticalMax = getMaximum(matrix, horizontalMax, false);

            System.out.printf("#%d %d\n", test_case, Math.max(horizontalMax,verticalMax));
        }
    }
    static int maxPalindrome(char[][] matrix,int start,boolean isHorizontal){
        int max = 0;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < matrix.length; i++){
            if(isHorizontal){
                builder.append(matrix[start][i]);
            }else{
                builder.append(matrix[i][start]);
            }
        }

        String str = builder.toString();

        for(int length = 0; length < str.length(); length++){
            for(int index = 0; index < str.length() - length; index++){
                String compare = str.substring(index, index+length+1);
                if(isPalindrome(compare) && max < compare.length()){
                    max = compare.length();
                }
            }
        }
        return max;
    }
    static boolean isPalindrome(String str){
        String reverse = new StringBuilder(str).reverse().toString();
        return str.equals(reverse);
    }
    static int getMaximum(char[][] matrix, int max, boolean isHorizontal){
        for(int i = 0; i < matrix.length; i++){
            int strLength = maxPalindrome(matrix,i,isHorizontal);
            if(max < strLength){
                max = strLength;
            }
        }
        return max;
    }
}
