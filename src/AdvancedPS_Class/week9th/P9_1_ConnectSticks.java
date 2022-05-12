package AdvancedPS_Class.week9th;

import java.util.Scanner;

public class P9_1_ConnectSticks {
    static ImplementingHeap heap;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        makeHeap(scanner);
        System.out.println(getMinimumCost());
    }
    static void makeHeap(Scanner scanner){
        heap = new ImplementingHeap(true);
        String[] input = scanner.next().split(",");

        for (String s : input) {
            heap.insertInHeap(new HeapElement(Integer.parseInt(s), true));
        }
    }
    static int getMinimumCost(){
        int sum = 0;
        while(heap.heapSize() != 2){
            int firstData = heap.poll().data;
            int secondData = heap.poll().data;
            sum += (firstData + secondData);
            heap.insertInHeap(new HeapElement(firstData + secondData,true));
        }
        return sum;
    }
}
