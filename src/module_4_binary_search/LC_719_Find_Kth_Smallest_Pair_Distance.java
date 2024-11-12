package module_4_binary_search;

import java.util.Arrays;

public class LC_719_Find_Kth_Smallest_Pair_Distance {

    // prerequisite:
    // a pair in array - 2 different located elements in array makes a pair (nums[i], nums[j])
    // we regard (nums[i], nums[j]) & (nums[j], nums[i]) are the same pair

    // Given an arr, where length == n, the total number pairs is C(1,n) * C(1,n-1)/2 >> n*(n-1)/2
    // The reason why "being divided by 2" is because we need to remove duplicates,
    //    e.g. arr[0] == 1, arr[1]==3, then (1,3) & (3,1) are duplicates

    // solution 1: brute force
    //  - iterate all pairs in array - time: O(N^2)
    //  - for each pair, calculate distance and save it in arraylist
    //  - sort the arraylist - time: O(NlogN)
    //  - get (k-1)th element in arraylist as result
    // time: O(N^2 )+ O((N^2)log(N^2))
    // space: O(N^2) + O(log(N^2))

    // solution 2: brute force (optimize solution 1 with Max Heap)
    //  - iterate all pairs in array
    //  - for each pair, calculate distance
    //  - use a max-heap to keep track of the k smallest distances. If the heap size exceeds k, we remove the largest element.
    //  - After processing all pairs, the root of the heap will represent the k-th smallest distance.


    // solution 3: bucket sort - optimize solution 1
    // key 1: the range of k (possible distances) is finite & bounded
    //      - minimum k is 0 (two elements having equal values)
    //      - maximum k is max(arr[i]) (difference between the largest and smallest numbers in array, where each number in arr >=0)
    // key 2: treat each possible integer within the range as a bucket
    // key 3: inside each bucket, we only store the count of the number in array
    //
    // time: O(N + N^2 + M) >> O(N^2 + M) - N is array length, M is max value in array
    // space: O(M) - caused by bucket
    public int smallestDistancePair_3(int[] nums, int k) {
        // find max number in the array
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        // initialize bucket for range 0 ~ max(arr[i]) inclusive
        int[] bucket = new int[max + 1];

        // traverse each pair in array to fill in bucket (traverse while sorting)
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                bucket[diff]++;
            }
        }

        // traverse bucket to find kth the smallest value (template)
        for (int i = 0; i < bucket.length; i++) {
            k = k - bucket[i];

            if (k <= 0) {
                return i;
            }
        }

        return -1; // we never reach here
    }

    // solution 4: binary search + dynamic programming
    // Sorting is crucial because it enables us to efficiently count pairs whose distances <= a given value
    // use DP to implement the counting process
    //---  skip this solution deliberately, come back after learning DP  ---//


    // solution 5: binary search + sliding window (see example graph in note)
    //   - do binary search on range of distance of input array [0, max-min]
    //   - search goal: find the distance D from the range, so that the count of pairs in input array whose distances <= k
    //   - for each distance-mid in binary search, do sliding window on input array to count how many pairs of input array whose distances <= distance-mid
    //   - sliding window's prerequisite: array is sorted
    //
    // time: O(NlogN) + O(NlogM) - M is the largest number in input array
    // space: O(logN)
    public int smallestDistancePair_5(int[] nums, int k) {
        Arrays.sort(nums);

        // initialize the distance range
        int minRange = 0;
        int maxRange = nums[nums.length - 1] - nums[0];

        int L = minRange;
        int R = maxRange;
        while (L < R) {
            int mid =  L + (R - L) / 2;

            int count = countDistances(nums, mid);

            if (count < k) {
                L = mid + L;
            } else {
                // count >= k - for this condition, see note
                R = mid;
            }
        }

        return L; // L == R

    }

    // sliding window on sorted input array (coding template)
    //  - traverse each pair of input array to count how many pairs of input array whose distances <= distance-mid
    //  - we must sort nums to use sliding window. Otherwise, it is not working, e.g. count pairs in [1, 6, 1], targetDistance = 2
    //
    // time: O(2N) >> O(N)
    // space: O(1)
    private static int countDistances(int[] nums, int targetDistance) {
        int pairCount = 0;

        int low = 0;
        int high = 1; // low == high is not a valid pair
        while (high < nums.length) {
            while (nums[high] - nums[low] > targetDistance) {
                low++;
            }

            // (high - low) is the number of qualified pairs between high & low
            // because all pairs (left, right), (left+1, right), ..., (right-1, right) are valid
            pairCount = pairCount + (high - low);

            high++;
        }

        return pairCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 7};

        int[] nums_ = {1, 3, 1, 7};

        int count = countDistances(nums, 3);
        System.out.println(count);
    }
}
