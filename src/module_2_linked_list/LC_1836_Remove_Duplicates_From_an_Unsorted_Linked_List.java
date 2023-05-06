package module_2_linked_list;

import java.util.HashMap;
import java.util.Map;

// constraints: 1 <= N <= 10^5
public class LC_1836_Remove_Duplicates_From_an_Unsorted_Linked_List {

    // solution: 2 passes + hashmap
    // time: O(2N) >> O(N)
    // space: O(N)
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        // record the frequency of each node into map
        Map<Integer, Integer> map = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            if (!map.containsKey(curr.val)) {
                map.put(curr.val, 1);
            } else {
                // update the value for a key
                int k = map.get(curr.val);
                k++;
                map.put(curr.val, k);
            }
            curr = curr.next;
        }

        // use sentinel head to delete node. This will make it convenient to delete head node
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        curr = sentinel;
        while (curr.next != null) {
            int val = curr.next.val;
            int freq = map.get(val);
            if (freq > 1) {
                curr.next = curr.next.next; // delete node
            } else {
                curr = curr.next; // move pointer
            }
        }

        return sentinel.next;
    }

    public static void main(String[] args) {
        // Create a new HashMap
        HashMap<String, Integer> map = new HashMap<>();

        // Add a key-value pair to the HashMap using putIfAbsent()
        System.out.println("The value is: " + map.putIfAbsent("apple", 1)); // returns null

        // Try to add the same key-value pair again using putIfAbsent()
        int newAppleValue = map.putIfAbsent("apple", 2); // returns 1 - auto-unboxing by JVM

        // Check the value associated with the key "apple"
        int finalAppleValue = map.get("apple"); // returns 1
    }
}
