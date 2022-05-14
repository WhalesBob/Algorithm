package AlgorithmPractice_Class.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KnapSack01 {
    static int W;
    static int n;
    static int maxProfit;
    static ArrayList<Item2> bestList;
    static double previousBound;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        W = scanner.nextInt();
        Item2[] items = getItems(scanner,n);

        Arrays.sort(items);
        knapSack(0,0,0,items,new ArrayList<>());
        System.out.println(maxProfit);
        for(int i = 0; i < bestList.size(); i++){
            System.out.printf("%d %d",bestList.get(i).weight, bestList.get(i).profit);
            if(i < bestList.size()-1){
                System.out.println();
            }
        }
    }
    static Item2[] getItems(Scanner scanner, int size){
        Item2[] items = new Item2[size+1];
        int[] weights = new int[size+1];
        items[0] = new Item2(1,1000);
        for(int i = 1; i <= size; i++){
            weights[i] = scanner.nextInt();
        }
        for(int i = 1; i <= size; i++){
            items[i] = new Item2(weights[i], scanner.nextInt());
        }
        return items;
    }
    static void knapSack(int i, int profit, int weight, Item2[] items, ArrayList<Item2> list){
        System.out.printf("%d %d %d ",i,weight,profit);
        if(weight <= W && profit > maxProfit){
            maxProfit = profit;
            bestList = list;
        }
        if(promising(i,profit,weight,items)){
            ArrayList<Item2> newList = new ArrayList<>(list);
            newList.add(items[i+1]);

            knapSack(i+1,profit + items[i+1].profit,weight+items[i+1].weight,items,newList);
            knapSack(i+1,profit,weight,items,list);
        }
    }
    static boolean promising(int i, int profit, int weight, Item2[] items){
        double bound = previousBound;
        if(weight < W){
            int plusIndex = i+1;
            bound = profit;
            int totalWeight = weight;
            while(plusIndex <= n && totalWeight + items[plusIndex].weight <= W){
                totalWeight += items[plusIndex].weight;
                bound += items[plusIndex].profit;
                plusIndex++;
            }
            if(plusIndex <= n){
                bound += ((W-totalWeight) * items[plusIndex].profitPerWeight);
            }
        }
        System.out.printf("%d %d\n",(int)bound,maxProfit);
        previousBound = bound;
        return weight < W && (bound > maxProfit);
    }
}
class Item2 implements Comparable<Item2>{
    int weight;
    int profit;
    double profitPerWeight;

    public Item2(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.profitPerWeight = (double)profit / (double)weight;
    }

    @Override
    public int compareTo(Item2 o) {
        return (this.profitPerWeight < o.profitPerWeight) ? 1 : -1;
    }
}
