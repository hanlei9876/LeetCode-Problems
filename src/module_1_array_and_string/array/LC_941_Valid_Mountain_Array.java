package module_1_array_and_string.array;

public class LC_941_Valid_Mountain_Array {
    // one pass
    // time: O(N)
    // space: O(1)
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3)
            return false;

        // now arr length always >= 3
        int i = 1;
        while (i < arr.length) {
            if (arr[i - 1] == arr[i]) {
                return false;
            } else if (arr[i - 1] > arr[i]) {
                break;
            } else {
                i++;
            }
        }

        if (i == 1 || i == arr.length) {
            return false;
        }

        // until this point, we found the peak (j-1). Now, continue to check if it is constantly reducing
        while (i < arr.length) {
            if (arr[i - 1] <= arr[i]) {
                return false;
            } else {
                i++;
            }
        }

        return true;
    }
}
