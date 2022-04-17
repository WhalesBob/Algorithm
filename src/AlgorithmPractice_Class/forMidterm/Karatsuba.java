package AlgorithmPractice_Class.forMidTerm;

import java.util.ArrayList;
import java.util.Scanner;

public class Karatsuba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int threshold = scanner.nextInt();
        String A = scanner.next();
        String B = scanner.next();

        LargeInteger largeA = new LargeInteger(A);
        LargeInteger largeB = new LargeInteger(B);

        LargeInteger C = LargeInteger.multiply(largeA,largeB,threshold);
        C.print();
    }
}
class LargeInteger{
    static int count = 0;
    public String value;
    public ArrayList<Integer> list;

    public LargeInteger(){ // checked
        this.value = Integer.toString(0);
        this.list = new ArrayList<>();
    }

    public LargeInteger(String value) { // checked
        this.value = value;
        list = new ArrayList<>();
        String take = new StringBuilder(value).reverse().toString();
        for(int i = 0; i < take.length(); i++){
            list.add(Integer.parseInt(take.substring(i,i+1)));
        }
    }

    static LargeInteger add(LargeInteger A, LargeInteger B){ // checked
        LargeInteger C = new LargeInteger();
        int carry = 0, i = 0, data, remainder;
        while((i < A.list.size()) && (i < B.list.size())){
            data = A.list.get(i) + B.list.get(i);
            remainder = data % 10;
            C.list.add(remainder + carry);
            carry = data / 10;
            i++;
        }
        while(i < A.list.size()){
            data = (carry + A.list.get(i++));
            C.list.add(data % 10);
            carry = data / 10;
        }
        while(i < B.list.size()){
            data = (carry + B.list.get(i++));
            C.list.add(data % 10);
            carry = data / 10;
        }
        if(carry != 0){
            C.list.add(carry);
        }
        C.makeValue();
        return C;
    }
    int largeIntegerToInt(){ // checked
        int data = 0;
        for(int i = 0; i < this.list.size(); i++){
            data += this.list.get(i) * Math.pow(10,i);
        }
        return data;
    }
    LargeInteger traditionalMultiply(LargeInteger B){ // checked
        int a = this.largeIntegerToInt(), b = B.largeIntegerToInt();
        return new LargeInteger(Integer.toString(a*b));
    }
    LargeInteger powByExponential(int m){ // checked
        LargeInteger result = new LargeInteger();
        if((this.list.size() == 0) || this.value.equals("0")){
            return new LargeInteger();
        }else{
            for(int i = 0; i < m; i++){
                result.list.add(0);
            }
            result.list.addAll(this.list);
            result.makeValue();
            return result;
        }
    }
    LargeInteger remainderByExponential(int m){ // checked
        LargeInteger result = new LargeInteger();
        if(this.list.size() == 0){
            return new LargeInteger();
        }else{
            int k = Math.min(m, this.list.size());
            for(int i = 0; i < k; i++){
                result.list.add(this.list.get(i));
            }
            result.removeLeadingZero();
            result.makeValue();
            return result;
        }
    }
    void removeLeadingZero(){ // checked
        for(int i = this.list.size()-1; i >0; i--){
            int data = this.list.get(i);
            if(data == 0){
                this.list.remove(i);
            }else{
                break;
            }
        }
    }
    LargeInteger divideByExponential(int m){ // checked;
        LargeInteger result = new LargeInteger();
        for(int i = m; i < this.list.size(); i++){
            result.list.add(this.list.get(i));
        }
        result.makeValue();
        return result;
    }
    void makeValue(){
        if(this.value.equals("0")){
            StringBuilder result = new StringBuilder();
            for(int i = this.list.size()-1; i >= 0; i--){
                result.append(this.list.get(i));
            }
            this.value = result.toString().equals("") ? Integer.toString(0) : result.toString();
        }
    }
    static LargeInteger multiply(LargeInteger A, LargeInteger B, int threshold){
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

            LargeInteger result1 = multiply(X,W,threshold).powByExponential(2*m);
            LargeInteger result2 = add(multiply(X,Z,threshold),multiply(W,Y,threshold)).powByExponential(m);
            LargeInteger result3 = multiply(Y,Z,threshold);

            return add(add(result1,result2),result3);
        }
    }
    void print(){
        System.out.println(count);
        this.makeValue();
        System.out.println(this.value);
    }
}