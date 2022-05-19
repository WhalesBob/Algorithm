package AdvancedPS_Class.week10th;

import java.util.Scanner;

public class P10_1_MakeHashSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] orders = scanner.nextLine().split(",");

    }
}
class MyHashSet {
    Node[] hashSet;
    static int modular = 10;

    public MyHashSet() {
        hashSet = new Node[modular];
    }

    void add(int key){
        int index = hashFunction(key);
        if(hashSet[index] == null){
            hashSet[index] = new Node(key);
        }else{
            Node node;
            for(node = hashSet[index]; node.link != null; node = node.link);
            node.link = new Node(key);
        }
    }

    boolean contains(int key){
        int index = hashFunction(key);
        for(Node node = hashSet[index]; node != null; node = node.link){
            if(node.value == key){
                return true;
            }
        }
        return false;
    }

    void remove(int key){
        int index = hashFunction(key);
        if(hashSet[index].value == key){
            hashSet[index] = hashSet[index].link;
        }else{
            for(Node node = hashSet[index]; node.link != null; node = node.link){
                if(node.link.value == key){
                    removeProcess(node);
                }
            }
        }
    }
    int hashFunction(int value){
        return value % modular;
    }
    static void removeProcess(Node node){
        node.link = (node.link.link == null) ? null : node.link.link;
    }
}
class Node{
    int value;
    Node link;

    public Node(int value) {
        this.value = value;
        this.link = null;
    }
}
