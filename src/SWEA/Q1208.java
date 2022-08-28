package SWEA;

import java.util.Scanner;

public class Q1208 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 1; int vert = 100;

        for(int test_case = 1; test_case <= T; test_case++){
            int dump = sc.nextInt();
            int[] boxes = new int[vert];

            for(int i = 0; i < vert; i++) {
                boxes[i] = sc.nextInt();
            }

            for(int i = 0; (i < dump); i++){
                int maxIndex = maxIndex(boxes);
                int minIndex = minIndex(boxes);

                boxes[maxIndex]--;
                boxes[minIndex]++;

                if(boxes[maxIndex] - boxes[minIndex] == 1){
                    break;
                }
            }

            System.out.printf("#%d %d\n", test_case, boxes[maxIndex(boxes)] - boxes[minIndex(boxes)]);
        }
    }
    static int maxIndex(int[] boxes){
        int max = 0; int index = -1;

        for(int i = 0; i < boxes.length; i++){
            if(boxes[i] > max){
                max = boxes[i];
                index = i;
            }
        }
        return index;
    }
    static int minIndex(int[] boxes){
        int min = 1001; int index = -1;

        for(int i = 0; i < boxes.length; i++){
            if(boxes[i] < min){
                min = boxes[i];
                index = i;
            }
        }
        return index;
    }
}
