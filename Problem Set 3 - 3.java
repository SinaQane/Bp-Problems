// Solution 1. (By Mehrafarin Kazemi)

import java.util.*;

public class Main {
    final static int N = 600 + 13;
    static Vector<Long>[] answer = new Vector[N];
    static int[] nxt = new int[N];
    static String s;
    static int n;

    static boolean IsSign(char c) {
        return c == '(' || c == ')' || c == '#' || c == '+' || c == '-' || c == '*';
    }

    static void Calculate(int l, int r) {
        if (nxt[l] == 0) {
            long x = 0, k = 0;
            for (int i = l; i < r; i++)
                if (s.charAt(i) == 'x') {
                    long temp = x;
                    x = k; k = temp;
                }
                else
                    k = 10 * k + Character.getNumericValue(s.charAt(i));
            while (answer[l].size() <= k)
                answer[l].add(0L);
            answer[l].set((int)k, x);
            return;
        }
        if (nxt[l] + 1 == r) {
            Calculate(l + 1, r - 1);
            Vector<Long> temp = answer[l];
            answer[l] = answer[l + 1];
            answer[l + 1] = temp;
            return;
        }
        char[] types = {'#', '*', '-', '+'};
        for (char c: types)
            for (int i = l; i < r; i = nxt[i] + 2)
                while (nxt[i] + 1 < r && s.charAt(nxt[i] + 1) == c) {
                    if (answer[i].isEmpty()) {
                        Calculate(i + 1, nxt[i]);
                        Vector<Long> temp = answer[i];
                        answer[i] = answer[i + 1];
                        answer[i + 1] = temp;
                    }
                    if (answer[nxt[i] + 2].isEmpty()) {
                        Calculate(nxt[i] + 3, nxt[nxt[i] + 2]);
                        Vector<Long> temp = answer[nxt[i] + 2];
                        answer[nxt[i] + 2] = answer[nxt[i] + 3];
                        answer[nxt[i] + 3] = temp;
                    }
                    if (c == '+') {
                        if (answer[i].size() < answer[nxt[i] + 2].size()) {
                            Vector<Long> temp = answer[i];
                            answer[i] = answer[nxt[i] + 2];
                            answer[nxt[i] + 2] = temp;
                        }
                        for (int j = 0; j < answer[nxt[i] + 2].size(); j++)
                            answer[i].set(j, answer[i].get(j) + answer[nxt[i] + 2].get(j));
                    }
                    if (c == '-') {
                        while (answer[i].size() < answer[nxt[i] + 2].size())
                            answer[i].add(0L);
                        for (int j = 0; j < answer[nxt[i] + 2].size(); j++)
                            answer[i].set(j, answer[i].get(j) - answer[nxt[i] + 2].get(j));
                    }
                    if (c == '#') {
                        if (answer[i].size() > answer[nxt[i] + 2].size())
                            answer[i].setSize(answer[nxt[i] + 2].size());
                        for (int j = 0; j < answer[i].size(); j++)
                            answer[i].set(j, answer[i].get(j) * answer[nxt[i] + 2].get(j));
                    }
                    if (c == '*') {
                        Vector<Long> temp = new Vector<Long>();
                        while (temp.size() < answer[i].size() + answer[nxt[i] + 2].size() - 1)
                            temp.add(0L);
                        for (int j = 0; j < answer[i].size(); j++)
                            for (int k = 0; k < answer[nxt[i] + 2].size(); k++)
                                temp.set(j + k, temp.get(j + k) + answer[i].get(j) * answer[nxt[i] + 2].get(k));
                        answer[i] = temp;
                    }
                    answer[nxt[i] + 2].clear();
                    nxt[i] = nxt[nxt[i] + 2];
                }
    }
static void ReadInput() {
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        n = s.length();
        String t = "";
        for (int i = 0; i < n; i++) {
            if (i > 0 && s.charAt(i) == '(' && s.charAt(i - 1) == ')')
                t += '*';
            if (IsSign(s.charAt(i))) {
                boolean flag = t.isEmpty();
                if (flag == false)
                    flag = t.charAt(t.length() - 1) == '(';
                if (flag && (s.charAt(i) == '+' || s.charAt(i) == '-'))
                    t += "(0x0)";
                t += s.charAt(i);

            }
            else {
                t += '(';
                boolean p = false;
                long cur = 0, prv = 0;
                if (s.charAt(i) == 'x')
                    cur = 1;

                while (i < n && !IsSign(s.charAt(i)) && s.charAt(i) != 'x')
                    if (s.charAt(i) == '^') {
                        long temp = cur;
                        cur = prv; prv = temp;
                        p = true;
                        i++;
                    }
                    else
                        cur = 10 * cur + Character.getNumericValue(s.charAt(i++));
                if (p) {
                    long ans = 1;
                    for (int j = 0; j < cur; j++)
                        ans *= prv;
                    cur = ans;
                    //System.out.println(cur + " " + prv);
                }

                t += String.valueOf(cur) + "x";
                if (i < n && s.charAt(i) == 'x')
                    if (++i < n && s.charAt(i) == '^') {
                        long k = 0;
                        for (i++; i < n && !IsSign(s.charAt(i)); i++)
                            k = 10 * k + Character.getNumericValue(s.charAt(i));
                        t += String.valueOf(k);
                    }
                    else
                        t += '1';
                else
                    t += '0';
                t += ')';
                i--;
            }
        }
        n = t.length();
        s = t;
    }

