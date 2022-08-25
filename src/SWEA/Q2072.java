package SWEA;

import java.util.Scanner;

public class Q2072 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = 0; j < 10; j++){
                int element = scanner.nextInt();
                if(element % 2 == 1){
                    sum += element;
                }
            }
            System.out.printf("#%d %d\n", i+1,sum);
        }
    }
}

