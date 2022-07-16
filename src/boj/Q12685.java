package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Q12685 {

    static int maxProfit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int W = Integer.parseInt(str[1]);

        BnBItem[] items = getItems(br, n);
        Arrays.sort(items);

        knapSack(n,W,items);
        System.out.print(maxProfit);
    }
    static BnBItem[] getItems(BufferedReader br, int size) throws IOException {
        BnBItem[] items = new BnBItem[size+1];
        items[0] = new BnBItem(1,1000);

        for(int i = 1; i <= size; i++){
            String[] str = br.readLine().split(" ");
            items[i] = new BnBItem(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }
        return items;
    }
    static int bound(BnBNode node, BnBItem[] items, int n, int W){
        int totalWeight, result;
        if(node.weight >= W){
            return 0;
        }else{
            result = node.profit;
            int j = node.level + 1;
            totalWeight = node.weight;
            while(j <= n && totalWeight + items[j].weight <= W){
                totalWeight += items[j].weight;
                result += items[j].profit;
                j++;
            }
            if(j <= n){
                result += (W-totalWeight) * (items[j].profitPerWeight);
            }
            return result;
        }
    }
    static void knapSack(int n, int W, BnBItem[] items){
        PriorityQueue<BnBNode> priorityQueue = new PriorityQueue<>();
        BnBNode nodeV = new BnBNode(0,0,0);

        maxProfit = 0;
        nodeV.bound = bound(nodeV,items,n,W);
        priorityQueue.add(nodeV);
        while(!priorityQueue.isEmpty()){
            nodeV = priorityQueue.poll();

            if(nodeV.bound > maxProfit){
                BnBNode leftChild = new BnBNode(0,0,nodeV.level + 1);
                leftChild.weight = nodeV.weight + items[leftChild.level].weight;
                leftChild.profit = nodeV.profit + items[leftChild.level].profit;
                leftChild.bound = bound(leftChild,items,n,W);
                if(leftChild.weight <= W && leftChild.profit > maxProfit){
                    maxProfit = leftChild.profit;
                }
                if(leftChild.bound > maxProfit){
                    priorityQueue.add(leftChild);
                }

                BnBNode rightChild = new BnBNode(0,0,nodeV.level + 1);
                rightChild.weight = nodeV.weight;
                rightChild.profit = nodeV.profit;
                rightChild.bound  = bound(rightChild,items,n,W);
                if(rightChild.bound > maxProfit){
                    priorityQueue.add(rightChild);
                }
            }
        }
    }
}
class BnBItem implements Comparable<BnBItem> {
    int weight;
    int profit;
    double profitPerWeight;

    public BnBItem(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.profitPerWeight = (double)profit / (double)weight;
    }

    @Override
    public int compareTo(BnBItem o) {
        return (this.profitPerWeight < o.profitPerWeight) ? 1 : -1;
    }
}
class BnBNode implements Comparable<BnBNode>{
    int weight;
    int profit;
    int level;
    int bound;

    public BnBNode(int weight, int profit, int level) {
        this.weight = weight;
        this.profit = profit;
        this.level = level;
    }

    @Override
    public int compareTo(BnBNode o) {
        return (this.bound < o.bound) ? 1 : -1;
    }
}