package module_2_linked_list;

import java.util.ArrayList;
import java.util.List;

// constraints: size of the list [1, 10^4]
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
public class LC_382_Linked_List_Random_Node {

    public static void main(String[] args) {
        double random = Math.random();
        System.out.println(random);

        int random_int = (int) random;
        System.out.println(random_int);

        // 0.6784814185464051 >> 0
        // 0.7160915980132836 >> 0
        // 0.9461309952699628 >> 0
        // 0.0536810556941875 >> 0
    }
}

// solution 1: fixed-range sampling
// copy values to ArrayList, get the size of the array (range), then compute a random number from the range of [0, len-1]
// time: constructor O(N), getRandom() O(1)
// space: O(N), costed by the ArrayList
class Solution_1 {

    private List<Integer> list = new ArrayList<>();

    // time: O(N) - if we don't have to consider the time costed by resizing of ArrayList
    // if we need to make sure O(N) time, we can use an array with 2 passes: calculate the linked list's length, copy values to the array
    public Solution_1(ListNode head) {
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        // if we want a random integer from the range [0, 4], then simply go: (int) (Math.random() * 5)
        int random = (int) (Math.random() * list.size());
        return list.get(random);
    }
}

// solution 2: Reservoir Sampling
// used to handle the situation, where the linked list's size is fixed but unknown
// time: O(N)
// space: O(1)
class Solution_2 {

    ListNode head;

    public Solution_2(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        ListNode curr = head;
        int reservoir = 0; // in this case, we only need to sample 1 (k=1) element from the linked list, so the reservoir is a single variable, instead of an array
        int counter = 1;
        while (curr != null) {
            // decide whether to include the element in reservoir
            if ((int) (Math.random() * counter) == 0) {
                reservoir = curr.val;
            }

            counter++;
            curr = curr.next;
        }

        return reservoir;
    }
}
