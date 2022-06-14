package AlgorithmPractice_Class.AlgorithmClass_Final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solve {
    static int INF;
    static int numOfVertex;
    static ArrayList<Integer> optimalTour;
    static int minLength;
    public static void main(String[] args) {
        INF = 999;
        numOfVertex = 5; // 정점의 갯수 n

        int[][] graph = makeGraph(); // Adjacency List 만들기
        traveling(graph); // 딱봐도 여기서 본격시작
        System.out.println(minLength);
        for(int i = 0; i < optimalTour.size(); i++){
            System.out.print(optimalTour.get(i));
            if(i < optimalTour.size() - 1){
                System.out.print(" ");
            }
        }
    }
    static int[][] makeGraph(){
        int[][] graph =
                {
                        {0,0,0,0,0,0},
                        {0,0,6,6,10,8},
                        {0,3,0,12,7,6},
                        {0,8,7,0,14,20},
                        {0,5,13,9,0,8},
                        {0,9,8,10,6,0}};
        return graph;
    }
    static int length(ArrayList<Integer> path,int[][] graph){
        int length = 0;
        for(int i = 0; i < path.size(); i++){
            if(i < path.size() - 1){
                length += graph[path.get(i)][path.get(i+1)];
            }
        }
        return length;
    }
    static int bound(TSPNode node, int[][] graph){
        int lower = length(node.path,graph);
        for(int i = 1; i <= numOfVertex; i++){
            if(hasOutgoing(i,node.path)){
                continue;
            }
            int min = INF;
            for(int j = 1; j <= numOfVertex; j++){
                boolean isSame = (i == j);
                boolean alreadyInclude = (j == 1 && i == node.path.get(node.path.size()-1));

                if(isSame || alreadyInclude || hasIncoming(j,node.path)){
                    continue;
                }
                if(min > graph[i][j]){
                    min = graph[i][j];
                }
            }
            lower += min;
        }
        return lower;
    }
    static boolean hasOutgoing(int n, ArrayList<Integer> path){
        for(int i = 0; i < path.size()-1; i++){
            if(path.get(i) == n){
                return true;
            }
        }
        return false;
    }
    static boolean hasIncoming(int n, ArrayList<Integer> path){
        for(int i = 1; i < path.size(); i++){
            if(path.get(i) == n){
                return true;
            }
        }
        return false;
    }
    static void traveling(int[][] graph){
        PriorityQueue<TSPNode> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        // 마찬가지. priorityQueue 소환
        TSPNode parentNode = new TSPNode(0); //level "0"부터 시작한다.
        parentNode.path.add(1); // path 에 1 추가하고,
        parentNode.bound = bound(parentNode,graph); // 1 넣은대로 bound 계산해서 넣는다.
        printNode(parentNode); // 첫 node 프린트

        minLength = INF; // minLength 를 INF 로 세팅.
        priorityQueue.add(parentNode); // 역시 PQ에 넣음.

        while(!priorityQueue.isEmpty()){ // 빌때까지
            parentNode = priorityQueue.poll(); // poll 땡겨옴.
            if(parentNode.bound < minLength){ // 바운드가 minLength 보다 작을 때(애초에 제일 작은애로 업데이트)
                for(int i = 2; i <= numOfVertex; i++){ //
                    if(isIn(i,parentNode.path)){ // 경로상 안 간 곳을 가야함. 경로에 들어있으면 continue
                        continue;
                    }
                    TSPNode childNode = new TSPNode(parentNode.level+1,parentNode.path); // 자식노드를 만들고, 부모노드의 level + 1 , path 받아옴.
                    childNode.path.add(i); // i 를 path 에 추가
                    if(childNode.level == numOfVertex-2){ // level 이 numOfVertex - 2 면(n-1개 돌았으면)
                        childNode.path.add(remainingVertex(childNode.path)); // 남은 하나를 넣어주고,
                        childNode.path.add(1); // 마지막에 1로 돌아가야 하므로 그냥 넣어줌.
                        childNode.bound = bound(childNode,graph); // bound 값 계산해줌.
                        if(length(childNode.path,graph) < minLength){ // 이렇게 만든 경로가, 기존 경로보다 짧으면
                            minLength = length(childNode.path,graph); // 길이를 갱신해주고
                            optimalTour = childNode.path; // tour Path 를 등록
                        }
                    }else{
                        childNode.bound = bound(childNode,graph); // 아직 덜 들렀으면, bound 값 계산
                        if(childNode.bound < minLength){ // 여전히 bound 가 minLength 보다 적으면,
                            priorityQueue.add(childNode); // 넣어주기
                        }
                    }
                    printNode(childNode); // 이렇게 "방문" 한 childNode 를 print
                }
            }
        }
    }
    static boolean isIn(int n, ArrayList<Integer> path){ // 해당 경로에 n이 들어있으면 true, 아니면 false
        for (Integer integer : path) {
            if (n == integer) {
                return true;
            }
        }
        return false;
    }
    static int remainingVertex(ArrayList<Integer> path){ // path 에서 못끼고 남은 하나를 구하는 함수
        int sum = 0;
        for (Integer integer : path) {
            sum += integer;
        }
        return (((numOfVertex+1)*numOfVertex)/2) - sum;
    }
    static void printNode(TSPNode node){ // 뭐 그냥 print 해주는거지.
        System.out.print(node.level);
        System.out.printf(" %s ",(node.bound >= INF) ? "INF" : Integer.toString(node.bound));
        for(int i = 0; i < node.path.size(); i++){
            System.out.printf("%d",node.path.get(i));
            if(i < node.path.size() - 1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
class TSPNode implements Comparable<TSPNode>{ // level, bound, path
    int level;
    int bound;
    ArrayList<Integer> path;

    public TSPNode(int level) {
        this.level = level;
        this.path = new ArrayList<>();
        this.bound = 0;
    }
    public TSPNode(int level, ArrayList<Integer> path){
        this.level = level;
        this.bound = 0;
        this.path = (ArrayList<Integer>)path.clone();
    }

    @Override
    public int compareTo(TSPNode o) {
        return (this.bound <= o.bound) ? 1 : -1;
    }
}