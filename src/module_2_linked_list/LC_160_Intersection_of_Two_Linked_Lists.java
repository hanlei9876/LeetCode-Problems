package module_2_linked_list;

import java.util.HashSet;
import java.util.Set;

public class LC_160_Intersection_of_Two_Linked_Lists {

    // solution 1: brute force
    // time: O(M * N)
    // space: O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        while (currA != null) {
            ListNode currB = headB;
            while (currB != null) {
                if (currA == currB) {
                    return currA;
                }

                currB = currB.next;
            }

            currA = currA.next;
        }

        return null;
    }


    // solution 2: use HashSet
    // time: O(M + N), where M for A's length, N for B's length
    // space: O(M)
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        ListNode currA = headA;
        while (currA != null) {
            set.add(currA);
            currA = currA.next;
        }

        ListNode currB = headB;
        while (currB != null) {
            if (set.contains(currB)) {
                return currB;
            }

            currB = currB.next;
        }

        return null;
    }

    // solution 3: two pointers from two heads
    // calculate the lengths of two lists >> move the pointer of longer list (m-n) steps
    // >> move two pointers together >> if equals at some point, then meet; or both moving to the end
    // time: O(M + N), where M for A's length, N for B's length
    // space: O(1)
    public ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        // calculate the lengths of the two linked lists
        int lenA = 0;
        int lenB = 0;
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != null) {
            lenA++;
            pA = pA.next;
        }

        while (pB != null) {
            lenB++;
            pB = pB.next;
        }

        // claculate which length is longer, and iterate to find the meeting node
        pA = headA;
        pB = headB;

        if (lenA > lenB) {
            int steps = lenA - lenB;
            while (steps != 0) {
                pA = pA.next;
                steps--;
            }
        } else if (lenA < lenB) {
            int steps = lenB - lenA;
            while (steps != 0) {
                pB = pB.next;
                steps--;
            }
        }

        while (pA != null) {
            if (pA == pB) {
                return pA;
            }

            pA = pA.next;
            pB = pB.next;
        }

        return null;
    }

    // solution 4: optimized two pointers from two heads
    // time: O(M + N), which is the worst case
    // space: O(1)
    public ListNode getIntersectionNode_4(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        // no matter if two list intersect or not, the two pointers will always meet at some point
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }

        return p1;
    }
}

