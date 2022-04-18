package AlgorithmPractice_Class.forMidTerm;

import java.util.Scanner;

public class P09 {
    static int n;
    static int[] array;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        array = new int[n+1];
        array[0] = 1;
        for(int i = 0; i < n; i++){
            array[i] = fill();
        }
    }
    static int fill(){
        int sum = 0;
        for(int i = 1; i < n; i++){
            sum += array[i] * array[n-i-1];
        }
        return sum;
    }

}
