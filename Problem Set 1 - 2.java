// Problem: Josephus problem for n, m.

// Solution 1.
 import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] People = new int[n]; // So, let me explain my algorithm. First we create an array named People representing the prople around the table.
        // In each step, when we kill these people, we turn their value from 1 to 0 (or true to false).
        // We use an infinite loop with condition that if the number of killed people hits the number of all people, it breaks.
        // We use a variable called last_person which takes the index of a person whenever someone dies. We print this index when the loop ends.
        // In the loop, we start counting the number of alive people using the counter variable, whenever we reach m,
        // we turn the value of this person from 1 to 0 and start counting again. 
        
        for (int i = 0; i < n; i++) {
            People[i] = 1;
        }

        int counter = 0; // Counts the number of alive people in every cycle until it hits m. That's when we need to kill again.
        int killed = 0; // Counts the number of killed people. Whenever it reaches m, the while loop ends. 
        int i = 0; // The index of people we're checking every time. It should be (mod n) since we're at a circular table.
        int last_person = 0; // The index of the last person we've killed every time.

        boolean bool = true;

        while (bool) {

            if (Adam[i] == 1) {
                counter++;
            }

            if (counter == m) {
                Adam[i] = 0;
                killed++;
                last_person = i + 1;
                counter = 0;
            }

            if (killed == n) {
                bool = false; // You can use "break;" here as well.
            }
            i++;
            i = i % n; // When we reach to the end of the table, since it's circular we start again from the beginning.
            // e.g. for n=5, we start checking 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ... 
            // Therefore, we need to use (mod n) indices.
        }

        System.out.println(last_person);

    }
}

// Solution 2.
/* import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (sum + m) % i;
        }

        System.out.println(sum + 1);
    }
} */
