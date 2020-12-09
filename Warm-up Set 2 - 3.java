import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextInt();
        long M = scanner.nextInt();

        long[] A = new long[(int)N+2];
        for (long i = 1; i<=N; i++){
            A[(int)i] = scanner.nextInt();
        }

        long[][] Interval = new long[(int)M+2][2];

        for (long i = 1; i<=M; i++) {
            Interval[(int)i][0] = scanner.nextInt();
            Interval[(int)i][1] = scanner.nextInt();
        }

        // We save A[1]+A[2]+...+A[n] in Sum_to_n[n] since we have 256 Mb memory and only few seconds for solving the problem.
        // Therefore, the sum A[i]+A[i+1]+...+A[j] will be equal to Sum_to_n[j] - Sum_to_n[i-1] (This is called a partial sum).
        long[] Sum_to_n = new long[(int)N+2];
        Sum_to_n[1] = A[1];
        for (int i = 2; i<=N; i++){
            Sum_to_n[i] = Sum_to_n[i-1] + A[i];
        }

        for (long i = 1; i<=M; i++){
            long Start = Interval[(int)i][0];
            long Stop = Interval[(int)i][1];
            System.out.println(Sum_to_n[(int)Stop] - Sum_to_n[(int)Start-1]);
        }


    }
} 
  
