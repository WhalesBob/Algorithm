package AdvancedPS_Class;

import java.util.Scanner;

public class P5_5_DeleteNodeOfBST {
    static int[] treeArray;
    public static void main(String[] args) throws InterruptedException {
        P5_0_ForMakingTreeNode make = new P5_0_ForMakingTreeNode();
        Scanner scanner = new Scanner(System.in);
        String[][] input = make.getInput(scanner);
        treeArray = makeTreeAsArray(input[0]);
        int deleteIndex = findNode(Integer.parseInt(input[1][0]));
        deleteNode(deleteIndex);
        printTreeArray();
    }
    static int[] makeTreeAsArray(String[] input){
        int[] treeArray = new int[input.length+1];
        treeArray[0] = 0;
        for(int i = 1; i <= input.length; i++){
            if(input[i-1].equals("null")){
                treeArray[i] = 0;
            }else{
                treeArray[i] = Integer.parseInt(input[i-1]);
            }
        }
        return treeArray;
    }
    static int findNode(int value){
        for(int i = 1; i < treeArray.length; i++){
            if(treeArray[i] == value){
                return i;
            }
        }
        return -1;
    }
    static void deleteNode(int deleteIndex){
        int childCount = howManyChild(deleteIndex);
        if(childCount == 0){
            treeArray[deleteIndex] = 0;
        }else if(childCount == 1){
            deleteOneChild(deleteIndex);
        }else{
            deleteBetweenTwoChild(deleteIndex);
        }
    }
    static int howManyChild(int deleteIndex) {
        boolean isLeftInBound = (2 * deleteIndex < treeArray.length);
        boolean isRightInBound = (2 * deleteIndex + 1 < treeArray.length);

        boolean isLeftNotNull = isLeftInBound && (treeArray[2 * deleteIndex] != 0);
        boolean isRightNotNull = isRightInBound && (treeArray[2 * deleteIndex + 1] != 0);

        boolean noChild = !(isLeftNotNull || isRightNotNull);
        boolean twoChild = (isLeftNotNull && isRightNotNull);

        if (noChild) {
            return 0;
        } else if (twoChild) {
            return 2;
        } else {
            return 1;
        }
    }
    static void deleteOneChild(int deleteIndex){
        int childIndex;
        if(treeArray[2*deleteIndex] != 0){
            childIndex = 2*deleteIndex;
        }else{
            childIndex = 2*deleteIndex + 1;
        }
        swapAndDelete(deleteIndex,childIndex);
    }
    static void swapAndDelete(int deleteIndex, int childIndex){
        treeArray[deleteIndex] = treeArray[childIndex];
        treeArray[childIndex] = 0;
    }
    static void deleteBetweenTwoChild(int deleteIndex){
        int leftMax = 2 * deleteIndex;
        int rightMin = 2 * deleteIndex + 1;
        int leftMaxDepth = (int)log2(leftMax);
        int rightMaxDepth = (int)log2(rightMin);

        while(true){
            if((leftMax * 2 + 1 < treeArray.length) && (treeArray[leftMax * 2 + 1] != 0)){
                leftMax = leftMax * 2 + 1;
                leftMaxDepth++;
            }else{
                break;
            }
        }

        while(true){
            if((rightMin * 2 < treeArray.length) && (treeArray[rightMin * 2] != 0)){
                rightMin *= 2;
                rightMaxDepth++;
            }else{
                break;
            }
        }

        if(leftMaxDepth > rightMaxDepth){
            swapAndDelete(deleteIndex,leftMax);
        }else{
            swapAndDelete(deleteIndex,rightMin);
        }
    }
    static double log2(int value){
        return (Math.log(value) / Math.log(2));
    }
    static void printTreeArray(){
        int newLength = treeArray.length;
        for(int i = treeArray.length - 1; i >= 0; i--){
            if(treeArray[i] != 0){
                newLength = i;
                break;
            }
        }

        for(int i = 1; i <= newLength; i++){
            System.out.printf("%s ",(treeArray[i] == 0) ? "null" : Integer.toString(treeArray[i]));
        }
    }
}
