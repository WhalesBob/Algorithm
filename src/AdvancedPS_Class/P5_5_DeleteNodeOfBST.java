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
        // find, delete 함수 만들어야 함.

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

        swap(deleteIndex, childIndex);
        treeArray[childIndex] = 0;
    }
    static void swap(int deleteIndex, int childIndex){
        int temp = treeArray[deleteIndex];
        treeArray[deleteIndex] = treeArray[childIndex];
        treeArray[childIndex] = temp;
    }
    static void deleteBetweenTwoChild(int deleteIndex){

    }
}
