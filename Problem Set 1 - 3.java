    import java.util.Scanner;
    
    public class Main {
        public static void main(String[] args) {
    
            Scanner scanner = new Scanner(System.in);
    
            String str1 = scanner.nextLine().toLowerCase();
            String str2 = scanner.nextLine().toLowerCase();
            String alpha = scanner.nextLine();
    
            int len1 = str1.length();
            int len2 = str2.length();
            int len_alpha = alpha.length();
    
            int temp1 = 0;
            int temp2 = 0;
    
            String ans = "";
    
            // In case that two string were alike up to the last character of one of them, we need to put the smaller string as the answer.
            // e.g. str1 = "foo" and str2 = "foobar". In this case the answer is str1 = "foo".
            
            int min = 0;
            if (len1 < len2) { 
                min = len1;
                ans = str1;
            } else {
                min = len2;
                ans = str2;
            }
    
            int i = 0;
            while (i < min) {
                String char1 = String.valueOf(str1.charAt(i)); // The i-th characher of str1
                String char2 = String.valueOf(str2.charAt(i)); // The i-th characher of str2
                int j = 0;
                while (j < len_alpha) { // Looping through the alphabet in order to find the place of char1 and char2.
                    if (String.valueOf(alpha.charAt(j)).equals(char1)) {
                        temp1 = j; // The index of str1's i-th character in our given alphabetical order.
                    }
                    if (String.valueOf(alpha.charAt(j)).equals(char2)) {
                        temp2 = j; // The index of str2's i-th character in our given alphabetical order.
                    }
                    j++;
                }
                // If two characters we not alike, then the asnwer is the string with the smaller value for it's i-th character's index.
                if (temp1 < temp2) {
                    ans = str1;
                    break;
                } else if (temp1 > temp2) {
                    ans = str2;
                    break;
                }
                i++;
            }
            // The conversions asked in the problem. Nothing serious...
            String upper = String.valueOf(ans.charAt(0)).toUpperCase() + ans.substring(1);
            String reverse = new StringBuffer(upper).reverse().toString();
            System.out.println(upper + reverse);
        }
    }
