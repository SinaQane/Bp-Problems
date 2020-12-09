import java.util.Scanner;

public class Main {

    public static void FillArray(String[][] Arr, int tool) {
        for (int i = 0; i < tool; i++) {
            for (int j = 0; j < tool; j++) {
                Arr[i][j] = ".";
            }
        }
    }

    public static void Print(int n, String[][] Arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.println("");
        }
    }

    public static void FillSquare(int xU, int xD, int yL, int yR, String[][] Arr) {
        for (int i = yL; i <= yR; i++) {
            Arr[xU][i] = "#";
            Arr[xD][i] = "#";
            Arr[i][yL] = "#";
            Arr[i][yR] = "#";
        }
    }

    public static void FillDiamond(int xU, int xD, int yL, int yR, int n, String[][] Arr) {
        int yU = yL + n;
        int yD = yU;
        int xL = xU + n;
        int xR = xL;

        for (int j = 0; j <= n; j++) {
            Arr[xU + j][yU - j] = "#";
        }
        for (int j = 0; j <= n; j++) {
            Arr[xL + j][yL + j] = "#";
        }
        for (int j = 0; j <= n; j++) {
            Arr[xD - j][yD + j] = "#";
        }
        for (int j = 0; j <= n; j++) {
            Arr[xR - j][yR - j] = "#";
        }
    }

    public static void AllSquares(int n, int number_of_squares, String[][] Arr) {
        int tool = n;
        int xU = 0;
        int xD = n - 1;
        int yL = 0;
        int yR = n - 1;
        
        FillSquare(xU, xD, yL, yR, Arr);
        
        for (int i = 0; i < number_of_squares - 1; i++) {
        
            xU = (int) ((xU + 1 + (int) ((xU + xD) / 2)) / 2);
            xD = tool - xU - 1;
            yL = xU;
            yR = xD;
            n = (int) Math.floor((double) n / 2);
            
            FillSquare(xU, xD, yL, yR, Arr);
        }
    }

    public static void AllDiamonds(int n, int number_of_diamonds, String[][] Arr) {

        int tool = n;
        int xU = 1;
        int xD = n - 2;
        int yL = 1;
        int yR = n - 2;

        n = (int) Math.floor((double) n / 2) - 1;

        FillDiamond(xU, xD, yL, yR, n, Arr);

        for (int i = 0; i < number_of_diamonds - 1; i++) {
        
            int xL = xU + n;
            xU = (int) ((xU + xL) / 2) + 1;
            xD = tool - xU - 1;
            yL = xU;
            yR = xD;
            n = (int) Math.floor((double) n / 2) - 1;
            
            FillDiamond(xU, xD, yL, yR, n, Arr);
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        if (n == 1) {
            System.out.println("###");
            System.out.println("#.#");
            System.out.println("###");
        } else {
            int number_of_squares = (int) Math.ceil((double) n / 2);

            int number_of_diamonds = (int) Math.floor((double) n / 2);

            int tool = (int) Math.pow(2, (int) Math.floor((double) n / 2) + 2) - 1;

            String[][] A = new String[tool][tool];

            FillArray(A, tool);

            AllSquares(tool, number_of_squares, A);

            AllDiamonds(tool, number_of_diamonds, A);

            Print(tool, A);
        }
    }
}
