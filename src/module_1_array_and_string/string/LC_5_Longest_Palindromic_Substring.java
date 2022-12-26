package module_1_array_and_string.string;

public class LC_5_Longest_Palindromic_Substring {

    // solution-1: brute force
    // enumerate all possible substrings >> check if it's palindromic
    // time: O(N^3) - O( N(N-1)/2 ) for find all possible substrings, it takes O(N) time ot check palindrome against each substring
    // space: O(1)
    public static String longestPalindrome(String s) {
        int maxLen = 0;
        int L = -1;
        int R = -1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // i~j is the substring for checking

                boolean isPalindrome = true;

                int l = i;
                int r = j;
                while (l < r) {
                    if (s.charAt(l) != s.charAt(r)) {
                        isPalindrome = false;
                    }
                    l++;
                    r--;
                }

                if (isPalindrome) {
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        L = i;
                        R = j;
                    }
                }
            }
        }

        return s.substring(L, R + 1);
    }

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(s.substring(1, 3)); // bc

        String s1 = "babad";
        System.out.println(longestPalindrome(s1));
    }
}
