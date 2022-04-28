package AdvancedPS_Class.week7th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P7_3_MST {
    static int[] parent;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("points = ");
        ArrayList<Vertex> getInput = getInput(scanner);

        ArrayList<Edges> edgeList = makeList(getInput);
        edgeList.sort(Collections.reverseOrder());

        parent = new int[getInput.size()];
        Arrays.fill(parent, -1);

        minimumCost(edgeList);
    }
    static ArrayList<Vertex> getInput(Scanner scanner){
        ArrayList<Vertex> output = new ArrayList<>();
        P7_0_InputProcess input = new P7_0_InputProcess();
        String[] stringArray = input.deleteBrackets(scanner.next(),true,true).split("],");

        for(int i = 0; i < stringArray.length-1; i++){
            String[] getOneString = input.deleteBrackets(stringArray[i],true,false).split(",");
            Vertex vertex = new Vertex(Integer.parseInt(getOneString[0]),Integer.parseInt(getOneString[1]));
            output.add(vertex);
        }
        String[] getOneString = input.deleteBrackets(stringArray[stringArray.length-1],true,true).split(",");
        Vertex vertex = new Vertex(Integer.parseInt(getOneString[0]),Integer.parseInt(getOneString[1]));
        output.add(vertex);

        return output;
    }
    static ArrayList<Edges> makeList(ArrayList<Vertex> vertexList){
        ArrayList<Edges> edges = new ArrayList<>();
        for(int i = 0; i < vertexList.size(); i++){
            for(int j = i+1; j < vertexList.size(); j++){
                edges.add(new Edges(vertexList.get(i), vertexList.get(j)));
            }
        }
        return edges;
    }
    static void minimumCost(ArrayList<Edges> edges){
        int sum = 0, count = 0;
        for(int i = 0; i < edges.size(); i++){
            Edges edge = edges.get(i);
            if(find(edge.A.ID) != find(edge.B.ID)){
                sum += edge.weight;
                union(edge.A.ID,edge.B.ID);
                System.out.printf("%d %d\n",edge.A.ID,edge.B.ID);
                count++;
            }
            if(count == parent.length -1){
                break;
            }
        }
        System.out.println(sum);
    }
    static int find(int value){
        if(parent[value] == -1){
            return value;
        }else{
            return parent[value] = find(parent[value]);
        }
    }
    static void union(int a, int b){
        a = find(a); b = find(b);
        if(a != b){
            parent[a] = b;
        }
    }
}
class Edges implements Comparable<Edges>{
    Vertex A;
    Vertex B;
    int weight;

    public Edges(Vertex a, Vertex b) {
        A = a;
        B = b;
        this.weight = Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
    }

    @Override
    public int compareTo(Edges o) {
        return (this.weight <= o.weight) ? 1 : -1;
    }
}
class Vertex{
    int x;
    int y;
    int ID;
    static int count;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
        this.ID = count++;
    }
}