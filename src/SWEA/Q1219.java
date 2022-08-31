package SWEA;

import java.util.Scanner;

public class Q1219 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        // 전형적인 DFS 문제
        for(int test_case = 1; test_case <= T; test_case++){
            sc.nextInt();
            int[] m1 = new int[100];
            int[] m2 = new int[100];
            int vCount = sc.nextInt();

            for(int i = 0; i < vCount; i++){
                int before = sc.nextInt();
                int after = sc.nextInt();

                if(m1[before] == 0){
                    m1[before] = after;
                }else{
                    m2[before] = after;
                }
            }

            DFSNode[] nodes = new DFSNode[100];
            for(int i = 0; i < nodes.length; i++){
                if(m1[i] == 0){
                    continue;
                }
                if(m1[i] != 0){
                    if(nodes[i] == null){
                        nodes[i] = new DFSNode(i);
                    }
                    nodes[m1[i]] = create(nodes,m1[i]);
                    nodes[i].leftChild = nodes[m1[i]];
                }
                if(m2[i] != 0){
                    nodes[m2[i]] = create(nodes,m2[i]);
                    nodes[i].rightChild = nodes[m2[i]];
                }
            }
            System.out.printf("#%d %d\n",test_case,travel(nodes[0]) ? 1 : 0);
        }
    }
    static DFSNode create(DFSNode[] nodes, int data){
        return (nodes[data] == null) ? new DFSNode(data) : nodes[data];
    }
    static boolean travel(DFSNode node){
        boolean result1,result2;
        if(!node.isChecked){
            node.isChecked = true;

            if(node.data == 99){
                return true;
            }
            if(node.leftChild != null){
                result1 = travel(node.leftChild);
                if(result1){
                    return true;
                }
            }
            if(node.rightChild != null){
                result2 = travel(node.rightChild);
                return result2;
            }
        }
        return false;
    }
}
class DFSNode{
    int data;
    DFSNode leftChild;
    DFSNode rightChild;
    boolean isChecked;

    public DFSNode(int data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
        this.isChecked = false;
    }
}

