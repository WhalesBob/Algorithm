package boj;

import java.io.*;

public class Q2263 {
    static StringBuilder builder;
    public static void main(String[] args) throws IOException {
        builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] inOrder = makeArray(br,n);
        int[] postOrder = makeArray(br,n);

        TreeNode head = makeTree(inOrder,postOrder,0,n);
        head.printPreOrder(builder);
        bw.write(builder.toString().trim());
        bw.flush();
        br.close();
        bw.close();
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        String[] input = br.readLine().split(" ");
        for(int i = 0; i < size; i++){
            array[i] = Integer.parseInt(input[i]);
        }
        return array;
    }
    static TreeNode makeTree(int[] inOrder, int[] postOrder, int start,int end){
        if(start >= end){
            return new TreeNode(inOrder[start]);
        }else{
            int headNum = postOrder[--end];
            TreeNode node = new TreeNode(headNum);
            int divideIndex = findIndex(inOrder,headNum, start, end);
            node.leftChild = makeTree(inOrder,postOrder,start,divideIndex-1);
            node.rightChild = makeTree(inOrder,postOrder,divideIndex+1, end);

            return node;
        }
    }
    static int findIndex(int[] array, int value, int start, int end){
        for(int i = start; i <= end; i++){
            if(array[i] == value){
                return i;
            }
        }
        return -1;
    }
}
class TreeNode{
    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
    void printPreOrder(StringBuilder builder){

        builder.append(String.format("%d ",this.data));
        if(this.leftChild != null){
            this.leftChild.printPreOrder(builder);
        }
        if(this.rightChild != null){
            this.rightChild.printPreOrder(builder);
        }
    }
}
