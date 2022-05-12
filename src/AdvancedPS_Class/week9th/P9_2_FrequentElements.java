package AdvancedPS_Class.week9th;

import java.util.ArrayList;
import java.util.Scanner;

public class P9_2_FrequentElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pair> list = getPair(scanner);
        System.out.print("k = ");
        int k = scanner.nextInt();

        ImplementingHeap heap = new ImplementingHeap(false);
        for (Pair pair : list) {
            HeapElement element = new HeapElement(pair.data, true);
            element.compareData = pair.frequency;
            heap.insertInHeap(element);
        }

        for(int i = 0; i < k; i++){
            System.out.printf("%d ",heap.poll().data);
        }
    }
    static ArrayList<Pair> getPair(Scanner scanner){
        ArrayList<Pair> pairs = new ArrayList<>();
        System.out.print("nums = ");
        String[] input = scanner.next().split(",");
        for (String s : input) {
            int value = Integer.parseInt(s);
            if (pairs.isEmpty()) {
                pairs.add(new Pair(value));
            } else {
                Pair item = containedItem(value, pairs);
                if (item != null) {
                    item.frequency++;
                } else {
                    pairs.add(new Pair(value));
                }
            }
        }
        return pairs;
    }
    static Pair containedItem(int value, ArrayList<Pair> pairs){
        for (Pair pair : pairs) {
            if (value == pair.data) {
                return pair;
            }
        }
        return null;
    }
}
class Pair{
    int data;
    int frequency;

    public Pair(int data) {
        this.data = data;
        this.frequency = 1;
    }
}
