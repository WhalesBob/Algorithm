package AdvancedPS_Class.week7th;

import java.util.ArrayList;
import java.util.Scanner;

public class P7_2_PrintPath {
    static int INF;
    static ArrayList<String> history;
    public static void main(String[] args) {
        INF = 999; history = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("adj_list = ");
        int[][] graph = makeGraphArray(scanner);
        arrive(0,new StringBuilder(),graph,graph.length-1);

        for(int i = 0; i < history.size(); i++){
            System.out.println(history.get(i));
        }
    }
    static int[][] makeGraphArray(Scanner scanner){
        ArrayList<Integer[]> list = new ArrayList<>();
        P7_0_InputProcess input = new P7_0_InputProcess();
        String[] array = input.deleteBrackets(scanner.next(),true,true).split("],");

        for(int i = 0; i < array.length - 1; i++){
            Integer[] intArray = convertToIntArray(array[i]);
            for(int j = 0; j < intArray.length; j++){
                Integer[] toAdd = new Integer[2];
                toAdd[0] = i; toAdd[1] = intArray[j];
                list.add(toAdd);
            }
        }
        int[][] graphArray = new int[array.length][array.length];
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                graphArray[i][j] = (i == j) ? 0 : INF;
            }
        }
        for (Integer[] fromList : list) {
            graphArray[fromList[0]][fromList[1]] = 1;
        }
        return graphArray;
    }
    static Integer[] convertToIntArray(String str){
        P7_0_InputProcess input = new P7_0_InputProcess();
        String[] convert = input.deleteBrackets(str,true,false).split(",");
        Integer[] output = new Integer[convert.length];
        for(int i = 0; i < output.length; i++){
            output[i] = Integer.parseInt(convert[i]);
        }

        return output;
    }
    static void arrive(int address,StringBuilder builder, int[][] array, int destination){
        String toAppend = String.format("%d ",address);
        builder.append(toAppend);
        if(address == destination){
            history.add(builder.toString());
            return;
        }
        for(int i = 0; i < array.length; i++){
            if(array[address][i] == 1){
                arrive(i,new StringBuilder(builder.toString()),array,destination);
            }
        }
    }
}
