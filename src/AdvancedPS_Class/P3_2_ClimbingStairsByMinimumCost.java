package AdvancedPS_Class;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P3_2_ClimbingStairsByMinimumCost {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] numArray = makeArray(input);
        System.out.println(dynamicProgramming(numArray));
    }
    static int[] makeArray(String str){
        StringTokenizer token = new StringTokenizer(str," ");
        int[] nums = new int[token.countTokens()+1]; int i = 0;
        while(token.hasMoreTokens()){
            nums[i++] = Integer.parseInt(token.nextToken());
        }
        nums[i] = 0;
        return nums;
    }
    static int dynamicProgramming(int[] input){
        int[] optimal = new int[input.length];
        for(int i = 0; i < optimal.length; i++){
            if((i == 0) || (i == 1)){
                optimal[i] = input[i];
            }else{
                optimal[i] = Math.min(optimal[i-1], optimal[i-2]) + input[i];
            }
        }
        return optimal[optimal.length-1];
    }
}
