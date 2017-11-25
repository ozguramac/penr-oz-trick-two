import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Özgür V. Amaç on 11/23/17.
 */
public class Nervous {

    public static void main(String... args) {
        final Nervous n = new Nervous();
        printWellFormed(n, "");
        printWellFormed(n, "a");
        printWellFormed(n, "(a");
        printWellFormed(n, "(a)");
        printWellFormed(n, "((a))");
        printWellFormed(n, "([{[{()}]}])");
        printWellFormed(n, "\"{[({({()\"");
        printWellFormed(n, "(ab[cde{fg}hij]klm)");
        printWellFormed(n, "(ab[cde{fg}hij]klm");
        printWellFormed(n, "(ff[fe43{3423]34th}rty6");
        printWellFormed(n, "(111\"[qwer{dhj1]d}\"sss)");

        final String[] sArr = {"cat","far", "act", "car", "arc", "rac" };
        System.out.println("There are "+n.countAnagrams(sArr)+ " anagram(s) in " + Arrays.toString(sArr));
    }

    private static void printWellFormed(final Nervous n, final String str) {
        System.out.println(str + " is " + (n.isWellFormed(str) ? "" : "not ") + "well formed");
    }

    private boolean isWellFormed(final String str) {
        final Stack<Character> s = new Stack<>();
        final char[] chArr = str.toCharArray();
        for (char c : chArr) {
            if (s.size() > 0) {
                final char p = s.peek();
                if (p == '"') {
                    if (c == '"') {
                        s.pop();
                    }
                    continue; //Special case: ignore until closing quote
                }

                if (p == '(') {
                    if (c == ']' || c == '}') {
                        return false;
                    }
                    if (c == ')') {
                        s.pop();
                        continue;
                    }
                }
                else if (p == '[') {
                    if (c == ')' || c == '}') {
                        return false;
                    }
                    if (c == ']') {
                        s.pop();
                        continue;
                    }
                }
                else if (p == '{') {
                    if (c == ']' || c == ')') {
                        return false;
                    }
                    if (c == '}') {
                        s.pop();
                        continue;
                    }
                }
            }

            if (c == '"' || c == '(' || c == '[' || c == '{') {
                s.push(c);
            }
        }
        return s.size() < 1;
    }

    private int countAnagrams(final String[] sArr) {
        final int n = sArr.length;
        final boolean[] flags = new boolean[n];
        for (int i=0; i < n; i++) {
            if (flags[i]) {
                continue;
            }
            final String s = sArr[i];
            for (int j=i+1; j < n; j++) {
                if (isAnagram(s, sArr[j])) {
                    flags[i] = true;
                    flags[j] = true;
                }
            }
        }

        int cnt = 0;
        for (boolean flag : flags) {
            if (flag) cnt++;
        }
        return cnt;
    }

    private boolean isAnagram(final String str1, final String str2) {
        if (null == str1 || null == str2) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        final String ss1 = sort(str1);
        final String ss2 = sort(str2);
        return ss1.equals(ss2);
    }

    private String sort(final String str) {
        final char[] chArr = str.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }
}
