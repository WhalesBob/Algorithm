package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1647 {
    static int INF;
    static int start;
    static Check[] checkTable;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        INF = 999;

        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        checkTable = new Check[n];

        int[][] distanceTable = makeTable(br,n,m); // Okay
        initCheck(n,distanceTable); // Okay

        int[][] historyData = prim(n-1,distanceTable);
        printHistory(historyData);
    }
    static int[][] makeTable(BufferedReader br,int n, int m) throws IOException{
        int[][] table = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    table[i][j] = 0;
                }else{
                    table[i][j] = INF;
                }
            }
        }
        for(int i = 0; i < m; i++){
            String[] str = br.readLine().split(" ");
            int row = Integer.parseInt(str[0]);
            if(i == 0){
                start = row;
            }
            int col = Integer.parseInt(str[1]);
            int weight = Integer.parseInt(str[2]);
            table[row-1][col-1] = table[col-1][row-1] = weight;
        }

        return table;
    }
    static void initCheck(int size, int[][] distanceTable){
        for(int i = 0; i < size; i++){
            checkTable[i] = new Check(distanceTable[start-1][i],start);
        }
        checkTable[start-1].isChecked = true;
    }

    static int[][] prim(int edgeMax, int[][] distanceTable){
        int[][] history = new int[edgeMax][3];
        printNearest(start);

        for(int count = 0; count < edgeMax; count++){ //

            int minIndex = findMinimumIndex();
            int from = checkTable[minIndex].from, weight = distanceTable[minIndex][from-1];
            history[count] =
                    writeInHistory(minIndex+1,from,weight);

            checkTable[minIndex].isChecked = true;
            compareAndChange(distanceTable[minIndex],minIndex+1);
            printNearest(start);
        }

        return history;
    }
    static void printNearest(int start){
        for(int i = 0; i < checkTable.length; i++){
            if(i != (start-1)){
                System.out.print(checkTable[i].from);
                if(i < checkTable.length-1){
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }
    static int findMinimumIndex(){
        int min = INF, minIndex = INF;
        for(int i = 0; i < checkTable.length; i++){
            if((min > checkTable[i].distance) && (!checkTable[i].isChecked)){
                min = checkTable[i].distance;
                minIndex = i;
            }
        }
        return minIndex;
    }
    static int[] writeInHistory(int to, int from, int weight){
        int[] array = new int[3];
        array[0] = to; array[1] = from; array[2] = weight;
        return array;
    }
    static void compareAndChange(int[] distance, int compare){
        for(int i = 0; i < checkTable.length; i++){
            if(!checkTable[i].isChecked && (checkTable[i].distance > distance[i])){
                checkTable[i].distance = distance[i];
                checkTable[i].from = compare;
            }
        }
    }
    static void printHistory(int[][] data){
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(data[i][j]);
                if(j < 2){
                    System.out.print(" ");
                }
            }
            if(i < data.length-1){
                System.out.println();
            }
        }
    }
}
class Check{
    int distance; // 최소거리
    int from; // 어디에서 가는?
    boolean isChecked;

    public Check(int distance,int from) {
        this.distance = distance;
        this.isChecked = false;
        this.from = from;
    }
}