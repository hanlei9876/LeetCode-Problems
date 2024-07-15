package module_3_recursion_I_and_II;

import java.util.*;

// solution 1 - backtrack
// time:
// time upper bound O(2^(2N+1) - 1) + O(2N * 2^N) = O(N * 2^N)
//   - total nodes in tree = total number of solutions * 2N
//   - total number solutions * O(2N) - for each solution, takes O(2N) to copy StringBuilder to a String object. Ignored
// the total number of solutions is nth Catalan Number C(n)
// Given a full binary tree with height = h, the total amount of nodes in the tree = 2^(h+1) - 1
// For a full binary tree of height h, the last level (Level h) has 2^h nodes.

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
        // test StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");
        System.out.println(sb); // ab
        System.out.println(sb.length()); // 2

        sb.deleteCharAt(sb.length() - 1); // index is 0-based
        System.out.println(sb); // a


        // test if() + else_if() vs if() if()
        int a = 10;
        if (a < 20) {
            System.out.println("first if");
        } else if (a < 15) {
            System.out.println("second if"); // not executed
        }

        int b = 5;
        if (b > 1) {
            System.out.println("third if");
        }
        if (b > 2) {
            System.out.println("forth if");
        }


        // test StringBuilder.setLength()
        // If the new length is less than the current length, the StringBuilder is truncated to the new length.
        // If the new length is greater than the current length, the StringBuilder is extended and filled with null characters (\0).
        StringBuilder sb1 = new StringBuilder("hello world");

        // Truncate to length 5
        sb1.setLength(5);
        System.out.println(sb1.toString());  // Outputs "hello"

        // Extend to length 10, adding null characters
        sb1.setLength(10);
        System.out.println(sb1.toString());  // Outputs "hello\0\0\0\0\0"
    }
}



// solution 2 - recursion to iteration for backtrack solution
// key points to do it:
//   - use stack/queue to hold arguments of recursion function
//   - write loop to iterate stack/queue to simulate call stack movement
//
// for backtracking recursion, the challenges are:
//   - solutionCandidate is part of arguments
//   - revert solutionCandidate to last state (backtrack) when finishing recursion function call
//
// solution: stack - DFS
// time: upper limit - total amount of nodes * N + nodes of last level * N
//   - Given a full binary tree with height = N, the total amount of nodes in the tree = 2^(N+1) - 1
//   - For a full binary tree of height N, the last level (Level N) has 2^N nodes.
// space: O(N^2) for upper limit
//   - stack max height = N. So, upper limit is each stack space has max length solution candidate - O(N^2)
//   - res is not included
class LC_22_Generate_Parentheses_v2 {

    // define inner class to hold al arguments
    class State {
        StringBuilder solutionCandidate;
        int leftCount;
        int rightCount;

        State(StringBuilder solutionCandidate, int leftCount, int rightCount) {
            this.solutionCandidate = solutionCandidate;
            this.leftCount = leftCount;
            this.rightCount = rightCount;
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Stack<State> stack = new Stack<>();
        stack.push(new State(new StringBuilder(), 0, 0));

        while (!stack.isEmpty()) {
            State currState = stack.pop();

            // base case
            if (currState.solutionCandidate.length() == 2 * n) {
                res.add(currState.solutionCandidate.toString());
                continue;
            }

            if (currState.leftCount < currState.rightCount) {
                continue;
            }

            if (currState.rightCount < n) {
                StringBuilder sb =  new StringBuilder(currState.solutionCandidate); // cost time + space
                sb.append(")");
                State newState = new State(sb, currState.leftCount, currState.rightCount + 1);
                stack.push(newState);
            }

            if (currState.leftCount < n) {
                StringBuilder sb =  new StringBuilder(currState.solutionCandidate); // cost time + space
                sb.append("(");
                State newState = new State(sb, currState.leftCount + 1, currState.rightCount);
                stack.push(newState);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // test: StringBuilder sb2 = new StringBuilder(sb1);
        LC_22_Generate_Parentheses_v2 solution = new LC_22_Generate_Parentheses_v2();
        System.out.println(solution.generateParenthesis(2));

        StringBuilder sb1 = new StringBuilder("abc");
        sb1.append("d");
        System.out.println(sb1); // abcd

        StringBuilder sb2 = new StringBuilder(sb1);
        sb2.append("hhh");

        System.out.println(sb1); // abcd
        System.out.println(sb2); // abcdhhh
        System.out.println(sb1 == sb2); // false
    }
}


// solution 3 - brute force - create all possible combinations, then validate each of them
// use queue to implement BFS
//
// Complexity - see the graph in my note
// time: O(n * 2^(2n))
//  -  2^(2n) unique strings to build, each size is 2n - O(n * 2^(2n))
//  -  iterate each formed string for validation -
// space: O(n * 2^(2n))
//   - res is counted
//   - max size oof queue - 2n * total amount of leaf nodes at last level.
//   - The height of full tree is 2*n >> total amount of leaf nodes at last level is 2^(2n)
class LC_22_Generate_Parentheses_V3 {

    private boolean isValid(String s) {
        int leftCount = 0;

        for (char c : s.toCharArray()) {
            if (leftCount == -1)
                return false;

            if (c == '(') {
                leftCount++;
            } else {
                leftCount--;
            }
        }

        return leftCount == 0;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>(Arrays.asList(""));

        while (!queue.isEmpty()) {
            String currString = queue.poll();

            if (currString.length() == 2 * n) {
                if (isValid(currString)) {
                    res.add(currString);
                }
                continue;
            }

            queue.offer(currString + "(");
            queue.offer(currString + ")");
        }

        return res;
    }

    // test Arrays.asList()
    // This method acts as bridge between array-based and collection-based APIs, in combination with Collection.toArray().
    public static void main(String[] args) {
        String[] array = {"a", "b", "c"};

        List<String> list = Arrays.asList(array);

        System.out.println("List: " + list);

        list.set(0, "z");
        System.out.println("Modified List: " + list);

        System.out.println("Modified Array: " + Arrays.toString(array));

        String s1 = "ab";
        String s2 = s1 + "c";
        System.out.println(s1);
        System.out.println(s2);
    }
}