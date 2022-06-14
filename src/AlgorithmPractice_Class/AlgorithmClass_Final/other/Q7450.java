package AlgorithmPractice_Class.AlgorithmClass_Final.other;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Q7450 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfItems = readInteger(br);
        int maxLength = readInteger(br);

        Integer[] items = new Integer[numberOfItems];
        for(int i = 0; i < numberOfItems; i++){
            items[i] = readInteger(br);
        }

        Arrays.sort(items,Collections.reverseOrder());

        int front = 0, end = items.length-1;
        int count = 0;

        while(front <= end){
            if(items[front] + items[end] <= maxLength){
                end--;
            }
            front++;
            count++;
        }
        System.out.println(count);
    }
    static int readInteger(BufferedReader bufferedReader) throws IOException {
        String number = bufferedReader.readLine();
        return Integer.parseInt(number);
    }
}
