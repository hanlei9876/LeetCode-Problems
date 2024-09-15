package module_4_binary_search;

// make as few API calls as we can
public class LC_278_First_Bad_Version {

    // solution 1: linear search (brute force)
    // time: O(n) - make n-1 calls to API
    // space: O(1)
    public int firstBadVersion_v1(int n) {
        for (int i = 1; i < n; i++) {
            if (isBadVersion(i)) {
                return i;
            }
        }

        return n;
    }

    // solution 2: binary search (my own developed one)
    // time: O(logn)
    // space: O(1)
    public int firstBadVersion_v2(int n) {
        int L = 1;
        int R = n;

        while (L <= R) {
            int mid = L + (R - L) / 2;

            if (isBadVersion(mid)) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return L;
    }

    // solution 3: binary search - using template 2 (recommended)
    // ensure that left ~ right (inclusive) is the search space that contains the first bad version
    //
    // why the meeting point of left & right is the first Bad version?
    // This can be proved. But we can use a tip to test an input of size 2, and check the result:
    // case 1: B B
    // case 2: G B
    //
    // time: O(logn)
    // space: O(1)
    public int firstBadVersion_v3(int n) {
        int left = 1;
        int right = n;

        // if we set while (left <= right), then this will be infinite loop
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                // mid could be the first bad version, so need to include mid as next search space
                right = mid;
            } else {
                // the first bad version must be to the right of mid
                left = mid + 1;
            }
        }

        return left;
    }

    boolean isBadVersion(int i) {return false;}
}
