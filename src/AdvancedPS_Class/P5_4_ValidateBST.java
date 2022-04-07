package AdvancedPS_Class;

import java.util.Scanner;

public class P5_4_ValidateBST {
    static P5_0_ForMakingTreeNode make;
    public static void main(String[] args) throws InterruptedException{
        make = new P5_0_ForMakingTreeNode();
        Scanner scanner = new Scanner(System.in);
        String[] input = getInput(scanner);
        TreeNode head = make.makeTree(input);
        System.out.println(isValid(head));
    }
    static String[] getInput(Scanner scanner){
        String[] input = scanner.nextLine().split(" ");
        return make.deleteBrackets(input[2],true).split(",");
    }
    static boolean isValid(TreeNode node){
        //postOrder traversal
        boolean left = true, right = true;
        if(node.leftChild != null){
            left = isValid(node.leftChild);
        }
        if(node.rightChild != null){
            right = isValid(node.rightChild);
        }

        int leftNumMax = (node.leftChild == null) ? node.data : node.leftChild.maxData;
        int rightNumMax = (node.rightChild == null) ? node.data : node.rightChild.maxData;
        int leftNumMin = (node.leftChild == null) ? node.data : node.leftChild.minData;
        int rightNumMin = (node.rightChild == null) ? node.data : node.rightChild.minData;
        node.maxData = Math.max(leftNumMax,rightNumMax);
        node.minData = Math.min(leftNumMin,rightNumMin);

        boolean isLeaf = (node.leftChild == null) && (node.rightChild == null);
        boolean smallerThanRight = (node.rightChild != null) && (node.data < node.rightChild.minData);
        boolean biggerThanLeft = (node.leftChild != null) && (node.data > node.leftChild.maxData);
        boolean previous = left && right;

        if(isLeaf){
            return previous;
        }else{
            if(node.rightChild == null){
                return biggerThanLeft && previous;
            }else if(node.leftChild == null){
                return smallerThanRight && previous;
            }else{
                return smallerThanRight && biggerThanLeft && previous;
            }
        }
    }
}
