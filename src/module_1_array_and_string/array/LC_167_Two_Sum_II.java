package module_1_array_and_string.array;

import java.util.HashMap;
import java.util.Map;

// 167. Two Sum II - Input Array Is Sorted
// each input has one & only one solution
public class LC_167_Two_Sum_II {

    // solution 1: brute force - enumerate each possible pair
    // time: O(N^2)
    // space: O(1)
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    res[0] = i + 1;
                    res[1] = j + 1;
                    break;
                }
            }
        }

        return res;
    }

    // solution 2: use HashMap
    // time: O(N)
    // space: O(N)
    public int[] twoSum_2(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(numbers[i])) {
                int complement = target - numbers[i];
                map.put(complement, i);
            } else {
                int index = map.get(numbers[i]);
                res[0] = index + 1;
                res[1] = i + 1;
                break;
            }
        }

        return res;
    }

    // solution 3: two-pointers (two-end pointers) - since array is sorted
    // time: O(N)
    // space: O(1)
    public int[] twoSum_3(int[] numbers, int target) {
        int[] res = new int[2];

        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                res[0] = i + 1;
                res[1] = j + 1;
                break;
            }
        }

        return res;
    }

    // solution 4: binary search
    // goal: for each element from left, do binary search to find if the answer exist
    // time: O(NlogN)
    // space: O(1)
    public int[] twoSum_4(int[] numbers, int target) {
        int[] res = new int[2];

        for (int i = 0; i < numbers.length - 1; i++) {
            int complement = target - numbers[i];

            int resultIndex = binarySearch(numbers, complement, i + 1);

            if (resultIndex != -1) {
                res[0] = i + 1;
                res[1] = resultIndex + 1;

                break;
            }
        }

        return res;
    }

    private int binarySearch(int[] nums, int tar, int leftBoundary) {
        int L = leftBoundary;
        int R = nums.length - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;

            if (nums[mid] == tar) {
                return mid;
            } else if (nums[mid] < tar) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return -1;
    }
}
