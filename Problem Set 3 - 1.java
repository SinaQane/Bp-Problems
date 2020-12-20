import java.util.Scanner;

public class Main {

    public static int f(int[][] Matrix, int n, int m, int x, int y, int Max_Value) {
        if (Matrix[x][y] == Max_Value) {
            return 1;
        } else {
            int right = 0;
            int left = 0;
            int up = 0;
            int down = 0;
            if (y != m - 1) {
                if (Matrix[x][y] < Matrix[x][y + 1]) {
                    right = f(Matrix, n, m, x, y + 1, Max_Value);
                }
            }
            if (y != 0) {
                if (Matrix[x][y] < Matrix[x][y - 1]) {
                    left = f(Matrix, n, m, x, y - 1, Max_Value);
                }
            }
            if (x != 0) {
                if (Matrix[x][y] < Matrix[x - 1][y]) {
                    up = f(Matrix, n, m, x - 1, y, Max_Value);
                }
            }
            if (x != n - 1) {
                if (Matrix[x][y] < Matrix[x + 1][y]) {
                    down = f(Matrix, n, m, x + 1, y, Max_Value);
                }
            }
            int horizontal = Math.max(right, left);
            int vertical = Math.max(up, down);
            int Max = Math.max(horizontal, vertical);
            return Max + 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] Matrix = new int[n][m];
        int Max_Value = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int temp = scanner.nextInt();
                Matrix[i][j] = temp;
                if (temp>Max_Value){
                    Max_Value = temp;
                }
            }
        }
        int Answer = 1;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                int Temp = f(Matrix, n, m, x, y, Max_Value);
                if (Temp > Answer){
                    Answer = Temp;
                }
            }
        }
        System.out.println(Answer);
    }
}
