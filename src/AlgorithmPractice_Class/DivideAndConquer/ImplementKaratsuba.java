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

        LargeInteger A = new LargeInteger(largeA);// Midterm Modify
        LargeInteger B = new LargeInteger(largeB);// Midterm Modify
        LargeInteger C = Karatsuba.karatsuba(A,B,threshold);// Midterm Modify
        C.print();// Midterm Modify
        System.out.println(count);// Midterm Modify
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

            LargeInteger result1 = P.powByExponential(2*m).add(R.add(P).add(Q).powByExponential(m)).add(Q); // Midterm Modify

            return result1;
        }
    }
}
