package SWEA;

import java.util.Scanner;

public class Q1211 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 10;
        int length = 100;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            Node[][] matrix = new Node[length][length];
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    matrix[i][j] = new Node(sc.nextInt());
                }
            }
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int i = 0; i < length; i++){
                if(matrix[0][i].n == 1){
                    int n = move(Node.matrixClone(matrix),0,i);
                    if(n < min){
                        min = n;
                        minIndex = i;
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, minIndex);
        }
    }
    static int move(Node[][] matrix, int y, int x){
        boolean isValid = isValid(matrix, x, y);
        boolean haveLeft = isValid(matrix, x-1, y) && (matrix[y][x-1].n == 1);
        boolean haveRight = isValid(matrix, x+1, y) && (matrix[y][x+1].n == 1);

        if(isValid){
            matrix[y][x].isVisited = true;
            if(y == 99){
                return 1;
            }else if(haveLeft && !matrix[y][x-1].isVisited){
                return move(matrix, y, x-1) + 1;
            }else if(haveRight && !matrix[y][x+1].isVisited){
                return move(matrix,y,x+1) + 1;
            }else{
                return move(matrix, y+1,x) + 1;
            }
        }
        return 0;
    }
    static boolean isValid(Node[][] matrix, int x, int y){
        return ((x >= 0) && (y >= 0)) && (x < matrix.length && y < matrix.length);
    }
}