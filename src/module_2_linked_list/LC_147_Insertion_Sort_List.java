package module_2_linked_list;

// Constraints:
//      The number of nodes in the list is in the range [1, 5000]
//      -5000 <= Node.val <= 5000
public class LC_147_Insertion_Sort_List {

    // time: O(N^2)
    // space: O(1) - only a sentinel node
    public ListNode insertionSortList(ListNode head) {
        ListNode sentinel = new ListNode(0);
        ListNode sortedP = sentinel;

        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;

            while (sortedP.next != null) {
                if (curr.val <= sortedP.next.val)
                    break;

                sortedP = sortedP.next;
            }

            curr.next = sortedP.next;
            sortedP.next = curr;

            sortedP = sentinel;
            curr = next;
        }

        return sentinel.next;
    }

    // implement insertion sort on an array
    // time: O(N(N-1)/2) >> O(N^2)
    // space: O(1)
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int temp = arr[i];

            int j = i;
            while (j - 1 >= 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp; // the position is always to be inserted
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 1, 3};
        insertionSort(a);

        for (int num : a) {
            System.out.print(num + " ");
        }
        // 1 2 3 4
    }
}
