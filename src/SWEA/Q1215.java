package SWEA;

import java.util.Scanner;

public class Q1215 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 10;
        int length = 8;

        for(int test_case = 1; test_case <= T; test_case++){

            int palindromeLength = sc.nextInt();
            int count = 0;

            char[][] matrix = new char[length][length];
            for(int i = 0; i < length; i++){
                String input = sc.next();
                for(int j = 0; j < input.length(); j++){
                    matrix[i][j] = input.charAt(j);
                }
            }

            for(int i = 0; i < length; i++){
                for(int j = 0; j <= length - palindromeLength; j++){
                    if(palindrome(matrix, i, j, palindromeLength, true)){
                        count++;
                    }
                }
            }
            for(int i = 0; i <= length - palindromeLength; i++){
                for(int j = 0; j < length; j++){
                    if(palindrome(matrix,i,j,palindromeLength,false)){
                        count++;
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, count);
        }
    }
    static boolean palindrome(char[][] matrix, int startY, int startX, int length, boolean isHorizontal){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            if(isHorizontal){
                builder.append(matrix[startY][startX + i]);
            }else{
                builder.append(matrix[startY + i][startX]);
            }
        }

        String reverse = builder.reverse().toString();
        return reverse.equals(builder.reverse().toString());
    }
}
