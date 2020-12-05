import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int Sigma_A2 = 0;
        int Sigma_A = 0;

        // R = a_{1}a_{2} + a_{1}a_{3} + ... + a_{1}a_{n} + a_{2}a_{3} + a_{2}a_{4} + ... + a_{2}a_{n} + ... + a_{n-1}a_{n}
        //   = ( (a_{1} + a_{2} + ... + a_{n})^2 - (a_{1}^2 + a_{2}^2 + ... + a_{n}^2) ) / 2
        for (int i = 1; i <= n; i++) {
            int temp = scanner.nextInt();
            Sigma_A += temp; // For a_{1} + a_{2} + ... + a_{n}
            Sigma_A2 += Math.pow(temp, 2); // For a_{1}^2 + a_{2}^2 + ... + a_{n}^2
        }

        double R = (Math.pow(Sigma_A, 2) - Sigma_A2) / 2;
        int counter = 0; // For counting the number of "Two-Faced" numbers

        for (int i = 2; i <= R; i++) {

            int sum = 0;
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                sum += Math.pow(Integer.parseInt(String.valueOf(String.valueOf(i).charAt(j))), 2); // Calculating the given sum for each number i in range (2, R).
            }
            
            boolean f1 = false; // Checking if sum is prime (1 is also a prime in this problem)
            if (sum != 1) {
                for (int k = 2; k <= sum / 2; ++k) {
                    if (sum % k == 0) {
                        f1 = true;
                        break;
                    }
                }
            } else if (sum == 1) {
                f1 = false;
            }
            
            boolean f2 = false; // Checking if i is prime (1 is also a prime in this problem)
            if (i != 1) {
                for (int k = 2; k <= i / 2; ++k) {
                    if (i % k == 0) {
                        f2 = true;
                        break;
                    }
                }
            } else if (sum == 1) {
                f2 = false;
            }
            
            if ((f1 && !f2) || (!f1 && f2)) { // If sum is prime but i isn't or if i is prime and sum isn't, we add one to the counter.
                counter++;
            }
        }
        
        System.out.println(counter);
    }
}
