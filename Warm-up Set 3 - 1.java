import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Removing first row and Column-th column for further use in det function
    public static ArrayList<ArrayList<Integer>> submatrix(ArrayList<ArrayList<Integer>> Matrix, int Column) {

        ArrayList<ArrayList<Integer>> Matrix_to_Change = new ArrayList<ArrayList<Integer>>();

        for (int l = 0; l < Matrix.size(); l++) {
            ArrayList<Integer> Temp = new ArrayList<Integer>();
            for (int k = 0; k < Matrix.size(); k++) {
                Temp.add(Matrix.get(l).get(k));
            }
            Matrix_to_Change.add(Temp);
        }

        Matrix_to_Change.remove(0);

        ArrayList<ArrayList<Integer>> New_Matrix = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> Temp : Matrix_to_Change) {
            Temp.remove(Column);
            New_Matrix.add(Temp);
        }
        return New_Matrix;
    }

    public static long det(ArrayList<ArrayList<Integer>> M) {
        long Det = 0;
        long mod = 1000000007;

        if (M.size() <= 2) {
            // Since our numbers are large, using (a*b % mod) may cause an overflow. Therefore, we need to use
            // (a % mod)*(b % mod) instead of that. And since we need positive answers, we need to use
            // ((a % mod)*(b % mod) + mod) % mod to calculate (a*b % mod).
            return (((((M.get(0).get(0) % mod) * (M.get(1).get(1) % mod) + mod) % mod)
                    - (((M.get(0).get(1) % mod) * (M.get(1).get(0) % mod) + mod) % mod)) + mod) % mod;
        } else {
            for (int i = 0; i < M.size(); i++) {
                Det = ((Det + ((long) Math.pow(-1, i) *
                        (((M.get(0).get(i) % mod) * (det(submatrix(M, i)) % mod) + mod) % mod)) % mod) + mod) % mod;
            }
        }

        return Det;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> Matrix = new ArrayList<ArrayList<Integer>>();

        // Getting our Matrix and putting it in an Arraylist
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> Temp = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                Temp.add(scanner.nextInt());
            }
            Matrix.add(Temp);
        }

        long Det = det(Matrix);

        System.out.println(Det);

    }
}
