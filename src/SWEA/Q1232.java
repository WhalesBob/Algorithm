package SWEA;

import java.util.Scanner;

public class Q1232 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            int nodeCount = sc.nextInt();
            Node32[] nodes = new Node32[nodeCount];

            String[][] inputs = new String[nodeCount][];
            sc.next();
            for(int i = 0; i < nodeCount; i++){
                inputs[i] = sc.nextLine().split(" ");
                nodes[i] = new Node32("");
            }

            for(int i = 0; i < nodeCount; i++){
                nodes[i].setData(inputs[i][1]);
                if(inputs[i].length >= 3){
                    nodes[i].leftChild = nodes[Integer.parseInt(inputs[i][2]) - 1];
                }
                if(inputs[i].length == 4){
                    nodes[i].rightChild = nodes[Integer.parseInt(inputs[i][3]) - 1];
                }
            }
            nodes[0].calculate();
            System.out.printf("#%d %d\n",test_case,nodes[0].n);
        }
    }
}
class Node32{
    String data;
    int n;
    Node32 leftChild;
    Node32 rightChild;

    public Node32(String data) {
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
    public void calculate(){
        if(this.leftChild != null){
            this.leftChild.calculate();
        }
        if(this.rightChild != null){
            this.rightChild.calculate();
        }

        try{
            switch(data){
                case "+":
                    this.n = this.leftChild.n + this.rightChild.n;
                    break;
                case "-":
                    this.n = this.leftChild.n - this.rightChild.n;
                    break;
                case "*":
                    this.n = this.leftChild.n * this.rightChild.n;
                    break;
                case "/":
                    this.n = this.leftChild.n / this.rightChild.n;
            }
        }catch(NullPointerException e){
            return;
        }
    }
}