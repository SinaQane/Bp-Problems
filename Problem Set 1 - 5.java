import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        
        // My algorithm is simple. We start; If a character is a quotation mark, we change the mood.
        // This mood variable shows that in each step, how many quotation marks have been counted.
        // If the number of quotation marks in even, we're good to go and the parenthesis we see are valid.
        // If not, we just ignore the parenthesis until we reach another quotation mark.
        // While counting parenthesis, if the number of quotation marks is even, If we see a (,  we count it and add 1 to the number "right".
        // Every time we see a ), again, if the number of quotation marks is even, there are two cases:
        // 1. The number of (s is zero. This means that the parenthesis we saw was never opened and it's useless. So we add 1 to the number "left".
        // 2. The number of (s is not zero. This means that the parenthesis we saw was opened before. So it's ok for it to be there and
        //  we've counted a ( by mistake. So we subtract 1 from "right" to fix our mistake.
        
        int right = 0;
        int left = 0;
        boolean mood = true;

        for (int j = 0; j < string.length(); j++) {
            if (string.charAt(j) == '"') {
                mood = !mood; // If mood == true, it means that the number of quotation marks so far is even and if mood == false, odd.
            } else if (String.valueOf(string.charAt(j)).equals(")")) {
                if (mood) {
                    right++;
                    if (left > 0) {
                        left--;
                        right--;
                    }
                }
            } else if (String.valueOf(string.charAt(j)).equals("(")) {
                if (mood) {
                    left++;
                }
            }
        }

        System.out.println(right + " " + left);
    }
}
