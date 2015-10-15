package practice.oldschool;

/**
 * Created by yichen on 10/15/15.
 *
 * Knuth-Morris-Pratt StringSearch
 */
public class KMP_StringSearch {
    private int[] f;
    private String pat;
    private String str;

    public KMP_StringSearch(String str) {
        this.str = str;
    }

    public int fastFind(String pat) {
        this.pat = pat;
        f = new int[pat.length()];
        createF();
        printF();

        int match = 0;
        int i = 0;
        while (i < str.length() - pat.length() + 1) {
            if (str.charAt(i) == pat.charAt(match)) {
                match++;
            }
            else {
                i += f[match] + 1;
                match = 0;
            }

            if (match >= pat.length()) {
                break;
            }
            i++;
        }

        if (match > 0)
            return i - match;
        else
            return -1;
    }

    private void printF() {
        for (int i : f) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    private void createF() {

        f[0] = -1;

        for (int i = 1; i < pat.length(); i++) {
            int j = f[i-1];

            if (pat.charAt(i) == pat.charAt(1 + j)) {
                j++;
            }
            else {
                j = -1;
            }

            f[i] = j;
        }
    }

    public static void main(String[] args) {
        String str = "bcbcbabcabcaabcabcabcacab";
        String pat = "abcabcacab";
        KMP_StringSearch kmp_stringSearch = new KMP_StringSearch(str);

        int found = kmp_stringSearch.fastFind(pat);
        System.out.println("Found: " + found);

    }
}
