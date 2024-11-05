package module_4_binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 1 <= nums1.length, nums2.length <= 1000
// need to master solution 2 (two pointers), 4 (two sets), 5 (hashmap)
public class LC_349_Intersection_of_Two_Arrays {

    // solution 1: Java built-in HashSet.retainAll()
    // time: O(m + n) + O(m + n) + O(intersection)
    // space: O(m + n) - res is not counted
    public int[] intersection_1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }

        // time: O(m + n)
        // space: O(1)
        set1.retainAll(set2);

        int[] res = new int[set1.size()];
        int i = 0;
        for (int num : set1) {
            res[i] = num;
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        // HashSet.retainAll()
        // time: O(N) - N is the larger set, as it will iterate through both of the collections
        // space: O(1) - do it in place

        // test 1 - Integers
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(7);

        System.out.println("before");
        System.out.println(set1); // [1, 2, 3]
        System.out.println(set2); // [2, 7]

        set1.retainAll(set2);

        System.out.println("after");
        System.out.println(set1); // [2]
        System.out.println(set2); // [2, 7]


        // test 2 - String
        Set<String> set3 = new HashSet<>();
        set3.add("ask");
        set3.add("me");
        set3.add("hello");

        Set<String> set4 = new HashSet<>();
        set4.add("me");
        set4.add("xxxxxx");

        System.out.println("before");
        System.out.println(set3); // [ask, me, hello]
        System.out.println(set4); // [me, xxxxxx]

        set3.retainAll(set4);

        System.out.println("after");
        System.out.println(set3); // [me]
        System.out.println(set4); // [me, xxxxxx]



        // test 3: HashMap.put(key, value)
        // what will happen if we run map.puy(2, 1) multiple times
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 1);
        System.out.println(map);
        map.put(7, 1);
        System.out.println(map);
        map.put(2, 1);
        System.out.println(map);
    }

    // solution 2: sort array + two pointers to find intersection in sorted arrays
    /* NOTE: if we use this approach, the result is also sorted */
    // time: O(mlogm) + O(nlogn) + O(m + n) + O(intersection)
    // space: O(logm) + O(logn) + O(intersection) - res is not counted
    public int[] intersection_2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> set = new HashSet<>(); // use set to avoid duplicate values in result

        // time complexity - in worst case where p1 & p2 have all unique elements and they are crossed as below, it will iterate O(m) + O(n)
        // p1: [1, 3, 5, 7]
        // p2: [2, 4, 6, 8]
        int p1 = 0;
        int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                set.add(nums1[p1]);
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        int[] res = new int[set.size()];
        int i = 0;
        for (int num : set) {
            res[i] = num;
            i++;
        }

        return res;
    }

    // solution 3 (not recommended): two sets + iterate over 1 (shorter) set - need to optimize
    // time: O(m + n) + O(m) + O(intersection)
    // space: O(m + n) + O(intersection) - res is not counted
    public int[] intersection_3(int[] nums1, int[] nums2) {
        // this will always ensure nums1.length <= nums2.length
        if (nums1.length > nums2.length) {
            intersection_3(nums2, nums1);
        }

        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }

        // iterate over the shorter set - set1
        List<Integer> list = new ArrayList<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                list.add(num);
            }
        }

        int[] res = list.stream().mapToInt(i -> i).toArray();
        return res;
    }

    // solution 4 (optimized): two sets
    // time: O(m + n) + O(intersection)
    // space: O(m) + O(intersection) - res is not counted
    public int[] intersection_4(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersectionSet.add(num);
            }
        }

        // convert set to array
        int[] res = new int[intersectionSet.size()];
        int i = 0;
        for (int num : intersectionSet) {
            res[i] = num;
            i++;
        }
        return res;
    }

    // solution 5: one hashmap (this is equivalent to LC's Approach 4)
    // time: O(m + n) + O(intersection)
    // space: O(m) - map only, res is not counted
    public int[] intersection_5(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map1 = new HashMap<>();

        for (int num : nums1) {
            map1.put(num, 0);
        }

        for (int num : nums2) {
            if (map1.containsKey(num) && map1.get(num) == 0) {
                res.add(num);
                map1.put(num, 1);
            }
        }

        // convert list to int[]
        // don't use return res.stream().mapToInt(i->i).toArray(); >> it is slow
        int[] resultArray = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resultArray[i] = res.get(i);
        }

        return resultArray;
    }


    // solution 6: binary search (check binary search solution in LC-350 first)
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
        int i = 0;
        while (i < nums1.length) { // difference 1 compared to in LC-349
            if (i > 0 && nums1[i - 1] == nums1[i]) {
                i++;
                continue;
            }

            int targetIndex = binarySearch(nums2, nums1[i], leftSearchBoundary);

            if (targetIndex != -1) {
                resList.add(nums1[i]);
                leftSearchBoundary = targetIndex + 1;
            }

            i++;
        }

        // convert list to array
        // don't use return res.stream().mapToInt(i->i).toArray(); >> it is slow
        int[] res = new int[resList.size()];
        for (int k = 0; k < resList.size(); k++) {
            res[k] = resList.get(k);
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
                if (mid == 0 || nums[mid - 1] != nums[mid]) { // difference 2 compared to in LC-349
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
}
