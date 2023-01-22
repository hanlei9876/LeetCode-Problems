package module_2_linked_list;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_141_Linked_List_Cycle {

    // solution-1: use HashSet
    // time: O(N)
    // space: O(N), caused by HashSet
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return true;
            }

            set.add(curr);
            curr = curr.next;
        }

        return false;
    }

    // solution-2: two-pointers (fast-slow pointers)
    // time: O(N). This depends on how many step the slow pointer moves.
    // The movement of the slow pointer has two parts: non-cyclic part & cyclic part.
    // In the worst case, if the input list is a cycle, when slow pointer moves N steps, fast pointer will meet slow pointer
    // space: O(1)
    public boolean hasCycle_2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

}


// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
