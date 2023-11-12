package module_3_recursion_I_and_II;

// 1 - merge sort
public class LC_912_Sort_an_Array_merge_sort {

    // merge sort - top down
    // time: O(logN) * O(N + N) = O(NlogN)
    // space: O(logN) + O(N) + O(N) = O(logN + 2N) = O(2N) = O(N)
    public int[] sortArray(int[] nums) {
        // base case
        if (nums.length <= 1) {
            return nums;
        }

        /*** recursion relation ***/

        // divide list to sub-lists (divide)
        int mid = nums.length / 2;
        int[] list_L = new int[mid];
        int[] list_R = new int[nums.length - mid];

        for (int i = 0; i < mid; i++) {
            list_L[i] = nums[i];
        }

        for (int i = mid, j = 0; i < nums.length; i++, j++) {
            list_R[j] = nums[i];
        }

        // sort sub-lists (conquer)
        int[] sorted_list_L = sortArray(list_L);
        int[] sorted_List_R = sortArray(list_R);

        // merge sorted sub-lists to 1 list (merge)
        int[] res_List = merge(sorted_list_L, sorted_List_R);

        return res_List;
    }

    // assumption: each input arr's length >= 1
    // time: O(N) - N is the total length of the 2 input list
    // space: O(N)
    private static int[] merge(int[] leftList, int[] rightList) {
        int len = leftList.length + rightList.length;
        int[] resList = new int[len];

        int p = 0;
        int p1 = 0;
        int p2 = 0;

        // copy the elements from both sub-lists
        while (p1 < leftList.length && p2 < rightList.length) {
            if (leftList[p1] <= rightList[p2]) {
                resList[p] = leftList[p1];
                p++;
                p1++;
            } else {
                resList[p] = rightList[p2];
                p++;
                p2++;
            }
        }

        // copy the rest elements
        while (p1 < leftList.length) {
            resList[p] = leftList[p1];
            p++;
            p1++;
        }

        while (p2 < rightList.length) {
            resList[p] = rightList[p2];
            p++;
            p2++;
        }

        return resList;
    }




    public static void printList(int[] l) {
        for (int i = 0; i < l.length; i++) {
            System.out.print(l[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] l1 = {3, 7};
        int[] l2 = {2, 4, 9};
        printList(merge(l1, l2));

        int[] l3 = null;
        System.out.println(l3.length); // Null Pointer Exception
    }
}
