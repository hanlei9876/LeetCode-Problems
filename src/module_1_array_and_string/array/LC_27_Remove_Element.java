package module_1_array_and_string.array;

// space complexity must be O(1). must do it in-place
public class LC_27_Remove_Element {

    // time: O(N + (N-1) + ... + 1) = O((N^2 - N)/2) = O(N^2)
    // space: O(1)
    // both edge cases are covered: [], [1] with val=1
    public static int removeElement(int[] nums, int val) {
        int countOfRemove = 0;

        int i = 0;
        while (i < nums.length - countOfRemove) {
            if (nums[i] == val) {
                // in this condition, we cannot make i++, since the next element copied to i could be to remove. for example,
                // [0,1,2,2,3,0,4,2] val = 2
                int j = i + 1;
                while (j < nums.length - countOfRemove) {
                    nums[j - 1] = nums[j];
                    j++;
                }

                countOfRemove++;
            } else {
                i++;
            }
        }

        return nums.length - countOfRemove;
    }

    // two-pointer technique (i, j): i from beginning; j from end, until i=j
    // i is for accessing each element to check, j is for preparing element for replacement
    // time: O(N)
    // space: O(1)
    // // both edge cases are covered: [], [1] with val=1, [2] with val=1
    public static int removeElement_2(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            if (nums[i] == val) {
                nums[i] = nums[j];
                j--;
            } else {
                i++;
            }
        }

        return j + 1;
    }


    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};

        int a = removeElement(nums, 2);

        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println(a);


        // edge case:
        int[] nums_1 = new int[0];
        System.out.println(nums_1.length);

    }
}
