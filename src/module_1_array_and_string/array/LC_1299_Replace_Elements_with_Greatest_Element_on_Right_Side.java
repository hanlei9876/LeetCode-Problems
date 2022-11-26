package module_1_array_and_string.array;

public class LC_1299_Replace_Elements_with_Greatest_Element_on_Right_Side {
    // solution 1: brute force
    // time: O(N^2)
    // space: O(1)
    public int[] replaceElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 == arr.length) {
                arr[i] = -1;
                break;
            }

            int j = i + 1;
            int max = arr[j];
            while (j < arr.length) {
                if (max < arr[j]) {
                    max = arr[j];
                }
                j++;
            }

            // max is found for arr[i]
            arr[i] = max;
        }

        return arr;
    }

    // solution 2: special algorithm for "replace with greatest element to the right"
    // starting at the end, and then constantly memorizing the maximum so far, and assigning it to the next lower index.
    // time: O(N)
    // space: O(1)
    public int[] replaceElements_2(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i]; // step-1: stash the element
            arr[i] = max; // step-2: replace with the max

            // step-3: compare the origin arr[i] and max, to update the max so far
            max= Math.max(max, temp);
            /*if (max < temp) {
                max = temp;
            }*/
        }

        return arr;
    }
}
