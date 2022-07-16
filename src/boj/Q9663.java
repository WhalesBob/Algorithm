package boj;

import java.util.Scanner;

public class Q9663 {
    static int[] col;
    static int count; //
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        col = new int[n+1]; // col 에서 각 애들의 배치를 넣어준다.
        for(int i = 0; i < col.length; i++){
            col[i] = (-2) * i; // 그냥 이렇게 세팅해버렸나보다.
        }

        queens(1); // 1행(맨윗줄) 시작.
        System.out.println(count); // 다 잘 배치되었을때의 count++ 해준거.

    }
    static void queens(int n){
        if(n == col.length){ // 끝까지 내려갔을 때
            count++; // 성공했으니, count++
        }else {
            for (int j = 1; j < col.length; j++) { // 당연히 정사각형이니까, 1부터 col.length 까지
                col[n] = j; // col[n] 에 j 넣어주기.
                if(promising(n)) { // promising 조건에 되면 재귀, 아니면 pruning.
                    queens(n + 1); // 다음 행으로 넘어가서 하기.
                }
            }
        }
    }
    static boolean promising(int n){
        boolean output = true; // output 을 true 로 초기화
        for(int k = 1; (k < n && output); k++ ){ // k = 1부터 n까지 도는데, 와중에 output 이 한번이라도 false 되면 나가리.
            if((col[n] == col[k]) || ((Math.abs(col[n] - col[k]) == n-k))){ // n이랑 k랑 같은 열에 있거나, 빼기 한게 같으면(대각선이면)
                output = false; // output false 되면 끝
            }
        }
        return output; // 1행부터 n 행까지, 한번이라도 겹치는 자리가 있으면 false. 한번도 안걸리면 true
    }
}
