package module_4_binary_search;

import java.util.AbstractMap;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LC_362_Design_Hit_Counter {
/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */

    // key points:
    //  1 - All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing)
    //  2 - how is "multiple calls made at the same time" realized?  e.g. 3 hits at timestamp 2
    //      call hit(2) 3 times in a row


    // basis operations of queue
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(2);
        queue.add(3);
        queue.add(4);

        System.out.println(queue); // head [2, 3, 4] tail

        System.out.println(queue.peek()); // 2
    }
}

// solution 1: use queue
// time: O(1) >> check the 2 methods below
// space: O(N) is is the size of queue
class HitCounter_1 {

    Queue<Integer> queue; // Java interface

    public HitCounter_1() {
        queue = new LinkedList<>();
    }

    /***
     * need to design: where to implement the removal operation - hit() or getHits()
     *  - considering hit() is called more frequently than getHits(), we put removal operation in getHits() to improve performance
     */

    // time: O(1)
    // space: O(1)
    public void hit(int timestamp) {
        queue.add(timestamp);
    }

    // time: worst case: 300 >> O(1)
    // space: O(1)
    public int getHits(int timestamp) {
        // edge case: queue.isEmpty()

        // remove elements from queue that are out of current 300s-window
        int leftBoundary = timestamp - 300 + 1;

        while (!queue.isEmpty() && queue.peek() < leftBoundary) {
            queue.remove();
        }

        return queue.size();
    }
}


// solution 2: use deque to optimize solution 1's space O(N)
// the optimization is targeting at multiple hits at the same timestamp
// time: O(1)
// space: O(N)
class HitCounter_2 {

    Deque<Map.Entry<Integer, Integer>> deque; // Java interface
    int totalCount; // total count of hits in current deque

    public HitCounter_2() {
        deque = new LinkedList<>();
        totalCount = 0;
    }

    // time: O(1)
    // space: O(1)
    public void hit(int timestamp) {
        if (deque.isEmpty() || deque.peekLast().getKey() != timestamp) {
            deque.add( new AbstractMap.SimpleEntry<>(timestamp, 1) );
        } else {
            int prevCount = deque.peekLast().getValue();
            deque.removeLast();
            deque.add(new AbstractMap.SimpleEntry<>(timestamp, prevCount + 1));
        }

        totalCount++;
    }

    // time: worst case: 300 >> O(1)
    // space: O(1)
    public int getHits(int timestamp) {
        int leftBoundary = timestamp - 300 + 1;

        while (!deque.isEmpty() && deque.peekFirst().getKey() < leftBoundary) {
            Map.Entry<Integer, Integer> firstElement = deque.removeFirst();
            totalCount = totalCount - firstElement.getValue();
        }

        return totalCount;
    }
}
