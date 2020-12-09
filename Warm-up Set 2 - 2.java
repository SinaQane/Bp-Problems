import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] A = new int[n + 2];
        int[] B = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            A[i] = i;
            B[i] = scanner.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (B[i] > B[j]) {
                
                    int temp = B[i]; // Simple swaping two variables
                    B[i] = B[j];
                    B[j] = temp;
                    
                    int temp2 = A[i]; // We use related sort to map the objects in A to B without using map.
                    A[i] = A[j];
                    A[j] = temp2;

                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(A[i] + " ");
        }
    }
} 
  
