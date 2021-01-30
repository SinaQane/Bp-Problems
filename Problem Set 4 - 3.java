import java.util.Scanner;

public class Main {
    public static int gcd(int n1, int n2) {
        if (n1 == 0) {
            return n2;
        }
        if (n2 == 0) {
            return n1;
        }
        int n;
        for (n = 0; ((n1 | n2) & 1) == 0; n++) {
            n1 >>= 1;
            n2 >>= 1;
        }
        while ((n1 & 1) == 0) {
            n1 >>= 1;
        }
        do {
            while ((n2 & 1) == 0) {
                n2 >>= 1;
            }
            if (n1 > n2) {
                int temp = n1;
                n1 = n2;
                n2 = temp;
            }
            n2 = (n2 - n1);
        } while (n2 != 0);
        return n1 << n;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] numerator = new int[n];
        int[] denominator = new int[m];
        for (int i = 0; i < n; i++){
            numerator[i] = scanner.nextInt();
        }
        for (int j = 0; j < m; j++){
            denominator[j] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int temp = gcd(numerator[i], denominator[j]);
                if (temp>1){
                    numerator[i] = numerator[i]/temp;
                    denominator[j] = denominator[j]/temp;
                }
            }
        }
        int Numerator = 1;
        int Denominator = 1;
        for (int i : numerator){
            Numerator *= i;
        }
        for (int j : denominator){
            Denominator *= j;
        }
        System.out.println(Numerator + " " + Denominator);
    }
}
