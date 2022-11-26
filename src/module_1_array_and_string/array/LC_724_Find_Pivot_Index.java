package module_1_array_and_string.array;

public class LC_724_Find_Pivot_Index {

    // iterate through the array, maintain leftSum and rightSum
    // time: worst case: O(2N) -> O(N)
    // space: O(1)
    public static int pivotIndex(int[] nums) {
        int res = -1;

        // initialize leftSum, rightSum, i
        int rightSum = 0;
        for (int i = 1; i < nums.length; i++) {
            rightSum += nums[i];
        }

        int leftSum = 0;

        int i = 0;
        while (i < nums.length) {
            if (leftSum == rightSum) {
                res = i;
                break;
            } else {
                leftSum += nums[i];

                if (i + 1 < nums.length) {
                    rightSum -= nums[i + 1];
                }

                i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,7,3,6,5,6};

        int re = pivotIndex(a);
    }

}
