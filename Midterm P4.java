import java.util.ArrayList;
import java.util.Scanner;

public class P4 {
    public static String yekiDarmion(String str){
        String newStr = "";
        for (int i = 0; i<str.length(); i++){
            if (i%2==0){
                newStr += (str.charAt(i)+"").toUpperCase();
            } else {
                newStr += (str.charAt(i)+"").toLowerCase();
            }
        }
        return newStr;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str + "*";
        String temp = "";
        ArrayList<String> strings = new ArrayList<>(); // Instead of using Map, I used two Arraylists
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i<str.length(); i++){
            if ((str.charAt(i)+"").equals("+") || (str.charAt(i)+"").equals("*")){
                if (strings.contains(temp)){
                    int index = strings.indexOf(temp);
                    int num = numbers.get(strings.indexOf(temp))+1;
                    numbers.add(index, num);
                    numbers.remove(index+1);
                } else {
                    strings.add(temp);
                    numbers.add(1);
                }
                temp = "";
            } else {
                temp += (str.charAt(i)+"").toLowerCase();
            }
        }
        for (int i = 0; i<strings.size(); i++){ // Related sort using Bubble Sort
            for (int j = i+1; j<strings.size(); j++){
                if (numbers.get(i)<numbers.get(j)){
                    int temp1 = numbers.get(i);
                    numbers.add(i, numbers.get(j));
                    numbers.remove(i+1);
                    numbers.add(j, temp1);
                    numbers.remove(j+1);
                    String temp2 = strings.get(i);
                    strings.add(i, strings.get(j));
                    strings.remove(i+1);
                    strings.add(j, temp2);
                    strings.remove(j+1);
                }
            }
        }
        for (String s : strings){
            System.out.println(yekiDarmion(s));
        }
    }
}
