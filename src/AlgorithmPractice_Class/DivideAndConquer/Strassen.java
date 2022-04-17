package AlgorithmPractice_Class.DivideAndConquer;

import java.util.Scanner;

public class Strassen {
    static int count;
    public static void main(String[] args) {
        count = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int threshold = scanner.nextInt();
        int[][] A = makeMatrix(scanner, n);
        int[][] B = makeMatrix(scanner, n);

        int[][] C = strassen(threshold,A,B);

        System.out.println(count);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(C[i][j]);
                if(j < n-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
    static int[][] makeMatrix(Scanner scanner, int n){
        int powerOfTwo = (int)Math.pow(2,Math.ceil((Math.log(n) / Math.log(2))));
        int[][] matrix = new int[powerOfTwo][powerOfTwo];
        for(int i = 0; i < powerOfTwo; i++){
            for(int j = 0; j < powerOfTwo; j++){
                if((i < n) && (j < n)){
                    matrix[i][j] = scanner.nextInt();
                }else{
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }
    static int[][] strassen(int threshold, int[][] A, int[][] B){
        count++;

        int size = A.length;
        int[][] C = new int[size][size];
        if(size <= threshold){
            C = traditionMultiply(A,B);
        }else{
            int[][][] pA = partition(A,size); // pA = partitionA
            int[][][] pB = partition(B,size); // pB = partitionB
            int [][][] M = new int[7][][];
            //(0 : 1,1) / (1 : 1,2) / (2 : 2,1) / (3 : 2,2)

            M[0] = strassen(threshold,add(pA[0],pA[3]),add(pB[0],pB[3]));
            M[1] = strassen(threshold,add(pA[2],pA[3]),pB[0]);
            M[2] = strassen(threshold,pA[0],subtract(pB[1],pB[3]));
            M[3] = strassen(threshold,pA[3],subtract(pB[2],pB[0]));
            M[4] = strassen(threshold,add(pA[0],pA[1]),pB[3]);
            M[5] = strassen(threshold,subtract(pA[2],pA[0]),add(pB[0],pB[1]));
            M[6] = strassen(threshold,subtract(pA[1],pA[3]),add(pB[2],pB[3]));

            combine(C,size,M);
        }
        return C;
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
    static void combine(int[][] bigMatrix,int size, int[][][] M){
        int[][][] subMatrix = new int[4][][];
        subMatrix[0] = add(subtract(add(M[0],M[3]),M[4]),M[6]);
        subMatrix[1] = add(M[2],M[4]);
        subMatrix[2] = add(M[1],M[3]);
        subMatrix[3] = add(subtract(add(M[0],M[2]),M[1]),M[5]);

        copy(bigMatrix,subMatrix[0],0,0,size/2,size/2);
        copy(bigMatrix,subMatrix[1],0,size/2,size/2,size);
        copy(bigMatrix,subMatrix[2],size/2,0,size,size/2);
        copy(bigMatrix,subMatrix[3],size/2,size/2,size,size);
    }
    static void copy(int[][] bigMatrix, int[][] matrix, int rowStart, int colStart, int rowEnd, int colEnd){
        for(int i = 0; i < (rowEnd-rowStart); i++){
            if (colEnd - colStart >= 0)
                System.arraycopy(matrix[i], 0, bigMatrix[i + rowStart], colStart, colEnd - colStart);
        }
    }
    static int[][] traditionMultiply(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
    static int[][] add(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }
    static int[][] subtract(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
}