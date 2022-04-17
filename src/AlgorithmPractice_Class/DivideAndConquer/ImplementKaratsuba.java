package AlgorithmPractice_Class.DivideAndConquer;

import java.util.Scanner;

public class ImplementKaratsuba {
    static int count;
    public static void main(String[] args) {
        count = 0;
        Scanner scanner = new Scanner(System.in);
        int threshold = scanner.nextInt();
        String largeA = scanner.next();
        String largeB = scanner.next();


    }
}
class Karatsuba extends LargeInteger{
    static LargeInteger karatsuba(LargeInteger A, LargeInteger B, int threshold){
        count++;

        int n = Math.max(A.list.size(), B.list.size());
        if(A.value.equals("0") || B.value.equals("0")){
            return new LargeInteger("0");
        }else if(n <= threshold){
            return A.traditionalMultiply(B);
        }else{
            int m = n/2;
            LargeInteger X = A.divideByExponential(m);
            LargeInteger Y = A.remainderByExponential(m);
            LargeInteger W = B.divideByExponential(m);
            LargeInteger Z = B.remainderByExponential(m);

            LargeInteger R = karatsuba(X.add(Y), W.add(Z),threshold);
            LargeInteger P = karatsuba(X,W,threshold);
            LargeInteger Q = karatsuba(Y,Z,threshold);

            LargeInteger result1 = P.powByExponential(2*m);

            return result1;
        }
    }
}
