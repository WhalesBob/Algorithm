package AdvancedPS_Class.week5th;

import java.util.Scanner;

public class P5_3_PathSum {
    static P5_0_ForMakingTreeNode make;
    public static void main(String[] args) throws InterruptedException {
        make = new P5_0_ForMakingTreeNode();
        Scanner scanner = new Scanner(System.in);
        String[][] inputs = make.getInput(scanner);
        TreeNode head = make.makeTree(inputs[0]);
        System.out.println(targetSum(head,0,Integer.parseInt(inputs[1][0])));
    }
    static boolean targetSum(TreeNode node, int sum, int compare){
        // preorder 방식으로 가야한다.
        boolean left = false, right = false;
        sum += node.data;
        if(node.leftChild != null){
            left = targetSum(node.leftChild,sum,compare);
        }
        if(node.rightChild != null){
            right = targetSum(node.rightChild,sum,compare);
        }
        return (sum == compare) || left || right;
    }
}
