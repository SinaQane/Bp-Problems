import java.util.Scanner;

class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] Nums = new int[n][n];

        Nums[0][0] = 1;
        Nums[1][0] = 1;
        Nums[1][1] = 1;

        for (int line1 = 2; line1 < n; line1++) {
            Nums[line1][0] = 1;
            Nums[line1][line1] = 1;

            for (int index = 1; index < line1; index++) {
                Nums[line1][index] = (Nums[line1 - 1][index - 1] + Nums[line1 - 1][index]) % 10;
            }
        }


        for (int line2 = 0; line2 < n; line2++) {

            for (int j = 1; j <= n - line2 - 1; j++) {
                System.out.print(" ");
            }
            for (int i = 0; i <= line2; i++) {
                System.out.print(Nums[line2][i] + " ");
            }
            for (int j = 1; j < n - line2 - 1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
