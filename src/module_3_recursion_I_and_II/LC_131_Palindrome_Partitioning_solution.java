package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

public class LC_131_Palindrome_Partitioning_solution {
    public static void main(String[] args) {
        boolean[] l = new boolean[3];
        for (boolean a : l) {
            System.out.println(a);
        }
        // false
        // false
        // false
    }
}

// solution 1: backtrack
// time: O(N * 2^N) - see note
// space: O(N + N) = O(N) - stack max height, solutionCandidate max length
//      where N is the length of string
class LC_131_Palindrome_Partitioning_solution_1 {

    String s;
    List<List<String>> res;

    public List<List<String>> partition(String s) {
        this.s = s;
        res = new ArrayList<>();

        backtrack(new ArrayList<>(), 0);

        return res;
    }

    private void backtrack(List<String> solutionCandidate, int start) {
        // base case
        if (start == s.length()) {
            res.add(new ArrayList<>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int end = start; end < s.length(); end++) {
            if (!isPalindrome(s, start, end)) {
                continue;
            }

            String subString = s.substring(start, end + 1);
            solutionCandidate.add(subString);

            backtrack(solutionCandidate, end + 1);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
        }
    }

    // both low and high are inclusive
    private boolean isPalindrome(String s, int low, int high) {
        while (low <= high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }

            low++;
            high--;
        }
        return true;
    }
}


// solution 2: Dynamic Programming - optimize time of validating if an enumerated substring is palindrome with DP
// time: O(N * 2^N) - worst case
// space: O(N) + O(N^2)
class LC_131_Palindrome_Partitioning_solution_2 {

    String s;
    boolean[][] dp;
    List<List<String>> res;

    public List<List<String>> partition(String s) {
        this.s = s;
        dp = new boolean[s.length()][s.length()];
        res = new ArrayList<>();

        backtrack(new ArrayList<>(), 0);

        return res;
    }

    private void backtrack(List<String> solutionCandidate, int start) {
        // base case
        if (start == s.length()) {
            res.add(new ArrayList<>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int end = start; end < s.length(); end++) {
            // optimize with DP - if this iterated substring [start, end] is NOT palindrome
            if (s.charAt(start) != s.charAt(end) || (end - start > 2 && dp[start + 1][end - 1] == false)) {
                continue;
            }

            dp[start][end] = true;
            solutionCandidate.add(s.substring(start, end + 1));

            backtrack(solutionCandidate, end + 1);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
        }
    }
}
