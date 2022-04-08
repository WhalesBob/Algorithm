package AdvancedPS_Class.week5th;

import sun.misc.Queue;
import java.util.Scanner;

public class P5_2_SearchOrders {
    static int count;
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int[][] ordersArray = getInput(scanner);
        Tree head = makeTree(ordersArray,0,ordersArray[0].length,1);
        checkDepth(head);
        print(head);
    }
    static int[][] getInput(Scanner scanner){
        String[] input = scanner.nextLine().split(" ");
        String[][] orders = new String[2][];
        orders[0] = deleteBrackets(deleteBrackets(input[2],false),true).split(",");
        orders[1] = deleteBrackets(input[5],true).split(",");

        int[][] result = new int[2][orders[0].length];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < orders[0].length; j++){
                result[i][j] = Integer.parseInt(orders[i][j]);
            }
        }
        return result;
    }
    static String deleteBrackets(String input, boolean isBracket){
        StringBuilder builder = new StringBuilder(input);
        if(isBracket){
            builder.delete(0,1);
        }
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }
    static Tree makeTree(int[][] orders,int start, int end, int depth){
        if(start >= end){
            return new Tree(orders[0][start],depth);
        }else{
            int headNum = orders[1][--end]; // preorder의 맨마지막 숫자로 잡음.
            Tree node = new Tree(headNum,depth);
            int divideIndex = divide(orders[0],headNum,start,end);
            node.leftChild = makeTree(orders,start,divideIndex-1,depth+1);
            node.rightChild = makeTree(orders,divideIndex+1,end,depth+1);

            return node;
        }
    }
    static int divide(int[] array, int find, int start, int end){
        for(int i = start; i <= end; i++){
            if(find == array[i]){
                return i;
            }
        }
        return -1;
    }
    static void checkDepth(Tree node){

        if(node.leftChild != null){
            checkDepth(node.leftChild);
        }
        if(node.rightChild!= null){
            checkDepth(node.rightChild);
        }
        if(node.depth > count){
            count = node.depth;
        }
    }
    static void print(Tree node) throws  InterruptedException{
        Queue<Tree> queue = new Queue<>();
        queue.enqueue(node);
        Tree n = queue.dequeue();
        while(n.depth <= 3){
            if(n.leftChild != null){
                queue.enqueue(n.leftChild);
            }else{
                queue.enqueue(new Tree(-1,n.depth+1));
            }
            if(n.rightChild != null){
                queue.enqueue(n.rightChild);
            }else{
                queue.enqueue(new Tree(-1,n.depth+1));
            }

            if(n.data != -1){
                System.out.printf("%d ",n.data);
            }else{
                System.out.print("null ");
            }

            n = queue.dequeue();
        }
    }
}
class Tree{
    int data;
    Tree leftChild;
    Tree rightChild;
    int depth;

    public Tree(int data,int depth) {
        this.data = data;
        this.depth = depth;
        this.leftChild = null;
        this.rightChild = null;
    }
}