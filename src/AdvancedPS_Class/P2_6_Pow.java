package AdvancedPS_Class;

import java.math.BigDecimal;
import java.util.Scanner;

public class P2_6_Pow {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        int n = scanner.nextInt();

        BigDecimal X = new BigDecimal(Double.toString(x));
        System.out.printf("%.5f\n",implementPow(X,n));

    }
    static BigDecimal implementPow(BigDecimal x, int n){

        if(n == 1){
            return x;
        }else if(n == -1){
            return (BigDecimal.valueOf(1).divide(x,10,BigDecimal.ROUND_HALF_UP));
        }else if(n > 1){
            return implementPow(x,n-1).multiply(x);
        }else{
            return implementPow(x,n+1).divide(x,10,BigDecimal.ROUND_HALF_UP);
        }
    }
}
