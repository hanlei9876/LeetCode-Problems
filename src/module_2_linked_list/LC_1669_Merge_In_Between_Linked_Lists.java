package module_2_linked_list;

// 3 <= list1.length <= 10^4
// 1 <= a <= b < list1.length - 1
// 1 <= list2.length <= 10^4
// both a & b are 0-indexed
public class LC_1669_Merge_In_Between_Linked_Lists {

    // solution: find 4 nodes
    // time: O(M + N)
    // space: O(1)
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head_1 = null;
        ListNode tail_1 = null;

        int i = 0;
        ListNode p1 = list1;
        while (p1 != null) {
            if (i == a - 1) {
                head_1 = p1;
            }

            if (i == b + 1) {
                tail_1 = p1;
                break;
            }

            i++;
            p1 = p1.next;
        }

        ListNode tail_2 = list2;
        while (tail_2 != null && tail_2.next != null) {
            tail_2 = tail_2.next;
        }

        head_1.next = list2;
        tail_2.next = tail_1;

        return list1;
    }
}
