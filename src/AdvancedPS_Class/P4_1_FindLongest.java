package AdvancedPS_Class;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P4_1_FindLongest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputArray = inputArray(scanner);
        System.out.println(findLongest(inputArray));
    }
    static int[] inputArray(Scanner scanner){
        String str = scanner.nextLine();
        StringTokenizer token = new StringTokenizer(str, " ");
        int[] array = new int[token.countTokens()];
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(token.nextToken());
        }
        return array;
    }
    static int findLongest(int[] array){
        // 0 1 0 1 0 1 >> 그 전 것보다 크지 않으면 그냥 둔다? 이거는 말 되네.
        int[] find = new int[array.length];
        find[0] = 1;
        for(int i = 1; i < array.length; i++){
            find[i] = findInfo(array,i,find);
        }
        Arrays.sort(find);
        return find[find.length - 1];
    }
    static int findInfo(int[] array, int index, int[] find){
        int length = 1;
        for(int i = 0; i < index; i++){
            if(array[index] > array[i]){
                length = Math.max(length,find[i] + 1);
            }
        }
        return length;
    }
}

