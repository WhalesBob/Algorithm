// 유튜브를 보다가, 누군가가 어려웠다고 했던 문제라서, 쉬울것 같은데? 싶어 나도 풀어 보는 문제
// 숫자에, 원화 돈찍는것마냥 콤마(Comma,',')를 삽입해서 리턴해 보자.

import java.util.Scanner;

public class InsertingComma { // testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(insertComma(n));
    }

    static String insertComma(int n){ // implementing
        StringBuilder before = new StringBuilder(Integer.toString(n)).reverse();
        StringBuilder after = new StringBuilder();

        for(int i = 0; i < before.length(); i++){
            after.append(before.charAt(i));
            if(i % 3 == 2){
                after.append(',');
            }
        }

        return (after.reverse().toString());
    }
}
