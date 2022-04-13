package AdvancedPS_Class.week6th;

import java.util.Arrays;
import java.util.Scanner;

public class P6_1_UseUnionFind {
    static int[] check;
    public static void main(String[] args) {
        P6_0_InputHandle handler = new P6_0_InputHandle();
        Scanner scanner = new Scanner(System.in);
        int[][] edgeArray = handler.inputEdges(scanner);

        check = new int[P6_0_InputHandle.numOfNode];
        Arrays.fill(check, -1);
        findParents(edgeArray);
        System.out.println(countFinalParent());
    }
    static void findParents(int[][] edges){
        // u,v 있을 때 u가 v의 부모. 최종부모 다 찾자
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
    }
    static void union(int a, int b){
        a = find(a); b = find(b);
        if(a != b){
            check[b] = a;
        }
    }
    static int find(int value){
        if(check[value] == -1){
            return value;
        }else{
            return check[value] = find(check[value]);
        }
    }
    static int countFinalParent(){
        int count = 0;
        for (int j : check) {
            if (j == -1) {
                count++;
            }
        }
        return count;
    }
}