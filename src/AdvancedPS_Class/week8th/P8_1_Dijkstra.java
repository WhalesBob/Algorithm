package AdvancedPS_Class.week8th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P8_1_Dijkstra {
    static int INF;
    static Check[] touchTable;
    public static void main(String[] args) {
        INF = 999;
        Scanner scanner = new Scanner(System.in);
        System.out.print("times : ");
        String input = scanner.nextLine();
        System.out.print("n : ");
        int n = scanner.nextInt();
        System.out.print("k : ");
        int k = scanner.nextInt();

        int[][] graph = makeGraph(input,n);
        setInit(n,graph[k],k);

        try{
            dijkstra(graph,n);
            touchTable[0] = new Check(0,0);
            Arrays.sort(touchTable, Comparator.reverseOrder());
            System.out.println(touchTable[0].distance);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(-1);
        }
    }
    static int[][] makeGraph(String input, int size){
        P8_0_InputProcess process = new P8_0_InputProcess();
        int[][] graph = new int[size+1][size+1];
        ArrayList<Integer[]> edges = process.getEdges(input,size);

        for(int i = 1; i < graph.length; i++){
            for(int j = 1; j < graph[0].length; j++){
                graph[i][j] = (i == j) ? 0 : 999;
            }
        }
        for(int i = 0; i < edges.size(); i++){
            Integer[] list = edges.get(i);
            graph[list[0]][list[1]] = list[2];
        }
        return graph;
    }
    static void setInit(int size, int[] firstDistance, int start){
        touchTable = new Check[size+1];
        for(int i = 1; i <= size; i++){
            touchTable[i] = new Check(firstDistance[i],start);
        }
        touchTable[start].isChecked = true;
    }
    static void dijkstra(int[][] graph,int n) throws ArrayIndexOutOfBoundsException {
        for(int i = 1; i < n; i++){
            int minIndex = findMinimum();
            touchTable[minIndex].isChecked = true;
            compareAndWrite(graph,minIndex);
        }
    }
    static int findMinimum(){
        int min = INF, minIndex = INF;
        for(int i = 1; i < touchTable.length; i++){
            if((!touchTable[i].isChecked) && (min > touchTable[i].distance)){
                min = touchTable[i].distance;
                minIndex = i;
            }
        }
        return minIndex;
    }
    static void compareAndWrite(int[][] distanceArray, int compare){
        for(int i = 1; i < touchTable.length; i++){
            int value = touchTable[compare].distance + distanceArray[compare][i];
            if(!touchTable[i].isChecked && value < touchTable[i].distance){
                touchTable[i].distance = value;
                touchTable[i].from = compare;
            }
        }
    }
}
class Check implements Comparable<Check>{
    int distance;
    int from;
    boolean isChecked;

    public Check(int distance, int from) {
        this.distance = distance;
        this.from = from;
        this.isChecked = false;
    }

    @Override
    public int compareTo(Check o) {
        return (this.distance >= o.distance) ? 1 : -1;
    }
}