package module_1_array_and_string.array;

// 167. Two Sum II - Input Array Is Sorted
public class LC_167_Two_Sum_II {

    // solution 1: brute force - enumerate each possble pair
    // time: O(N^2)
    // space: O(1)
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    res[0] = i + 1;
                    res[1] = j + 1;
                    break;
                }
            }
        }

        return res;
    }

    // solution 2: two-pointers (two-end pointers)
    // time: O(N)
    // space: O(1)
    public int[] twoSum_2(int[] numbers, int target) {
        int[] res = new int[2];

        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                res[0] = i + 1;
                res[1] = j + 1;
                break;
            }
        }

        return res;
    }
}
