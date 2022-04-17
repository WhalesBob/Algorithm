package AlgorithmPractice_Class.forMidTerm;

import java.util.ArrayList;

public class Collatz1 {

    public static void main(String[] args) {

        int minCount = makeList(128);
        int max = 0;
        for(int i = 100; i < 1000; i++){
            int listCount = makeList(i);
            if(listCount <= minCount){
                max = i;
            }
        }

        System.out.println(max);

    }
    static int makeList(int n){
        ArrayList<Integer> list = new ArrayList<>();
        while(n != 1){
            list.add(n);
            if(n % 2 == 0){
                n /= 2;
            }else{
                n = 3*n+1;
            }
        }
        list.add(1);
        return list.size();
    }
}
