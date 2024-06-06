package module_3_recursion_I_and_II;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_52_N_Queens_II {

    private int size;

    // solution: backtrack
    // time: O(N!) - upper bound, see analysis in note
    // space: O(N), recursion call stack O(N), three sets: O(N) + O(2N-1) + O(2N-1)
    public int totalNQueens(int n) {
        size = n;
        return backtrack(0, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>());
    }

    private int backtrack(int row, Set<Integer> cols, Set<Integer> diagonals, Set<Integer> antiDiagonals) {
        // base case - go out of all the rows
        // this is the only situation that we have found a complete solution
        if (row == size) {
            return 1;
        }

        // recursion relation
        int solutions = 0;
        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;

            // check if this location is under attack
            if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
                continue;
            }

            // if not under attack, "add" the queen to the board
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);

            // after placement, move on to the next row with the updated board state
            int count = backtrack(row + 1, diagonals, antiDiagonals, cols);
            solutions = solutions + count;

            // after return back,
            // "remove" the queen from the board since we have already explored all valid paths subsequent to this location (row, col)
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
        }
        // when out of the loop, we have completed the exploration of all the cols within this row
        // so, the solution is done calculating, and awe can return solutions

        return solutions;
    }
}

// brute force
// use a Map to track if it is valid for each complete solution candidate
class LC_52_N_Queens_II_v2 {

    int size;

    // time: O(N^N) - it will iterate every single combination
    // space: O(N) - recursion stack O(N), 4 Maps O(N) + O(N) + O(N) + O(N)
    public int totalNQueens(int n) {
        size = n;
        return backtrack(0, new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>(), new HashMap<Integer, Boolean>());
    }

    private int backtrack(int row, Map<Integer, Integer> cols, Map<Integer, Integer> diagonals, Map<Integer, Integer> antiDiagonals, Map<Integer, Boolean> validationPath) {
        // base case
        if (row == size) {
            if (validationPath.containsValue(false)) {
                return 0;
            }
            else {
                return 1;
            }
        }

        // recursion relation
        int solutions = 0;
        for (int col = 0; col < size; col++) {
            // calculate all required elements
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;

            // validate if this cell is under attack
            if (cols.containsValue(col) || diagonals.containsValue(currDiagonal) || antiDiagonals.containsValue(currAntiDiagonal)) {
                validationPath.put(row, false);
            } else {
                validationPath.put(row, true);
            }

            // place the queen directly
            cols.put(row, col);
            diagonals.put(row, currDiagonal);
            antiDiagonals.put(row, currAntiDiagonal);

            // go to next row
            solutions = solutions + backtrack(row + 1, cols, diagonals, antiDiagonals, validationPath);

            // when come back from next row to current row, remove this queen from this cell
            cols.remove(row);
            diagonals.remove(row);
            antiDiagonals.remove(row);
            validationPath.remove(row);
        }

        return solutions;
    }
}


