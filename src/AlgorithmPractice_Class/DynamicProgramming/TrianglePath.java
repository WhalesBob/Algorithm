package AlgorithmPractice_Class.DynamicProgramming;

import java.util.Scanner;

public class TrianglePath {
    static TriangleInfo[][] pathArray;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] triangle = setTriangle(n,scanner);
        System.out.println(calculateMax(n,triangle));
        printPath(triangle);
    }
    static int[][] setTriangle(int size,Scanner scanner){
        int[][] triangle = new int[size][];
        for(int i = 0; i < size; i++){
            triangle[i] = new int[i+1];
            for(int j = 0; j <= i; j++){
                triangle[i][j] = scanner.nextInt();
            }
        }
        return triangle;
    }
    static int calculateMax(int size, int[][] triangle ){
        pathArray = new TriangleInfo[size][];
        pathArray[size-1] = new TriangleInfo[size];
        for(int i = 0; i < size; i++){
            pathArray[size-1][i] = new TriangleInfo(triangle[size-1][i], 'C');
        }

        for(int i = size - 2; i >= 0; i--){
            pathArray[i] = new TriangleInfo[i+1];
            for(int j = 0; j <= i; j++){
                pathArray[i][j] = sumAndDirection(triangle,i,j);
            }
        }
        return pathArray[0][0].data;
    }
    static TriangleInfo sumAndDirection(int[][] triangle, int row, int col){
        int left = pathArray[row+1][col].data, right = pathArray[row+1][col+1].data;
        int data = Math.max(left,right) + triangle[row][col];
        char direction = (left > right) ? 'L' : (left == right ? 'B' : 'R');
        return new TriangleInfo(data,direction);
    }
    static void printPath(int[][] triangle){
        int row = 0, col = 0;
        TriangleInfo path = pathArray[row][col];
        while(true){
            System.out.print(triangle[row][col]);
            if((path.direction == 'R') ||(path.direction == 'B')){
                System.out.print(" ");
                path = pathArray[++row][++col];
            }else if(path.direction == 'L'){
                System.out.print(" ");
                path = pathArray[++row][col];
            }else{
                break;
            }
        }

    }
}
class TriangleInfo{
    int data;
    char direction;

    public TriangleInfo(int data, char direction) {
        this.data = data;
        this.direction = direction;
    }
}