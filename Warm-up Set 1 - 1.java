// Problem: Write a code that converts number n from base 10 to base 2.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int Number = scanner.nextInt();
        
        StringBuilder Answer = new StringBuilder();

        while(Number > 0) {
            int temp = Number % 2; // Typical base 10 to base 2 convertor. We just divide Number by 2 until it hits 0 and put the reminders in a reversed order.
            Number = Number / 2;
            Answer.append(String.valueOf(temp));
        }
        
        StringBuilder Final_Answer = Answer.reverse(); // Reversing Answer since the reminders mustt be written in a reversed order.
        System.out.println(Final_Answer);

    }
}
