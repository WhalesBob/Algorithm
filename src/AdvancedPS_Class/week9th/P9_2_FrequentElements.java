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
        for(int i = 0; i < list.size(); i++){
            HeapElement element = new HeapElement(list.get(i).data,true);
            element.compareData = list.get(i).frequency;
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
        for(int i = 0; i < input.length; i++){
            int value = Integer.parseInt(input[i]);
            if(pairs.isEmpty()){
                pairs.add(new Pair(value));
            }else{
                Pair item = containedItem(value,pairs);
                if(item != null){
                    item.frequency++;
                }else{
                    pairs.add(new Pair(value));
                }
            }
        }
        return pairs;
    }
    static Pair containedItem(int value, ArrayList<Pair> pairs){
        for(int i = 0; i < pairs.size(); i++){
            if(value == pairs.get(i).data){
                return pairs.get(i);
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
