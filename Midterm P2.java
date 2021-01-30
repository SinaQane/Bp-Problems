import java.util.Scanner;

public class P2 {

    static boolean isSmaller(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return true;
        } else if (num2.length() < num1.length()) {
            return false;
        } else {
            for (int i = 0; i < num1.length(); i++) {
                if (num1.charAt(i) < num2.charAt(i)) {
                    return true;
                }
                else if (num1.charAt(i) > num2.charAt(i)) {
                    return false;
                }
            }
            return false;
        }
    }
    static String minus(String num1, String num2) { // Absolute value of num1-num2
        if (isSmaller(num1, num2)) { // Swap
            String tempStr = num1;
            num1 = num2;
            num2 = tempStr;
        }
        String str = "";

        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        int temp = 0;

        for (int i = 0; i < num2.length(); i++) {
            int sub = Integer.parseInt(num1.charAt(i) + "") - Integer.parseInt(num2.charAt(i) + "") - temp;
            if (sub < 0) {
                sub = sub + 10;
                temp = 1;
            } else {
                temp = 0;
            }
            str += Integer.parseInt(sub + "");
        }

        for (int i = num2.length(); i < num1.length(); i++) {
            int sub = Integer.parseInt(num1.charAt(i) + "") - temp;
            if (sub < 0) {
                sub = sub + 10;
                temp = 1;
            } else {
                temp = 0;
            }
            str += Integer.parseInt(sub + "");
        }
        return new StringBuilder(str).reverse().toString();
    }

    static String multiply(String num1, String num2){ // Copied this from my own code in problem 4 / problem set 4
        StringBuilder Answer = new StringBuilder();

        if ((num1.charAt(0) + "").equals("-") && !(num2.charAt(0) + "").equals("-")) {
            Answer.append("-");
            num1 = num1.substring(1);
        } else if (!(num1.charAt(0) + "").equals("-") && (num2.charAt(0) + "").equals("-")) {
            Answer.append("-");
            num2 = num2.substring(1);
        } else if ((num1.charAt(0) + "").equals("-") && (num2.charAt(0) + "").equals("-")) {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        }

        if (num1.length() == 0 || num2.length() == 0) {
            return "0";
        } else {
            int[] result = new int[num1.length() + num2.length()];
            int index1 = 0, index2 = 0;
            for (int i = num1.length() - 1; i >= 0; i--) {
                int temp = 0;
                int char1 = Integer.parseInt(num1.charAt(i) + "");
                index2 = 0;
                for (int j = num2.length() - 1; j >= 0; j--) {
                    int char2 = Integer.parseInt(num2.charAt(j) + "");
                    int sum = char1 * char2 + result[index1 + index2] + temp;
                    temp = sum / 10;
                    result[index1 + index2] = sum % 10;
                    index2++;
                }
                if (temp > 0)
                    result[index1 + index2] += temp;
                index1++;
            }

            int len = result.length - 1;
            while (len >= 0 && result[len] == 0) {
                len--;
            }
            if (len == -1) {
                return "0";
            } else {
                for (int i = len; i >= 0; i--) {
                    Answer.append(result[i]);
                }
                return Answer.toString();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num1 = scanner.nextLine();
        String num2 = scanner.nextLine();
        String answer = minus(num1, multiply("2", num2));
        while (true){
            if (answer.equals("0")){
                break;
            } else {
                if ((answer.charAt(0) + "").equals("0")) {
                    answer = answer.substring(1);
                } else {
                    break;
                }
            }
        }
        if (isSmaller(num1, multiply("2", num2))){
            answer = "-" + answer;
        }
        System.out.println(answer);
    }
}
