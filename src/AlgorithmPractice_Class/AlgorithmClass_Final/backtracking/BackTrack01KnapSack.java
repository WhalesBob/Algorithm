package AlgorithmPractice_Class.AlgorithmClass_Final.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BackTrack01KnapSack {
    static int W; // 배낭무게
    static int n; // 아이템의 갯수
    static int maxProfit; // maxProfit
    static ArrayList<Item2> bestList; // 가장 best 인 list 업데이트해주는 곳.
    static double previousBound; // 그 전 bound 처리해주는 것.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        W = scanner.nextInt();
        Item2[] items = getItems(scanner,n);  // item 받아옴.

        Arrays.sort(items); // 정렬. (PriorityQueue)
        knapSack(0,0,0,items,new ArrayList<>());  // 시작부분
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
        System.out.printf("%d %d %d ",i,weight,profit); // 재귀 들어가자마자 출력해주는 부분.
        // 상태공간트리를 "방문"하는 노드의 상태 순으로 출력. 처음에는 i, weight, profit
        if(weight <= W && profit > maxProfit){ // 지금 갖고 있는 profit 이 maxProfit 보다 크면
            maxProfit = profit; // profit 업데이트
            bestList = list; // bestList 업데이트
        }
        if(promising(i,profit,weight,items)){ // promising 조건
            ArrayList<Item2> newList = new ArrayList<>(list); // 분기해서 넣어줄건지 말건지 결정
            newList.add(items[i+1]); // newList 에는 넣어줌.

            knapSack(i+1,profit + items[i+1].profit,weight+items[i+1].weight,items,newList); // 넣어준애
            knapSack(i+1,profit,weight,items,list); // 안넣어준애
        }
    }
    static boolean promising(int i, int profit, int weight, Item2[] items){
        double bound = previousBound; // 그 전 bound.
        if(weight < W){ // 애초에 현재 갖고있는 weight 가 W 보다는 작아야함.
            int plusIndex = i+1; // 그다음거 index
            bound = profit; // 일단 profit 으로 bound 잡아줌.
            int totalWeight = weight; // 현재 weight 로 totalWeight 잡아줌.
            while(plusIndex <= n && totalWeight + items[plusIndex].weight <= W){
                // 그다음 index 가 n보다 작거나 같고, totalWeight 와 그다음 weight 합이 W보다 작거나 같아야
                totalWeight += items[plusIndex].weight; // 다 더하고
                bound += items[plusIndex].profit; // 더하고
                plusIndex++; // 더할수 있는곳까지 더해줌.
            }
            if(plusIndex <= n){
                bound += ((W-totalWeight) * items[plusIndex].profitPerWeight); // 그래서 무적의 bound 만들어주기!(Greedy 하게)
            }
        }
        System.out.printf("%d %d\n",(int)bound,maxProfit); // 업데이트한 bound 랑 현 maxProfit
        previousBound = bound; // 그래서 previousBound 자리에 저장해줌.
        return weight < W && (bound > maxProfit); // promising 조건 반환
    }
}
class Item2 implements Comparable<Item2>{ // Item 받아오는것.
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