package AlgorithmPractice_Class.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Floyd {
    static int INF = 999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int numOfVertex = Integer.parseInt(str[0]);
        int numOfLine = Integer.parseInt(str[1]);
        int[][] path = new int[numOfVertex][numOfVertex];
        int[][] directArray = make2DArray(br, numOfVertex, numOfLine);
        fill(directArray,path, numOfVertex);
        printArrays(directArray);
        printArrays(path);
        print(br,path,directArray);
    }
    static int[][] make2DArray(BufferedReader br, int size,int numOfLine) throws IOException{
        int[][] array = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                array[i][j] = ((i == j) ? 0 : INF);
            }
        }
        for(int i = 0; i < numOfLine; i++){
            String[] str = br.readLine().split(" ");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);
            int weight = Integer.parseInt((str[2]));

            array[row-1][col-1] = weight;
        }
        return array;
    }
    static void fill(int[][] directArray, int[][] path, int size){
        for(int k = 0; k < size; k++ ){
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    if(directArray[i][j] > directArray[i][k] + directArray[k][j]){
                        path[i][j] = k+1;
                        directArray[i][j] = directArray[i][k] + directArray[k][j];
                    }
                }
            }
        }
    }
    static void print(BufferedReader br, int[][] path,int[][] direct) throws IOException{
        int repeat = Integer.parseInt(br.readLine());
        for(int i = 0; i < repeat; i++){
            String[] str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            printPath(start,end,path,direct);
        }
    }
    static void printPath(int start, int end, int[][] path,int[][] directArray){
        if(directArray[start-1][end-1] == INF){
            System.out.println("NONE");
        }else if(path[start-1][end-1] == 0){
            System.out.printf("%d %d\n",start,end);
        } else{
            TreeNode parent = new TreeNode(start,end);
            parent.makeChild(path);
            parent.printAll(true);
        }
    }
    static void printArrays(int[][] arrays){
        for(int i = 0; i < arrays.length; i++){
            for(int j = 0; j < arrays[0].length; j++){
                System.out.print(arrays[i][j]);
                if(j < arrays[0].length - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
class TreeNode{
    int start;
    int end;
    TreeNode leftNode;
    TreeNode rightNode;

    public TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.leftNode = null;
        this.rightNode = null;
    }

    void addLeft(TreeNode leftChild){
        this.leftNode = leftChild;
    }
    void addRight(TreeNode rightChild){
        this.rightNode = rightChild;
    }
    void makeChild(int[][] path){
        if(path[start-1][end-1] == 0){
            return;
        }
        TreeNode leftChild = new TreeNode(start,path[start-1][end-1]);
        TreeNode rightChild = new TreeNode(path[start-1][end-1],end);
        this.leftNode = leftChild;
        this.rightNode = rightChild;
        this.leftNode.makeChild(path);
        this.rightNode.makeChild(path);
    }
    void printAll(boolean parent){
        if(parent){
            System.out.print(this.start);
        }
        if(this.leftNode != null){
            this.leftNode.printAll(false);
        }
        if(this.rightNode != null){
            this.rightNode.printAll(false);
        }
        if((this.leftNode == null) && (this.rightNode == null)){
            System.out.printf(" %d",this.end);
        }
        if(parent){
            System.out.println();
        }
    }
}