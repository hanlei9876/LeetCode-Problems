package module_2_linked_list;

// must read my notes for this problem
// constraints:
//  - number of nodes = 0, 1, 2, ...
//  - the value of a node could be either negative, 0, positive
public class LC_708_Insert_into_a_Sorted_Circular_Linked_List {

    // time: O(N) - in the worst case
    // space: O(1)
    public Node insert(Node head, int insertVal) {
        // edge case
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }

        // iterate through list
        Node p1 = head;
        Node p2 = p1.next;
        do {
            if (p1.val < p2.val) {
                // case-1
                if ((p1.val < insertVal && insertVal < p2.val) || p1.val == insertVal || p2.val == insertVal) {
                    Node node = new Node(insertVal);
                    p1.next = node;
                    node.next = p2;
                    return head;
                }
            } else if (p1.val > p2.val) {
                // case-2
                if (insertVal >= p1.val || insertVal <= p2.val) {
                    Node node = new Node(insertVal);
                    p1.next = node;
                    node.next = p2;
                    return head;
                }
            }

            p1 = p1.next;
            p2 = p2.next;
        } while (p1 != head);

        // case-3
        Node node = new Node(insertVal);
        p1.next = node;
        node.next = p2;
        return head;
    }
}





class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}