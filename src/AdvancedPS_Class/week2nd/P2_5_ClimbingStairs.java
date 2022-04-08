package AdvancedPS_Class.week2nd;

import java.util.Scanner;

public class P2_5_ClimbingStairs {
    static int[] cache;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        cache = new int[n]; // 45개 까지 커버 가능해야 하므로, 전역변수 로 두고 배열을 만들어 저장하는 식으로 했습니다!
        System.out.println(climbingStairs(n));
    }
    static int climbingStairs(int n){
        // 10개의 계단 오르는 경우의 수 : (어떻게든 9개 오르고 한칸 오르기) + (어떻게든 8개 오르고 두칸 오르기) -> 피보나치 수열!
        if(cache[n-1] != 0){
            // 공란으로 비웠습니다.
        }
        else if(n < 3){
            cache[n-1] = n;
        }else{
            cache[n-1] = climbingStairs(n-1) + climbingStairs(n-2);
        }
        return cache[n-1];
    }
}
