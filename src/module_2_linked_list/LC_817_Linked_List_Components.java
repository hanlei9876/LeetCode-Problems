package module_2_linked_list;

import java.util.HashSet;
import java.util.Set;

// The number of nodes in the linked list is n.
//  1 <= n <= 104
//  0 <= Node.val < n
//  All the values Node.val are unique.
//  1 <= nums.length <= n
public class LC_817_Linked_List_Components {

    // use hashset
    // use size to maintain a window
    // two passes: through nums, through linked list
    // tome: O(N), two passes
    // space: O(N), costed by hashset
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            set.add(i);
        }

        int size = 0; // maintain a window
        int count = 0; // hold result
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr.val)) {
                size++;
            } else {
                if (size != 0) {
                    count++;
                    size = 0;
                }
            }
            curr = curr.next;
        }

        if (size > 0)
            count++;

        return count;
    }


}
