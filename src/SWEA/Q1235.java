package SWEA;

import java.util.ArrayList;
import java.util.Scanner;

public class Q1235 {
    static final int maxNumber = 100;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            ArrayList<Node35> max = new ArrayList<>();
            max.add(new Node35(-1,0));
            int dataLength = sc.nextInt();
            int startNode = sc.nextInt();

            ArrayList<Integer>[] graph = makeGraphArray(sc,dataLength);
            boolean[] isVisited = new boolean[maxNumber+1];

            Queue<Node35> queue = new Queue();
            int sequence = 0;
            queue.enqueue(new Node35(startNode,sequence));
            isVisited[startNode] = true;

            while(!queue.isEmpty()){

                Node35 node = queue.dequeue();

                if(max.size() <= node.sequence){
                    max.add(new Node35(-1, sequence));
                }
                if(max.get(node.sequence).data < node.data){
                    max.get(node.sequence).data = node.data;
                }

                for(int i = 0; i < graph[node.data].size(); i++){
                    int newNumber = graph[node.data].get(i);
                    sequence = node.sequence + 1;
                    if(!isVisited[newNumber]){
                        isVisited[newNumber] = true;
                        queue.enqueue(new Node35(newNumber, sequence));
                    }
                }

            }
            System.out.printf("#%d %d\n",test_case,max.get(max.size()-1).data);
        }
    }
    static ArrayList[] makeGraphArray(Scanner sc, int dataLength){
        int length = dataLength / 2;
        ArrayList[] array = new ArrayList[maxNumber+1];

        for(int i = 0; i <= maxNumber; i++){
            array[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < length; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();

            if(!array[from].contains(to)){
                array[from].add(to);
            }
        }
        return array;
    }
}
class Node35{
    int data;
    int sequence;

    public Node35(int data, int sequence) {
        this.data = data;
        this.sequence = sequence;
    }
}