package AdvancedPS_Class.week6th;

import java.util.Scanner;

public class P6_0_InputHandle {
    static int numOfNode;
    int[][] inputEdges(Scanner scanner){
        String[] input = scanner.nextLine().split(" ");
        numOfNode =
                Integer.parseInt(deleteFrontAndBack(input[2],false,true));

        return inputHandling(input[5]);
    }
    String deleteFrontAndBack(String input,boolean haveFront, boolean haveBack){
        StringBuilder builder = new StringBuilder(input);
        if(haveFront){
            builder.delete(0,1);
        }
        if(haveBack){
            builder.delete(builder.length()-1, builder.length());
        }
        return builder.toString();
    }
    int[][] inputHandling(String input){
        String[] handling =
                deleteFrontAndBack(input,true,true).split("],");
        for(int i = 0; i < handling.length-1; i++){
            handling[i] = deleteFrontAndBack(handling[i],true,false);
        }
        handling[handling.length-1] = deleteFrontAndBack(handling[handling.length-1], true,true);

        int[][] output = new int[handling.length][2];
        for(int i = 0; i < handling.length; i++){
            String[] handle = handling[i].split(",");
            for(int j = 0; j < 2; j++){
                output[i][j] = Integer.parseInt(handle[j]);
            }
        }

        return output;
    }
}









