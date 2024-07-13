package module_3_recursion_I_and_II;

import java.util.*;

// time:
// time upper bound O(2^(2N+1) - 1) + O(2N * 2^N) = O(N * 2^N)
//   - total nodes in tree = total number of solutions * 2N
//   - total number solutions * O(2N) - for each solution, takes O(2N) to copy StringBuilder to a String object. Ignored
// the total number of solutions is a Catalan Number C(n)
// Given a full binary tree with height = h, the total amount of nodes in the tree = 2^(h+1) - 1
// For a complete binary tree of height h, the last level (Level h) has 2^h nodes.

// space: O(N) = O(N + N)
//   - tree height - O(2N)
//   - max length of StringBuilder - O(2N)
public class LC_22_Generate_Parentheses {
    int n;
    List<String> res;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        res = new ArrayList<>();
        StringBuilder solutionCandidate = new StringBuilder();

        backtrack(solutionCandidate, 0, 0);

        return res;
    }

    private void backtrack(StringBuilder solutionCandidate, int leftCount, int rightCount) {
        // base case
        if (solutionCandidate.length() == 2 * n) {
            String s = solutionCandidate.toString();
            res.add(s);
            return;
        }

        // recursion relation
        if (leftCount < rightCount) {
            return;
        }

        if (leftCount < n) {
            solutionCandidate.append("(");
            backtrack(solutionCandidate, leftCount + 1, rightCount);
            solutionCandidate.deleteCharAt(solutionCandidate.length() - 1);
        }

        if (rightCount < n) {
            solutionCandidate.append(")");
            backtrack(solutionCandidate, leftCount, rightCount + 1);
            solutionCandidate.deleteCharAt(solutionCandidate.length() - 1);
        }
        // leftCount >= 3 && rightCount >= 3
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");

        System.out.println(sb);

        System.out.println(sb.length());

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}

// recursion to iteration for backtrack solution
class LC_22_Generate_Parentheses_v2 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder solutionCandidate = new StringBuilder();

        // base case - Part 1/2 - can be omitted
        if (solutionCandidate.length() == 2 * n) {
            res.add(solutionCandidate.toString());
            return res;
        }

        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("leftCount", 0);
        map.put("rightCount", 0);
        stack.push(map);

        while (!stack.empty()) {
            // base case - Part 2/2
            if (solutionCandidate.length() == 2 * n) {
                res.add(solutionCandidate.toString());
                continue;
            }

            // recursion relation
            Map<String, Integer> args = stack.pop();
            int leftCount = args.get("leftCount");
            if (leftCount < 3) {
                solutionCandidate.append("(");
                args.put("leftCount", leftCount + 1);
            }
        }

        return null;
    }
}

class Solution_ChatGPT {
    class State {
        StringBuilder solutionCandidate;
        int leftCount;
        int rightCount;

        State(StringBuilder solutionCandidate, int leftCount, int rightCount) {
            this.solutionCandidate = new StringBuilder(solutionCandidate);
            this.leftCount = leftCount;
            this.rightCount = rightCount;
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Stack<State> stack = new Stack<>();
        stack.push(new State(new StringBuilder(), 0, 0));

        while (!stack.isEmpty()) {
            State current = stack.pop();

            if (current.solutionCandidate.length() == 2 * n) {
                res.add(current.solutionCandidate.toString());
                continue;
            }

            if (current.leftCount < n) {
                State newState = new State(current.solutionCandidate, current.leftCount + 1, current.rightCount);
                newState.solutionCandidate.append("(");
                stack.push(newState);
            }

            if (current.rightCount < current.leftCount) {
                State newState = new State(current.solutionCandidate, current.leftCount, current.rightCount + 1);
                newState.solutionCandidate.append(")");
                stack.push(newState);
            }
        }

        return res;
    }
}
