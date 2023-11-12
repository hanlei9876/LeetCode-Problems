package module_3_recursion_I_and_II;

// 1 - quick sort
public class LC_912_Sort_an_Array_quick_sort {

    // time limit exceeded when sorting highly duplicate values
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1); // sort nums in place
        return nums;
    }

    // the quickSort will sort the array in-place
    private void quickSort(int[] nums, int low, int high) {
        // base case: len == 0 or 1
        if (low >= high) {
            return; // do nothing
        }

        // recurrence relation
        int pivot = partition(nums, low, high);
        quickSort(nums, low, pivot - 1);
        quickSort(nums, pivot + 1, high);
    }

    // this method will return the index of pivot after partition
    private int partition(int[] nums, int low, int high) {
        // always: low < high
        // here, we use high as pivot index
        int p = high;
        int i = low; // slow pointer
        for (int j = low; j < p; j++) {
            if (nums[j] < nums[p]) {
                // swap nums[i] & nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
            }
        }

        // swap nums[i] & nums[p]
        int temp = nums[i];
        nums[i] = nums[p];
        nums[p] = temp;

        return i; // return index of pivot
    }
}
