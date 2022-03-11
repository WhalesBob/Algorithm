import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Q2693 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for(int i = 0; i < count; i++) {
            scanner.nextLine();
            String[] str = scanner.nextLine().split(" ");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                numbers.add(Integer.parseInt(str[j]));
            }
            Collections.sort(numbers);
            System.out.println(numbers.indexOf(7));
        }
    }
}
