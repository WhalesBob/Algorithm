package AdvancedPS_Class;

import java.lang.reflect.Array;
import java.util.*;

public class P2_7_Permutations {
    static ArrayList<Integer[]> permutationArray;
    public static void main(String[] args) {
        permutationArray = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<Integer> numberArray = convertToArray(input);
        Collections.sort(numberArray);
        Integer[] plusArray = new Integer[numberArray.size()];

        makePermutation(plusArray,numberArray,numberArray.size(),0);
        for(int i = 0; i < permutationArray.size(); i++){
            System.out.println(Arrays.toString(permutationArray.get(i)));
        }
    }

    static ArrayList<Integer> convertToArray(String str) {
        StringTokenizer token = new StringTokenizer(str, " ");
        int count = token.countTokens();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            array.add(Integer.parseInt(token.nextToken()));
        }
        return array;
    }
    static void makePermutation(Integer[] plusArray, ArrayList<Integer> array, int howManyLeft,int index){

        if(howManyLeft == 0){
            permutationArray.add(plusArray);
        }else{
            for(int i = 0; i < howManyLeft; i++){
                ArrayList<Integer> newList = (ArrayList<Integer>)array.clone();
                Integer[] subArray = plusArray.clone();
                subArray[index] = newList.remove(i);
                makePermutation(subArray,newList,(howManyLeft-1),(index+1));
            }
        }
    }
}