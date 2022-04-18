package AlgorithmPractice_Class.forMidTerm;

public class P03 {
    static int F[];
    public static void main(String[] args) {
        F = new int[1000];
        F[0] = 1;
        F[1] = 2;

        System.out.println(fibonacci(999));

    }
    static int fibonacci(int n){
        for(int i = 2; i <= n; i++){
            F[i] = (F[i-2] + F[i-1]) % 1000;
        }
        return F[n];
    }
}
