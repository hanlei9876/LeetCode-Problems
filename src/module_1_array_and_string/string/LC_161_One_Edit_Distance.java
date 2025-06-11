package module_1_array_and_string.string;

public class LC_161_One_Edit_Distance {

    // solution 1: one pass - count differences
    // time: O(N)
    // space: O(1)
    public static boolean isOneEditDistance_1(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        // trick: make sure s <= t
        if (sLen > tLen) {
            return isOneEditDistance_1(t, s);
        }

        if (tLen - sLen > 1) {
            return false;
        }

        if (sLen == tLen) {
            int diffCount = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diffCount++;
                }
            }

            return diffCount == 1;

        } else {
            int diffCount = 0;

            int i = 0, j = 0;
            while ( i < s.length() && j < t.length()) {
                if (s.charAt(i) != t.charAt(j)) {
                    diffCount++;
                    j++;
                } else {
                    i++;
                    j++;
                }
            }

            // case 1: diffCount == 0 >> "a" "ab"
            // case 2: diffCount == 1 >> "ab" "acb"
            // other cases: more than 1 edit distance
            return diffCount <= 1;
        }
    }



    // solution 2 (recommended): one pass - when encountering diff, check if remaining string identical
    // time: O(N)
    // space: O(N) - String is immutable in Java, so sSub & tSub cost O(2N) space
    public boolean isOneEditDistance_2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        // trick: make sure s <= t
        if (sLen > tLen) {
            return isOneEditDistance_2(t, s);
        }

        if (tLen - sLen > 1) {
            return false;
        }

        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) != t.charAt(i)) {
               if (sLen == tLen) {
                   String sSub = s.substring(i + 1); // cost space
                   String tSub = t.substring(i + 1); // cost space
                   return (sSub.equals(tSub));
               } else {
                   String sSub = s.substring(i); // cost space
                   String tSub = t.substring(i + 1); // cost space
                   return (sSub.equals(tSub));
               }
            }
        }

        // if arrive here. there are 2 scenarios:
        //  1) "ab"   "abc"
        //  2) "abc"  "abc"
        return (sLen == tLen) ? false : true;
    }

    public static void main(String[] args) {
        String s = "";
        // System.out.println(s.charAt(0)); // out of bound exception

        isOneEditDistance_1("a", "ac");
    }
}
