package AdvancedPS_Class.week8th;

import java.util.ArrayList;

public class P8_0_InputProcess {
    String deleteBrackets(String str,boolean deleteFront, boolean deleteBack){
        StringBuilder builder = new StringBuilder(str);
        if(deleteFront){
            builder.delete(0,1);
        }
        if(deleteBack){
            builder.delete(builder.length()-1,builder.length());
        }

        return builder.toString();
    }
    ArrayList<Integer[]> getEdges(String input, int size){
        P8_0_InputProcess process = new P8_0_InputProcess();
        ArrayList<Integer[]> list = new ArrayList<>();

        String[] process1 = process.deleteBrackets(input,true,true).split("],");
        for(int i = 0; i < process1.length; i++){
            boolean isLast = ( i == process1.length-1);
            String[] process2 = process.deleteBrackets(process1[i],true,isLast).split(",");
            Integer[] toAdd = new Integer[3];
            for(int j = 0; j < 3; j++){
                toAdd[j] = Integer.parseInt(process2[j]);
            }
            list.add(toAdd);
        }
        return list;
    }
}
