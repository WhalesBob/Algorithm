package AdvancedPS_Class;

import java.util.Scanner;

public class P5_1_BinaryTreeTraversal {
    public static void main(String[] args) throws InterruptedException{
        P5_0_ForMakingTreeNode make = new P5_0_ForMakingTreeNode();
        Scanner scanner = new Scanner(System.in);
        String[][] input = inputValues(scanner);

        TreeNode head = make.makeTree(input[0]);
        printByOrder(head,input[1][0]);
    }
    static String[][] inputValues(Scanner scanner){ // 1번
        P5_0_ForMakingTreeNode make = new P5_0_ForMakingTreeNode();
        String[] input = scanner.nextLine().split(" ");
        String[] numArray = make.deleteBrackets(input[2],true).split(",");
        String[][] output = new String[2][];
        output[0] = numArray;
        output[1] = new String[1];
        output[1][0] = scanner.next();

        return output;
    }
    static void printByOrder(TreeNode node, String order){
        switch(order){
            case "Preorder":
                node.preOrder();
                break;
            case "Inorder":
                node.inOrder();
                break;
            case "Postorder":
                node.postOrder();
                break;
            default:
                System.out.println("Error");
        }
    }
}

