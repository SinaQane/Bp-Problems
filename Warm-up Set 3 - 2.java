import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static int Counter = 0;
    private static final ArrayList<String> Moves = new ArrayList<String>();;

    static void hanoi(char from, char to, char help, int n) {
        if (n == 1) {
            String temp = String.valueOf(from) + ' ' + String.valueOf(to);
            Moves.add(temp);
            Counter++;
            return;
        }
        hanoi(from, help, to, n - 1);

        String temp2 = String.valueOf(from) + ' ' + String.valueOf(to);
        Moves.add(temp2);
        Counter++;
        hanoi(help, to, from, n - 1);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        hanoi('A', 'C', 'B', n);

        System.out.println(Counter);

        for (String x : Moves) {
            System.out.println(x);
        }
    }
}
