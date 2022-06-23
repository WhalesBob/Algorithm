package AdvancedPS_Class.week10th;

import java.util.HashSet;
import java.util.Scanner;

public class P10_3_HappyNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(isHappyNumber(n));
    }
    static boolean isHappyNumber(int value){
        HashSet<Integer> hashSet = new HashSet<>();
        while(true){
            int newNumber = numberProcess(value);
            if(newNumber == 1){
                return true;
            }else{
                if(hashSet.contains(newNumber)){
                    return false;
                }else{
                    hashSet.add(newNumber);
                    value = newNumber;
                }
            }
        }
    }
    static int numberProcess(int n){
        char[] charArray = Integer.toString(n).toCharArray();
        int sum = 0;
        for(int i = 0; i < charArray.length; i++){
            sum += Math.pow(Integer.parseInt(Character.toString(charArray[i])),2);
        }
        return sum;
    }
}
