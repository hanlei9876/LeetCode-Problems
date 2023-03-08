package module_2_linked_list;

// constraints: connect all rhe nodes without using additional space
public class LC_21_Merge_Two_Sorted_Lists {

    // solution-1: brute force
    // for each node in list_1, find the place in list_2 to insert
    // three pointers: prev, curr on list1, p on list2
    // time: O(M*N) in worst case
    // space: O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // assume list1 is the list to accept insertion
        ListNode sentinel = new ListNode(0);
        sentinel.next = list1;

        ListNode p = list2;
        while (p != null) {
            ListNode prev = sentinel;
            // ListNode curr = list1; // this has bug, as the original head "list1" could not be the real head laster
            ListNode curr = sentinel.next;
            while (curr != null) {
                if (p.val <= curr.val) {
                    // insert p between prev and curr
                    ListNode temp = p;
                    p = p.next; // this statement must be places before the two lines below. Otherwise, p.next will be changed by the two lines

                    prev.next = temp;
                    temp.next = curr;

                    break;
                } else {
                    prev = prev.next;
                    curr = curr.next;
                }
            }

            // check if p is greater than all nodes in list1
            if (curr == null) {
                // insert p as the last node of list1
                ListNode temp = p;
                p = p.next;

                prev.next = temp;
                temp.next = null;
            }
        }

        return sentinel.next;
    }

    // solution-2: three pointers: prev on assumed resulting list, p1 on list1, p2 on list2
    // time: O(M + N) in worst case, where M == N
    // space: O(1)
    public ListNode mergeTwoLists_2(ListNode list1, ListNode list2) {
        // assume sentinel node as the first node of the resulting list
        ListNode sentinel = new ListNode(0);
        ListNode prev = sentinel;
        ListNode p1 = list1;
        ListNode p2 = list2;

        while (p1 != null && p2 != null) {
            if (p1.val <+p2.val) {
                prev.next = p1;
                p1 = p1.next;
            } else {
                prev.next = p2;
                p2 = p2.next;
            }
            prev = prev.next;
        }

        prev.next = p1 != null ? p1 : p2;

        return sentinel.next;
    }

}
