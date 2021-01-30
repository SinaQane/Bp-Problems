import java.util.ArrayList;
import java.util.Scanner;

public class P1 {
    public static boolean isPrime(int n) {
        int k = (int) Math.sqrt(n) + 1;
        if (n <= 1)
            return false;
        for (int i = 2; i < k; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<int[]> tuples = new ArrayList<>();
        for (int i = 2; i < n / 2 + 1; i++) {
            if (isPrime(i)){
                if (isPrime(n-i)){
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = n-i;
                    tuples.add(temp);
                }
            }
        }
        int max = 0;
        for (int[] array : tuples){
            max = Math.max(max, array[0]);
        }
        System.out.println(max);
    }
}
