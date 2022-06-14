package AlgorithmPractice_Class.AlgorithmClass_Final.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanNodeProblem {
    static HuffmanNode head;

    // HuffmanNode 를 만들어서 인코딩, 디코딩하는 문제
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfNode = scanner.nextInt();
        HuffmanNode[] nodes = getData(scanner,numOfNode); // 허프만 노드 클래스를 정의하고, 데이터를 배열에 집어넣기.

        PriorityQueue<HuffmanNode> priorityQueue = setPriorityQueue(nodes); // priorityQueue 만들어놓기.
        head = HuffmanNode.makeTree(priorityQueue);  // HuffmanNode.makeTree 에서 실질적으로 한다.

        head.preorder(); // 출력
        System.out.println();
        head.inorder(); // 출력

        head.setCode(new StringBuilder()); // 헤드에서 부터 시작해서, 밑으로 다 내려가면서 빈 StringBuilder 인코딩 작업을 다 해준다.
        encoding(scanner,nodes);

        decoding(scanner);
    }
    static HuffmanNode[] getData(Scanner scanner, int size){
        HuffmanNode[] nodes = new HuffmanNode[size];
        for(int i = 0; i < size; i++){
            char data = scanner.next().charAt(0);
            nodes[i] = new HuffmanNode(data,0);
        }

        for(int i = 0; i < size; i++){
            nodes[i].frequency = scanner.nextInt();
        }

        return nodes;
    }
    static PriorityQueue<HuffmanNode> setPriorityQueue(HuffmanNode[] nodes){
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(); // priorityQueue 설정. (HuffmanNode)
        priorityQueue.addAll(Arrays.asList(nodes)); // 그냥 다 집어넣으면 된다고 한다. 어차피 Priority Queue 가 다 해줌.
        return priorityQueue;
    }
    static void encoding(Scanner scanner, HuffmanNode[] array){

        int repeat = scanner.nextInt();
        for(int i = 0; i < repeat; i++){
            System.out.println();
            String toEncode = scanner.next(); // 받아온다.
            for(int j = 0; j < toEncode.length(); j++){ // 길이만큼 받아주면 될것이다.
                HuffmanNode find = HuffmanNode.findData(array,toEncode.charAt(j)); // findNode 이다. 계속 노드를 따라 내려가는 함수.
                System.out.print(find.binaryCode.toString());
            }
        }
    }
    static void decoding(Scanner scanner){
        int repeat = scanner.nextInt();
        for(int i = 0; i < repeat; i++){
            System.out.println();
            String toDecode = scanner.next();

            HuffmanNode node = head;
            for(int j = 0; j < toDecode.length(); j++){
                node = (toDecode.charAt(j) == '0') ? node.leftChild : node.rightChild;
                if(node.leftChild == null && node.rightChild == null){
                    System.out.print(node.data);
                    node = head;
                }
            }
        }
    }
}
class HuffmanNode implements Comparable<HuffmanNode>{ // 정의
    char data;
    int frequency;
    HuffmanNode leftChild;
    HuffmanNode rightChild;
    StringBuilder binaryCode;

    public HuffmanNode(char data, int frequency) { // 허프만노드. 트리로 함.
        this.data = data;
        this.frequency = frequency;
        this.leftChild = null;
        this.rightChild = null;
        this.binaryCode = new StringBuilder(); // StringBuilder 로 해당 부분을 저장.
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency <= o.frequency ? -1 : 1;
    }

    static HuffmanNode makeTree(PriorityQueue<HuffmanNode> priorityQueue){
        while(priorityQueue.size() != 1){
            HuffmanNode A = priorityQueue.poll(); // 두개빼서
            HuffmanNode B = priorityQueue.poll(); // 두개빼서

            HuffmanNode plus = new HuffmanNode('+',A.frequency + B.frequency); // 두개 더하고, frequency 값 더해서
            plus.leftChild = A; plus.rightChild = B; // 두개 자식노드로 만들어주고
            priorityQueue.add(plus); // PQ에 던짐
        }
        return priorityQueue.poll(); // 이런식으로 하면 딱 하나의 노드만 남게 된다. 얘가 head Node
    }
    void preorder(){
        System.out.printf("%c:%d ",this.data,this.frequency);
        if(this.leftChild != null){
            this.leftChild.preorder();
        }
        if(this.rightChild != null){
            this.rightChild.preorder();
        }
    }
    void inorder(){
        if(this.leftChild != null){
            this.leftChild.inorder();
        }
        System.out.printf("%c:%d ",this.data,this.frequency);
        if(this.rightChild != null){
            this.rightChild.inorder();
        }
    }

    static HuffmanNode findData(HuffmanNode[] array, char data){ // 어레이를 애초에 받아놓고, 데이터를 순차탐색 함.
        for(int i = 0; i < array.length; i++){
            if(array[i].data == data){
                return array[i];
            }
        }
        return null;
    }
    void setCode(StringBuilder builder){
        if(this.leftChild != null){
            StringBuilder leftBuilder = new StringBuilder(builder.toString());
            this.leftChild.binaryCode.append(leftBuilder.append("0"));
            this.leftChild.setCode(leftBuilder);
        }
        if(this.rightChild != null){
            StringBuilder rightBuilder = new StringBuilder(builder.toString());
            this.rightChild.binaryCode.append(rightBuilder.append("1"));
            this.rightChild.setCode(rightBuilder);
        }
    }

}