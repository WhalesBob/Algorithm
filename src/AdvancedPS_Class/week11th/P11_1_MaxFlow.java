package AdvancedPS_Class.week11th;

import java.util.Scanner;

public class P11_1_MaxFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] capacityGraph = makeGraph(scanner);
    }


    static int[][] makeGraph(Scanner scanner){
        System.out.println("graph = ");
        StringBuilder builder = new StringBuilder(scanner.nextLine());
        builder.delete(0,1); builder.delete(builder.length()-1, builder.length());
        String[] stringArray = builder.toString().split("],");
        int[][] resultArray = new int[stringArray.length][stringArray.length];
        for(int i = 0; i < stringArray.length; i++){
            if(i < stringArray.length-1){
                resultArray[i] = oneArray(stringArray[i],false);
            }else{
                resultArray[i] = oneArray(stringArray[i],true);
            }
        }
        return resultArray;
    }
    static int[] oneArray(String array, boolean isEnd){
        StringBuilder builder = new StringBuilder(array);
        if(isEnd){
            builder.delete(builder.length()-1,builder.length());
        }
        builder.delete(0,1);

        String[] realArray = builder.toString().split(",");
        int[] result = new int[realArray.length];
        for(int i = 0; i < realArray.length; i++){
            result[i] = Integer.parseInt(realArray[i]);
        }
        return result;
    }
}
