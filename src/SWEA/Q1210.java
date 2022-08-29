package SWEA;

import java.util.Scanner;

public class Q1210 {
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

            for(int i = 0; i < length; i++){
                if(matrix[0][i].n == 1 && move(Node.matrixClone(matrix),0,i, test_case, i)){
                    break;
                }
            }
        }
    }
    static boolean move(Node[][] matrix, int y, int x, int count, int startX){
        boolean isValid = isValid(matrix, x, y);
        boolean haveLeft = isValid(matrix, x-1, y) && (matrix[y][x-1].n == 1);
        boolean haveRight = isValid(matrix, x+1, y) && (matrix[y][x+1].n == 1);

        if(isValid){
            matrix[y][x].isVisited = true;

            if(matrix[y][x].n == 2){
                System.out.printf("#%d %d\n", count, startX);
                return true;
            }else if(haveLeft && !matrix[y][x-1].isVisited){
                return move(matrix, y, x-1, count, startX);
            }else if(haveRight && !matrix[y][x+1].isVisited){
                return move(matrix,y,x+1, count, startX);
            }else{
                return move(matrix, y+1,x,count, startX);
            }
        }
        return false;
    }
    static boolean isValid(Node[][] matrix, int x, int y){
        return ((x >= 0) && (y >= 0)) && (x < matrix.length && y < matrix.length);
    }
}
class Node{
    int n;
    boolean isVisited;

    public Node(int n) {
        this.n = n;
        this.isVisited = false;
    }

    static Node[][] matrixClone(Node[][] matrix){
        Node[][] node = new Node[matrix.length][matrix.length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                node[i][j] = new Node(matrix[i][j].n);
            }
        }
        return node;
    }
}