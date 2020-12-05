// Problem: Write a code that converts a number from base a to base b where 1<a<11 and 1<b<17.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] line_arr = line.split("\\s");

        String n = line_arr[0]; // Since our number in base 2 can be very very big, we can't even use long variables.
        // We can use big int but it's too messy, strings are the best for loops and  our usage.
        int a = Integer.parseInt(line_arr[1]); // Converting from base a
        int b = Integer.parseInt(line_arr[2]); // Converting to base b

        int i = 0;
        int Number = 0; // We need to convert out number to base 10 first. It's well known that this can be done using this method.
        while (i < n.length()) {
            Number += Integer.parseInt(String.valueOf(n.charAt(i))) * Math.pow(a, n.length() - i - 1);
            i++;
        }

        StringBuilder Answer = new StringBuilder(); // A string builder for putting our final answer in it.

        while (Number > 0) { // We need to keep dividing Number by b until it reaches 0, then, write the reminders in a reversed order.
            int temp = Number % b;
            Number = Number / b;
            if (temp < 10) { // We can't show numbers bigger than 9 as a digit. Therefore we use alphabet to take their place.
                Answer.append(String.valueOf(temp));
            } else if (temp == 10) {
                Answer.append("a");
            } else if (temp == 11) {
                Answer.append("b");
            } else if (temp == 12) {
                Answer.append("c");
            } else if (temp == 13) {
                Answer.append("d");
            } else if (temp == 14) {
                Answer.append("e");
            } else if (temp == 15) {
                Answer.append("f");
            }

        }
        StringBuilder Final = Answer.reverse(); // Reversing the answer as told above.
        System.out.println(Final);

    }
}
