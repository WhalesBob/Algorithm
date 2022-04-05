package AdvancedPS_Class;

import java.util.Scanner;

public class P4_3_findPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] path = new int[m][n];
        System.out.println(pathFind(path));
    }
    static int pathFind(int[][] path){
        path[0][0] = 1;
        int m = path.length, n = path[0].length;
        for(int i = 1; i < m; i++){
            path[i][0] = 1;
        }
        for(int i = 1; i < n; i++){
            path[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                path[i][j] = path[i-1][j] + path[i][j-1];
            }
        }
        return path[m-1][n-1];
    }
}