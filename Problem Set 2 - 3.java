// Solution 1. (By Nima Amoey)

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        int x = (int) Math.pow(2, (n / 2) + 2) - 1;
        String[][] str = new String[x][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                str[i][j] = ".";
            }
        }
        for (int j = 1; Math.pow(2, j) < x / 2 + 2; j++) {
            int k = x / 2 - (int) Math.pow(2, j) + 1;
            for (int l = k; l < x / 2 + 1; l++) {
                str[k][l] = "#";
                str[l][k] = "#";
            }
            for (int l = k, h = k; l < x / 2 + 1 && h >= 0; l++, h--) {
                str[l][h] = "#";
                str[h][l] = "#";
            }
        }
        for (int i = 0; i < x / 2 + 1; i++) {
            for (int j = 0; j < x / 2; j++) {
                str[i][x - 1 - j] = str[i][j];
            }
        }
        for (int i = 0; i < x / 2; i++) {
            for (int j = 0; j < x; j++) {
                str[x - 1 - i][j] = str[i][j];
            }
        }
        if (n % 2 == 0) {
            str[x / 2][x / 2 - 1] = ".";
            str[x / 2][x / 2 + 1] = ".";
            str[x / 2 - 1][x / 2] = ".";
            str[x / 2 + 1][x / 2] = ".";
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(str[i][j]);
            }
            System.out.println("");
        }
    }
}

// Solution 2. (By Negin Lotfi)

/* import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[][] str = new String[1024][1024];
        double m = Math.pow(2, Math.floor(n / 2) + 2) - 1;
        for (int i = 0; i < Math.pow(2, Math.floor(n / 2) + 2) - 1; i++) {
            for (int j = 0; j < Math.pow(2, Math.floor(n / 2) + 2) - 1; j++) {
                str[i][j] = ".";
            }
        }
        for (int i = 0; i < Math.pow(2, Math.floor(n / 2) + 2) - 1; i++) {
            str[0][i] = "#";
            str[(int) m -1][i] = "#";
            str[i][0] = "#";
            str[i][(int) m-1] = "#";
        }
        int tavan = 4;
        int y = 0;
        for(int i = 1;i<=Math.ceil((n-1)/2);i++) {
            y += (int) Math.ceil(m / tavan);
            str[y][y] = "#";
            tavan *= 2;
            for (int j = y; j < m - y; j++) {
                str[y][j] = "#";
                str[(int) m - y-1][j] = "#";
                str[j][y] = "#";
                str[j][(int) m - y-1] = "#";
            }
        }
        int tavan1 = 2;
        int z = 1;
        int sum = 0;
        for (int i = 1; i <= (n / 2); i++) {
            sum += Math.ceil(m / tavan1);
            for (int j = z; j <= (m / 2); j++) {
                str[sum - j][j] = "#";
                str[sum - j][(int) m - j - 1] = "#";
                str[(int) m - sum - 1 + j][j] = "#";
                str[(int) m - sum - 1 + j][(int) m - j - 1] = "#";
            }
            tavan1 *= 2;
            z += m / tavan1 + 1;


        }

        for (int i = 0; i < Math.pow(2, Math.floor(n / 2) + 2) - 1; i++) {
            for (int j = 0; j < Math.pow(2, Math.floor(n / 2) + 2) - 1; j++) {
                System.out.print(str[i][j]);
            }
            System.out.println();
        }
    }
} */


// Solution 3. (By Sina Ghaseminejad)

/*  import java.util.Scanner;

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
} */
