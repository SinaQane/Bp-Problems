import java.util.ArrayList;
import java.util.Scanner;

public class Keyboard {

    public static int lastWord(String str, int pointer) {
        if ((str.charAt(pointer) + "").equals(" ")) {
            return pointer;
        }
        for (int i = pointer - 1; i > 1; i--) {
            if ((str.charAt(i) + "").equals(" ")) {
                return i + 1;
            }
        }
        return 1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String Str = scanner.nextLine();

        Str = Str.replace("backspace", "bksp");
        Str = Str.replace("capsLock", "caps");
        Str = Str.replace("delete", "delt");
        Str = Str.replace("distinctMode", "dist");
        Str = Str.replace("end", "endd");
        Str = Str.replace("enter", "entr");
        Str = Str.replace("right", "rght");
        Str = Str.replace("shift", "shft");
        Str = Str.replace("space", "spcc");
        Str = Str.replace("up", "uppp");
        Str = Str.replace("numLock", "nmlk");
        Str = Str.replace("ctrlkeyP", "PRNT");
        Str = Str.replace("ctrlkeyC", "COPY");
        Str = Str.replace("ctrlkeyV", "PAST");
        Str = Str.replace("ctrlkeyX", "CUTT");
        Str = Str.replace("ctrlkeyD", "DELT");
        Str = Str.replace("ctrlkeyR", "REWT");
        Str = Str.replace("shftkey", "Big");

        ArrayList<String> Keys = new ArrayList<>();
        while (Str.length() > 0) {
            Keys.add(Str.substring(0, 4));
            Str = Str.substring(4);
        }

        boolean capsLock = false; // capsLock = true whenever capsLock is on
        boolean numLock = false; // numLock = true whenever numLock is on
        boolean distinctMode = false; // distinctMode = true whenever distinctMode is on

        ArrayList<String> Lines = new ArrayList<>();
        ArrayList<String> Used = new ArrayList<>();

        int i = 0;
        int pointer = 0;
        int line = 0;
        int exception = 0;

        String TempStr = " ";
        String Memory = "";
        Lines.add(line, TempStr);
        do {
            String temp = Keys.get(i);
            if (temp.equals("PRNT")) {
                System.out.println("{");
                int k = 0;
                while (Lines.size() > k) {
                    System.out.println(Lines.get(k).substring(1));
                    k++;
                }
                System.out.println("}");
            } else if (temp.equals("COPY")) {
                int index = lastWord(TempStr, pointer);
                if ((TempStr.charAt(index) + "").equals(" ")) {
                    Memory = TempStr.substring(index + 1, pointer + 1);
                } else {
                    Memory = TempStr.substring(index, pointer + 1);
                }
            } else if (temp.equals("CUTT")) {
                int index = lastWord(TempStr, pointer);
                int addpt = 0;
                if ((TempStr.charAt(index) + "").equals(" ")) {
                    Memory = TempStr.substring(index + 1, pointer + 1);
                    addpt = Memory.length();
                    TempStr = TempStr.substring(0, index + 1) + TempStr.substring(pointer + 1);
                } else {
                    Memory = TempStr.substring(index, pointer + 1);
                    addpt = Memory.length();
                    TempStr = TempStr.substring(0, index) + TempStr.substring(pointer + 1);
                }
                Lines.add(line, TempStr);
                Lines.remove(line + 1);
                pointer -= addpt;
                if (distinctMode) {
                    for (int k = 0; k < Memory.length(); k++) {
                        if (Used.contains(Memory.charAt(k) + "")) {
                            Used.remove(Memory.charAt(k) + "");
                        }
                    }
                }
            } else if (temp.equals("PAST")) {
                String NewMemory = "";
                if (distinctMode) {
                    for (int k = 0; k < Memory.length(); k++) {
                        if (!Used.contains(Memory.charAt(k) + "")) {
                            Used.add(Memory.charAt(k) + "");
                            NewMemory += Memory.charAt(k) + "";
                        }
                    }
                } else {
                    NewMemory = Memory;
                }
                int addpt = NewMemory.length();
                TempStr = TempStr.substring(0, pointer + 1) + NewMemory + TempStr.substring(pointer + 1);
                Lines.add(line, TempStr);
                Lines.remove(line + 1);
                pointer += addpt;
            } else if (temp.equals("DELT")) {
                int index = lastWord(TempStr, pointer);
                int addpt = 0;
                if ((TempStr.charAt(index) + "").equals(" ")) {
                    addpt = TempStr.substring(index+1, pointer + 1).length();
                    if (distinctMode) {
                        String Delete = TempStr.substring(index+1, pointer);
                        for (int k = 0; k < Delete.length(); k++) {
                            if (Used.contains(Delete.charAt(k) + "")) {
                                Used.remove(Delete.charAt(k) + "");
                            }
                        }
                    }
                    TempStr = TempStr.substring(0, index+1) + TempStr.substring(pointer + 1);
                    Lines.add(line, TempStr);
                    Lines.remove(line + 1);
                    pointer -= addpt;
                } else {
                    addpt = TempStr.substring(index, pointer + 1).length();
                    if (distinctMode) {
                        String Delete = TempStr.substring(index, pointer);
                        for (int k = 0; k < Delete.length(); k++) {
                            if (Used.contains(Delete.charAt(k) + "")) {
                                Used.remove(Delete.charAt(k) + "");
                            }
                        }
                    }
                    TempStr = TempStr.substring(0, index) + TempStr.substring(pointer + 1);
                    Lines.add(line, TempStr);
                    Lines.remove(line + 1);
                    pointer -= addpt;
                }
            } else if (temp.equals("REWT")) {
                int index = lastWord(TempStr, pointer);
                int addpt = TempStr.substring(index, pointer + 1).length();
                String ToAdd = TempStr.substring(index, pointer + 1);
                if ((ToAdd.charAt(0)+"").equals(" ")) {
                    ToAdd = ToAdd.replace(" ", "");
                    addpt--;
                }
                String ToAdd2 = "";
                if (distinctMode) {
                    for (int k = 0; k < ToAdd.length(); k++) {
                        if (!Used.contains(ToAdd.charAt(k) + "")) {
                            Used.add(ToAdd.charAt(k) + "");
                            ToAdd2 += ToAdd.charAt(k) + "";
                        }
                    }
                } else {
                    ToAdd2 = ToAdd;
                }
                TempStr = TempStr.substring(0, pointer + 1) + ToAdd2 + TempStr.substring(pointer + 1);
                Lines.add(line, TempStr);
                Lines.remove(line + 1);
                pointer += addpt;
            } else if (temp.equals("home")) {
                pointer = 0;
            } else if (temp.equals("endd")) {
                pointer = TempStr.length() - 1;
            } else if (temp.equals("bksp")) {
                if (pointer!=0) {
                    if (distinctMode) {
                        if (Used.contains(TempStr.charAt(pointer) + "")) {
                            Used.remove(TempStr.charAt(pointer) + "");
                            TempStr = TempStr.substring(0, pointer) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer--;
                        } else {
                            TempStr = TempStr.substring(0, pointer) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer--;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer) + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer--;
                    }
                } else {
                    if (line > 0) {
                        Lines.remove(line);
                        TempStr = Lines.get(line - 1) + TempStr.substring(1);
                        pointer = Lines.get(line - 1).length() - 1;
                        line--;
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                    } else {
                        exception++;
                    }
                }
            } else if (temp.equals("delt")) {
                try {
                    if (distinctMode) {
                        if (Used.contains(TempStr.charAt(pointer + 1) + "")) {
                            Used.remove(TempStr.charAt(pointer + 1) + "");
                            TempStr = TempStr.substring(0, pointer + 1) + TempStr.substring(pointer + 2);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                        } else {
                            TempStr = TempStr.substring(0, pointer + 1) + TempStr.substring(pointer + 2);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + TempStr.substring(pointer + 2);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                    }
                } catch (Exception e) {
                    exception++;
                }
            } else if (temp.equals("caps")) {
                capsLock = !capsLock;
            } else if (temp.equals("dist")) {
                distinctMode = !distinctMode;
                Used.clear();
            } else if (temp.equals("nmlk")) {
                numLock = !numLock;
            } else if (temp.equals("entr")) {
                if (pointer == Lines.get(line).length() - 1) {
                    Lines.add(line, TempStr);
                    Lines.remove(line + 1);
                    TempStr = " ";
                    line++;
                    Lines.add(line, TempStr);
                    pointer = 0;
                } else {
                    Lines.add(line, TempStr.substring(0, pointer+1));
                    Lines.remove(line + 1);
                    TempStr = " " + TempStr.substring(pointer+1);
                    line++;
                    Lines.add(line, TempStr);
                    pointer = 0;
                }
            } else if (temp.equals("rght")) {
                if (pointer < TempStr.length() - 1) {
                    pointer++;
                }
            } else if (temp.equals("left")) {
                if (pointer > 0) {
                    pointer--;
                }
            } else if (temp.equals("down")) {
                if (line == Lines.size()-1) {
                    exception++;
                } else {
                    if (Lines.get(line + 1).length() - 1 < pointer) {
                        TempStr = Lines.get(line + 1);
                        line++;
                        pointer = Lines.get(line).length() - 1;
                    } else {
                        TempStr = Lines.get(line + 1);
                        line++;
                    }
                }
            } else if (temp.equals("uppp")) {
                if (line == 0){
                    exception++;
                } else {
                    if (Lines.get(line - 1).length() - 1 < pointer) {
                        TempStr = Lines.get(line - 1);
                        line--;
                        pointer = Lines.get(line).length() - 1;
                    } else {
                        TempStr = Lines.get(line - 1);
                        line--;
                    }
                }
            } else if (temp.equals("shft")) {
                exception++;
            } else if (temp.equals("ctrl")) {
                exception++;
            } else if (temp.equals("spcc")) {
                if (distinctMode) {
                    if (!Used.contains(" ")) {
                        Used.add(" ");
                        TempStr = TempStr.substring(0, pointer + 1) + " " + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else {
                    TempStr = TempStr.substring(0, pointer + 1) + " " + TempStr.substring(pointer + 1);
                    Lines.add(line, TempStr);
                    Lines.remove(line + 1);
                    pointer++;
                }
            } else if (temp.substring(0, 3).equals("key")) {
                if (capsLock) {
                    if (distinctMode) {
                        if (!Used.contains(temp.charAt(3) + "")) {
                            Used.add(temp.charAt(3) + "");
                            TempStr = TempStr.substring(0, pointer + 1) + temp.charAt(3) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + temp.charAt(3) + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else {
                    if (distinctMode) {
                        if (!Used.contains(Character.toLowerCase(temp.charAt(3)) + "")) {
                            Used.add(Character.toLowerCase(temp.charAt(3)) + "");
                            TempStr = TempStr.substring(0, pointer + 1) + Character.toLowerCase(temp.charAt(3)) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + Character.toLowerCase(temp.charAt(3)) + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                }
            } else if (temp.substring(0, 3).equals("Big")) {
                if ((temp.charAt(3) + "").equals("'")) {
                    if (distinctMode) {
                        if (!Used.contains("\"")) {
                            Used.add("\"");
                            TempStr = TempStr.substring(0, pointer + 1) + "\"" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "\"" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals(",")) {
                    if (distinctMode) {
                        if (!Used.contains("<")) {
                            Used.add("<");
                            TempStr = TempStr.substring(0, pointer + 1) + "<" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "<" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("-")) {
                    if (distinctMode) {
                        if (!Used.contains("_")) {
                            Used.add("_");
                            TempStr = TempStr.substring(0, pointer + 1) + "_" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "_" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals(".")) {
                    if (distinctMode) {
                        if (!Used.contains(">")) {
                            Used.add(">");
                            TempStr = TempStr.substring(0, pointer + 1) + ">" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + ">" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("/")) {
                    if (distinctMode) {
                        if (!Used.contains("?")) {
                            Used.add("?");
                            TempStr = TempStr.substring(0, pointer + 1) + "?" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "?" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals(";")) {
                    if (distinctMode) {
                        if (!Used.contains(":")) {
                            Used.add(":");
                            TempStr = TempStr.substring(0, pointer + 1) + ":" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + ":" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("=")) {
                    if (distinctMode) {
                        if (!Used.contains("+")) {
                            Used.add("+");
                            TempStr = TempStr.substring(0, pointer + 1) + "+" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "+" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("[")) {
                    if (distinctMode) {
                        if (!Used.contains("{")) {
                            Used.add("{");
                            TempStr = TempStr.substring(0, pointer + 1) + "{" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "{" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("\\")) {
                    if (distinctMode) {
                        if (!Used.contains("|")) {
                            Used.add("|");
                            TempStr = TempStr.substring(0, pointer + 1) + "|" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "|" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("]")) {
                    if (distinctMode) {
                        if (!Used.contains("}")) {
                            Used.add("}");
                            TempStr = TempStr.substring(0, pointer + 1) + "}" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "}" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("0")) {
                    if (distinctMode) {
                        if (!Used.contains(")")) {
                            Used.add(")");
                            TempStr = TempStr.substring(0, pointer + 1) + ")" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + ")" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("1")) {
                    if (distinctMode) {
                        if (!Used.contains("!")) {
                            Used.add("!");
                            TempStr = TempStr.substring(0, pointer + 1) + "!" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "!" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("2")) {
                    if (distinctMode) {
                        if (!Used.contains("@")) {
                            Used.add("@");
                            TempStr = TempStr.substring(0, pointer + 1) + "@" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "@" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("3")) {
                    if (distinctMode) {
                        if (!Used.contains("#")) {
                            Used.add("#");
                            TempStr = TempStr.substring(0, pointer + 1) + "#" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "#" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("4")) {
                    if (distinctMode) {
                        if (!Used.contains("$")) {
                            Used.add("$");
                            TempStr = TempStr.substring(0, pointer + 1) + "$" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "$" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("5")) {
                    if (distinctMode) {
                        if (!Used.contains("%")) {
                            Used.add("%");
                            TempStr = TempStr.substring(0, pointer + 1) + "%" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "%" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("6")) {
                    if (distinctMode) {
                        if (!Used.contains("^")) {
                            Used.add("^");
                            TempStr = TempStr.substring(0, pointer + 1) + "^" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "^" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("7")) {
                    if (distinctMode) {
                        if (!Used.contains("&")) {
                            Used.add("&");
                            TempStr = TempStr.substring(0, pointer + 1) + "&" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "&" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("8")) {
                    if (distinctMode) {
                        if (!Used.contains("*")) {
                            Used.add("*");
                            TempStr = TempStr.substring(0, pointer + 1) + "*" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "*" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else if ((temp.charAt(3) + "").equals("9")) {
                    if (distinctMode) {
                        if (!Used.contains("(")) {
                            Used.add("(");
                            TempStr = TempStr.substring(0, pointer + 1) + "(" + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + "(" + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                } else {
                    if (capsLock) {
                        if (distinctMode) {
                            if (!Used.contains(Character.toLowerCase(temp.charAt(3)) + "")) {
                                Used.add(Character.toLowerCase(temp.charAt(3)) + "");
                                TempStr = TempStr.substring(0, pointer + 1) + Character.toLowerCase(temp.charAt(3)) + TempStr.substring(pointer + 1);
                                Lines.add(line, TempStr);
                                Lines.remove(line + 1);
                                pointer++;
                            }
                        } else {
                            TempStr = TempStr.substring(0, pointer + 1) + Character.toLowerCase(temp.charAt(3)) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        if (distinctMode) {
                            if (!Used.contains(temp.charAt(3) + "")) {
                                Used.add(temp.charAt(3) + "");
                                TempStr = TempStr.substring(0, pointer + 1) + temp.charAt(3) + TempStr.substring(pointer + 1);
                                Lines.add(line, TempStr);
                                Lines.remove(line + 1);
                                pointer++;
                            }
                        } else {
                            TempStr = TempStr.substring(0, pointer + 1) + temp.charAt(3) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    }
                }
            } else if (temp.substring(0, 3).equals("num")) {
                if (numLock) {
                    if (distinctMode) {
                        if (!Used.contains(temp.charAt(3) + "")) {
                            Used.add(temp.charAt(3) + "");
                            TempStr = TempStr.substring(0, pointer + 1) + temp.charAt(3) + TempStr.substring(pointer + 1);
                            Lines.add(line, TempStr);
                            Lines.remove(line + 1);
                            pointer++;
                        }
                    } else {
                        TempStr = TempStr.substring(0, pointer + 1) + temp.charAt(3) + TempStr.substring(pointer + 1);
                        Lines.add(line, TempStr);
                        Lines.remove(line + 1);
                        pointer++;
                    }
                }
            }
            i++;
        } while (i != Keys.size());
    }
}
