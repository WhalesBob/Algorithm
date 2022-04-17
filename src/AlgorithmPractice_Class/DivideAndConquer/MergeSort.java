package AlgorithmPractice_Class.DivideAndConquer;

import java.util.Scanner;

public class MergeSort {

    static int count;
    static int[] intArray;

    public static void main(String[] args) {
        count = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        intArray = new int[n];
        for(int i = 0; i < n; i++){
            intArray[i] = scanner.nextInt();
        }
        mergeSort(0,n-1);
        System.out.println(count);
        for(int i = 0; i < n; i++){
            System.out.print(intArray[i]);
            if(i < n-1){
                System.out.print(" ");
            }
        }

    }
    static void mergeSort(int low, int high){
        if(low < high){
            int mid = (low + high) / 2;
            mergeSort(low,mid);
            mergeSort(mid+1, high);
            merge2(low,mid,high);
        }
    }
    static void merge2(int low, int mid, int high){
        count++;

        int[] tempArray = new int[high-low + 1];
        int front = low, back = mid + 1, index = 0;
        while(front <= mid && back <= high){
            if(intArray[front] < intArray[back]){
                tempArray[index++] = intArray[front++];
            }else{
                tempArray[index++] = intArray[back++];
            }
        }
        while(front <= mid){
            tempArray[index++] = intArray[front++];
        }
        while(back <= high){
            tempArray[index++] = intArray[back++];
        }

        index = 0;
        int copy = low;
        for(int i = 0; i < high-low+1; i++){
            intArray[copy++] = tempArray[index++];
        }
    }
}