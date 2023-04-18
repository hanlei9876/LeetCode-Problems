package module_2_linked_list;

/* constraints:
The number of the nodes in the given list is in the range [2, 1000].
-1000 <= Node.val <= 1000
The value of each node in the list is unique.
The node to be deleted is in the list and is not a tail node.
**/
public class LC_237_Delete_Node_in_a_Linked_List {

    // pretend to delete a node
    // time: O(1)
    // space:O(1)
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
