package module_2_linked_list;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Constraints:
//      The given linked list will contain between 1 and 1000 nodes.
//      Each node in the linked list has -1000 <= node.val <= 1000.
//
// Question: do we have to delete a node, whose value==0? -- Yes
public class LC_1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List {

    // solution: prefix sum/cumulative sum + HashMap
    // time: O(2N) >> O(N)
    // space: O(N)
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        Map<Integer, ListNode> map = new HashMap<>();
        int prefixSum = 0;
        map.put(prefixSum, sentinel);

        ListNode curr = head;
        while (curr != null) {
            prefixSum = prefixSum + curr.val;
            if (map.containsKey(prefixSum)) {
                // delete all entries within the window from map
                ListNode p = map.get(prefixSum).next;
                int sum = prefixSum;
                while (p != curr) {
                    sum = sum + p.val;
                    map.remove(sum);
                    p = p.next;
                }

                // delete all nodes within the window from the list
                map.get(prefixSum).next = curr.next;
            } else {
                map.put(prefixSum, curr);
            }

            curr = curr.next;
        }

        return sentinel.next;
    }
}
