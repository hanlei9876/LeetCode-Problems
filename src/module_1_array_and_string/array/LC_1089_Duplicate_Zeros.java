package module_1_array_and_string.array;

// in-place operation on array
public class LC_1089_Duplicate_Zeros {

    // use a new array as helper, but return the modified input array
    // time: O(N)
    // Space: O(N)
    public void duplicateZeros(int[] arr) {
        int[] nums = new int[arr.length];

        // step-1: duplicate the zeros in presence
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (arr[i] != 0) {
                nums[j] = arr[i];
                i++;
                j++;
            } else {
                /*
                nums[j] = 0;
                j++;
                if (j < nums.length) {
                  nums[j] = 0;
                  j++;
                }
                i++;
                */

                // the initialized values of an array are zeros, so we don't have to set the spots to be zeros
                j = j + 2;
                i++;
            }
        }

        // step-2: copy the elements from helper array to input array
        for (int k = 0; k < arr.length; k++) {
            arr[k] = nums[k];
        }
    }

    // brute force, using two pointers i & j
    // as long as pointer i is 0, we shift all the elements from i to its right for one step
    // time: O(N^2) when worst case, which is an array of all 0s. In the worst case: O( N + (N-1) + ... + 1 ) = O( (N^2 + N)/2 ) = O(N^2 + N) = O(N^2)
    // space: O(1)
    public void duplicateZeros_2(int[] arr) {
        int i = 0;

        while(i < arr.length) {
            if(arr[i] == 0) {
                // modify the array in-place
                for(int j = arr.length - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                i = i + 2;
            } else {
                i++;
            }
        }
    }

    // time: O(N)
    // Space: O(1)
    public static void duplicateZeros_3(int[] arr) {
        // step-1: calculate the number of elements which would be discarded from the end of the array
        // to achieve this, we need to prepare two things:
        //      (1) dupZeroCounter (init: 0) - this will help to calculate the boundary of kept elements for the next step
        //      (2) length_ (init: arr.length - 1) - if the edge case occures, length_ will be arr.length - 1.
        //          edge case: [1, 0, 2, 0, 4, 0, 5, 0, 7], where the 0 at index of 5 will be kept but not be duplicated
        int dupZeroCounter = 0;
        int length_ = arr.length - 1;
        boolean isEdgeCase = false;

        for (int i = 0; i <= length_ - dupZeroCounter; i++) {
            if ( arr[i] == 0) {
                // below is how to deal with the edge case
                if (i == length_ - dupZeroCounter) {
                    arr[length_] = 0;
                    isEdgeCase = true;
                    break;
                }
                dupZeroCounter++;
            }
        }

        // calculate the boundary of kept elements for the next step
        int effectiveIndex;
        if (isEdgeCase) {
            effectiveIndex = length_ - dupZeroCounter - 1;
            length_--;
        } else {
            effectiveIndex = length_ - dupZeroCounter;
        }

        System.out.println(effectiveIndex);


        // step-2: iterate through the effective elements of the array ONLY, to duplicate each occurrence of 0
        // use two pointers to achieve this
        int k = effectiveIndex;
        int l = length_--;
        while (k >= 0) {
            if (arr[k] == 0) {
                arr[l] = arr[k];
                l--;
                arr[l] = arr[k];

                k--;
                l--;
            } else {
                arr[l] = arr[k];
                k--;
                l--;
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[5];
//        for (int num : nums) {
//            System.out.println(num);
//        }

        // int[] a = {1,0,2,3,0,4,5,0};  // normal case
        // int[] a = {1,0,2,0,4,0,5,0};  // edge case the 0 at index 5 don't get duplicated
        int[] a = {0,0,0,0,0,0,0};
        for (int num : a) {
            System.out.print(num);
        }
        System.out.println();
        duplicateZeros_3(a);
        for (int num : a) {
            System.out.print(num);
        }
    }
}
