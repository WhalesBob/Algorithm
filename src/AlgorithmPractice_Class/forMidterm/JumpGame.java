package AlgorithmPractice_Class.forMidTerm;

import java.util.ArrayList;
import java.util.Scanner;

public class JumpGame {
    static int[][] inputArray;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        inputArray = getMatrix(scanner, row, col);
        ArrayList<Character> startPath = new ArrayList<>();

        getPath(startPath, 0,0);
    }

    static int[][] getMatrix(Scanner scanner, int row, int col){
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    static void getPath(ArrayList<Character> currentPath,int presentRow, int presentCol){

        int toGo = inputArray[presentRow][presentCol];
        if(toGo == 0){
            System.out.println("Yes");
            printPath(currentPath);
        }else if(presentRow == inputArray.length-1 && presentCol == inputArray[0].length-1){
            System.out.println("No");
        }else{
            if(presentCol + toGo < inputArray[0].length){
                ArrayList<Character> goRight = (ArrayList<Character>) currentPath.clone();
                goRight.add('R');
                getPath(goRight, presentRow, presentCol + toGo);
            }

            if(presentRow + toGo < inputArray.length){
                ArrayList<Character> goDown = (ArrayList<Character>) currentPath.clone();
                goDown.add('D');
                getPath(goDown, presentRow + toGo, presentCol);
            }
        }// 2분할
    }
    static void printPath(ArrayList<Character> path){
        for(int i = 0; i < path.size(); i++){
            System.out.printf("%c ", path.get(i));
        }
    }
}
