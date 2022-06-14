package AlgorithmPractice_Class.AlgorithmClass_Final.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GreedyKnapSack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Items[] items = getInput(scanner,n); // 아이템 정보 받아줌
        Arrays.sort(items); // 그냥 내장소팅 돌리기(PriorityQueue)
        getOptimize(scanner,items); // 코드 실행하는 부분.
    }
    static Items[] getInput(Scanner scanner, int size){
        Items[] items = new Items[size]; // 아이템을 받아줌.
        int[] weightArray = new int[size];
        for(int i = 0; i < size; i++){
            weightArray[i] = scanner.nextInt();
        }
        for(int i = 0; i < size; i++){
            items[i] = new Items(weightArray[i], scanner.nextInt());
        }
        return items;
    }
    static void getOptimize(Scanner scanner, Items[] items){ // 실제 돌아가는부분.
        int repeat = scanner.nextInt();
        for(int i = 0; i < repeat; i++){
            int index = 0; // 받아주는 index
            ArrayList<Integer[]> history = new ArrayList<>(); // history 출력해주는부분.
            double maxProfit = 0;  // maxProfit 계산.
            int leftBagWeight = scanner.nextInt(); // 남은 BagWeight
            while(index < items.length && leftBagWeight != 0){ //leftBagWeight 가 이미 0이면 쓰잘데기없는거고, 아니면 아이템 다 쓸때까지
                Integer[] h = new Integer[2];
                if(items[index].weight < leftBagWeight){ // 이미 소팅된 item 리스트의 weight 가 leftBagWeight 보다 작으면
                    maxProfit += items[index].profit; // 다 더해주기
                    leftBagWeight -= items[index].weight; // leftBagWeight 빼주기
                    h[0] = items[index].weight; h[1] = items[index].profit; // 히스토리용
                    history.add(h); // add
                }else{ // 다 넣어줄 수 없을 때
                    maxProfit += (items[index].profitPerWeight * leftBagWeight); // 남은 애들 더해주기
                    h[0] = leftBagWeight; h[1] = (int)(items[index].profitPerWeight * leftBagWeight);
                    history.add(h);
                    leftBagWeight = 0; // leftBagWeight 없애주기.(이걸해야, 위에서 걸려져 나온다)
                }
                index++; // index 하나씩 추가
            }
            System.out.println((int)maxProfit);
            for(int j = 0; j < history.size(); j++){
                System.out.printf("%d %d\n", history.get(j)[0], history.get(j)[1]);
            }
        }
    }
}
class Items implements Comparable<Items>{
    int weight;
    int profit;
    double profitPerWeight;

    public Items(int weight, int profit) { // weight, profit, profitPerWeight
        this.weight = weight;
        this.profit = profit;
        this.profitPerWeight = (double)profit/(double)weight;
    }

    @Override
    public int compareTo(Items o) {
            return this.profitPerWeight <= o.profitPerWeight ? 1 : -1;
        }
}

