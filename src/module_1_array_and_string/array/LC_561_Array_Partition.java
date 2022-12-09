package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_561_Array_Partition {

    // Approach 1: sort first, then, pair the adjacent ones
    // time: O(NlogN + N/2) = O(NlogN)
    // space: O(log(N))
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sum = sum + nums[i];
        }

        return sum;
    }

    // Approach 2 (Wrong version): use frequency array to avoid sorting
    // time: O(N + K), N is the number of pairs, while K is a constant 10^4
    // space: O(2*10^4 + 1)
    public int arrayPairSum_2(int[] nums) {
        final int K = 10000;
        int sum = 0;
        int[] frequencyArray = new int[2 * K + 1];

        // populate the frequency of each element in the input array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                frequencyArray[nums[i]]++;
            }
            else {
                int index = nums[i] * (-1) + K;
                frequencyArray[index]++;
            }
        }

        // iterate the frequency array to find even-indexed elements
        boolean isEvenIndex = true;
        for (int i = 0; i < frequencyArray.length; i++) {
            while (frequencyArray[i] > 0) {
                if (isEvenIndex)
                    sum = (i <= K) ? (sum + i) : (sum + (i-K)*(-1));

                frequencyArray[i]--;
                isEvenIndex = !isEvenIndex;
            }
        }

        return sum;
    }

    // Approach 2 (right version): use frequency array to avoid sorting
    // time: O(N + K), N is the number of pairs, while K is a constant 10^4
    // space: O(2*10^4 + 1)
    public static int arrayPairSum_3(int[] nums) {
        final int K = 10000;
        int sum = 0;
        int[] frequencyArray = new int[2 * K + 1];

        // populate the frequency of each element in the input array
        for (int i = 0; i < nums.length; i++) {
            frequencyArray[nums[i] + K]++;
        }

        // iterate the frequency array to find even-indexed elements
        boolean isEvenIndex = true;
        for (int i = 0; i < frequencyArray.length; i++) {
            while (frequencyArray[i] > 0) {
                if (isEvenIndex) {
                    sum = sum + (i - K);
                }

                frequencyArray[i]--;
                isEvenIndex = !isEvenIndex;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2};

        int res = arrayPairSum_3(arr);

        System.out.println(res);
    }
}
