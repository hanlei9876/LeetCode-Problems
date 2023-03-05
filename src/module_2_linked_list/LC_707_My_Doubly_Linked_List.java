package module_2_linked_list;

// we assume all nodes in linked list are 0-indexed
// check index's validity is necessary

// Definition: add a node at index i
//  - add a node before ith node of the linked list
//  - if index == length, append this node to the end of the list
public class LC_707_My_Doubly_Linked_List {

    Doubly_List_Node head;
    Doubly_List_Node tail;
    int length ;

    // constructor
    public LC_707_My_Doubly_Linked_List() {
        // introduce sentinel nodes as head & tail
        this.head = new Doubly_List_Node(-1);
        this.tail = new Doubly_List_Node(-1);
        // we need to connect the two nodes at initial state
        this.head.next = this.tail;
        this.tail.prev = this.head;

        this.length = 0;
    }

    // by introducing sentinel node, both addAtHead & addAtTali will be covered
    // time: O(N/2) >> O(N)
    // space: O(1)
    public void addAtIndex(int index, int val) {
        // check if index is valid
        if (index < 0 || index > length) {
            return;
        }

        Doubly_List_Node p;
        if (index < length / 2) {  // this is equivalent to index vs. length - index
            p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = 0; i <= length - index; i++) {
                p = p.prev; // ***** important
            }
        }
        // now, p is pointing to the previous node before index node

        Doubly_List_Node node = new Doubly_List_Node(val);
        // insert the node between p & p.next
        node.next = p.next;
        p.next = node;

        p.prev = node.next.prev;
        node.next.prev = node;

        length++;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(length, val);
    }

    // time: O(N/2) >> O(N)
    // space: O(1)
    public int get(int index) {
        if (index < 0 || index >= length) {
            return -1;
        }

        Doubly_List_Node p;
        if (index < length / 2) {
            p = head;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = 0; i < length - index; i++) {
                p = p.prev;
            }
        }

        return p.val;
    }

    // time: O(N/2) >> O(N)
    // space: O(1)
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }

        Doubly_List_Node p;
        if (index < length / 2) {
            p = head;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = 0; i < length - index; i++) {
                p = p.prev;
            }
        }
        // now, p is pointing to the node to delete

        Doubly_List_Node prev = p.prev;
        Doubly_List_Node next = p.next;
        prev.next = next;
        next.prev = prev;

        length--;
    }
}


class Doubly_List_Node {
    int val;
    Doubly_List_Node prev;
    Doubly_List_Node next;

    public Doubly_List_Node(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}