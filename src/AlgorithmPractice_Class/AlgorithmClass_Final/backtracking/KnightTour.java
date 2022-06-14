package AlgorithmPractice_Class.AlgorithmClass_Final.backtracking;

import java.util.Scanner;

public class KnightTour {
    static int circuitCount;
    static int[][] move;
    static int row;
    static int col;
    public static void main(String[] args) { // 주의(완성되지 않음)
        Scanner scanner = new Scanner(System.in);
        row = scanner.nextInt();
        col = scanner.nextInt();

        boolean[][] table = new boolean[row][col];
        setMove();

        tour(0,0,table);
        System.out.println(circuitCount);

        table[0][0] = false;
        int repeat = scanner.nextInt();
        for(int i = 0; i < repeat; i++){
            circuitCount = 0;
            int y = scanner.nextInt();
            int x = scanner.nextInt();
            pathTour(x,y,table);
            System.out.println(circuitCount);
        }
    }
    static void setMove(){
        move = new int[8][2]; // 0 -> x/ 1 -> y
        move[0][0] = -2; move[0][1] = -1;
        move[1][0] = -1; move[1][1] = -2;
        move[2][0] = 1; move[2][1] = -2;
        move[3][0] = 2; move[3][1] = -1;
        move[4][0] = 2; move[4][1] = 1;
        move[5][0] = 1; move[5][1] = 2;
        move[6][0] = -1; move[6][1] = 2;
        move[7][0] = -2; move[7][1] = 1;
    }
    static boolean promising(int currentX, int currentY, boolean[][] table){
        return isOnTable(currentX, currentY) && !table[currentY][currentX];
    }

    static void tour(int currentX, int currentY, boolean[][] table){
        if(isAllVisited(table)){
            if(currentX == 0 && currentY == 0){
                circuitCount++;
            }
        }else{
            if(promising(currentX,currentY,table)){
                table[currentY][currentX] = true;
                for(int moving = 0; moving < 8; moving++){
                    tour(currentX + move[moving][0], currentY + move[moving][1], clone(table));
                }
            }
        }
    }
    static void pathTour(int currentX, int currentY, boolean[][] table){
        if(promising(currentX,currentY,table)){
            table[currentY][currentX] = true;
            if(isAllVisited(table)){
                circuitCount++;
            }else{
                for(int moving = 0; moving < 8; moving++){
                    pathTour(currentX + move[moving][0], currentY + move[moving][1], clone(table));
                }
            }
        }
    }
    static boolean isOnTable(int currentX, int currentY){
        boolean onTableX = (currentX >= 0) && (currentX < col);
        boolean onTableY = (currentY >= 0) && (currentY< row );
        return (onTableX && onTableY);
    }
    static boolean isAllVisited(boolean[][] table){
        for (boolean[] booleans : table) {
            for (int j = 0; j < table[0].length; j++) {
                if (!booleans[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean[][] clone(boolean[][] table){
        boolean[][] newTable = new boolean[table.length][table[0].length];
        for(int i = 0; i < newTable.length; i++){
            System.arraycopy(table[i], 0, newTable[i], 0, newTable[0].length);
        }
        return newTable;
    }
}