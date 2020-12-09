import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N = scanner.nextInt();
    static int Counter = 0;

    static boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solveNQUtil(int board[][], int col) {

        if (col == N) {
            Counter++;
            return true;
        }

        boolean res = false;
        for (int i = 0; i < N; i++) {

            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                res = solveNQUtil(board, col + 1) || res;

                board[i][col] = 0;
            }
        }

        return res;
    }

    static void solveNQueen() {
        int[][] board = new int[N][N];

        if (!solveNQUtil(board, 0)) {
            Counter = 0;
        }
    }

    public static void main(String[] args) {
        solveNQueen();
        System.out.println(Counter);
    }
}
