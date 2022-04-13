package AdvancedPS_Class.week6th;

import java.util.Arrays;
import java.util.Scanner;

public class P6_2_ValidateTree {
    static int[] root;
    public static void main(String[] args) {
        P6_0_InputHandle handler = new P6_0_InputHandle();
        Scanner scanner = new Scanner(System.in);
        int[][] edgeArray = handler.inputEdges(scanner);

        root = new int[P6_0_InputHandle.numOfNode];
        Arrays.fill(root, -1);
        findParents(edgeArray);
    }
    static void findParents(int[][] edges){
        // u,v 있을 때 u가 v의 부모. 최종부모 다 찾자
        for (int[] edge : edges) {
            if(!union(edge[0], edge[1])){
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }
    static boolean union(int a, int b){
        a = find(a); b = find(b);
        if(a != b){
            root[b] = a;
        }else{
            return false;
        }
        return true;
    }
    static int find(int value){
        if(root[value] == -1){
            return value;
        }else{
            return root[value] = find(root[value]);
        }
    }
}
