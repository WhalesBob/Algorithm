package AdvancedPS_Class;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P4_4_FindPathwithObstacle {
    static int rowLength;
    static int colLength;
    public static void main(String[] args) throws IOException {
        int[] obstacleData = whereIsObstacle();
        int[][] path = makeArray();
        System.out.println(pathFind(path,obstacleData[1],obstacleData[0]));
    }

    static int[] whereIsObstacle() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().split(" ")[2];
        String[] stringArray = arrangeString(input);
        rowLength = stringArray.length;
        int[] read = new int[2];
        for(int i = 0; i < stringArray.length; i++){
            String[] forNum = stringArray[i].split(" ");
            colLength = forNum.length;
            for(int j = 0; j < forNum.length; j++){
                if(Integer.parseInt(forNum[j]) == 1){
                    read[0] = i; read[1] = j;
                }
            }
        }
        return read;
    }
    static String[] arrangeString(String input){
        String str = deleteBigs(input).replaceAll(","," ");
        String[] finalApproach = str.split("] ");
        for(int i = 0; i < finalApproach.length - 1; i++){
            finalApproach[i] = finalApproach[i].substring(1);
        }
        finalApproach[finalApproach.length-1] = deleteBigs(finalApproach[finalApproach.length-1]);

        return finalApproach;
    }
    static String deleteBigs(String str){
        StringBuilder builder = new StringBuilder(str);
        builder.delete(0,1);
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }
    static int pathFind(int[][] path,int obsX, int obsY){
        int m = path.length, n = path[0].length;
        for(int i = 1; i < m; i++){
            path[i][0] = 1;
        }
        for(int i = 1; i < n; i++){
            path[0][i] = 1;
        }
        path[obsY][obsX] = 0;
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(!(i == obsY && j == obsX)){
                    path[i][j] = path[i-1][j] + path[i][j-1];
                }
            }
        }
        return path[m-1][n-1];
    }
    static int[][] makeArray(){
        int[][] array = new int[rowLength][colLength];
        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < colLength; j++){
                array[i][j] = -1;
            }
        }
        return array;
    }
}
