package module_4_binary_search;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LC_658_Find_K_Closest_Elements {

    // time: O(N) + O(NlogN) + O(klogk)
    // space: O(N) + O(logN) + O(logk) + O(k)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // convert from array to list to make use of Collection.sort()
        List<Integer> list = new ArrayList<>();

        for (int num : arr) {
            list.add(num);
        }

        /*
         * What if Math.abs(num1 - x) - Math.abs(num2 - x) == 0?
         *   - Collections.sort() is to maintain their relative order from the original list (a.k.a. Stable Sorting).
         * */
        Collections.sort(list, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

        List<Integer> res = list.subList(0, k);

        Collections.sort(res);

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        System.out.println(list);


        Collections.sort(list, (num1, num2) -> Math.abs(num1 - 3) - Math.abs(num2 - 3));

        System.out.println(list);
    }
}
