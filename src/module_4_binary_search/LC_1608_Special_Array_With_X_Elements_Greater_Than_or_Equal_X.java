package module_4_binary_search;

// test cases:
// 1 - assert true:
//    [2, 3] - expect 2
//    [0, 0] - expect -1
//    [1, 4, 2] - expect 2
// 2 - assert false
//    [-1, -3, -4] - expect 0 (real result is -1)
// 3 - edge case
//    [ ] - expect -1

// constraints:
//   1 <= nums.length <= 100
//   0 <= nums[i] <= 1000

import java.util.Arrays;

// key points:
//    It can be proven that if x exists, then value for x must be unique
//    considering num.length >= 1, if x exists, then the range of x must be [1, num.length]
public class LC_1608_Special_Array_With_X_Elements_Greater_Than_or_Equal_X {
}



// solution 1: binary search to find x in range [1, num.length] + for each x: iterate array to calculate count of values who >= x
// time: O(NlogN)
// space: O(1)
class LC_1608_Solution_1 {

    public int specialArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1; // edge case

        int L = 1;
        int R = n;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            int count = countX(nums, mid);

            if (count == mid) {
                return count;
            } else if (count < mid) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return -1;
    }

    private int countX(int[] nums, int target) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                count++;
            }
        }

        return count;
    }
}


// solution 2: sort + binary search to find x in range [1, num.length] + binary search to find minimum value >= x
// time: O(NlogN) + O(logN * logN) >> O(NlogN)
// space: O(logN)
class LC_1608_Solution_2 {

    public int specialArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1; // edge case

        Arrays.sort(nums);

        int L = 1;
        int R = n;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            int index = findMiniIndex(nums, mid);
            int count = n - index + 1;

            if (count == mid) {
                return count;
            } else if (index == -1 || count < mid) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return -1;
    }

    // binary search to find minimum value >= x
    // the minimum value (>= target) might/might not exist. So, post-process is required.
    // for example [0, 0, 3, 3, 4]:
    //  - if target = 5, L/R will stop at index 4 (0-indexed)
    //  - if target = 2, L/R will stop at index 2 (0-indexed)
    private int findMiniIndex(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;

        while (L < R) {
            int mid = L + (R - L) / 2;

            if (nums[mid] >= target) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        // post-processing: check if L (R) is valid
        if (nums[L] >= target) {
            return L; // index is found
        } else {
            return -1; // the minimum value (>= target) doesn't exist in array
        }
    }
}

// solution 3: counting sort (count array) + prefix sum
// time: O(N)
// space: O(N)
class LC_1608_Solution_3 {

    // key 1: frequency array freq size = N + 1 (N is nums.length), where the last valve freq[N] holds the number of values in nums which are >= N
    // key 2: we choose to iterate frequency array from right end, while iterating freq array from right to left

    public int specialArray(int[] nums) {
        int N =  nums.length;
        if (N == 0) return -1; // edge case

        // initialize count (frequency) array + fill in the count array
        int[] freq = new int[N + 1];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= N) {
                freq[N]++;
            } else {
                freq[nums[i]]++;
            }
        }

        // find x by iterating from N to i
        // here, i can do 2 things:
        //   - iterate over range [N, 1]
        //   - iterate over freq array from right to left
        int totalValidCount = 0; // this is prefix sum

        for (int i = N; i >= 1; i--) {
            totalValidCount = totalValidCount + freq[i];

            if (totalValidCount == i) {
                return i;
            }
        }

        return -1;
    }
}