import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long sqrt = (long) Math.sqrt(n) + 1;
        String str = "";
        long i = 2;
        while (n > 1) {
            int counter = 0;
            while (n % i == 0) {
                n = n / i;
                counter++;
            }
            if (counter == 1) {
                str = i + "*" + str;
            } else if (counter != 0) {
                str = i + "^" + counter + "*" + str;
            }
            i++;
            if (i > sqrt+10) {
                if (str.equals("")){
                    str = n + " ";
                } else {
                    str = n + "*" + str;
                }
                break;
            }
        }
        System.out.println(str.substring(0, str.length() - 1));
    }
}
