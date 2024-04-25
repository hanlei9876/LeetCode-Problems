package module_3_recursion_I_and_II;

import java.util.Random;

// reference to "Code with John" on YouTube
// time: best case: NlogN, wrost case: N^2 >> average: NlogN
// space: best case: logN, worst case N >> average: logN
public class LC_912_Sort_an_Array_quick_sort_v2 {
    public static void main(String[] args) {
        // randomly generate an array
        int len = 10;
        int[] arr = new int[len];
        Random ran = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = ran.nextInt(100);
        }

        printArray(arr); // before sorting

        // sort array
        sortArray(arr);

        printArray(arr); // after sorting
    }

    private static void printArray(int[] arr) {
        System.out.println(" ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // required by the question
    public static int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quicksort(int[] arr, int lowIndex, int highIndex) {
        // base case
        if (lowIndex >= highIndex || arr.length <= 1)
            return;

        // recursive relation - partitioning using pivot
        int leftPointer = partition_v1(arr, lowIndex, highIndex);

        // recursively quicksort array
        quicksort(arr, lowIndex, leftPointer - 1);
        quicksort(arr, leftPointer + 1, highIndex);
    }

    // choose pivot v1 - always choose rightmost element as pivot
    // returns left-pointer as pivot index
    private static int partition_v1(int[] arr, int lowIndex, int highIndex) {
        // 1- choose pivot - the rightmost element
        int pivot = arr[highIndex];

        // 2 - partitioning
        int leftPointer = lowIndex;
        int rightPointer = highIndex; // important: rightPointer points to pivot

        while (leftPointer < rightPointer) {
            while (arr[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            while (arr[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(arr, leftPointer, rightPointer);
        }

        swap(arr, leftPointer, highIndex);

        return leftPointer;
    }

    // choose pivot v2 - choose a random element as pivot -  better average performance
    // returns left-pointer as pivot index
    private static int partition_v2(int[] arr, int lowIndex, int highIndex) {
        // 1- choose pivot - the rightmost element
        Random ran = new Random();
        int randomIndex = ran.nextInt(highIndex - lowIndex + 1) + lowIndex;
        swap(arr, randomIndex, highIndex);

        int pivot = arr[highIndex];

        // 2 - partitioning
        int leftPointer = lowIndex;
        int rightPointer = highIndex; // important: rightPointer points to pivot

        while (leftPointer < rightPointer) {
            while (arr[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            while (arr[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(arr, leftPointer, rightPointer);
        }

        swap(arr, leftPointer, highIndex);

        return leftPointer;
    }


    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
