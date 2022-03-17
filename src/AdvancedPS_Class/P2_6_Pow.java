package AdvancedPS_Class;

public class P2_6_Pow {
    public static void main(String[] args) {

        System.out.printf("%.5f\n",implementPow(2,10));
        System.out.printf("%.5f\n",implementPow(2.1,3));
        System.out.printf("%.5f\n",implementPow(2,-2));
    }
    static double implementPow(double x, int n){
        if(n == 1){
            return x;
        }else if(n == -1){
            return ((double)1 / x);
        }else if(n > 1){
            return x * implementPow(x,n-1);
        }else{
            return ((double)1/x) * implementPow(x,n+1);
        }
    }
}
