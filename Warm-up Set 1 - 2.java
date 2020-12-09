// Problem: Write a code that finds the length longest ascending subsequence in a sequence of numbers.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] A = new long[n];
        
        int i = 0;
        while(i < n) {
            A[i] = scanner.nextLong(); // Saving all numbers in an array
            i++;
        }
        
        int max = 1; // Since every number itself is an ascending subsequence.
        int counter = 1;
        int j = 0;
        while (j < n - 1) {
            if (A[j] < A[j+1]){
                counter++; // The counter variable counts the number of terms in an ascending subsequence for us. If we reach a number than makes our subsequence descending again, it stops counting.
            } else {
                if (max < counter) {
                    max = counter; // We check if our counter is greater than the max length of ascending subsequences so far or not. If yes, max = counter, and we continue. Otherwise, counter just begins counting again.
                }
                counter = 1;
            }
            j++;
        }
        if (max < counter) {
            max = counter;
        }
        System.out.println(max);
    }
}
