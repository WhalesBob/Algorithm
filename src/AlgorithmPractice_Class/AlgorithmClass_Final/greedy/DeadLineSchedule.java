package AlgorithmPractice_Class.AlgorithmClass_Final.greedy;

import java.util.ArrayList;
import java.util.Scanner;

public class DeadLineSchedule {// 데드라인 스케쥴링 문제.
    // 모든 노드(deadline, profit) 를 만든다음에, profit 이 큰것대로 정렬하고, deadline 비교해서 갖다 집어넣는 식으로 간다.
    static ArrayList<Node> list; // 노드를 받는 애.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfJob = scanner.nextInt(); // Job 갯수 입력받음
        Node[] nodes = Node.makeAllNode(scanner,numberOfJob); //모든 노드를 받아오는 부분.

        list = new ArrayList<>(); // static ArrayList<> 소환.
        list.add(new Node(0,0)); // 0부분이 필요했나?
        for(int i = 0; i < numberOfJob; i++){
            for(int j = list.size()-1; j >= 0; j--){ // 리스트에 있는것을 거꾸로 읽어오기.
                if(nodes[i].deadline >= list.get(j).deadline){ // i번째 데드라인이, 리스트에 있는 애들의 데드라인보다 하나라도 더 크면,
                    checkAndInsert(j+1,nodes[i]); // 체크하고 insert 넣기. (j+1) 번째에
                }
            }
        }
        printAll();
    }
    static void checkAndInsert(int index,Node node){
        list.add(index,node); // 일단 먼저 넣어보기.
        if(!isValid()){ // 넣어보고 애바다 싶으면
            list.remove(index); //
        }
    }
    static boolean isValid(){
        for(int i = 1; i < list.size(); i++){ //
            if(i > list.get(i).deadline){ // list.get(i).deadline 보다 i 가 크면(그냥 데드라인을 넘었으면)
                return false;
            }
        }
        return  true;
    }
    static void printAll(){
        int sum = 0;
        for(int i = 1; i < list.size(); i++){
            sum += list.get(i).profit;
        }
        System.out.println(sum);
        for(int i = 1; i < list.size(); i++){
            System.out.print(list.get(i).profit);
            if( i < list.size() - 1){
                System.out.print(" ");
            }
        }
    }
}
class Node{
    int profit;
    int deadline;

    public Node(int profit, int deadline) {
        this.profit = profit;
        this.deadline = deadline;
    }
    static Node[] makeAllNode(Scanner scanner, int size){
        Node[] nodes = new Node[size];
        for(int i = 0; i < size; i++){
            int input = scanner.nextInt();
            nodes[i] = new Node(0,input);
        }
        for(int i = 0; i < size; i++){
            nodes[i].profit = scanner.nextInt();
        }

        return nodes;
    }
}