package module_1_array_and_string.array;

public class LC_905_Sort_Array_By_Parity {

    // solution-1: use extra array to hold the result
    // time: O(N)
    // space: O(N)
    // edge cases: [1], [2]
    public int[] sortArrayByParity(int[] nums) {
        int[] results = new int[nums.length];

        // i and j are two pointers for the result array
        int i = 0;
        int j = results.length - 1;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] % 2 == 0) {
                results[i] = nums[k];
                i++;
            } else {
                results[j] = nums[k];
                j--;
            }
        }

        return results;
    }

    // solution-2: sort the array in place
    // two-pointer technique: two-ends pointers i, j.
    // we only check the parity of elements on index i. When elements on i and j are swapped, i will stay here and check the parity of the newly swapped element.
    // time: O(N)
    // space: O(1)
    // edge cases: [1], [2]
    public int[] sortArrayByParity_2(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] % 2 != 0) {
                // when nums[i] is odd, swap i & j
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                // after swapping, the new element on i could possibly be odd again,
                // so i should stay here and check parity on the newly swapped element
                j--;
            } else {
                i++;
            }
        }

        return nums;
    }

    public static void main(String[] args) {

        int a = 0;
        System.out.println(a / 2); // 0
        System.out.println(a % 2); // 0

    }
}
