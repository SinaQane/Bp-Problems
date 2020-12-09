import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = n / 2;

        String first_last = "";
        for (int i = 1; i <= k; i++) {
            first_last += " ";
        }
        first_last += "*";
        for (int i = 1; i <= k; i++) {
            first_last += " ";
        }

        System.out.println(first_last);

        String str = "";

        for (int j = 1; j < k; j++) {
            int in = 2 * j - 1;
            int out = (n - in - 2) / 2;

            for (int i = 1; i < out + 1; i++) {
                str += " ";
            }

            str += "*";

            for (int i = 1; i <= in; i++) {
                str += " ";
            }

            str += "*";

            for (int i = 1; i < out + 1; i++) {
                str += " ";
            }

            System.out.println(str);
            str = "";
        }

        String middle = "*";
        for (int i = 1; i <= n - 2; i++) {
            middle += " ";
        }
        middle += "*";
        System.out.println(middle);


        for (int j = k - 1; j >= 1; j--) {

            int in = 2 * j - 1;
            int out = (n - in - 2) / 2;

            for (int i = 1; i < out + 1; i++) {
                str += " ";
            }

            str += "*";

            for (int i = 1; i <= in; i++) {
                str += " ";
            }

            str += "*";

            for (int i = 1; i < out + 1; i++) {
                str += " ";
            }

            System.out.println(str);
            str = "";
        }

        System.out.println(first_last);
    }
}
