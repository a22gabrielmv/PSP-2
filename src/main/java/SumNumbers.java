import java.util.ArrayList;
import java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cases = Integer.parseInt(scanner.nextLine());
        ArrayList<Integer> array= new ArrayList();

        int count=0;
        int number;

        while (count<cases){
            number = Integer.parseInt(scanner.nextLine());
            array.add(number);
            count++;
        }
        count=0;

        for (int integer : array) {
            count += integer;
        }

        System.out.println((int) Math.pow(count,2));

    }
}
