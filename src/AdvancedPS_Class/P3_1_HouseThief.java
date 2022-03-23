package AdvancedPS_Class;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P3_1_HouseThief {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] nums = makeArray(input);
        System.out.println(dynamicProgramming(nums));
    }
    static int[] makeArray(String str){
        StringTokenizer token = new StringTokenizer(str," ");
        int[] nums = new int[token.countTokens()];
        int i = 0;
        while(token.hasMoreTokens()){
            nums[i++] = Integer.parseInt(token.nextToken());
        }

        return nums;
    }
    static int dynamicProgramming(int[] input){
        int[] optimal = new int[input.length];
        for(int i = 0; i < input.length; i++){
            if((i == 0) || (i == 1)){
                optimal[i] = input[i];
            }else{
                if(i == 2){
                    optimal[i] = optimal[i-2] + input[i];
                }else{
                    optimal[i] = Math.max(optimal[i-2],optimal[i-3]) + input[i];
                }
            }
        }
        Arrays.sort(optimal);
        return optimal[optimal.length-1];
    }
}
