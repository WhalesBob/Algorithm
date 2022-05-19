package AlgorithmPractice_Class.BranchAndBound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TraversingSalesPerson {

    static int INF;
    static int numOfVertex;
    static ArrayList<Integer> optimalTour;
    static int minLength;
    public static void main(String[] args) {
        INF = 999;
        Scanner scanner = new Scanner(System.in);
        numOfVertex = scanner.nextInt();
        int numOfEdge = scanner.nextInt();

        int[][] graph = makeGraph(scanner,numOfVertex,numOfEdge);
        traveling(graph);
        System.out.println(minLength);
        for(int i = 0; i < optimalTour.size(); i++){
            System.out.print(optimalTour.get(i));
            if(i < optimalTour.size() - 1){
                System.out.print(" ");
            }
        }
    }
    static int[][] makeGraph(Scanner scanner, int size, int numOfEdge){
        int[][] graph = new int[size+1][size+1];
        for(int i = 1; i < graph.length; i++){
            for(int j = 1; j < graph[0].length; j++){
                graph[i][j] = (i == j) ? 0 : INF;
            }
        }

        for(int i = 0; i < numOfEdge; i++){
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            graph[row][col] = scanner.nextInt();
        }
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
        TSPNode nodeV = new TSPNode(0);
        nodeV.path.add(1);
        nodeV.bound = bound(nodeV,graph);
        printNode(nodeV);

        minLength = INF;
        priorityQueue.add(nodeV);

        while(!priorityQueue.isEmpty()){
            nodeV = priorityQueue.poll();
            if(nodeV.bound < minLength){
                for(int i = 2; i <= numOfVertex; i++){
                    if(isIn(i,nodeV.path)){
                        continue;
                    }
                    TSPNode nodeU = new TSPNode(nodeV.level+1,nodeV.path);
                    nodeU.path.add(i);
                    if(nodeU.level == numOfVertex-2){
                        nodeU.path.add(remainingVertex(nodeU.path));
                        nodeU.path.add(1);
                        nodeU.bound = bound(nodeU,graph);
                        if(length(nodeU.path,graph) < minLength){
                            minLength = length(nodeU.path,graph);
                            optimalTour = nodeU.path;
                        }
                    }else{
                        nodeU.bound = bound(nodeU,graph);
                        if(nodeU.bound < minLength){
                            priorityQueue.add(nodeU);
                        }
                    }
                    printNode(nodeU);
                }
            }
        }
    }
    static boolean isIn(int n, ArrayList<Integer> path){
        for (Integer integer : path) {
            if (n == integer) {
                return true;
            }
        }
        return false;
    }
    static int remainingVertex(ArrayList<Integer> path){
        int sum = 0;
        for (Integer integer : path) {
            sum += integer;
        }
        return (((numOfVertex+1)*numOfVertex)/2) - sum;
    }
    static void printNode(TSPNode node){
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
class TSPNode implements Comparable<TSPNode>{
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
