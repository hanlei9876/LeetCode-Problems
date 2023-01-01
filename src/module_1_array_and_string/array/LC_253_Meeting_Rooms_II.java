package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_253_Meeting_Rooms_II {

    // solution 2: chronological ordering
    // time: O()
    // space: O()
    public int minMeetingRooms(int[][] intervals) {
        // copy start points & end points to two separate arrays, respectively
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        // sort the two arrays, respectively
        Arrays.sort(starts);
        Arrays.sort(ends);

        // use two pointers to iterate the two arrays, respectively
        int maxRoom = 0;
        int roomNum = 0;
        int i = 0; // iterate starts[i] array
        int j = 0; // iterate ends[j] array
        while (i < starts.length) {
            if (starts[i] < ends[j]) {
                roomNum++;
                i++;
            } else {
                roomNum--;
                j++;
            }

            maxRoom = Math.max(maxRoom, roomNum);
        }

        return maxRoom;
    }

}
