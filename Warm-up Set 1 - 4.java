// Problem: Wtire a code that removes all vowels, add a dot before every consonants, and print the resulting string in lowercase.

import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String Str = scanner.nextLine().toLowerCase(); // Converting the string to lowercase at the beginning.

        int Length = Str.length(); // Length of Str
        String Answer = "";
        
        int i = 0;
        while (i < Length) {
            if (!(String.valueOf(Str.charAt(i)).equals("a") || 
                  String.valueOf(Str.charAt(i)).equals("e") ||
                  String.valueOf(Str.charAt(i)).equals("o") ||
                  String.valueOf(Str.charAt(i)).equals("u") ||
                  String.valueOf(Str.charAt(i)).equals("i") )){
                Answer = Answer + "." + String.valueOf(Str.charAt(i)); // If a character is not a vowel, then it's a consonant and we should add it to the Answer string with a dot before it.
            }
            i++;
        }
        
        System.out.println(Answer);
    }
}
