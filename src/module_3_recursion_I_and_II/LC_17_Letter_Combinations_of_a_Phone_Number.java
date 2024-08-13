package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2 points:
 *  (1) the input string can't have duplicate numbers
 *
 *  (2) Given the constraint 0 <= digits.length <= 4,
 *      we need to discuss it's possible to use brute force.
 *  Quite often, you can figure out what sort of approach needs to be taken simply from looking at the input size.
 *  In an interview, asking your interviewer about the constraints will also show your attention to detail - on top of giving you information.
 **/


// solution: backtrack
// time: O(M^N * N + M^N * N) ==> O(M^N * N)
//   - this is upper bound
//   = total number of nodes (composed of each path to build one solution) + copying each solution
//   - total number of solutions: M^N
//   - each solution's length == N
//
// space: O(N)
//   - max height of recursion call stack
//   - not counting space used for the output
//
// where
//   M is the number of letters mapped from a number (for '7', '9', M == 4, otherwise, M == 3 )
//   N is the length of digits.
public class LC_17_Letter_Combinations_of_a_Phone_Number {

    List<String> res = new ArrayList<>();

    String digits;

    Map<Character, String> map = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        // if digits == "", return [], instead of [""]
        if (digits.length() == 0)
            return res;

        this.digits = digits; // make argument global

        backtrack(new StringBuilder(), 0);

        return res;
    }

    private void backtrack(StringBuilder solutionCandidate, int start) {
        // base case
        if (solutionCandidate.length() == digits.length()) {
            String solution = solutionCandidate.toString();
            res.add(solution);
            return;
        }

        // recursion relation
        char digit = digits.charAt(start);
        String letters = map.get(digit);

        for (int i = 0; i < letters.length(); i++) {
            solutionCandidate.append(letters.charAt(i));

            backtrack(solutionCandidate, start + 1);

            int len = solutionCandidate.length();
            solutionCandidate.deleteCharAt(len - 1);
        }
    }
}
