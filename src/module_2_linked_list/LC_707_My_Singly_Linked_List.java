package module_2_linked_list;

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
// we need a helper class Node from this package
// Time complexity: O(1) for addAtHead. O(k) for get, addAtIndex, and deleteAtIndex, where k is an index of the element to get, add or delete. O(N) for addAtTail.
// Space complexity: O(1) for all operations.
public class LC_707_My_Singly_Linked_List {

    int size;
    Node head; // head is always pointing to sentinel node

    public void LC_707_My_Linked_List() {
        size = 0; // this can be omitted, as default value will be 0
        head = new Node(0); // initialize the linked list with a sentinel node as head
    }

    // index is 0-based
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node curr = head;
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }

        return curr.value;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    // this is the method to start to write
    // by introducing sentinel node, addAtIndex() will cover addAtHead & addAtTail()
    public void addAtIndex(int index, int val) {
        // the key is to find (index - 1)th  node
        if (index < 0 || index > size) {
            return;
        }

        Node node = new Node(val);

        // find predecessor of the node to be added
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        // find predecessor of the node to be deleted
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node curr = prev.next;
        prev.next = curr.next;
        size--;
    }
}
