import java.io.*;
import java.util.*;

public class Q15654 {
    static ArrayList<Integer[]> permutationArray;
    public static void main(String[] args) throws IOException {
        permutationArray = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        ArrayList<Integer> numberArray = convertToArray(br);
        Collections.sort(numberArray);
        Integer[] plusArray = new Integer[m];
        makePermutation(plusArray,numberArray,m,n,0);

        for(int i = 0; i < permutationArray.size(); i++){
            for(int j = 0; j < permutationArray.get(i).length; j++){
                print(Integer.toString(permutationArray.get(i)[j]),bw);
                if(j < permutationArray.get(i).length -1){
                    print(" ",bw);
                }
            }
            print("\n",bw);
        }
        bw.close();
    }
    static ArrayList<Integer> convertToArray(BufferedReader br) throws IOException {
        String str = br.readLine();
        StringTokenizer token = new StringTokenizer(str, " ");
        int count = token.countTokens();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            array.add(Integer.parseInt(token.nextToken()));
        }
        return array;
    }
    static void makePermutation(Integer[] beingMadeArray, ArrayList<Integer> intArray, int leftElement,int n, int index){
        if(leftElement == 0){
            if(!includeInList(beingMadeArray)){
                permutationArray.add(beingMadeArray);
            }
        }else{
            for(int i = 0; i < n; i++){
                ArrayList<Integer> newList = (ArrayList<Integer>)intArray.clone();
                Integer[] subArray = beingMadeArray.clone();
                subArray[index] = newList.remove(i);
                makePermutation(subArray,newList,(leftElement-1),n-1,(index+1));
            }
        }
    }
    static boolean includeInList(Integer[] array){
        for (Integer[] integers : permutationArray) {
            if (Arrays.equals(integers, array)) {
                return true;
            }
        }
        return false;
    }
    static void print(String str,BufferedWriter bw) throws IOException{
        bw.write(str);
        bw.flush();
    }
}
