package SWEA;

import java.util.LinkedList;
import java.util.Scanner;

public class Q1225 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        int numbers = 8;
        Integer n;

        for(int test_case = 1; test_case <= T; test_case++){
            Queue<Integer> queue = new Queue();
            sc.nextInt();

            for(int i = 0; i < numbers; i++){
                queue.enqueue(sc.nextInt());
            }

            int index = 0;
            do{
                n = queue.dequeue() - (index++ % 5 + 1);
                n = Math.max(n, 0);
                queue.enqueue(n);
            }while(n != 0);

            System.out.printf("#%d ",test_case);
            for(int i = 0; i < 8; i++){
                System.out.printf("%d ",queue.dequeue());
            }
            System.out.println("");
        }
    }
}