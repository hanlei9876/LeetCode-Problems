package module_4_binary_search;

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
    public int smallestDistancePair(int[] nums, int k) {
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
}
