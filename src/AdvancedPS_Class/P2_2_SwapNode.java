package AdvancedPS_Class;

import java.util.Scanner;
import java.util.StringTokenizer;

public class P2_2_SwapNode {
    static Node head;
    public static void main(String[] args) {
        head = new Node(); // LinkedList head 설정
        Node first = head;
        head.link = first; // 맨첫번째 노드 설정
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringTokenizer token = new StringTokenizer(input," ");
        if(!token.hasMoreTokens()){
            System.out.println("");
            return;
        }
        while(token.hasMoreTokens()){
            Node node = new Node();
            first.link = node;
            node.setData(Integer.parseInt(token.nextToken()));
            first = node;
        }
        swapNode(head.link,head);
        printList();
    }
    static void swapNode(Node node, Node before){
        if(!((node == null) || (node.link == null) || (head.link.getData() == -1))){
            Node after = node.link;
            Node next = node.link.link;
            after.link = node;
            node.link = next;
            before.link = after;

            swapNode(next,node);
        }
    }
    static void printList(){
        for(Node node = head.link; node != null; node = node.link){
            System.out.printf("%d ",node.getData());
        }
    }
}
class Node{
    private int data;
    public Node link;

    public Node() {
        this.data = -1;
        this.link = null;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
