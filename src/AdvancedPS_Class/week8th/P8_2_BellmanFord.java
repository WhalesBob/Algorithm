package AdvancedPS_Class.week8th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P8_2_BellmanFord {
    static int INF;
    public static void main(String[] args) {
        INF = 999;
        Scanner scanner = new Scanner(System.in);
        System.out.print("n = ");
        int n = scanner.nextInt();
        System.out.print("flights = ");
        BFEdge[] graph = makeGraph(scanner,n);
        System.out.print("src = ");
        int start = scanner.nextInt();
        System.out.print("dst = ");
        int destination = scanner.nextInt();
        System.out.print("k = ");
        int k = scanner.nextInt();

        bellmanFord(graph,n,start,destination,k);
    }
    static BFEdge[] makeGraph(Scanner scanner,int vertexCount){
        P8_0_InputProcess process = new P8_0_InputProcess();

        ArrayList<Integer[]> edges = process.getEdges(scanner.next(),vertexCount);
        BFEdge[] edgeArray = new BFEdge[edges.size()];

        for(int i = 0; i < edges.size(); i++){
            Integer[] list = edges.get(i);
            edgeArray[i] = new BFEdge(list[0],list[1],list[2]);
        }
        return edgeArray;
    }
    static void bellmanFord(BFEdge[] edges, int nodeCount, int start, int finalDestination, int via){
        int[] distance = new int[nodeCount];
        int[] viaArray = new int[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            distance[i] = (i == start) ? 0 : INF;
        }
        Arrays.fill(viaArray,0);

        for(int i = 0; i <= nodeCount; i++){
            for(int j = 0; j < edges.length; j++){
                if((distance[edges[j].destination] >
                        distance[edges[j].start] + edges[j].weight) && viaArray[edges[j].start] <= via){
                    distance[edges[j].destination] = distance[edges[j].start] + edges[j].weight;
                    viaArray[edges[j].destination] = viaArray[edges[j].start] + 1;
                }
            }
        }

        System.out.print(distance[finalDestination]);

    }

}
class BFEdge{
    int start;
    int destination;
    int weight;

    public BFEdge(int start, int destination, int weight) {
        this.start = start;
        this.destination = destination;
        this.weight = weight;
    }
}
