package module_1_array_and_string.string;

import java.util.HashMap;
import java.util.Stack;

public class LC_20_Valid_Parentheses {

    // solution 1: use stack
    // time: O(N)
    // space: O(N), worst case "[[{({{{((", will push all chars to the stack
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            // when stack is empty, stack.peek() will throw null pointer exception
            // we cannot compare null : char. Exception will be thrown
            if (stack.empty() || map.get(stack.peek()) == null || map.get(stack.peek()) != s.charAt(i)) {
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }

        return stack.empty();
    }

    // solution 2: optimize time for solution 1
    public boolean isValid_2(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { // c is left
                stack.push(c);
            } else { // c is right
                if (stack.empty() || map.get(stack.peek()) != c) {
                    return false; // optimize time
                } else {
                    stack.pop();
                }
            }
        }

        return stack.empty();
    }

}
