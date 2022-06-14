package AdvancedPS_Class.week10th;

public class Node{
    int key;
    int value;
    Node link;

    public Node(int value) {
        this.key = 0;
        this.value = value;
        this.link = null;
    }

    public Node(int key, int value){
        this.key = key;
        this.value = value;
        this.link = null;
    }
    static void removeProcess(Node node) {
        node.link = (node.link.link == null) ? null : node.link.link;
    }
}
