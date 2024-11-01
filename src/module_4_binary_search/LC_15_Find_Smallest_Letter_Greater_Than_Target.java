package module_4_binary_search;

// constraints:
//   2 <= letters.length <= 10^4
//   letters is sorted in non-decreasing order.
//   letters contains at least two different characters.
//   letters[i] & target are all a lowercase English letter.
public class LC_15_Find_Smallest_Letter_Greater_Than_Target {

    // solution 1: linear search (brute force)
    // go through each element letters[i] to check if it satisfies letters[i - 1] <= target && letters[i] > target
    // time: O(N)
    // space: O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        for (int i = 1; i < letters.length; i++) {
            if (letters[i - 1] <= target && letters[i] > target) {
                return letters[i];
            }
        }

        return letters[0];
    }

    // solution 2: linear search (brute force) - simpler form
    public char nextGreatestLetter_2(char[] letters, char target) {
        for (char c : letters) {
            if (target < c) {
                return c;
            }
        }

        return letters[0];
    }

    // solution 3: binary search
    // convert "going through each element" to binary search on all possible elements
    // time: O(logN)
    // space: O(1)
    public char nextGreatestLetter_3(char[] letters, char target) {
        // initiate scope of binary search
        int L = 1;
        int R = letters.length - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;

           //  if (letters[mid - 1] <= target && target < letters[mid]) return letters[mid];
            if (target >= letters[mid]) {
                L = mid + 1;
            } else { // target < letters[mid]
                if (letters[mid - 1] <= target) {
                    return letters[mid];
                } else {
                    R = mid - 1;
                }
            }
        }

        // no element is valid
        return letters[0];
    }


    // solution 4: binary search (Leetcode's official solution)
    // Please DO read the explanation provided in Leetcode,
    // as this is inspiring to understand real meanings of the differences of binary search templates
    public char nextGreatestLetter_4(char[] letters, char target) {
        int left = 0, right = letters.length - 1, mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (letters[mid] <= target) {
                left = mid + 1; // all elements left to "left" index are for sure smaller than target
            } else {
                right = mid - 1; // all elements right to "right" index are for sure greater than target
            }
        }
        // "left > right" means there is no more element left to check (all the elements between left & right are checked)
        // But both "left" & "right" are still having the features below:
        //   - left: all elements left to "left" index are for sure smaller than target
        //   - right: all elements right to "right" index are for sure greater than target
        //
        // we can conclude that letters[right] <= target < letters[left]. Therefore, letters[left] is the solution

        return left == letters.length ? letters[0] : letters[left];
    }
}
