package SWEA;

import java.util.Scanner;

public class Q1220 {
    static int[][] table;
    static int count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 10;
        int length = 100;

        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            count = 0;
            table = new int[length][length];

            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    table[i][j] = sc.nextInt();
                }
            }
            for(int col = 0; col < length; col++){
                if(!onlyHaveOneOrNothing(col)) movingColumn(col);
            }
            System.out.printf("#%d %d\n", test_case, count);
        }
    }
    static boolean onlyHaveOneOrNothing(int col){
        boolean haveN = false;
        boolean haveS = false;

        for(int i = 0; (i < table.length) && !(haveN && haveS); i++){
            if(table[i][col] == 1) haveN = true;
            if(table[i][col] == 2) haveS = true;
        }
        return !(haveN && haveS);
    }
    static void movingColumn(int col){
        while(canMove(col)){
            for(int i = 0; i < table.length; i++){
                if(table[i][col] != 0){
                    if(goingToFall(table[i][col],i)){
                        table[i][col] = 0;
                    }else{
                        if(canMove(i,col)){
                            boolean isN = (table[i][col] == 1);
                            move(i,col, isN);
                            if(isN) i++;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < table.length - 1; i++){
            if(table[i][col] == 1 && table[i+1][col] == 2) count++;
        }
    }
    static void move(int row, int col, boolean isN){
        table[row][col] = 0;
        if(isN)table[row+1][col] = 1;
        else table[row-1][col] = 2;
    }
    static boolean canMove(int row,int col){
        // 움직일 수 있으면 true, 아니면 false

        try{
            if(table[row][col] == 1) return table[row+1][col] == 0;
            else if(table[row][col] == 2) return table[row-1][col] == 0;
            else return false;
        }catch (ArrayIndexOutOfBoundsException ex) {return true;}
    }
    static boolean canMove(int col){
        for(int i = 0; i < table.length; i++){
            if(canMove(i,col)) return true;
        }
        return false;
    }
    static boolean goingToFall(int data, int col){
        if(data != 0){
            if(data == 1 && col == table.length -1){
                return true;
            }else return data == 2 && col == 0;
        }
        return false;
    }
}