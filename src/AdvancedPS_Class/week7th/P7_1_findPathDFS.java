package AdvancedPS_Class.week7th;

import java.util.Scanner;
import java.util.Stack;

public class P7_1_findPathDFS {
    static int MAX;
    public static void main(String[] args) {
        MAX = 999;

        Scanner scanner = new Scanner(System.in);
        System.out.print("n = ");
        int n = scanner.nextInt();
        System.out.print("edges = ");
        int[][] graph = makeGraphArray(scanner,n);
        System.out.print("source = ");
        int source = scanner.nextInt();
        System.out.print("destination = ");
        int destination = scanner.nextInt();

        isConnected(source,destination,n,graph);
    }
    static int[][] makeGraphArray(Scanner scanner,int size){
        int[][] graphArray = new int[size][size];

        int[][] inputEdges = getInputEdges(scanner.next());
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i == j){
                    graphArray[i][j] = 0;
                }else{
                    graphArray[i][j] = MAX;
                }
            }
        }
        for(int i = 0; i < inputEdges.length; i++){
            graphArray[inputEdges[i][0]][inputEdges[i][1]]
                    = graphArray[inputEdges[i][1]][inputEdges[i][0]] = 1;
        }

        return graphArray;
    }
    static int[][] getInputEdges(String str){
        P7_0_InputProcess input = new P7_0_InputProcess();
        String[] stringArray = input.deleteBrackets(str,true,true).split("],");
        int[][] edges = new int[stringArray.length][2];
        for(int i = 0; i < stringArray.length-1; i++){
            String[] startToEnd =  input.deleteBrackets(stringArray[i],true,false).split(",");
            edges[i][0] = Integer.parseInt(startToEnd[0]);
            edges[i][1] = Integer.parseInt(startToEnd[1]);
        }
        String[] startToEnd = input.deleteBrackets(stringArray[stringArray.length -1],true,true).split(",");
        edges[stringArray.length-1][0] = Integer.parseInt(startToEnd[0]);
        edges[stringArray.length-1][1] = Integer.parseInt(startToEnd[1]);

        return edges;
    }
    static void isConnected(int source, int destination, int vertexSize, int[][] array){
        boolean[] visited = new boolean[vertexSize];
        int currentNode = source;
        Stack<Integer> stack = new Stack<>();

        do{
            visited[currentNode] = true;
            for(int i = 0; i < vertexSize; i++){
                if(array[currentNode][i] != MAX && !visited[i] && currentNode != i){
                    stack.push(i);
                }
            }
            currentNode = stack.pop();
        }while(!visited[destination] && !stack.isEmpty());

        System.out.println(visited[destination]);
    }
}
