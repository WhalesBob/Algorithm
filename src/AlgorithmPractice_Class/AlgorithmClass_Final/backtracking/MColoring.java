package AlgorithmPractice_Class.AlgorithmClass_Final.backtracking;

import java.util.Scanner;

public class MColoring { // 4색문제.
    static int[] vertexColor;
    static int maxColor;
    static int count;
    public static void main(String[] args) {

        maxColor = 4; count = 0; // 어차피 maxColor 를 초과할 수 없음. 그리고 count = 0 으로 초기화
        Scanner scanner = new Scanner(System.in);
        int numberOfVertex = scanner.nextInt();
        int numberOfEdge = scanner.nextInt();
        int[][] graph = getGraphArray(scanner,numberOfVertex,numberOfEdge); // Adjacency Matrix 만들어주기.
        vertexColor = new int[numberOfVertex+1]; // 각 node 의 vertexColor 넣어주는부분.

        mColoring(1,graph); // 1번째 node 부터 시작
        System.out.println(maxColor);
        System.out.print(count);
    }
    static int[][] getGraphArray(Scanner scanner, int size, int edge){ // Adjacency Matrix 만들어주기.
        int[][] graph = new int[size+1][size+1];
        for(int i = 0; i < edge; i++){
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            graph[row][col] = graph[col][row] = 1;
        }
        return graph;
    }
    static void mColoring(int i,int[][] graph){
        if(i == graph.length){ //
            int newMax = getMaxColor(); // newMax 구함.
            if(maxColor > newMax){ // 만약에 maxColor 보다 newMax 가 작으면, static 인 maxColor 에다가 업데이트 해주는 식.
                maxColor = newMax;
                count = 1; // 만약에 새로운 애로 업데이트 되었으면, 새로 1부터 시작
            }else{
                count++; // 그게 아니면, count++
            }
        }else{
            int color = 1; // 첫번째 color 부터 넣어보나 보다.
            while(color <= maxColor){ // 해당 색 숫자가 maxColor 보다 작아야지.
                vertexColor[i] = color++; // vertexColor[i] 가 해당 color 이고, color++;
                if(promising(i,graph)) { // promising 조건?
                    mColoring(i + 1, graph); // 다음 node 체크하면서 하기.
                }
            }
        }
    }
    static boolean promising(int n, int[][] graph){
        boolean switching = true; // true 로 초기화
        for(int i = 1; (i <= n && switching); i++){
            if((graph[n][i] == 1) && (vertexColor[n] == vertexColor[i])){ // 만약에 하나라도 연결되어 있는데, 해당 연결된 애랑 색상이 같다?
                switching = false; // false
            }
        }
        return switching; // 위의 for 문에서 한번도 안걸렸으면 true
    }
    static int getMaxColor(){
        int max = 0; // 그래서 넣은 애 중에, 몇개의 색상 분류가 들어가있는데?
        for(int i = 1; i < vertexColor.length; i++){
            if(max < vertexColor[i]){
                max = vertexColor[i];
            }
        }
        return max;
    }
}