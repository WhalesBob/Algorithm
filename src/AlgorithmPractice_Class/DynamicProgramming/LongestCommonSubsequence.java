package AlgorithmPractice_Class.DynamicProgramming;

import java.util.Scanner;

public class LongestCommonSubsequence {
    static int[][] path;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringA = scanner.next();
        String stringB = scanner.next();

        int l = LCS(stringA, stringB);
        System.out.println(l);
        if(l == 0){
            return;
        }
        System.out.println(getLCS(stringB.length(),stringA.length(),stringA));
    }
    static int LCS(String A, String B){
        int[][] array = new int[B.length()+1][A.length()+1];
        path = new int[B.length()+1][A.length()+1];

        for(int i = 1; i <= B.length(); i++){
            for(int j = 1; j <= A.length(); j++){
                if(B.charAt(i-1) == A.charAt(j-1)){
                    array[i][j] = array[i-1][j-1] + 1;
                    path[i][j] = 1;
                }else{
                    array[i][j] = Math.max(array[i][j-1],array[i-1][j]);
                    path[i][j] = (array[i][j-1] > array[i-1][j]) ? 2 : 3;
                }
            }
        }
        return array[B.length()][A.length()];
    }
    static String getLCS(int i, int j, String str){
        if ((i == 0) || (j == 0)) {
            return "";
        }else{
            if(path[i][j] == 1){
                return getLCS(i-1,j-1,str) + str.charAt(j-1);
            }else if(path[i][j] == 2){
                return getLCS(i,j-1,str);
            }else{
                return getLCS(i-1,j,str);
            }
        }
    }
}
