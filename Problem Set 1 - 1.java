import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long m = scanner.nextLong();
        long n = scanner.nextLong();

        long x = scanner.nextLong();
        long y = scanner.nextLong();

        scanner.nextLine();

        String moves = scanner.nextLine();

        long number_of_moves = moves.length();

        for (int i = 0; i < number_of_moves; i++) {
            if (String.valueOf(moves.charAt(i)).equals("U")) {
                if (!(x % 2 == 0 && y % 3 == 2)) { // We mustn't hit x = 2*s and y = 3*t squares. So in x = 2*s and y = 3*t - 1 squares, we mustn't move Up.
                    if (y != n) { // We mustn't go out of the table
                        y += 1;
                    }
                }
            } else if (String.valueOf(moves.charAt(i)).equals("R")) {
                if (!(x % 2 == 1 && y % 3 == 0)) { // We mustn't hit x = 2*s and y = 3*t squares. So in x = 2*s + 1 and y = 3*t squares, we mustn't move right.
                    if (x != m) { // We mustn't go out of the table
                        x += 1;
                    }
                }
            } else if (String.valueOf(moves.charAt(i)).equals("D")) {
                if (!(x % 2 == 0 && y % 3 == 1)) { // We mustn't hit x = 2*s and y = 3*t squares. So in x = 2*s and y = 3*t + 1 squares, we mustn't move down.
                    if (y != 1) { // We mustn't go out of the table
                        y -= 1;
                    }
                }
            } else if (String.valueOf(moves.charAt(i)).equals("L")) {
                if (!(x % 2 == 1 && y % 3 == 0)) { // We mustn't hit x = 2*s and y = 3*t squares. So in x = 2*s + 1 and y = 3*t squares, we mustn't move left.
                    if (x != 1) { // We mustn't go out of the table
                        x -= 1;
                    }
                }
            }
        }

        System.out.println(x + " " + y);
    }
}
