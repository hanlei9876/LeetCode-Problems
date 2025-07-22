package java_practice.Java_Strings;

public class IsPalindrome {

    public static void main(String[] args) {
        String s = "racecar";

        System.out.println(isPalindrome(s));

        int c = s.charAt(0);
        System.out.println((char) c);
    }

    private static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return false;

        if (s.length() == 1)
            return true;

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
