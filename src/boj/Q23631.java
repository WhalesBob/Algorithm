package boj;

import java.io.*;

public class Q23631 {
    static int location;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int repeat = Integer.parseInt(str);
        for(int i = 0; i < repeat; i++){
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            int leftMeter = (n-1) -addAll(k,n);

            String output;
            if(location > 0){
                output = String.format("%d L", location-leftMeter);
            }else{
                output = String.format("%d R", location+leftMeter);
            }
            bw.write(output);
            if(i < repeat -1){
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }
    static int addAll(int k, int N){
        double statement = ((double)(-1*k) + Math.sqrt(Math.pow(k,2) + (8*k*(N-1)))) / (double)(2*k);
        int count = (int)Math.floor(statement);
        int sum = (k*count*(count+1)) / 2;
        location = (int)(k * Math.ceil((double)count / (double)2));
        if(count % 2 == 0){
            location *= -1;
        }
        return sum;
    }
}
