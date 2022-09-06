package SWEA;

import java.util.Scanner;

public class Q1226 {
    static int[][] matrix;
    static Direction[] directions;
    static boolean[][] alreadyVisit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        int length = 100;
        directions = Direction.setDirections();
        Direction start = new Direction(0,0);

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            matrix = new int[length][length];
            alreadyVisit = new boolean[length][length];
            for(int i = 0; i < length; i++){
                String input = sc.next();
                for(int j = 0; j < length; j++){
                    matrix[i][j] = Integer.parseInt(Character.toString(input.charAt(j)));
                    if(matrix[i][j] == 2){
                        start.goX = j;
                        start.goY = i;
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, tour(start.goX, start.goY) ? 1 : 0);
        }
    }
    static boolean tour(int x, int y){

        for(int i = 0; i < 4; i++){
            Direction d = directions[i];
            int n = matrix[y + d.goY][x + d.goX];
            boolean visited = alreadyVisit[y + d.goY][x + d.goX];
            if(n != 1 && !visited){
                if(n == 3){
                    return true;
                }else{
                    alreadyVisit[y][x] = true;
                    boolean result = tour(x + d.goX, y+d.goY);
                    if(result){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
class Direction{
    int goX;
    int goY;

    public Direction(int x, int y) {
        this.goX = x;
        this.goY = y;
    }

    static Direction[] setDirections(){
        Direction[] directions = new Direction[4];

        directions[0] = new Direction(0,-1); // N
        directions[1] = new Direction(1,0); // E
        directions[2] = new Direction(0,1); // S
        directions[3] = new Direction(-1,0); // W

        return directions;
    }
}
