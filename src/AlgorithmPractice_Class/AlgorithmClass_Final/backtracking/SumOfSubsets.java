package AlgorithmPractice_Class.AlgorithmClass_Final.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SumOfSubsets {
    // 합이 W가 되는 부분집합 만들어 풀기. BackTracking
    static int W;
    static int[] elements;
    static ArrayList<Integer[]> history;
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 원소의 갯수
        W = scanner.nextInt(); // W
        elements = getList(scanner,n); // n개의 정수 받기
        history = new ArrayList<>(); // 되는 애들 history 받기.

        Arrays.sort(elements);
        int total = 0;
        for(int i = 0; i < elements.length; i++){
            total += elements[i]; // total 에다가 넣을 수 있는 전체 합을 넣어버리기.
        }
        sumOfSubsets(1,new SubsetNode(total)); // backtracking 시작.(0부터 시작)(새로운 SubsetNode 집어넣어주기)
        System.out.println(history.size());

        for(int i = 0; i < history.size(); i++){
            Integer[] h = history.get(i);
            for(int j = 0; j < h.length; j++){
                System.out.print(h[j]);
                if(j < h.length - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    static int[] getList(Scanner scanner, int size){
        int[] list = new int[size+1];
        for(int i = 0; i < list.length-1; i++){
            list[i] = scanner.nextInt();
        }
        list[size] = 0;
        return list;
    }
    static void sumOfSubsets(int i, SubsetNode node) throws CloneNotSupportedException{ //
        if(promising(node,i)){ // promising 조건
            if(node.weight == W){ // 완성되면
                history.add(node.list.toArray(new Integer[node.list.size()])); // 해당 노드의 리스트 내용을 더해줌.
            }else if(i == elements.length-1){
                // 이건 그냥 pruning 시키는 애인가보다.
            }else{
                SubsetNode newNode = (SubsetNode)node.clone(); // yes/no 분리하는 부분. 새로 clone 해서 하나에만 추가해주는 것이다.
                newNode.add(elements[i]); // 복제해서 하나에만 한다.
                sumOfSubsets(i+1,newNode);
                // yes 부분 : clone 해서 만들어 주고. 여기에 해당 부분 넣기
                sumOfSubsets(i+1,node);
                // no 부분 : 굳이 클론으로 넣을 필요 없을듯. 그냥 no 부분은 재귀를 넣자
            }
        }
    }
    static boolean promising(SubsetNode node, int i){ // i가 elements.length 보다 작야아함. && 현 weight 와 남은 totalLeft 쟤보다 작으면 볼것없다.
        // node.weight == W이면 그냥 통과니까 되고, 아니면 넣어줄 weight + elements[i] 가 W 초과할것같으면 필요없으니까 안해줌.
        return (i < elements.length) && ((node.weight + node.totalLeft >= W) && (node.weight == W || node.weight + elements[i] <= W));
    }
}
class SubsetNode implements Cloneable, Comparable<SubsetNode> { //
    int weight;
    ArrayList<Integer> list;
    int totalLeft;

    public SubsetNode(int totalLeft) {
        this.weight = 0; // 현재 받은 weight 를 넣을 수 있을듯.
        this.list = new ArrayList<>(); // 포함하는 list 인가보다. yes or no
        this.totalLeft = totalLeft; // 남은 total 말하나보다.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SubsetNode node = new SubsetNode(totalLeft);
        node.weight = weight;
        node.list = new ArrayList<>();
        node.list.addAll(list);

        return node;
    }
    void add(int elements){
        this.list.add(elements); // 맞네
        totalLeft -= elements; // 남은 total 에서 빼주기
        weight += elements; // weight 에 추가되면 당연히 더해줘야함.
    }

    @Override
    public int compareTo(SubsetNode o) {
        return (this.weight) > (o.weight) ? 1 : -1;
    }
}