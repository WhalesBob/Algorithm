package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9251 {
    static int[][] path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String stringA = br.readLine();
        String stringB = br.readLine();

        int l = LCS(stringA, stringB);
        System.out.println(l);
        br.close();
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
}