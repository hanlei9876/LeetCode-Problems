package module_3_recursion_I_and_II;

import java.util.HashSet;
import java.util.Set;
import javafx.util.Pair;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

// Must see the note for complexity analysis
// time: O(N - M)
// space: O(N - M)
public class LC_489_Robot_Room_Cleaner {

    // add robot to be global variable within class, so all methods can access to it
    Robot robot;
    Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();

    // clockwise:               up    right    down     left
    int[][] directionList = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    private void backtrack(int row, int col, int direction) {
        // mark this traversed cell as visited, and clean this cell
        visited.add(new Pair(row, col));
        robot.clean();

        // traverse each direction of the current cell (row, col)
        for (int i = 0; i < 4; i++) {
            // calculate the new direction, and potential coordinates (row, col) of move next
            int newDirection = (direction + i) % 4;
            int newRow = row + directionList[newDirection][0];
            int newCol = col + directionList[newDirection][1];

            if(!visited.contains(new Pair(newRow, newCol)) && robot.move()) {
                backtrack(newRow, newCol, newDirection);
                goBack();
                robot.turnRight();
            } else {
                robot.turnRight();
            }
        }
    }

    // helper method
    private void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
