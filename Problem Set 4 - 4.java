import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num1 = scanner.nextLine();
        String num2 = scanner.nextLine();
        StringBuilder Answer = new StringBuilder();

        if ((num1.charAt(0) + "").equals("-") && !(num2.charAt(0) + "").equals("-")) {
            Answer.append("-");
            num1 = num1.substring(1);
        } else if (!(num1.charAt(0) + "").equals("-") && (num2.charAt(0) + "").equals("-")) {
            Answer.append("-");
            num2 = num2.substring(1);
        } else if ((num1.charAt(0) + "").equals("-") && (num2.charAt(0) + "").equals("-")) {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        }

        if (num1.length() == 0 || num2.length() == 0) {
            System.out.println(0);
        } else {
            int[] result = new int[num1.length() + num2.length()];
            int index1 = 0, index2 = 0;
            for (int i = num1.length() - 1; i >= 0; i--) {
                int temp = 0;
                int char1 = Integer.parseInt(num1.charAt(i) + "");
                index2 = 0;
                for (int j = num2.length() - 1; j >= 0; j--) {
                    int char2 = Integer.parseInt(num2.charAt(j) + "");
                    int sum = char1 * char2 + result[index1 + index2] + temp;
                    temp = sum / 10;
                    result[index1 + index2] = sum % 10;
                    index2++;
                }
                if (temp > 0)
                    result[index1 + index2] += temp;
                index1++;
            }

            int len = result.length - 1;
            while (len >= 0 && result[len] == 0) {
                len--;
            }
            if (len == -1) {
                System.out.println(0);
            } else {
                for (int i = len; i >= 0; i--) {
                    Answer.append(result[i]);
                }
                System.out.println(Answer);
            }
        }
    }
}
