package module_3_recursion_I_and_II;

import javax.xml.stream.events.StartDocument;
import java.util.HashSet;
import java.util.Set;

// refer to LC-489 - Robot Clean Room
// use 2D matrix to track visited cells - different from leetcode's official solution
// use int index to track solution candidate
//
// time: O(N * N * (3^L))
// for each recursion tree,
//   - max tree height = L
//   - root node has 4 directions, all remaining nodes have 3 directions at most. So total number of tree nodes < 3^L
// In worst case, each cell will invoke callback function - N*N
//
// space: O(L + N*N)
//   - max tree height = L
//   - visited matrix = N*N
public class LC_79_Word_Search {

    char[][] board;
    String word;
    int[][] visited;
    int[][] directionList;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        visited = new int[board.length][board[0].length]; // 0 - not visited, 1 - visited

        // clockwise:                    up     right   down     left
        directionList = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean res = backtrack(i, j, 0);
                    if (res) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(int row, int col, int wordIndex) {
        // NOTE: when come into this file, it means:
        //      - board[row][col] == word.charAt(i)
        //      - the element in board[row][col] is NOT yet handled

        // base case - consider the case when word.length == 1
        if (wordIndex == word.length() - 1) {
            return true;
        }

        // recursion relation
        visited[row][col] = 1;

        for (int i = 0; i < 4; i++) {
            int newRow = row + directionList[i][0];
            int newCol = col + directionList[i][1];

            if (newRow < 0 || newRow == board.length || newCol < 0 || newCol == board[0].length || visited[newRow][newCol] == 1) {
                continue;
            }

            if (board[newRow][newCol] != word.charAt(wordIndex + 1)) {
                continue;
            }

            boolean result = backtrack(newRow, newCol, wordIndex + 1);

            if (result) {
                return true;
            }
        }

        visited[row][col] = 0;

        return false;
    }

    public static void main(String[] args) {
        Set<int[]> visited = new HashSet<>();

        int[] tuple1 = {0, 0};
        int[] tuple2 = {4, 1};
        visited.add(tuple1);
        visited.add(tuple2);

        for (int[] tuple : visited) {
            System.out.println("");
            System.out.println(tuple[0] + " " + tuple[1]);
        }

        System.out.println(visited.contains(new int[]{4, 1})); // false
    }
}

// this is NOT WORKING - check main() above
// optimize space by using Set<[i, j]>
class LC_79_Word_Search_solution_2_not_working {

    char[][] board;
    String word;
    Set<int[]> visited;
    int[][] directionList;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        visited = new HashSet<>();

        // clockwise:                    up     right   down     left
        directionList = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean res = backtrack(i, j, 0);
                    if (res) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(int row, int col, int wordIndex) {
        // NOTE: when come into this file, it means:
        //      - board[row][col] == word.charAt(i)
        //      - the element in board[row][col] is NOT yet handled

        // base case - consider the case when word.length == 1
        if (wordIndex == word.length() - 1) {
            return true;
        }

        // recursion relation
        int[] tuple = {row, col};
        visited.add(tuple);

        for (int i = 0; i < 4; i++) {
            int newRow = row + directionList[i][0];
            int newCol = col + directionList[i][1];

            if (newRow < 0 || newRow == board.length || newCol < 0 || newCol == board[0].length || visited.contains(new int[]{newRow, newCol})) {
                continue;
            }

            if (board[newRow][newCol] != word.charAt(wordIndex + 1)) {
                continue;
            }

            boolean result = backtrack(newRow, newCol, wordIndex + 1);

            if (result) {
                return true;
            }
        }

        visited.remove(tuple);

        return false;
    }
}

