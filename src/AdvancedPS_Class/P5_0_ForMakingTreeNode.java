package AdvancedPS_Class;

import sun.misc.Queue;
import java.util.Scanner;

public class P5_0_ForMakingTreeNode {

    String[][] getInput(Scanner scanner){ // 3,5번 입력
        String[] input = scanner.nextLine().split(" ");
        String[] orders = deleteBrackets(deleteBrackets(input[2],false),true).split(",");

        String[][] output = new String[2][];
        output[0] = orders;
        output[1] = new String[1];
        output[1][0] = input[5];

        return output;
    }
    String deleteBrackets(String input, boolean isBracket){
        StringBuilder builder = new StringBuilder(input);
        if(isBracket){
            builder.delete(0,1);
        }
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }
    TreeNode makeTree(String[] input) throws InterruptedException{
        Queue<TreeNode> queue = new Queue<>();

        TreeNode head = new TreeNode(input[0]);
        queue.enqueue(head);
        TreeNode node = queue.dequeue();
        for(int i = 1; i < input.length;){
            if(node.leftChild == null && !node.leftNull){
                node.leftChild = TreeNode.makeNode(input[i]);
                node.leftNull = input[i++].equals("null");
                if(!node.leftNull){
                    queue.enqueue(node.leftChild);
                }
            }else if(node.rightChild == null && !node.rightNull){
                node.rightChild = TreeNode.makeNode(input[i]);
                node.rightNull = input[i++].equals("null");
                if(!node.rightNull){
                    queue.enqueue(node.rightChild);
                }
            }else{
                node = queue.dequeue();
            }
        }
        return head;
    }
}
class TreeNode{
    int data;
    TreeNode leftChild;
    TreeNode rightChild;
    boolean leftNull;
    boolean rightNull;
    int maxData;
    int minData;

    public TreeNode(String data) {
        this.data = Integer.parseInt(data);
        this.leftChild = null;
        this.rightChild = null;
        this.leftNull = false;
        this.rightNull = false;
        this.maxData = this.data;
        this.minData = this.data;
    }
    static TreeNode makeNode(String data){
        if(data.equals("null")){
            return null;
        }else{
            return new TreeNode(data);
        }
    }
    void preOrder(){
        System.out.printf("%d ",this.data);
        if(this.leftChild != null){
            this.leftChild.preOrder();
        }
        if(this.rightChild != null){
            this.rightChild.preOrder();
        }
    }
    void inOrder(){
        if(this.leftChild != null){
            this.leftChild.inOrder();
        }
        System.out.printf("%d ",this.data);
        if(this.rightChild != null){
            this.rightChild.inOrder();
        }
    }
    void postOrder(){
        if(this.leftChild != null){
            this.leftChild.postOrder();
        }
        if(this.rightChild != null){
            this.rightChild.postOrder();
        }
        System.out.printf("%d ",this.data);
    }
}
