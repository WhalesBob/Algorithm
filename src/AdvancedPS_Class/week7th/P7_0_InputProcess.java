package AdvancedPS_Class.week7th;

public class P7_0_InputProcess {

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
}
