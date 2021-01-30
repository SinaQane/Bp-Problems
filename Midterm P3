import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String w = scanner.nextLine();
        String s = scanner.nextLine();
        for (int i = 0; i<w.length(); i++){
            if (s.equals("")){
                break;
            }
            if ((w.charAt(i)+"").equals(s.charAt(0)+"")){
                s = s.substring(1);
            }
        }
        if(s.equals("")){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
