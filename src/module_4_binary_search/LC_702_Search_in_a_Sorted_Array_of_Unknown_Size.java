package module_4_binary_search;

interface ArrayReader {
    int get(int i);
}

// constraints:
//   - 1 <= secret.length <= 10^4
//   - array is sorted in a strictly increasing order (no duplicates)
// Note: the input array is already there, but we just don't the right boundary index
public class LC_702_Search_in_a_Sorted_Array_of_Unknown_Size {

    // solution 1: linear search
    // time: O(N) - N is the length of invisible array
    // space: O(1)
    public int search_1(ArrayReader reader, int target) {
        int i = 0;
        int val = reader.get(i);
        while (val != Integer.MAX_VALUE) {
            if (val == target) {
                return i;
            }

            i++;
            val = reader.get(i);
        }

        return -1;
    }

    // solution 2: find L & R boundaries + binary search within the boundary
    // complexities (R is the index of right boundary, where the binary search range reached)
    // time: O(logR) + O(logR)
    // space: O(1)
    public int search_2(ArrayReader reader, int target) {
        // edge case:
        if (reader.get(0) == target) {
            return 0;
        } else if (reader.get(0) > target) {
            return -1;
        }

        // step 1: find left & right boundaries of search space in O(logN) time
        int L = 0;
        int R = 1;
        while (reader.get(R) < target) {
            L = R;
            R = R * 2;
        }
        // now, we can assure L < target <= R (see note)

        // step 2: binary search in L ~ R
        int left = L;
        int right = R;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}


