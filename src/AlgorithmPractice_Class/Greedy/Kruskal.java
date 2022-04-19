package AlgorithmPractice_Class.Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Kruskal {
    static int[] group;
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Edge[] edges = makeEdge(scanner,m);
        initGroup(n);
        Arrays.sort(edges);

        Edge[] kruskalArray = kruskal(edges,n-1);
        print(kruskalArray);
    }
    static Edge[] makeEdge(Scanner scanner, int size){
        Edge[] output = new Edge[size];
        for(int i = 0; i < size; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            output[i] = new Edge(u,v,w);
        }
        return output;
    }
    static void initGroup(int size){
        group = new int[size+1];
        for(int i = 1; i <= size; i++){
            group[i] = -1;
        }
    }
    static Edge[] kruskal(Edge[] edges, int size){
        Edge[] kruskalArray = new Edge[size];
        int count = 0, index = 0;
        while(count < size){
            int from = edges[index].v, to = edges[index].u;
            if(find(from) != find(to)){ // 한 부모를 가지고 있는가? 아닐때
                kruskalArray[count] = edges[index];
                union(from,to);
                count++;
            }
            index++;
        }

        return kruskalArray;
    }
    static int find(int value){
        if(group[value] < 0){
            return value;
        }else{
            return group[value] = find(group[value]);
        }
    }
    static void union(int from, int to){
        from = find(from); to = find(to);
        if(from != to){
            group[from] = to;
        }
    }
    static void print(Edge[] kruskal) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < kruskal.length; i++){
            String output = String.format("%d %d %d",kruskal[i].u, kruskal[i].v, kruskal[i].weight);
            bw.write(output);
            if(i < kruskal.length-1){
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
class Edge implements Comparable<Edge>{
    int u;
    int v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight < o.weight){
            return -1;
        }else if(this.weight == o.weight){
            return 0;
        }else{
            return 1;
        }
    }
}
