package SWEA;

import java.util.Scanner;

public class Q1233 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            int nodeCount = sc.nextInt();
            Node33[] nodes = new Node33[nodeCount];

            String[][] inputs = new String[nodeCount][];
            sc.next();
            for(int i = 0; i < nodeCount; i++){
                inputs[i] = sc.nextLine().split(" ");
                nodes[i] = new Node33("");
            }

            for(int i = 0; i < nodeCount; i++) {
                nodes[i].setData(inputs[i][1]);
                if (inputs[i].length >= 3) {
                    nodes[i].leftChild = nodes[Integer.parseInt(inputs[i][2]) - 1];
                }
                if (inputs[i].length == 4) {
                    nodes[i].rightChild = nodes[Integer.parseInt(inputs[i][3]) - 1];
                }
            }
            System.out.printf("#%d %d\n",test_case,nodes[0].isValid() ? 1 : 0);
        }
    }
}
class Node33{
    String data;
    int n;
    Node33 leftChild;
    Node33 rightChild;

    public Node33(String data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
        this.n = 0;
    }

    public void setData(String s){
        try{
            this.data = s;
            this.n = Integer.parseInt(s);
        }catch(NumberFormatException e){
            this.n = 0;
        }
    }
    boolean isValid(){
        boolean isLeftValid = true, isRightValid = true;
        if(this.leftChild != null){
            isLeftValid = this.leftChild.isValid();
        }
        if(this.rightChild != null){
            isRightValid = this.rightChild.isValid();
        }
        return isLeftValid && isRightValid && !(this.n == 0 && (this.leftChild == null || this.rightChild == null));
    }
}