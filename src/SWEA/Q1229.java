package SWEA;

import java.util.ArrayList;
import java.util.Scanner;

public class Q1229 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            int length = sc.nextInt();

            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < length; i++){
                list.add(sc.nextInt());
            }

            int commandCount = sc.nextInt();

            for(int i = 0; i < commandCount; i++){
                String command = sc.next();

                int location = sc.nextInt();
                int howMuch = sc.nextInt();

                if(command.equals("I")){
                    addCommand(sc,list,location,howMuch);
                }else{
                    deleteCommand(list,location,howMuch);
                }

            }
            System.out.printf("#%d ",test_case);
            for(int i = 0; i < 10; i++){
                System.out.printf("%d ",list.get(i));
            }
            System.out.println();
        }
    }
    static void addCommand(Scanner sc, ArrayList<Integer> list, int location, int howMuch){
        int[] numbers = new int[howMuch];
        for(int i = 0; i < howMuch; i++){
            numbers[i] = sc.nextInt();
        }

        for(int i = howMuch-1; i >= 0; i--){
            list.add(location,numbers[i]);
        }
    }
    static void deleteCommand(ArrayList<Integer> list, int location, int howMuch){
        for(int i = 0; i < howMuch; i++){
            list.remove(location);
        }
    }
}
