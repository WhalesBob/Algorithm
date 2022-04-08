package AdvancedPS_Class.week2nd;

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
        for (Integer[] integers : permutationArray) {
            System.out.println(Arrays.toString(integers));
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
    static void makePermutation(Integer[] beingMadeArray, ArrayList<Integer> intArray, int leftElement, int index){
        // 맨 처음에 부를 때는 beingMadeArray 를 main 에서 빈 Integer 배열을 주고, 계속 재귀함수를 부르며 채워가는 식입니다.
        if(leftElement == 0){ // beingMadeArray 다 채웠을 때
            if(!includeInList(beingMadeArray)){ // permutationArray에 안 포함되어 있으면 추가! 포함되있으면 겹치는 것이므로 X
                permutationArray.add(beingMadeArray);
            }
        }else{
            for(int i = 0; i < leftElement; i++){ // leftElement는 결국 재귀함수가 불림에 따라서 계속 줄어듭니다.
                ArrayList<Integer> newList = (ArrayList<Integer>)intArray.clone(); // 혹시나 intArray가 영향을 받으면 안되니 clone 해놓고
                // 걔에서 줄입니다. 결국 for 문을 돌면서 intArray 가 직접적인 영향을 안 받게 했습니다.
                Integer[] subArray = beingMadeArray.clone();  // subArray 도 clone 처리해서 for 문 돌때마다 하나씩 생성되게 했습니다.
                subArray[index] = newList.remove(i); // newList 에서 하나 빼서 주기.
                makePermutation(subArray,newList,(leftElement-1),(index+1)); // 결국 clone 되어 있는 애가 다 들어가면서 재귀함수를 돌립니다.
                // leftElement 도 재귀를 돌면서 하나씩 줄어들고, 결국에는 0이 되도록 하며, index 도 계속 다음 index를 받도록 해야 합니다.
            }
        }
    }
    static boolean includeInList(Integer[] array){
        for (Integer[] integers : permutationArray) {
            if (Arrays.equals(integers, array)) {
                return true;
            }
        }
        return false;
    }
}