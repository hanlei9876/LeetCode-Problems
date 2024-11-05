package module_4_binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_350_Intersection_of_Two_Arrays_II {

    // solution 1: sort array + two pointers
    // I can recommend to interviewer this method when the input is sorted.
    // In this way, the output is also sorted
    // time: O(mlogm) + O(nlogn) + O(m + n) (worst case) + O(intersection)
    // space: O(logm) + O(logn) + O(intersection) - res is not counted
    public int[] intersect_1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();
        // or we could just use nums1 or nums2 to hold results, so to avoid using ArrayList

        int p1 = 0;
        int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                list.add(nums1[p1]);
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        // convert list to array
        // don't use return res.stream().mapToInt(i->i).toArray(); >> it is slow
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }


    // solutions 2: hashmap
    // time: O(m + n) + O(intersection)
    // space: O(min(m, n)) + O(intersection) - res is not counted
    public int[] intersect_2(int[] nums1, int[] nums2) {
        // optimize space -  we use hashmap on the smaller array
        if (nums1.length > nums2.length) {
            return intersect_2(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int val = map.get(num);
                val++;
                map.put(num, val);
            }
        }

        List<Integer> list = new ArrayList<>();
        // or we could just use nums1 to hold results, so to avoid using ArrayList

        for (int num : nums2) {
            // if num not in map >> skip it
            // if num in map && val == 0 >> skip it
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);

                int val = map.get(num);
                val--;
                map.put(num, val);
            }
        }

        // convert list to array
        // don't use return res.stream().mapToInt(i->i).toArray(); >> it is slow
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }


    // solutions 3: binary search
    //    >> search shorter array nums1
    //    >> for each element in nums1, do binary search on nums2 to find first occurrence of this element
    //    >> when search is done, move L ro right for one step for next binary search
    // time: O(mlogm) + O(nlogn) + O(mlogn) + O(intersection)
    // space: O(logm) + O(logn) + O(intersection) - res is not counted
    public int[] intersect_3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect_3(nums2, nums1);
        }

        List<Integer> resList = new ArrayList<>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int leftSearchBoundary = 0;
        for (int num : nums1) {
            int targetIndex = binarySearch(nums2, num, leftSearchBoundary);

            if (targetIndex != -1) {
                resList.add(num);
                leftSearchBoundary = targetIndex + 1;
            }
        }

        // convert list to array
        // don't use return res.stream().mapToInt(i->i).toArray(); >> it is slow
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }

        return res;
    }

    // binary search
    // goal: find first occurrence of target in the dynamic search scope
    private int binarySearch(int[] nums, int target, int leftSearchBoundary) {
        int L = leftSearchBoundary;
        int R = nums.length - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;

            if (nums[mid] == target) { // mid might be or might not the first occurrence in the dynamic search scope
                // we cannot set: if (mid == 0 || nums[mid - 1] != nums[mid]), as it will not get right result
                // see example:
                // Input: nums1 = [1,2,2,1], nums2 = [2,2]
                // expected output: [2,2]
                // wrong output: [2] - the second 2 will be ignored
                if (leftSearchBoundary == mid || nums[mid - 1] != nums[mid]) {
                    return mid; // find first occurrence
                } else {
                    R = mid - 1; // mid is certainly not first occurrence
                }
            } else if (nums[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        // target is not found
        return -1;
    }




    // follow-up question
    /*
    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

      1. If nums1 fits into the memory, we can use Approach 2 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.

      2. If neither of the arrays fit into the memory, we can apply some partial processing strategies:
         - Split the numeric range into subranges that fits into the memory. Modify Approach 2 to collect counts only within a given subrange, and call the method multiple times (for each subrange).
         - Use an external sort for both arrays. Modify Approach 1 to load and process arrays sequentially.
    * */
}
