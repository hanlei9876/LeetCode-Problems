package module_1_array_and_string.array;

import java.util.Arrays;
import java.util.Comparator;

// discussion with interviewer: whether the given input array is sorted against some criteria,
// such as against the first element of each elemental array
// here, the input array is NOT sorted
public class LC_252_Meeting_Rooms_I {

    // solution 1: brute force
    // enumerate all possible interval pairs
    // time: (N^2)
    // space: O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0)
            return false;

        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {

                // check if two intervals have overlap
                if ((intervals[j][0] < intervals[i][1] && intervals[i][1] <= intervals[j][1]) ||
                        (intervals[i][0] < intervals[j][1] && intervals[j][1] <= intervals[i][1])) {
                    return false;
                }
            }
        }

        return true;
    }

    // solution 2: sort >> one pass
    // time: O(NlogN + N) >> O(NlogN)
    // space: O(logN), caused by Arrays.sort()
    public boolean canAttendMeetings_2(int[][] intervals) {
        Comparator<int[]> c = (int[] a, int[] b) -> (a[0] - b[0]);
        Arrays.sort(intervals, c);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }
}
