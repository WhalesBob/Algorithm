package AlgorithmPractice_Class.BranchAndBound;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ZeroOneKnapSack {

    static int maxProfit;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int W = scanner.nextInt();

        BnBItem[] items = getItems(scanner, n);
        Arrays.sort(items);

        knapSack(n,W,items);
        System.out.print(maxProfit);
    }
    static BnBItem[] getItems(Scanner scanner, int size){
        BnBItem[] items = new BnBItem[size+1];
        int[] weight = new int[size+1];
        items[0] = new BnBItem(1,1000);
        for(int i = 1; i <= size; i++){
            weight[i] = scanner.nextInt();
        }
        for(int i = 1; i <= size; i++){
            items[i] = new BnBItem(weight[i], scanner.nextInt());
        }
        return items;
    }
    static int bound(BnBNode node, BnBItem[] items, int n,int W){
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
        System.out.printf("%d %d %d %d\n",nodeV.level, nodeV.weight, nodeV.profit, nodeV.bound);
        while(!priorityQueue.isEmpty()){
            nodeV = priorityQueue.poll();

            if(nodeV.bound > maxProfit){
                BnBNode leftChild = new BnBNode(0,0,nodeV.level + 1);
                leftChild.weight = nodeV.weight + items[leftChild.level].weight;
                leftChild.profit = nodeV.profit + items[leftChild.level].profit;
                leftChild.bound = bound(leftChild,items,n,W);
                System.out.printf("%d %d %d %d\n",leftChild.level, leftChild.weight, leftChild.profit, leftChild.bound);
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
                System.out.printf("%d %d %d %d\n",rightChild.level, rightChild.weight, rightChild.profit, rightChild.bound);
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