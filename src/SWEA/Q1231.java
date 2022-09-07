package SWEA;

import java.util.Scanner;

public class Q1231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            int count = sc.nextInt();

            InorderNode[] nodes = new InorderNode[count+1];

            String[][] command = new String[count][];
            for(int i = 0; i < count; i++){
                nodes[i] = new InorderNode(' ');
                sc.next();
                command[i] = sc.nextLine().split(" ");
            }

            for(int i = 0; i < count; i++){
                nodes[i].data = command[i][1].charAt(0);
                if(command[i].length >= 3){
                    nodes[i].leftChild = nodes[Integer.parseInt(command[i][2])-1];
                }
                if(command[i].length == 4){
                    nodes[i].rightChild = nodes[Integer.parseInt(command[i][3])-1];
                }
            }
            System.out.printf("#%d ",test_case);
            nodes[0].inorderTraversal();
            System.out.println();
        }
    }
}
class InorderNode{
    char data;
    InorderNode leftChild;
    InorderNode rightChild;

    public InorderNode(char data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
    void inorderTraversal(){
        if(this.leftChild != null){
            this.leftChild.inorderTraversal();
        }
        System.out.print(this.data);
        if(this.rightChild != null){
            this.rightChild.inorderTraversal();
        }
    }
}