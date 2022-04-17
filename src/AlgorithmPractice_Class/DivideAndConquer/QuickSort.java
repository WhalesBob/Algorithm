package AlgorithmPractice_Class.DivideAndConquer;

import java.util.Scanner;

public class QuickSort {

    static int count;
    static int[] intArray;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        intArray = new int[n];
        for(int i = 0; i < n; i++){
            intArray[i] = scanner.nextInt();
        }
        quickSort(0,n-1);
        System.out.println(count);
        printArray(0,n-1);
    }
    static void quickSort(int low, int high){
        int pivotPoint = low;
        if(low < high){
            pivotPoint = partition(low,high);
            quickSort(low,pivotPoint - 1);
            quickSort(pivotPoint + 1, high);
        }
    }
    static int partition(int low,int high){
        count++;

        int j = low, pivotItem = intArray[low];
        for(int i = low + 1; i <= high; i++){
            if(intArray[i] < pivotItem){
                j++;
                swap(i,j);
            }
        }
        int pivotPoint = j;
        swap(low,pivotPoint);
        if(count == 1){
            printArray(low,high);
        }
        return pivotPoint;
    }
    static void swap(int x, int y){
        int temp = intArray[x];
        intArray[x] = intArray[y];
        intArray[y] = temp;
    }
    static void printArray(int low, int high){
        for(int i = low; i <= high; i++){
            System.out.print(intArray[i]);
            if(i < high){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