    static void Solve() {
        Stack<Integer> S = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            answer[i] = new Vector<Long>();
            if (s.charAt(i) == '(')
                S.push(i);
            else if (s.charAt(i) == ')')
                nxt[S.pop()] = i;
        }
    }

    static void WriteOutput() {
        Calculate(0, n);
        Vector<Long> ans = answer[0];
        while (!ans.isEmpty() && ans.lastElement() == 0)
            ans.remove(ans.size() - 1);
        for (int i = ans.size() - 1; i >= 0; i--)
            if (ans.get(i) != 0) {
                if (i < ans.size() - 1 && ans.get(i) > 0)
                    System.out.print('+');
                if (i == 0 || ans.get(i) != 1)
                    if (ans.get(i) != -1 || i == 0)
                        System.out.print(ans.get(i));
                    else
                        System.out.print('-');
                if (i > 0) {
                    System.out.print('x');
                    if (i > 1)
                        System.out.print('^' + String.valueOf(i));
                }
            }
        if (ans.isEmpty())
            System.out.print(0);
    }

    public static void main(String[] args) {
        ReadInput(); Solve(); WriteOutput();
    }
}

// Solution 2. (By Parsa Saberi)

/* import java.util.*;
import java.util.regex.*;
import java.lang.StringBuilder;

public class Main {

    static String input;
    static final Pattern tokenPatterns = Pattern.compile("[()\\-+#*\\^]|(\\d*?x\\^?\\d*)|(\\d+)");
    static final Pattern xPattern = Pattern.compile("(?<coeff>\\d*?)x\\^?(?<deg>\\d*)");
    static final Pattern numberPattern = Pattern.compile("\\d+");
    static final Pattern operatorPattern = Pattern.compile("[()\\-+#*\\^]");
    static HashMap<String, Integer> priorityMap;

    static int priority(String operator) {
        if (priorityMap.containsKey(operator))
            return priorityMap.get(operator);
        else
            return 0;
    }

    static class Poly {

        public final HashMap<Integer, Integer> coeffs;
        public final int maxDegree;

        public Poly() {
            coeffs = new HashMap<>();
            maxDegree = 0;
        }

        public Poly(HashMap<Integer, Integer> coeffMap) {
            coeffs = new HashMap<>();
            int maxD = 0;
            for (int d : coeffMap.keySet()) {
                maxD = Math.max(d, maxD);
                if (coeffMap.get(d) != 0)
                    coeffs.put(d, coeffMap.get(d));
            }
            maxDegree = maxD;
        }

        public Poly plus(Poly other) {
            HashMap<Integer, Integer> out = new HashMap<>(coeffs);
            other.coeffs.forEach((otherDegree, otherCoeff) -> {
                int coeff = (out.containsKey(otherDegree) ? out.get(otherDegree) : 0) + otherCoeff;
                out.put(otherDegree, coeff);
            });
            return new Poly(out);
        }

        public Poly negative() {
            HashMap<Integer, Integer> out = new HashMap<>(coeffs);
            coeffs.forEach((k, v) -> out.put(k, -v));
            return new Poly(out);
        }

        public Poly minus(Poly other) {
            return plus(other.negative());
        }

        public Poly hashtag(Poly other) {
            HashMap<Integer, Integer> out = new HashMap<>();
            for (int degree : other.coeffs.keySet())
                if (coeffs.containsKey(degree))
                    out.put(degree, other.coeffs.get(degree) * coeffs.get(degree));
            return new Poly(out);
        }

        public Poly mult(Poly other) {
            HashMap<Integer, Integer> out = new HashMap<>();
            int coeff;
            for (int d1 : coeffs.keySet()) {
                for (int d2 : other.coeffs.keySet()) {
                    coeff = coeffs.get(d1) * other.coeffs.get(d2);
                    if (out.containsKey(d1 + d2))
                        out.put(d1 + d2, out.get(d1 + d2) + coeff);
                    else
                        out.put(d1 + d2, coeff);
                }
            }
            return new Poly(out);
        }

        public Poly pow(Poly other) {
            int c = coeffs.get(0);
            int otherCoeff = 0;
            for(int d : other.coeffs.keySet())
                otherCoeff = other.coeffs.get(d);
            int newCoeff = 1;
            for (int i = 0; i < otherCoeff; i++)
                newCoeff *= c;
            HashMap<Integer, Integer> out = new HashMap<>();
            out.put(other.maxDegree, newCoeff);
            return new Poly(out);
        }

        private String expression(int degree, int coeff) {
            boolean isMax = degree == maxDegree;
            if (degree == 0)
                return ((!isMax && coeff > 0) ? "+" : "") + coeff;
            String term = "x" + ((degree > 1) ? ("^" + degree) : "");
            String sign = (coeff < 0) ? "-" : (isMax ? "" : "+");
            String coeffAbsStr = (Math.abs(coeff) == 1) ? "" : Integer.toString(Math.abs(coeff));
            return sign + coeffAbsStr + term;
        }

        @Override
        public String toString() {
            if (coeffs.isEmpty())
                return "0";
            StringBuilder out = new StringBuilder();
            TreeSet<Integer> sortedDegrees = new TreeSet<>(java.util.Collections.reverseOrder());
            coeffs.keySet().forEach(d -> sortedDegrees.add(d));
            int coeff;
            for (int d : sortedDegrees) {
                coeff = coeffs.get(d);
                out.append(expression(d, coeff));
            }
            return out.toString();
        }
    }

    static LinkedList<String> tokenize(String str) {
        LinkedList<String> out = new LinkedList<>();

        if (str.charAt(0) == '-')
            str = '0' + str;
        else if (str.charAt(0) == '+')
            str = str.substring(1, str.length());

        String edited = str.replace(")(", ")*(").replace("(-", "(0-").replace("(+", "(");

        Matcher m = tokenPatterns.matcher(edited);
        while (m.find())
            out.add(m.group());
        return out;
    }

    static boolean isOperator(String token) {
        return operatorPattern.matcher(token).matches();
    }

    static Poly parseToken(String token) {
        int coeff, deg;
        HashMap<Integer, Integer> out = new HashMap<>();
        Matcher m = xPattern.matcher(token);
        if (m.find()) {
            String c = m.group("coeff");
            String d = m.group("deg");
            coeff = c.equals("") ? 1 : Integer.parseInt(c);
            deg = d.equals("") ? 1 : Integer.parseInt(d);
        } else {
            deg = 0;
            coeff = Integer.parseInt(token);
        }
        out.put(deg, coeff);
        return new Poly(out);
    }

    static Poly applyOperator(Poly a, Poly b, String operator) {
        switch (operator) {
            case "*":
                return a.mult(b);
            case "-":
                return a.minus(b);
            case "+":
                return a.plus(b);
            case "#":
                return a.hashtag(b);
            case "^":
                return a.pow(b);
            default:
                break;
        }
        return new Poly();
    }

    static Poly parseExpression(String expression) {
        LinkedList<String> tokens = tokenize(expression);
        Stack<Poly> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                switch (token) {

                    case "(":
                        operators.push(token);
                        break;

                    case ")":
                        while (!operators.empty() && !operators.lastElement().equals("(")) {
                            Poly val2 = values.pop();
                            Poly val1 = (!values.empty()) ? values.pop() : new Poly();
                            String op = operators.pop();
                            values.push(applyOperator(val1, val2, op));
                        }
                        if (!operators.empty())
                            operators.pop();
                        break;

                    default:
                        while (!operators.empty() && priority(operators.lastElement()) >= priority(token)) {
                            Poly val2 = values.pop();
                            Poly val1 = (!values.empty()) ? values.pop() : new Poly();
                            String operator = operators.pop();
                            values.push(applyOperator(val1, val2, operator));
                        }
                        operators.push(token);
                        break;
                }
            } else {
                values.push(parseToken(token));
            }
        }
        while (!operators.empty()) {
            Poly val2 = values.pop();
            Poly val1 = (!values.empty()) ? values.pop() : new Poly();
            String operator = operators.pop();
            values.push(applyOperator(val1, val2, operator));
        }
        return values.pop();
    }

    public static void main(String[] args) {
        priorityMap = new HashMap<>();
        priorityMap.put("^", 4);
        priorityMap.put("#", 3);
        priorityMap.put("*", 2);
        priorityMap.put("-", 1);
        priorityMap.put("+", 1);

        Scanner sc = new Scanner(System.in);
        String entry = sc.next();
        System.out.println(parseExpression(entry));
        sc.close();
    }
} */

