package AdvancedPS_Class.week10th;

import java.util.Scanner;

public class P10_2_MakeHashMap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] orders = scanner.nextLine().split(" ");
        int[] ints = getInteger(scanner);
        MyHashMap hashMap = null;
        int index = 0;

        try{
            for(int i = 0; i < orders.length; i++,index++){
                switch (orders[i]){
                    case "MyHashMap":
                        hashMap = new MyHashMap();
                        break;
                    case "put":
                        hashMap.put(ints[index++],ints[index]);
                        break;
                    case "get":
                        hashMap.get(ints[index]);
                        break;
                    case "remove" :
                        hashMap.remove(ints[index]);
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
class MyHashMap{
    Node[] hashMap;
    static int modular = 10;

    public MyHashMap() {
        this.hashMap = new Node[modular];
        System.out.print("null ");
    }

    void put(int key, int value){
        int index = hashFunction(key);
        if(hashMap[index] == null){
            hashMap[index] = new Node(key,value);
        }else{
            Node node;
            for(node = hashMap[index]; node.link != null; node = node.link);
            node.link = new Node(key,value);
        }
        System.out.print("null ");
    }
    int get(int key){
        int index = hashFunction(key);
        for(Node node = hashMap[index]; node != null; node = node.link){
            if(node.key == key){
                System.out.print("true ");
                return node.value;
            }
        }
        System.out.print("false ");
        return -1;
    }

    void remove(int key){
        System.out.print("null ");
        int index = hashFunction(key);
        if(hashMap[index].key == key){
            hashMap[index] = hashMap[index].link;
        }else{
            for(Node node = hashMap[index]; node.link != null; node = node.link){
                if(node.link.key == key){
                    Node.removeProcess(node);
                }
            }
        }
    }
    int hashFunction(int key){
        return key % modular;
    }
}