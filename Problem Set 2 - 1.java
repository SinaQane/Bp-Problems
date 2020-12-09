import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int q = input.nextInt();

        long[] Numbers = new long[N];
        for (int i = 0; i < N; i++){
            Numbers[i] = input.nextLong();
        }

        long[] Sum_to_N = new long[N+2];
        Sum_to_N[1] =  Numbers[0];
        for (int i = 2; i <= N; i++){
            Sum_to_N[i] = Sum_to_N[i-1] + Numbers[i-1];
        }

        int[][] Intervals = new int[M][2];
        for (int i = 0; i < M; i++){
            Intervals[i][0] = input.nextInt();
            Intervals[i][1] = input.nextInt();
        }

        int[][] Orders = new int[q][4];
        for (int i = 0; i < q; i++){
            int temp = input.nextInt();
            if (temp == 1) {
                Orders[i][0] = temp;
                Orders[i][1] = input.nextInt();
                Orders[i][2] = input.nextInt();
                Orders[i][3] = input.nextInt();
            } else if (temp == 2) {
                Orders[i][0] = temp;
                Orders[i][1] = input.nextInt();
                Orders[i][2] = input.nextInt();
            }
        }

        long[] Answers = new long[q];
        int counter = 0;

        for (int i = 0; i < q; i++){
            if (Orders[i][0] == 1){
                Intervals[Orders[i][1]-1][0] = Orders[i][2];
                Intervals[Orders[i][1]-1][1] = Orders[i][3];
            } else if (Orders[i][0] == 2){
                int Left = Orders[i][1];
                int Right = Orders[i][2];
                long Temp = 0;
                for (int j = Left-1; j < Right; j++){
                    int temp_left = Intervals[j][0];
                    int temp_right = Intervals[j][1];
                    long temp_sum = Sum_to_N[temp_right] - Sum_to_N[temp_left-1];
                    Temp += temp_sum;
                }
                Answers[counter] = Temp;
                counter++;
                Temp = 0;
            }
        }
        for (int i = 0; i < counter; i++){
            System.out.println(Answers[i]);
        }

    }
}
