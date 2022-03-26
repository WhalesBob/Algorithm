package AdvancedPS_Class;

import java.util.Scanner;

public class P3_3_Levenshtein {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.next();
        String input2 = scanner.next();
        System.out.printf("레벤슈타인 거리는 %d",levenshteinDistance(input1,input2));
    }
    static int levenshteinDistance(String input1, String input2){
        int[][] distanceArray = new int[input1.length()+1][input2.length()+1];
        for(int i = 0; i <= input1.length(); i++){
            distanceArray[i][0] = i;
        }
        for(int i = 0; i <= input2.length(); i++){
            distanceArray[0][i] = i;
        }
        for(int i = 1; i <= input1.length(); i++){
            for(int j = 1; j <= input2.length(); j++){
                if(input1.charAt(i-1) == input2.charAt(j-1)){
                    distanceArray[i][j] = distanceArray[i-1][j-1];
                }else{
                    distanceArray[i][j] =
                            Math.min(Math.min(distanceArray[i-1][j-1],distanceArray[i][j-1]),distanceArray[i-1][j]) + 1;
                }
            }
        }
        return distanceArray[input1.length()][input2.length()];
    }
}
