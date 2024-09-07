package module_4_binary_search;

/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */
public class LC_374_Guess_Number_Higher_or_Lower {

    // solution 1: brute force - linear search
    // time: O(n)
    // space: O(1)
    public int guessNumber_v1(int n) {
        for (int i = 1; i < n; i++) {
            if (guess(i) == 0) {
                return i;
            }
        }

        return n;
    }

    // solution 2: binary search
    // time: O(logn), where log's base = 2
    // space: O(1)
    public int guessNumber_v2(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1; // this is never returned, according to the problem's scenario
    }

    // solution 3: ternary search (三分查找)



    // mock guess to avoid compiler error
    int guess(int x) {
        return x;
    }

}
