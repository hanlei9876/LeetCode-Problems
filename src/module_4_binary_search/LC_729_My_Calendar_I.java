package module_4_binary_search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LC_729_My_Calendar_I {

    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(startTime,endTime);
     */

    // 花花酱: https://www.youtube.com/watch?v=seQnf-5hlBo

    // for overall complexity, below is needed to upgrade - we consider taking N calls on methods hit(), getHits() separately. Refer to LC-981

    // key - overlap of two ranges/intervals [s1, e1), [s2, e2) <==> max(s1, s2) < min(e1, e2). In details,
    //   - if the two intervals have NO overlap <==> e1 <= s2 AND e2 <= s1
    //   - if the two intervals have overlap <==> e1 > s2 AND e2 > s1

    public static void main(String[] args) {
        // for solution 1:
        int[] arr1 = {1, 2, 3};
        int[] arr2 = null;
        int[] arr3 = new int[3]; // each element is initialized with 0

        for (int num : arr3) { // when arr2 - null pointer exception. when nums3 - nums[i] is 0
            System.out.println("me");
            System.out.println(num);
        }
        System.out.println("finished");

        List<int[]> calender = new ArrayList<>();
        System.out.println(calender.size()); // 0

        for (int[] tuple : calender) { // this for loop is skipped
            System.out.println("It's me!");
        }

        // for solution 2: (test TreeMap)
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2, "apple");
        treeMap.put(5, "pineapple");
        treeMap.put(1, "guava");

        System.out.println(treeMap); // {1=guava, 2=apple, 5=pineapple}


        int ceiling = treeMap.ceilingKey(6); // null pointer exception
        System.out.println(ceiling);
    }
}


// solution 1: brute force (linear search) - use List<int[2]> where int[2] as tuple for [s, e)
// time: O(1) + O(2) + O(3) + ... + O(N) = O(N^2)
// space: O(N)
// where N is the size of calendar / N is the number of events booked
class MyCalendar {

    List<int[]> calender;

    public MyCalendar() {
        calender = new ArrayList<>();
    }

    // single call's complexity
    // time: O(N)
    // space: O(N)
    public boolean book(int startTime, int endTime) {
        /*if (calender.size() == 0) {
            calender.add(new int[]{startTime, endTime});
            return true;
        }*/

        for (int[] event : calender) {
            // if (event[1] > startTime && endTime > event[0])
            if (Math.max(event[0], startTime) < Math.min(event[1], endTime)) {
                return false;
            }
        }

        calender.add(new int[]{startTime, endTime});
        return true;
    }
}


// solution 2: TreeMap (Red-Black tree) + Binary search
// time: O(log1 + log2 + ... logN) >> O(NlogN)
// space: O(N)
class MyCalendar_2 {

    TreeMap<Integer, Integer> calendar; // key - startTime, value - endTime

    public MyCalendar_2() {
        calendar = new TreeMap<>();
    }

    // single call's complexity
    // time: O(3logN) >> O(log(N))
    // space: O(N)
    public boolean book(int startTime, int endTime) {
        Integer floor = calendar.floorKey(startTime); // "int" - could be null pointer exception
        Integer ceiling = calendar.ceilingKey(startTime); // "int" - could be null pointer exception

        // check my note for all 4 possible cases
        // given input [s, e), there are 4 cases to input:
        //  1) TreeMap is empty
        //
        // TreeMap is NOT empty and has 3 key-value pairs:
        //          [s1, e1)    [s2, e2)    [s3, e3)
        //  2)   [s, e)
        //  3)               [s, e)
        //  4)                                  [s, e)
        if ( (floor != null && startTime < calendar.get(floor)) || (ceiling != null && ceiling < endTime) ) {
            return false;
        }

        calendar.put(startTime, endTime);
        return true;
    }
}