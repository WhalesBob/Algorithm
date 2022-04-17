package AlgorithmPractice_Class.DynamicProgramming;

import java.util.Scanner;

public class OptimalBST {
    static int[][] R;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] keys = inputValues(scanner,n);
        int[] p = inputValues(scanner,n);
        R = new int[n+1][n+1];
        int[][] A = fillArrays(p,n);
        printArray(A); printArray(R);
        System.out.println(A[0][n]);
        Tree head = makeTree(1,n,keys);
        head.preorder(n);
        head.inorder(n);
    }
    static int[] inputValues(Scanner scanner, int size){
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = scanner.nextInt();
        }
        return array;
    }
    static int[][] fillArrays(int[] keys, int size){
        int[][] arrays = new int[size+1][size+1];
        for(int i = 0; i < size; i++){
            arrays[i][i+1] = keys[i];
            R[i][i+1] = i+1;
        }
        for(int going = 2; going <= size; going++){
            for(int i = 0; i + going <= size; i++){
                arrays[i][i+going] = fillOne(arrays,keys,i,i+going);
            }
        }
        return arrays;
    }
    static int fillOne(int[][] arrays, int[] keys,int row, int col){
        int min = Integer.MAX_VALUE;
        for(int k = row+1; k <= col; k++){
            int value = arrays[row][k-1] + arrays[k][col] + sum(keys,row,col);
            if(value < min){
                min = value;
                R[row][col] = k;
            }
        }
        return min;
    }
    static int sum(int[] keys,int a, int b){
        int s = 0;
        for(int i = a; i < b; i++){
            s += keys[i];
        }
        return s;
    }
    static void printArray(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = i; j < array.length; j++){
                System.out.print(array[i][j]);
                if(j < array.length-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    static Tree makeTree(int row, int col, int[] keys){
        int k = R[row-1][col];
        if(k == 0){
            return null;
        }else{
            Tree t = new Tree(keys[k-1]);
            t.leftChild = makeTree(row, k-1, keys);
            t.rightChild = makeTree(k+1, col, keys);
            return t;
        }
    }
}
class Tree{
    int data;
    Tree leftChild;
    Tree rightChild;
    static int count = 0;

    public Tree(int data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
    void inorder(int size){
        if(this.leftChild != null) {
            this.leftChild.inorder(size);
        }
        System.out.print(this.data);
        if(count++ <  size-1){
            System.out.print(" ");
        }else{
            System.out.println();
        }
        if(this.rightChild != null){
            this.rightChild.inorder(size);
        }
    }
    void preorder(int size){
        System.out.print(this.data);
        if(count++ < size-1){
            System.out.print(" ");
        }else{
            System.out.println();
            count = 0;
        }
        if(this.leftChild != null){
            this.leftChild.preorder(size);
        }
        if(this.rightChild != null){
            this.rightChild.preorder(size);
        }
    }
}
