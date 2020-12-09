import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();


        String[] People = new String[N];
        for (int i = 0; i < N; i++){
            People[i] = input.next();
        }

        int[] Seats = new int[N];
        for (int i = 0; i < N; i++){
            Seats[i] = input.nextInt();
        }

        int[] TempSortedSeats = Arrays.copyOf(Seats,Seats.length);

        Arrays.sort(TempSortedSeats);

        int[] ReversedSeats = new int[N];

        for(int i = 0; i < N; i++){
            ReversedSeats[N-i-1] = TempSortedSeats[i];
        }

        int[] Indices = new int[N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(Seats[i]==ReversedSeats[j]){
                    Indices[i] = j;
                }
            }
        }
        int[] Indices2 = new int[N];
        for (int i = 0; i < N; i++){
            Indices2[i] = N - Indices[i] - 1;
        }

        String[] temp = new String[N];

        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                int temp_int = Indices2[j];
                temp[temp_int] = People[j];
            }
            People = Arrays.copyOf(temp, temp.length);
            Arrays.fill(temp, null);
        }
        for (int j = 0; j < N; j++){
            System.out.print(People[j]);
            System.out.print(" ");
        }
    }
}
