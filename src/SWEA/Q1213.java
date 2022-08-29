package SWEA;

import java.util.Scanner;

public class Q1213 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            String search = sc.next();
            String sentence = sc.next();

            int count = 0;
            for(int i = 0; i <= sentence.length() - search.length(); i++){
                if(search.compareTo(sentence.substring(i,i+search.length())) == 0){
                    count++;
                }
            }
            System.out.printf("#%d %d\n", test_case, count);
        }
    }
}
