package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// solution 1: backtrack
// time: O(N * 2^N) - see note
// space: O(N + N) = O(N) - stack max height, solutionCandidate max length
//      where N is the length of string
public class LC_131_Palindrome_Partitioning {

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
