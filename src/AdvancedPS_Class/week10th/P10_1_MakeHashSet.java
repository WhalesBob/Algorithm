package AdvancedPS_Class.week10th;

import java.util.Scanner;

public class P10_1_MakeHashSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] orders = scanner.nextLine().split(" ");
        int[] integers = getInteger(scanner);
        MyHashSet hashSet = null;

        try{
            for(int i = 0; i < orders.length; i++){
                switch(orders[i]){
                    case "MyHashSet":
                        hashSet = new MyHashSet();
                        break;
                    case "add" :
                        hashSet.add(integers[i]);
                        break;
                    case "contains" :
                        boolean result = hashSet.contains(integers[i]);
                        break;
                    case "remove" :
                        hashSet.remove(integers[i]);
                        break;
                    default:
                        System.out.print("Error");
                }
            }
        }catch (NullPointerException e){
            System.out.println("Error");
        }
    }
    static int[] getInteger(Scanner scanner){
        String[] input = scanner.nextLine().split(",");
        int[] output = new int[input.length+1];
        output[0] = 0;
        for(int i = 1; i < output.length; i++){
            output[i] = Integer.parseInt(input[i-1]);
        }
        return output;
    }
}
class MyHashSet {
    Node[] hashSet;
    static int modular = 10;

    public MyHashSet() {
        hashSet = new Node[modular];
        System.out.print("null ");
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
        System.out.print("null ");
    }
    boolean contains(int key){
        int index = hashFunction(key);
        for(Node node = hashSet[index]; node != null; node = node.link){
            if(node.value == key){
                System.out.print("true ");
                return true;
            }
        }
        System.out.print("false ");
        return false;
    }

    void remove(int key){
        System.out.print("null ");
        int index = hashFunction(key);
        if(hashSet[index].value == key){
            hashSet[index] = hashSet[index].link;
        }else{
            for(Node node = hashSet[index]; node.link != null; node = node.link){
                if(node.link.value == key){
                    Node.removeProcess(node);
                }
            }
        }
    }
    int hashFunction(int value){
        return value % modular;
    }
}