// Solution 3. (By Sina Ghaseminejad)

/* import java.util.*;

public class Test {

    public static String Print(ArrayList<int[]> Polynomial) {
        String Answer = "";
        for (int[] Binomial : Polynomial) {
            if (Binomial[1] == 0) {
                if (Binomial[0] < 0) {
                    Answer += String.valueOf(Binomial[0]);
                } else {
                    Answer += "+" + String.valueOf(Binomial[0]);
                }
            } else if (Binomial[1] == 1) {
                if (Binomial[0] < 0) {
                    Answer += String.valueOf(Binomial[0]) + "x";
                } else {
                    Answer += "+" + String.valueOf(Binomial[0]) + "x";
                }
            } else {
                if (Binomial[1] == Polynomial.get(0)[1]) {
                    if (Binomial[0] < 0) {
                        Answer += String.valueOf(Binomial[0]) + "x^" + String.valueOf(Binomial[1]);
                    } else {
                        Answer += String.valueOf(Binomial[0]) + "x^" + String.valueOf(Binomial[1]);
                    }
                } else {
                    if (Binomial[0] < 0) {
                        Answer += String.valueOf(Binomial[0]) + "x^" + String.valueOf(Binomial[1]);
                    } else {
                        Answer += "+" + String.valueOf(Binomial[0]) + "x^" + String.valueOf(Binomial[1]);
                    }
                }
            }
        }
        Answer = Answer.replace("-1x", "-x");
        Answer = Answer.replace("+1x", "+x");
        if (Answer.substring(0, 2).equals("1x")) {
            Answer = "x" + Answer.substring(2);
        }
        if (String.valueOf(Answer.charAt(0)).equals("+")){
            Answer = Answer.substring(1);
        }
        return Answer;
    }

    public static ArrayList<int[]> Polynomial(String Equation) {
        int LastVisitedIndex = 0;
        ArrayList<String> StringBinomials = new ArrayList<String>();
        Equation = Equation.replace("-x", "-1x");
        for (int i = 0; i < Equation.length(); i++) {
            if (i == Equation.length() - 1) {
                StringBinomials.add(Equation.substring(LastVisitedIndex, i + 1));
            } else {
                if (String.valueOf(Equation.charAt(i)).equals("+")) {
                    if (i != 0) {
                        StringBinomials.add(Equation.substring(LastVisitedIndex, i));
                        LastVisitedIndex = i;
                    }
                } else if (String.valueOf(Equation.charAt(i)).equals("-")) {
                    if (i != 0) {
                        StringBinomials.add(Equation.substring(LastVisitedIndex, i));
                        LastVisitedIndex = i;
                    }
                }
            }
        }
        ArrayList<int[]> Polynomial = new ArrayList<int[]>();
        for (String Str : StringBinomials) {
            int[] ArrayBinomial = new int[2];
            if (Str.contains("x^")) {
                String[] Temp = Str.split("x");
                if (Temp[0].equals("+") || Temp[0].equals("")) {
                    ArrayBinomial[0] = 1;
                } else {
                    if (Temp[0].contains("^")) {
                        String[] Power = Temp[0].split("\\^");
                        if (String.valueOf(Power[0].charAt(0)).equals("-") && Integer.parseInt(Power[1]) % 2 == 0) {
                            ArrayBinomial[0] = (int) -Math.pow(Integer.parseInt(Power[0]), Integer.parseInt(Power[1]));
                        } else {
                            ArrayBinomial[0] = (int) Math.pow(Integer.parseInt(Power[0]), Integer.parseInt(Power[1]));
                        }
                    } else {
                        ArrayBinomial[0] = Integer.parseInt(Temp[0]);
                    }
                }
                ArrayBinomial[1] = Integer.parseInt(Temp[1].substring(1));
            } else {
                if (Str.contains("x")) {
                    if (Str.length() == 1) {
                        ArrayBinomial[0] = 1;
                    } else if (String.valueOf(Str.charAt(1)).equals("x")) {
                        if (String.valueOf(Str.charAt(0)).equals("+") || (String.valueOf(Str.charAt(0)).equals("-"))) {
                            ArrayBinomial[0] = 1;
                        } else {
                            ArrayBinomial[0] = Integer.parseInt(String.valueOf(Str.charAt(0)));
                        }
                    } else {
                        if (Str.contains("^")) {
                            String New_String = Str.substring(0, Str.length() - 1);
                            String[] Power = New_String.split("\\^");
                            if (String.valueOf(Power[0].charAt(0)).equals("-") && Integer.parseInt(Power[1]) % 2 == 0) {
                                ArrayBinomial[0] = (int) -Math.pow(Integer.parseInt(Power[0]), Integer.parseInt(Power[1]));
                            } else {
                                ArrayBinomial[0] = (int) Math.pow(Integer.parseInt(Power[0]), Integer.parseInt(Power[1]));
                            }
                        } else {
                            ArrayBinomial[0] = Integer.parseInt(Str.substring(0, Str.length() - 1));
                        }
                    }
                    ArrayBinomial[1] = 1;
                } else {
                    if (Str.contains("^")) {
                        String[] Power = Str.split("\\^");
                        if (String.valueOf(Power[0].charAt(0)).equals("-") && Integer.parseInt(Power[1]) % 2 == 0) {
                            ArrayBinomial[0] = (int) -Math.pow(Integer.parseInt(Power[0]), Integer.parseInt(Power[1]));
                        } else {
                            ArrayBinomial[0] = (int) Math.pow(Integer.parseInt(Power[0]), Integer.parseInt(Power[1]));
                        }
                    } else {
                        ArrayBinomial[0] = Integer.parseInt(Str);
                    }
                    ArrayBinomial[1] = 0;
                }
            }
            Polynomial.add(ArrayBinomial);
        }
        return Clean(Polynomial);
    }

    public static ArrayList<int[]> Clean(ArrayList<int[]> Polynomial) {
        ArrayList<int[]> NewPolynomial = new ArrayList<int[]>();
        int x = 0;
        int Sum = 0;
        while (Polynomial.size() > 0) {
            boolean mood = true;
            int Power = Polynomial.get(0)[1];
            while (mood) {
                if (Power == Polynomial.get(x)[1]) {
                    Sum += Polynomial.get(x)[0];
                    Polynomial.remove(x);
                } else {
                    x++;
                }
                if (x == Polynomial.size()) {
                    int[] NewBinomial = new int[2];
                    NewBinomial[0] = Sum;
                    NewBinomial[1] = Power;
                    NewPolynomial.add(NewBinomial);
                    x = 0;
                    Sum = 0;
                    mood = false;
                }
            }
        }
        ArrayList<int[]> NewNewPolynomial = new ArrayList<int[]>();
        for (int[] A : NewPolynomial){
            if (A[0]!=0){
                NewNewPolynomial.add(A);
            }
        }
        if (NewNewPolynomial.size()==0){
            int[] Zero = new int[]{0, 0};
            NewNewPolynomial.add(Zero);
        }
        return Sort(NewNewPolynomial);
    }

    public static ArrayList<int[]> Sort(ArrayList<int[]> Polynomial) {
        for (int i = 0; i < Polynomial.size(); i++) {
            for (int j = 0; j < Polynomial.size() - i - 1; j++) {
                if (Polynomial.get(j + 1)[1] > Polynomial.get(j)[1]) {
                    Collections.swap(Polynomial, j, j + 1);
                }
            }
        }
        return Polynomial;
    }

    public static String Sum(String StringPolynomial1, String StringPolynomial2) {
        String Sum;
        if (String.valueOf(StringPolynomial2.charAt(0)).equals("-") || String.valueOf(StringPolynomial2.charAt(0)).equals("+")) {
            Sum = StringPolynomial1 + StringPolynomial2;
        } else {
            Sum = Print(Polynomial(StringPolynomial1)) + "+" + Print(Polynomial(StringPolynomial2));
        }
        ArrayList<int[]> Answer = Clean(Polynomial(Sum));
        return Print(Answer);
    }

    public static String Minus(String StringPolynomial1, String StringPolynomial2) {
        ArrayList<int[]> NewPolynomial2 = new ArrayList<int[]>();
        for (int[] i : Polynomial(StringPolynomial2)) {
            int[] j = new int[2];
            j[0] = -i[0];
            j[1] = i[1];
            NewPolynomial2.add(j);
        }
        String NewStringPolynomial2 = Print(NewPolynomial2);
        String Minus;
        if (String.valueOf(NewStringPolynomial2.charAt(0)).equals("-") || String.valueOf(NewStringPolynomial2.charAt(0)).equals("+")) {
            Minus = StringPolynomial1 + NewStringPolynomial2;
        } else {
            Minus = StringPolynomial1 + "+" + NewStringPolynomial2;
        }
        ArrayList<int[]> Answer = Clean(Polynomial(Minus));
        return Print(Answer);
    }

    public static String Hashtag(String StringPolynomial1, String StringPolynomial2) {
        ArrayList<int[]> Polynomial1 = Polynomial(StringPolynomial1);
        ArrayList<int[]> Polynomial2 = Polynomial(StringPolynomial2);
        ArrayList<int[]> NewPolynomial = new ArrayList<int[]>();
        for (int[] i : Polynomial1) {
            for (int[] j : Polynomial2) {
                if (i[1] == j[1]) {
                    int[] Temp = new int[2];
                    Temp[1] = i[1];
                    Temp[0] = i[0] * j[0];
                    NewPolynomial.add(Temp);
                }
            }
        }
        return Print(Clean(NewPolynomial));
    }

    public static String Multiply(String StringPolynomial1, String StringPolynomial2) {
        ArrayList<int[]> Polynomial1 = Polynomial(StringPolynomial1);
        ArrayList<int[]> Polynomial2 = Polynomial(StringPolynomial2);
        ArrayList<int[]> NewPolynomial = new ArrayList<int[]>();
        for (int[] i : Polynomial1) {
            for (int[] j : Polynomial2) {
                int[] Temp = new int[2];
                Temp[1] = i[1] + j[1];
                Temp[0] = i[0] * j[0];
                NewPolynomial.add(Temp);
            }
        }
        return Print(Clean(NewPolynomial));
    }

    public static ArrayList<String> Stack(String StringPolynomial) {
        ArrayList<String> Stack = new ArrayList<String>();
        int Counter = 0;
        int StartingIndex = 0;
        int EndingIndex;
        for (int i = 0; i < StringPolynomial.length(); i++) {
            if (i == StringPolynomial.length() - 1) {
                EndingIndex = i;
                Stack.add(StringPolynomial.substring(StartingIndex, EndingIndex + 1));
            } else {
                if (String.valueOf(StringPolynomial.charAt(i)).equals("(")) {
                    Counter++;
                    if (Counter == 1) {
                        EndingIndex = i - 1;
                        if (EndingIndex != -1) {
                            String Temp = StringPolynomial.substring(StartingIndex, EndingIndex + 1);
                            if (Temp.length() > 1 &&
                                    (String.valueOf(Temp.charAt(Temp.length() - 1)).equals("+")
                                            || String.valueOf(Temp.charAt(Temp.length() - 1)).equals("-")
                                            || String.valueOf(Temp.charAt(Temp.length() - 1)).equals("#"))
                            ) {
                                Stack.add(Temp.substring(0, Temp.length() - 1));
                                Stack.add(String.valueOf(Temp.charAt(Temp.length() - 1)));
                            } else {
                                Stack.add(Temp);
                            }
                        }
                        StartingIndex = i;
                    }
                }
                if (String.valueOf(StringPolynomial.charAt(i)).equals(")")) {
                    Counter--;
                    if (Counter == 0) {
                        EndingIndex = i;
                        String Temp = StringPolynomial.substring(StartingIndex + 1, EndingIndex);
                        if (Temp.length() > 1 &&
                                (String.valueOf(Temp.charAt(Temp.length() - 1)).equals("+")
                                        || String.valueOf(Temp.charAt(Temp.length() - 1)).equals("-")
                                        || String.valueOf(Temp.charAt(Temp.length() - 1)).equals("#"))
                        ) {
                            Stack.add(Temp.substring(0, Temp.length() - 1));
                            Stack.add(String.valueOf(Temp.charAt(Temp.length() - 1)));
                        } else {
                            Stack.add(Temp);
                        }
                        StartingIndex = i + 1;
                    }
                }
            }
        }
        ArrayList<String> FinalStack = new ArrayList<String>();
        int f = 0;
        while (f < Stack.size()) {
            if (f == Stack.size() - 1) {
                if (Stack.get(f).contains("(")) {
                    if (Stack.get(f).substring(1, Stack.get(f).length() - 1).contains("(")) {
                        int counter = 1;
                        int last_index = 0;
                        for (int h = 1; h < Stack.get(f).length(); h++){
                            if (String.valueOf(Stack.get(f).charAt(h)).equals("(")){
                                counter++;
                            } else if (String.valueOf(Stack.get(f).charAt(h)).equals(")")){
                                counter--;
                            }
                            if (counter == 0) {
                                last_index = h;
                                break;
                            }
                        }
                        if (last_index == Stack.get(f).length() - 1){
                            FinalStack.add(Stack.get(f).substring(1, Stack.get(f).length() - 1));
                        } else {
                            FinalStack.add(Stack.get(f));
                        }
                    } else {
                        FinalStack.add(Stack.get(f).substring(1, Stack.get(f).length() - 1));
                    }
                } else {
                    FinalStack.add(Stack.get(f));
                }
                f++;
            } else {
                if ((!(Stack.get(f).equals(""))
                        && !(Stack.get(f).equals("+"))
                        && !(Stack.get(f).equals("-"))
                        && !(Stack.get(f).equals("#"))) && ((Stack.get(f+1).equals(""))
                        || (Stack.get(f+1).equals("+"))
                        || (Stack.get(f+1).equals("-"))
                        || (Stack.get(f+1).equals("#")))) {
                    if (Stack.get(f).contains("(")) {
                        if (Stack.get(f).substring(1, Stack.get(f).length() - 1).contains("(")) {
                            int counter = 1;
                            int last_index = 0;
                            for (int h = 1; h < Stack.get(f).length(); h++){
                                if (String.valueOf(Stack.get(f).charAt(h)).equals("(")){
                                    counter++;
                                } else if (String.valueOf(Stack.get(f).charAt(h)).equals(")")){
                                    counter--;
                                }
                                if (counter == 0) {
                                    last_index = h;
                                    break;
                                }
                            }
                            if (last_index == Stack.get(f).length() - 1){
                                FinalStack.add(Stack.get(f).substring(1, Stack.get(f).length() - 1));
                            } else {
                                FinalStack.add(Stack.get(f));
                            }
                        } else {
                            FinalStack.add(Stack.get(f).substring(1, Stack.get(f).length() - 1));
                        }
                    } else {
                        FinalStack.add(Stack.get(f));
                    }
                    FinalStack.add(Stack.get(f + 1));
                    f = f + 2;
                } else if ((!(Stack.get(f).equals(""))
                        && !(Stack.get(f).equals("+"))
                        && !(Stack.get(f).equals("-"))
                        && !(Stack.get(f).equals("#"))) && (!(Stack.get(f+1).equals(""))
                        && !(Stack.get(f+1).equals("+"))
                        && !(Stack.get(f+1).equals("-"))
                        && !(Stack.get(f+1).equals("#")))) {
                    if (Stack.get(f).contains("(")) {
                        if (Stack.get(f).substring(1, Stack.get(f).length() - 1).contains("(")) {
                            int counter = 1;
                            int last_index = 0;
                            for (int h = 1; h < Stack.get(f).length(); h++){
                                if (String.valueOf(Stack.get(f).charAt(h)).equals("(")){
                                    counter++;
                                } else if (String.valueOf(Stack.get(f).charAt(h)).equals(")")){
                                    counter--;
                                }
                                if (counter == 0) {
                                    last_index = h;
                                    break;
                                }
                            }
                            if (last_index == Stack.get(f).length() - 1){
                                FinalStack.add(Stack.get(f).substring(1, Stack.get(f).length() - 1));
                            } else {
                                FinalStack.add(Stack.get(f));
                            }
                        } else {
                            FinalStack.add(Stack.get(f).substring(1, Stack.get(f).length() - 1));
                        }
                    } else {
                        FinalStack.add(Stack.get(f));
                    }
                    FinalStack.add("+");
                    f = f + 1;
                }
            }
        }
        return FinalStack;
    }

    public static String Solve(String StringPolynomial) {
        ArrayList<String> Stack = Stack(StringPolynomial);
        for (String Str : Stack) {
            if (Str.contains("(")) {
                Stack.set(Stack.indexOf(Str), Solve(Str));
            }
        }
        int k = 0;
        do {
            if (Stack.get(k).equals("#")) {
                String New = Hashtag(Stack.get(k - 1), Stack.get(k + 1));
                Stack.set(k - 1, New);
                Stack.remove(k);
                Stack.remove(k);
            } else {
                k++;
            }
        } while (k != Stack.size());
        int l = 0;
        do {
            if (Stack.get(l).equals("")) {
                String New = Multiply(Stack.get(l - 1), Stack.get(l + 1));
                Stack.set(l - 1, New);
                Stack.remove(l);
                Stack.remove(l);
            } else {
                l++;
            }
        } while (l != Stack.size());
        int m = 0;
        do {
            if (Stack.get(m).equals("+")) {
                String New = Sum(Stack.get(m - 1), Stack.get(m + 1));
                Stack.set(m - 1, New);
                Stack.remove(m);
                Stack.remove(m);
            } else if (Stack.get(m).equals("-")) {
                String New = Minus(Stack.get(m - 1), Stack.get(m + 1));
                Stack.set(m - 1, New);
                Stack.remove(m);
                Stack.remove(m);
            } else {
                m++;
            }
        } while (m != Stack.size());
        return Print(Polynomial(Stack.get(0)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String Str = scanner.nextLine();
        for (int i = 0; i < Str.length(); i++){
            if (String.valueOf(Str.charAt(i)).equals("(")){
                if (String.valueOf(Str.charAt(i+1)).equals("-")
                        || String.valueOf(Str.charAt(i+1)).equals("+")
                        || String.valueOf(Str.charAt(i+1)).equals("#")) {
                    Str = Str.substring(0, i + 1) + "+0" + Str.substring(i + 1);
                } else {
                    Str = Str.substring(0, i + 1) + "+0+" + Str.substring(i + 1);
                }
            }
        }
        if (String.valueOf(Str.charAt(0)).equals("-") || String.valueOf(Str.charAt(0)).equals("+")){
            Str = "1" + Str + "-1";
        }
        System.out.println(Solve(Str));
    }
} */
