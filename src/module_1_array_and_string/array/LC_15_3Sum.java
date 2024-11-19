package module_1_array_and_string.array;

import java.util.*;

// Key Challenge: the returned triplets should be distinct
// Input: nums = [-1,0,1,2,-1,-4]
// Expected output: [[-1,-1,2],[-1,0,1]]
// Invalid output: [[-1,0,1],[-1,2,-1],[0,1,-1]]
public class LC_15_3Sum {

    // solution-1 (Time Limit Exceeded): brute force - find each triplet pair
    // time: O(N^3 + N/3) >> N(N^3), in the worse case, there are N/3 distinct triplets
    // space: O(N/3) >> O(N), caused by HashSet, the ArrayList is not considered.
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length -2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list); // after sorting the array, all duplicate triplets will have the element order

                        set.add(list); // the set will remove duplicated triplets
                    }
                }
            }
        }

//        List<List<Integer>> results = new ArrayList<>();
//        for (List<Integer> list : set) {
//            results.add(list);
//        }
//        return results;

        return new ArrayList(set); // directly convert set to an ArrayList
    }


    // solution-2: sort + two-pointers (two-ends pointers)
    // this solution is the most recommended
    // time: O(NlogN + N^2) >> O(N^2)
    // space: O(1)
    public List<List<Integer>> threeSum_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) { // skip duplicate triplet based on pivot pointer i
                continue;
            }

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    List<Integer> triple = new ArrayList<>();
                    triple.add(nums[i]);
                    triple.add(nums[l]);
                    triple.add(nums[r]);
                    res.add(triple);

                    l++;
                    r--;

                    while (l < r && nums[l - 1] == nums[l]) { // skip duplicate triplet based on left pointer l
                        l++;
                    }
                }
            }
        }

        return res;
    }


    // solution-3: sort + HashSet
    // time: O(NlogN + N^2) >> O(N^2)
    // space: O(N), caused by the HashSet
    public List<List<Integer>> threeSum_3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) { // skip duplicate triplet based on pivot pointer i
                continue;
            }

            Set<Integer> set = new HashSet<>();

            for (int j = i + 1; j < nums.length; j++) {
                int complement = 0 - nums[i] - nums[j];

                if (!set.contains(complement)) {
                    set.add(nums[j]);

                } else {
                    List<Integer> triple = new ArrayList<>();
                    triple.add(nums[i]);
                    triple.add(nums[j]);
                    triple.add(complement);
                    res.add(triple);

                    set.add(nums[j]);

                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) { // skip duplicate triplet based on pointer j
                        j++;
                    }
                }
            }
        }

        return res;
    }

    // solution-4: no-sort -> find all i-j pairs + HashMap
    // this solution is running as a follow-up question, like what if we are not allowed to sort/change the array
    // time: O(N^2)
    // space: O(N), caused by HashMap
    public List<List<Integer>> threeSum_4(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();

        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length - 1; i++) {
            // Use HashSet to skip duplicates in the outer loop
            // Without this optimization, the submission will time out for the test case with 3,000 zeroes.
            // otherwise, it is not necessary to add this if-block
            if (!set.contains(nums[i])) {
                set.add(nums[i]);

                for (int j = i + 1; j < nums.length; j++) {
                    int complement = 0 - nums[i] - nums[j];
                    if (map.containsKey(complement) && map.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }

                    map.put(nums[j], i);
                }
            }
        }

        return new ArrayList(res);
    }

    // solution-5: sort + binary search (time limit exceeded)
    // sort array >> fix two elements >> binary search for 3rd element
    // how to remove duplicate triplets? - since array is sorted, duplicate triplets will have exactly the same elements. So, we can use set to remove duplicate triplets
    // time: O(NlogN) + O((N^2)logN) + O((N^2)logN) - set's size
    // space: O(logN) + O((N^2)logN) - set's size
    public static List<List<Integer>> threeSum_5(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        Arrays.sort(nums);

        // fix two elements
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {

                // use binary search to quickly iterate 3rd element
                int L = j + 1;
                int R = nums.length - 1;

                while (L <= R) {
                    int mid = L + (R - L) / 2;
                    int currSum = nums[i] + nums[j] + nums[mid];

                    if (currSum == 0) {
                        List<Integer> sumList = new ArrayList<>();
                        sumList.add(nums[i]);
                        sumList.add(nums[j]);
                        sumList.add(nums[mid]);
                        set.add(sumList); // remove duplicate triplets

                        // Now we MUST move either pointer L or R so to avoid dead loop. Either will work:
                        //  1)  L = mid + 1;
                        //  2)  R = mid - 1;
                        //  3)  break; - // (recommended) Given j, j are fixed, mid is fixed as well. now if mid is found, we can stop this binary search immediately
                        // Because, no matter in which side (left ort right) the next duplicate will be, we will also ignore it in result set
                        break;
                    } else if (currSum < 0) {
                        L = mid + 1;
                    } else {
                        R = mid - 1;
                    }
                }
            }
        }

        // time: O(set's size)
        return new ArrayList(set); // directly convert set to an ArrayList
    }

    // solution-5.1: sort + binary search - optimize time (recommended)
    public static List<List<Integer>> threeSum_5_1(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        Arrays.sort(nums);

        // fix two elements
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) { // if j>0, then it's wrong
                    continue;
                }

                // use binary search to quickly iterate 3rd element
                int L = j + 1;
                int R = nums.length - 1;

                while (L <= R) {
                    int mid = L + (R - L) / 2;
                    int currSum = nums[i] + nums[j] + nums[mid];

                    if (currSum == 0) {
                        List<Integer> sumList = new ArrayList<>();
                        sumList.add(nums[i]);
                        sumList.add(nums[j]);
                        sumList.add(nums[mid]);
                        set.add(sumList); // remove duplicate triplets

                        // Now we MUST move either pointer L or R so to avoid dead loop. Either will work:
                        //  1)  L = mid + 1;
                        //  2)  R = mid - 1;
                        //  3)  break; - // (recommended) Given j, j are fixed, mid is fixed as well. now if mid is found, we can stop this binary search immediately
                        // Because, no matter in which side (left ort right) the next duplicate will be, we will also ignore it in result set
                        break;
                    } else if (currSum < 0) {
                        L = mid + 1;
                    } else {
                        R = mid - 1;
                    }
                }
            }
        }

        // time: O(set's size)
        return new ArrayList(set); // directly convert set to an ArrayList
    }


    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();

        boolean b = set.add(1);
        // true - if this set did not already contain the specified element
        // false - if this set already contains the specified element


        List<List<Integer>> res = threeSum_5_1(new int[]{-1,0,1,2,-1,-4});
        System.out.println(res);
    }
}
