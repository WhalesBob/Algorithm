package AdvancedPS_Class.week9th;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P9_3_HuffmanCode {
    static ArrayList<HuffmanNode> nodeList;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<HuffmanNode> priorityQueue = setPriorityQueue(scanner);

        HuffmanNode head = HuffmanNode.makeHuffmanTree(priorityQueue);
        enCoding(head);
        printWithCode();
    }
    static PriorityQueue<HuffmanNode> setPriorityQueue(Scanner scanner){
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        nodeList = new ArrayList<>();
        String[] input = scanner.nextLine().split(" ");
        for(int i = 0; i < input.length; i+=2){
            HuffmanNode node = new HuffmanNode(input[i].charAt(0),Double.parseDouble(input[i+1]));
            priorityQueue.add(node);
            nodeList.add(node);
        }
        return priorityQueue;
    }
    static void enCoding(HuffmanNode node){
        // 각 0과 1을 저장하면서 나아가면 될 것이다. Preorder Traversal
        if(node.leftChild != null){
            node.leftChild.code.append(node.code.toString()).append("0");
            enCoding(node.leftChild);
        }
        if(node.rightChild != null){
            node.rightChild.code.append(node.code.toString()).append("1");
            enCoding(node.rightChild);
        }
    }
    static void printWithCode(){
        for (HuffmanNode node : nodeList) {
            System.out.printf("%c %s\n", node.data, node.code.toString());
        }
    }
}
class HuffmanNode implements Comparable<HuffmanNode>{
    char data;
    double frequency;
    HuffmanNode leftChild;
    HuffmanNode rightChild;
    StringBuilder code;

    public HuffmanNode(char data, double frequency) {
        this.data = data;
        this.frequency = frequency;
        this.leftChild = null;
        this.rightChild = null;
        this.code = new StringBuilder();
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return (this.frequency < o.frequency) ? 1 : -1;
    }

    static HuffmanNode makeHuffmanTree(PriorityQueue<HuffmanNode> priorityQueue){
        while(priorityQueue.size() != 1){
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode combine = new HuffmanNode('+',left.frequency + right.frequency);
            combine.leftChild = left; combine.rightChild = right;
            priorityQueue.add(combine);
        }
        return priorityQueue.poll();
    }
}
