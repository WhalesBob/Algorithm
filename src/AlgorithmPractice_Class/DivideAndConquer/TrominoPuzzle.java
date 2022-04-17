package AlgorithmPractice_Class.DivideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TrominoPuzzle {
    static int nth;
    public static void main(String[] args) {
        nth = 1;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        int[][] totalArray = make2DArray(n);
        totalArray[row][col] = 0;
        trominoPuzzle(totalArray,row,col,totalArray.length);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.printf("%d",totalArray[i][j]);
                if(j < n-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    static int[][] make2DArray(int n){
        int[][] array = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                array[i][j] = -1;
            }
        }

        return array;
    }
    static void trominoPuzzle(int[][] array, int zeroRow, int zeroCol, int size){
        if(size < 2){
            return;
        }

        int zeroBelong = whereToBelong(array,zeroRow,zeroCol);
        filling(array,zeroBelong);
        int[][][] partition = partition(array,size);
        for(int i = 0; i < 4; i++){
            if(i == zeroBelong){
                switch(i){
                    case 0:
                        break;
                    case 1:
                        zeroCol -= (size/2);
                        break;
                    case 2:
                        zeroRow -= (size/2);
                        break;
                    case 3:
                        zeroRow -= (size/2);
                        zeroCol -= (size/2);
                        break;
                }
                trominoPuzzle(partition[i],zeroRow,zeroCol,size/2);
            }else{
                switch(i){
                    case 0:
                        trominoPuzzle(partition[0],(size/2)-1,(size/2)-1,size/2);
                        break;
                    case 1:
                        trominoPuzzle(partition[1],(size/2)-1,0,size/2);
                        break;
                    case 2:
                        trominoPuzzle(partition[2],0,(size/2)-1,size/2);
                        break;
                    case 3:
                        trominoPuzzle(partition[3],0,0,size/2);
                        break;
                }
            }
        }
        if(size > 2){
            copy(array,partition[0],0,0,size/2,size/2);
            copy(array,partition[1],0,size/2,size/2,size);
            copy(array,partition[2],size/2,0,size,size/2);
            copy(array,partition[3],size/2,size/2,size,size);
        }
    }
    static int whereToBelong(int[][] array, int row, int col){ // (0 : (1,1)) / (1 : (1,2)) / (2 : (2,1))/ (3 : (2,2))
        int belong = 0;
        if(row >= (array.length/2)){
            belong += 2;
        }
        if(col >= (array.length/2)){
            belong ++;
        }

        return belong;
    }
    static int[][][] partition(int[][] matrix, int size){
        int[][][] partitionByFour = new int[4][size/2][size/2];
        partitioning(matrix,partitionByFour[0],0,0,size/2,size/2);
        partitioning(matrix,partitionByFour[1],0,size/2,size/2,size);
        partitioning(matrix,partitionByFour[2],size/2,0,size,size/2);
        partitioning(matrix,partitionByFour[3],size/2,size/2,size,size);

        return partitionByFour;
    }
    static void partitioning(int[][] original,int [][] matrix, int rowStart, int colStart, int rowEnd, int colEnd){
        for(int i = 0; i < (rowEnd-rowStart); i++){
            if (colEnd - colStart >= 0)
                System.arraycopy(original[i + rowStart], colStart, matrix[i], 0, colEnd - colStart);
        }
    }
    static void filling(int[][] matrix, int zeroBelong){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3));
        list.remove(zeroBelong);
        int size = matrix.length;
        for(Integer n : list){
            switch(n){
                case 0:
                    matrix[(size/2)-1][(size/2)-1] = nth;
                    break;
                case 1:
                    matrix[(size/2)-1][(size/2)] = nth;
                    break;
                case 2:
                    matrix[(size/2)][(size/2)-1] = nth;
                    break;
                case 3:
                    matrix[(size/2)][(size/2)] = nth;
                    break;
            }
        }
        nth++;
    }
    static void copy(int[][] bigMatrix, int[][] matrix, int rowStart, int colStart, int rowEnd, int colEnd){
        for(int i = 0; i < (rowEnd-rowStart); i++){
            if (colEnd - colStart >= 0)
                System.arraycopy(matrix[i], 0, bigMatrix[i + rowStart], colStart, colEnd - colStart);
        }
    }
}