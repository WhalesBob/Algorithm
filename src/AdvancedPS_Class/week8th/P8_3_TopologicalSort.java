package AdvancedPS_Class.week8th;

import java.util.ArrayList;
import java.util.Scanner;

public class P8_3_TopologicalSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("numCourses : ");
        int numCourse = scanner.nextInt();
        System.out.print("prerequisites : ");
        ArrayList<Integer>[] courses = getPrerequisites(scanner,numCourse);

        topologicalSort(courses);
    }
    static ArrayList<Integer>[] getPrerequisites(Scanner scanner,int course){
        P8_0_InputProcess process = new P8_0_InputProcess();
        ArrayList<Integer>[] list = new ArrayList[course];
        for(int i = 0; i < course; i++){
            list[i] = new ArrayList<>();
        }

        String[] process1 = process.deleteBrackets(scanner.next(),true,true).split("],");
        for(int i = 0; i < process1.length; i++){
            boolean isLast = ( i == process1.length-1);
            String[] process2 = process.deleteBrackets(process1[i],true,isLast).split(",");
            list[Integer.parseInt(process2[0])].add(Integer.parseInt(process2[1]));
        }
        return list;
    }
    static void topologicalSort(ArrayList<Integer>[] list){
        boolean[] visited = new boolean[list.length];
        for(int i = 0; i < list.length; i++){
            int item = -1;
            for(int j = 0; j < list.length; j++){
                if(list[j].isEmpty() && !visited[j]){
                    item = j;
                    visited[j] = true;
                    break;
                }
            }
            System.out.printf("%d ",item);
            for(int j = 0; j < list.length; j++){
                if(list[j].contains(item)){
                    list[j].remove(list[j].indexOf(item));
                }
            }
        }
    }
}
