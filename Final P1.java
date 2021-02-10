import java.util.InputMismatchException;
import java.util.Scanner;

public class P1
{

    public static void main(String[] args)
    {
        // Get dimensions
        int m, n, p;
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        p = scanner.nextInt();
        if (m < 1 || n < 1 || p < 1)
            throw new InputMismatchException("m,n and p must be more than zero");
        // Create matrix A and At
        long[][] A = new long[m][n];
        long[][] At = new long[n][m];
        // Read them
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = At[j][i] = scanner.nextInt();

        // Create matrix B and Bt
        long[][] B = new long[n][p];
        long[][] Bt = new long[p][n];
        // Read them
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                B[i][j] = Bt[j][i] = scanner.nextInt();
        // Now calculate
        long[][] AtABt = transpose(multiply(multiply(At, A), B));
        long[][] C = multiply(multiply(multiply(B, AtABt), B), Bt);
        // Print
        for (long[] row : C)
        {
            for (long num : row)
                System.out.print(num + " ");
            System.out.println();
        }
    }
    /**
     * Multiply two matrix
     *
     * @param a First matrix
     * @param b Second matrix
     * @return The result
     */
    static long[][] multiply(final long[][] a, final long[][] b)
    {
        if (a[0].length != b.length)
            throw new IllegalArgumentException("Number of columns in A must be equal to number of rows in B");
        long[][] result = new long[a.length][b[0].length];
        for (long row = 0; row < result.length; row++)
            for (long column = 0; column < result[0].length; column++)
                for (long index = 0; index < a[0].length; index++)
                    result[(int)row][(int)column] += a[(int)row][(int)index] * b[(int)index][(int)column];

        return result;
    }

    /**
     * @param a The matrix
     * @return Transposed matrix
     */
    static long[][] transpose(final long[][] a)
    {
        long[][] result = new long[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[j][i] = a[i][j];

        return result;
    }
}
