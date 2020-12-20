import java.util.ArrayList;
import java.util.Scanner;

public class Main{


    public static double dist(int[] Point1, int[] Point2) {
        return Math.sqrt((long)Math.abs(Point1[0] - Point2[0]) * Math.abs(Point1[0] - Point2[0])
                + (long) Math.abs(Point1[1] - Point2[1]) * Math.abs(Point1[1] - Point2[1]));
    }

    public static double f(boolean[] Visited_Inner, boolean[] Visited_Outer,
                           double[][] Dists, int Index, boolean Mood, int Visited, int n){
        Visited++;
        if (n == 1){
            return 2 * Dists[0][0];
        }
        if (Mood){ // Mood == true whenever we're in an inner point
            Visited_Inner[Index] = true;
        } else { // Mood == false whenever we're in an outer point
            Visited_Outer[Index] = true;
        }
        if (Visited == 2 * n){
            Visited_Outer[Index] = false;
            Visited--;
            return Dists[0][Index];
        } else {
            ArrayList<Double> Temp = new ArrayList<Double>();
            for (int j = 0; j < n; j++){
                if (Mood){ // Mood == true whenever we're in an inner point
                    if (!Visited_Outer[j]){
                        double X = f(Visited_Inner, Visited_Outer, Dists, j, !Mood, Visited, n) + Dists[Index][j];
                        Temp.add(X);
                    }
                } else { // Mood == false whenever we're in an outer point
                    if (!Visited_Inner[j]){
                        double X = f(Visited_Inner, Visited_Outer, Dists, j, !Mood, Visited, n) + Dists[j][Index];
                        Temp.add(X);
                    }
                }
            }
            double Min = Temp.get(0);
            for (double i : Temp){
                Min = Math.min(Min, i);
            }
            Temp.clear();

            if (Mood){ // Mood == true whenever we're in an inner point
                Visited_Inner[Index] = false;
                Visited--;
            } else { // Mood == false whenever we're in an outer point
                Visited_Outer[Index] = false;
                Visited--;
            }
            return Min;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] Inner_Points = new int[n][2]; // Coordinates of inner cities and border cities
        int[][] Outer_Points = new int[n][2];
        boolean[] Visited_Inner = new boolean[n]; // We mark every city we visit with "true"
        boolean[] Visited_Outer = new boolean[n];
        for (int i = 0; i < n; i++) {
            Inner_Points[i][0] = scanner.nextInt();
            Inner_Points[i][1] = scanner.nextInt();
            Visited_Inner[i] = false;
        }
        for (int j = 0; j < n; j++) {
            Outer_Points[j][0] = scanner.nextInt();
            Outer_Points[j][1] = scanner.nextInt();
            Visited_Outer[j] = false;
        }
        double[][] Dists = new double[n][n]; // Calculating the distance between Inner_Points[i] and Outer_Point[j] for
        // every i, j so we don't calculate it every time and don't exceed the time limit.

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                Dists[i][j] = dist(Inner_Points[i], Outer_Points[j]);
            }
        }
        double Min = f(Visited_Inner, Visited_Outer, Dists, 0, true, 0, n);
        System.out.println(String.format("%.3f", Min));
    }
}
