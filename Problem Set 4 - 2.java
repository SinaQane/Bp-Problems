import java.util.Scanner;
public class Main {
    public static int f(int Dices, int Number){
        if (Dices == 1){
            if (Number>0 && Number<7){
                return 1;
            } else {
                return 0;
            }
        } else {
            return f(Dices-1, Number-1) + f(Dices-1, Number-2)
                 + f(Dices-1, Number-3) + f(Dices-1, Number-4)
                 + f(Dices-1, Number-5) + f(Dices-1, Number-6);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Dices = scanner.nextInt();
        int Number = scanner.nextInt();
        System.out.println(f(Dices, Number));
    }
}
