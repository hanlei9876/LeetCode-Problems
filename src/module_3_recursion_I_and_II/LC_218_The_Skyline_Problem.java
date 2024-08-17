package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_218_The_Skyline_Problem {
}

// solution: divide & conquer
// at merge phase, sweep line algorithm is used
// time: O(NlogN) - total number of nodes * time spent on each node
// think from a different perspective: total time = tree height * time spent at each level
//   - stack height is O(logN)
//   - at each level, all elements in the buildings array will get merged, So,total is O(N)
// space: O(logN) + O(N) = O(N)
//   - O(logN) is stack height
//   - O(N) is the maximum size of res for collecting the merged result in each stack item
class LC_218_The_Skyline_Problem_v1 {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        return divideAndConquer(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> divideAndConquer(int[][] buildings, int left, int right) {
        // base case
        if (left == right) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Arrays.asList(buildings[left][0], buildings[left][2]));
            res.add(Arrays.asList(buildings[left][1], 0));
            return res;
        }

        // recursion relation
        int mid = left + (right - left) / 2;
        List<List<Integer>> leftRes = divideAndConquer(buildings, left, mid);
        List<List<Integer>> rightRes = divideAndConquer(buildings, mid + 1, right);

        List<List<Integer>> res = merge(leftRes, rightRes);

        return res;
    }

    // Sweep Line Algorithm (simplified)
    private List<List<Integer>> merge(List<List<Integer>> leftSkyLinePoints, List<List<Integer>> rightSkyLinePoints) {
        List<List<Integer>> res = new ArrayList<>();

        int leftPointer = 0;
        int rightPointer = 0;
        int leftPrevHeight = 0;
        int rightPrevHeight = 0;
        int currX = 0;
        int currY = 0;

        while (leftPointer < leftSkyLinePoints.size() && rightPointer < rightSkyLinePoints.size()) {
            if (leftSkyLinePoints.get(leftPointer).get(0) < rightSkyLinePoints.get(rightPointer).get(0)) {
                leftPrevHeight = leftSkyLinePoints.get(leftPointer).get(1);

                currX = leftSkyLinePoints.get(leftPointer).get(0);
                currY = Math.max(leftPrevHeight, rightPrevHeight);

                leftPointer++;
            } else if (leftSkyLinePoints.get(leftPointer).get(0) > rightSkyLinePoints.get(rightPointer).get(0)) {
                rightPrevHeight = rightSkyLinePoints.get(rightPointer).get(1);

                currX = rightSkyLinePoints.get(rightPointer).get(0);
                currY = Math.max(leftPrevHeight, rightPrevHeight);

                rightPointer++;
            } else {
                leftPrevHeight = leftSkyLinePoints.get(leftPointer).get(1);
                rightPrevHeight = rightSkyLinePoints.get(rightPointer).get(1);

                currX = leftSkyLinePoints.get(leftPointer).get(0);
                currY = Math.max(leftPrevHeight, rightPrevHeight);

                leftPointer++;
                rightPointer++;
            }

            if (res.isEmpty() || currY != res.get(res.size() - 1).get(1)) {
                res.add(Arrays.asList(currX, currY));
            }
        }

        while (leftPointer < leftSkyLinePoints.size()) {
            res.add(leftSkyLinePoints.get(leftPointer));
            leftPointer++;
        }

        while (rightPointer < rightSkyLinePoints.size()) {
            res.add(rightSkyLinePoints.get(rightPointer));
            rightPointer++;
        }

        return res;
    }
}
