package AlgorithmClass_Final.backtracking;

import java.util.Scanner;

public class Hamiltonian {
    static int[][] graph;
    static int count;
    static int first;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfVertex = scanner.nextInt();
        int numberOfEdge = scanner.nextInt();
        graph = getGraph(scanner,numberOfVertex,numberOfEdge); // vertex, edge 숫자 받고, Adjacency Matrix 만들어주기
        count = 0; first = 0;

        hamiltonian(0,first,true,new boolean[numberOfVertex]); // 해밀토니안 시작.
        System.out.println(count);
    }
    static int[][] getGraph(Scanner scanner, int vertex, int edge){
        int[][] graph = new int[vertex][vertex];
        for(int i = 0; i < edge; i++){
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            graph[row-1][col-1] = graph[col-1][row-1] = 1;
        }
        return graph;
    }
    static void hamiltonian(int previous, int n, boolean isFirst,boolean[] visited){
        if(promising(previous,n,visited)){ // promising 조건에 만족하면, 들어가는 것.
            if(n == 0 && !isFirst){ // n이 0으로 돌아왔는데, 처음 넣어주는게 아니면.
                count++; // 된거임. count++
            }else{ // 아직 0으로 안돌아왔으면,
                visited[n] = true; // visited[n] 방문됨.
                for(int i = 0; i < visited.length; i++){ // for 문 돌면서, 해당 애들을 하나씩 소환.
                    if(i == 0 && !allCount(visited)){ // i가 0인데, 덜 방문했으면 건너뛰고 그냥 그 다음애 소환해주면 되는 식으로 했나보다.
                        continue;
                    }
                    if(graph[n][i] == 1){ // 연결 되어 있으면(이미 promising 조건을 만족해버림)
                        hamiltonian(n,i,false,visited.clone()); // 돌아야지. pruning 조건 만족 못하면 짤린거고 아니면 하고 식.
                    }
                }
            }
        }
    }
    static boolean promising(int previous,int after, boolean[] visited){
        if(after == 0) { // 묻따 after = 0이면 true를 하나보다.
            return true;
        }
        return !visited[after] && graph[previous][after] != 0; // 해당 after 방문 했으면 false, 방문안했는데 연결되어 있으면 true.
    }
    static boolean allCount(boolean[] visited){
        for (boolean b : visited) {
            if (!b) { // 하나라도 방문 안했으면 false
                return false;
            }
        }
        return true; // 싹다 방문 했으면 true
    }
}
