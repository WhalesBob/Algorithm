package AlgorithmClass_Final.dynamicProgramming;

import java.util.*;

public class Dynamic01KnapSack  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ItemStolen[] items = makeItems(scanner,n); // 훔쳐질 아이템 정보 업데이트
        items[0] = new ItemStolen(1,1000); // 일부러 넣어줌.
        Arrays.sort(items, Comparator.reverseOrder()); // profitPerWeight 큰것이 앞에 오게 정렬.

        int repeat = scanner.nextInt(); // 배낭 갯수
        for(int i = 0; i < repeat; i++){
            int bagWeight = scanner.nextInt(); // bagWeight 받아주기.
            HashMap<BagPair,Integer> map = new HashMap<>(); // 해시맵으로,, 받아준다.
            int profit = knapSack(n,bagWeight,items,map); // profit 을, knapSack 결과로써 받아준다. 해시맵에 넣어주면서 한다.
            System.out.println(profit); // 가장 큰 profit 출력.

            TreeMap<BagPair, Integer> sortedMap = new TreeMap<>((o1, o2) -> { // 그래서 treeMap으로 map을 만들어서, 정렬
                if(o1.n == o2.n){
                    return Integer.compare(o1.weight, o2.weight); // n이 같으면, weight 로 비교
                }else{
                    return (o1.n > o2.n) ? 1 : -1; // 아니면, n이 큰쪽으로.
                }
            });

            sortedMap.putAll(map); // 다 넣어줌.
            sortedMap.keySet().forEach(key-> System.out.printf("%d %d %d\n",key.n,key.weight,map.get(key))); // 출력
        }
    }
    static ItemStolen[] makeItems(Scanner scanner, int size){
        int[] weight = new int[size+1];
        ItemStolen[] items = new ItemStolen[size+1];
        for(int i = 1; i <= size; i++){
            weight[i] = scanner.nextInt();
        }
        for(int i = 1; i <= size; i++){
            items[i] = new ItemStolen(weight[i],scanner.nextInt());
        }
        return items;
    }
    static int knapSack(int n, int bagWeightLeft, ItemStolen[] items, HashMap<BagPair,Integer> hashMap){
        // n : 몇번째 아이템인지 / weight : 남은 무게 / items : 훔칠 아이템 배열 / hashMap : 해시맵
        if(n == 0 || bagWeightLeft <= 0){
            return 0;
        }

        int leftValue = hashMap.containsKey(new BagPair(n-1,bagWeightLeft)) ? // hashMap 이 (n-1, bagWeightLeft) 를 가지고 있으면
                hashMap.get(new BagPair(n-1,bagWeightLeft)) : knapSack(n-1,bagWeightLeft,items, hashMap); // bagPair 받아옴.
        // 아니면, 새로 knapSack 을 불러서 넣어줌. 어차피 다 없을거라 여기서 실질적으로 재귀를 돈다.
        int rightValue = hashMap.containsKey(new BagPair(n-1,bagWeightLeft-items[n].weight)) ?
                hashMap.get(new BagPair(n-1,bagWeightLeft-items[n].weight)) : knapSack(n-1,bagWeightLeft-items[n].weight,items,hashMap);
        // left 가 안 넣어주는애. right 는 넣어주는애.

        // 남은 해당 아이템의 무게가 남은 가방무게의 무게보다 무거울 경우, leftValue 로 하고, 아니면 둘중에 큰 쪽으러 넣어줌.
        int profit = (items[n].weight > bagWeightLeft) ? leftValue : Math.max(leftValue, rightValue + items[n].profit);
        hashMap.put(new BagPair(n,bagWeightLeft),profit); // 해시맵에 넣어줌.
        return profit; // profit 을 위로 올린다.
    }
}
class ItemStolen implements Comparable<ItemStolen>{ // 훔칠 아이템
    int weight;
    int profit;
    double profitPerWeight;

    public ItemStolen(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.profitPerWeight = (double)profit/(double)weight;
    }

    @Override
    public int compareTo(ItemStolen o) {
        return (this.profitPerWeight > o.profitPerWeight) ? 1 : -1;
    }
}
class BagPair { // n : 넣어줄 수 있는 남은 아이템 갯수? weight : 남은 bag 크기.
    int n;
    int weight;

    public BagPair(int n, int weight) {
        this.n = n;
        this.weight = weight;
    }
}