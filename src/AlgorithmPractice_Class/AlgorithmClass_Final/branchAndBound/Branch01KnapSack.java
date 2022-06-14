package AlgorithmPractice_Class.AlgorithmClass_Final.branchAndBound;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Branch01KnapSack {
    static int maxProfit;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 아이템 갯수
        int W = scanner.nextInt(); // 배낭무게

        BnBItem[] items = getItems(scanner, n); // 아이템 리스트 업데이트
        Arrays.sort(items); // 정렬 (PriorityQueue)

        knapSack(n,W,items); // 시작
        System.out.print(maxProfit);
    }
    static BnBItem[] getItems(Scanner scanner, int size){
        BnBItem[] items = new BnBItem[size+1]; //
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
        PriorityQueue<BnBNode> priorityQueue = new PriorityQueue<>(); // priorityQueue
        BnBNode nodeV = new BnBNode(0,0,0); // BnBNode 소환(). 시작점.

        maxProfit = 0; // 처음 maxProfit 소환 이후, 0으로 맞추기
        nodeV.bound = bound(nodeV,items,n,W); // nodeV의 bound 계산
        priorityQueue.add(nodeV); // 처음에 시작점을 PQ에 add
        System.out.printf("%d %d %d %d\n",nodeV.level, nodeV.weight, nodeV.profit, nodeV.bound); // 방문하는 모든 노드를 먼저 출력.
        while(!priorityQueue.isEmpty()){ // empty 안나는 이상, 계속 돈다.
            nodeV = priorityQueue.poll(); // nodeV : PQ 에서 하나 뽑아옴.

            if(nodeV.bound > maxProfit){
                BnBNode leftChild = new BnBNode(0,0,nodeV.level + 1); // leftChild 에는 넣는애
                leftChild.weight = nodeV.weight + items[leftChild.level].weight; // 기존 nodeV에 추가추가
                leftChild.profit = nodeV.profit + items[leftChild.level].profit; // 마찬가지
                leftChild.bound = bound(leftChild,items,n,W); // 두개 넣어주고, bound 새로 계산해서 넣는다.
                System.out.printf("%d %d %d %d\n",leftChild.level, leftChild.weight, leftChild.profit, leftChild.bound);
                // 계산만 해도 "방문" 했다고 간주함.
                if(leftChild.weight <= W && leftChild.profit > maxProfit){ // 한계 W보다 leftChild.weight 가 작은데, leftChild.profit 은 이미
                    // maxProfit 보다 크면
                    maxProfit = leftChild.profit; // maxProfit 갱신
                }
                if(leftChild.bound > maxProfit){
                    priorityQueue.add(leftChild); // maxProfit 보다 해당 애의 bound 가 크면 넣어줌. (애초에 bound 가 안크면 볼것도 없음)
                }

                BnBNode rightChild = new BnBNode(0,0,nodeV.level + 1); // 안넣어주는애
                rightChild.weight = nodeV.weight; // 안넣음.
                rightChild.profit = nodeV.profit; // 안넣음
                rightChild.bound  = bound(rightChild,items,n,W); // 해당 정보로 bound 계산
                System.out.printf("%d %d %d %d\n",rightChild.level, rightChild.weight, rightChild.profit, rightChild.bound);
                // 위와 마찬가지로 방문노드 출력
                if(rightChild.bound > maxProfit){
                    priorityQueue.add(rightChild); // 마찬가지. 해당 bound 가 maxProfit 보다는 커야함.
                }
            }
        }
    }
}
class BnBItem implements Comparable<BnBItem> { // weight, profit, profitPerWeight
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
class BnBNode implements Comparable<BnBNode>{ // weight, profit, level, bound 선언.
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