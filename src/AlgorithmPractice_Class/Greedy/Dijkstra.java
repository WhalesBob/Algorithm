package AlgorithmPractice_Class.Greedy;

import java.io.*;
import java.util.*;

public class Dijkstra {
    static BufferedWriter bw;
    static int INF;
    static Check2[] touchTable;
    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        INF = 999;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] tableArray = makeTable(scanner,n,m);

        setInit(n,tableArray[0]); // okay, 출력 X
        int[][] historyArray = dijkstra(n,tableArray);
        printArray(historyArray);

        int count = scanner.nextInt();
        for(int i = 0; i < count; i++){
            int end = scanner.nextInt();
            printPath(end,i,count);
        }
        bw.flush();
        bw.close();
    }
    static int[][] makeTable(Scanner scanner,int size, int edge){
        int[][] table = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i == j){
                    table[i][j] = 0;
                }else{
                    table[i][j] = INF;
                }
            }
        }
        for(int i = 0; i < edge; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            table[u-1][v-1] = weight;
        }
        return table;
    }
    static void setInit(int size, int[] firstDistance){
        touchTable = new Check2[size];
        for(int i = 0; i < size; i++){
            touchTable[i] = new Check2(firstDistance[i],1);
        }
        touchTable[0].isChecked = true;
    }
    static int[][] dijkstra(int edgeSize,int[][] distanceArray) throws IOException{
        int[][] history = new int[edgeSize-1][3];
        printTouch();

        for(int i = 0; i < edgeSize-1; i++){
            int minIndex = findMinimum();
            int from = touchTable[minIndex].from, weight = distanceArray[from-1][minIndex];
            history[i] = writeOnHistory(from,minIndex,weight);
            touchTable[minIndex].isChecked = true;
            compareAndWrite(distanceArray,minIndex);
            printTouch();
        }

        return history;
    }
    static int[] writeOnHistory(int from, int to, int weight){
        int[] history = new int[3];
        history[0] = from; history[1] = (to+1); history[2] = weight;
        return history;
    }
    static int findMinimum(){
        int min = INF, minIndex = INF;
        for(int i = 0; i < touchTable.length; i++){
            if((!touchTable[i].isChecked) && (min > touchTable[i].distance)){
                min = touchTable[i].distance;
                minIndex = i;
            }
        }
        return minIndex;
    }
    static void compareAndWrite(int[][] distanceArray, int compare){
        for(int i = 0; i < touchTable.length; i++){
            int value = touchTable[compare].distance + distanceArray[compare][i];
            if(!touchTable[i].isChecked && value < touchTable[i].distance){
                touchTable[i].distance = value;
                touchTable[i].from = (compare+1);
            }
        }
    }
    static void printTouch() throws IOException{
        for(int i = 1; i < touchTable.length; i++){
            newPrint(touchTable[i].from);
            if(i < touchTable.length -1){
                newPrint(" ");
            }
        }
        newPrint("\n");
    }
    static void printArray(int[][] array) throws IOException{
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                newPrint(array[i][j]);
                if(j < array[0].length -1 ){
                    newPrint(" ");
                }
            }
            newPrint("\n");
        }
    }
    static void printPath(int end, int count, int max) throws IOException{
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1,end));
        int value = end;
        while(touchTable[value-1].from != 1){
            value = touchTable[value-1].from;
            list.add(1,value);
        }

        for(int i = 0; i < list.size(); i++){
            newPrint(list.get(i));
            if(i < list.size() - 1){
                newPrint(" ");
            }
        }
        if(count < max-1){
            newPrint("\n");
        }
    }
    static void newPrint(int value) throws  IOException{
        bw.write(Integer.toString(value));
    }
    static void newPrint(String value) throws IOException{
        bw.write(value);
    }
}
class Check2{
    int distance; // 최소거리
    int from; // 어디에서 가는?
    boolean isChecked;

    public Check2(int distance,int from) {
        this.distance = distance;
        this.isChecked = false;
        this.from = from;
    }
}
