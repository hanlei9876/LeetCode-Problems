package module_1_array_and_string.array;

public class LC_11_Container_With_Most_Water {

    // solution 1 (Time Limit Exceeded): brute force
    // find every pair within the array, calculate the area, find max area
    // time: O(N^2)
    // space: O(1)
    public int maxArea(int[] height) {
        int len = height.length;
        int maxArea = 0;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int h = Math.min(height[i], height[j]);
                int area = h * (j - i);

                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    // solution 2: Greedy Algorithm + Two-Pointers (two-ends)
    // start at the widest width >> in each iteration, move the shorter bound inward
    // time: O(N)
    // space: O(1)
    public int maxArea_2(int[] height) {
        int maxArea = 0;

        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            int area = h * (j - i);
            maxArea = Math.max(maxArea, area);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxArea;
    }

}
