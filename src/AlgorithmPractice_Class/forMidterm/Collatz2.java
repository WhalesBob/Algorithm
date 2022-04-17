package AlgorithmPractice_Class.forMidTerm;

public class Collatz2 {
    static int max;
    public static void main(String[] args) {
        max = 0;
        int maxIndex = 0;
        for(int i = 90000; i < 99999; i++){
            int listCount = makeList(i);
            if(max < listCount){
                maxIndex = i;
                max = listCount;
            }
        }
        System.out.println(max);
        System.out.println(maxIndex);
    }
    static int makeList(int n){
        int count = 0;
        while(n != 1) {
            count++;
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        return ++count;
    }
}
