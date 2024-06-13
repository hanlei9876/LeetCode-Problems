package module_3_recursion_I_and_II;

// my solution - this is recommended
// time: O(1) - the actual runtime < upper limit (9!)^9
// space: O(1) - stack height = 81 (max), board = 81, 3 tracking caches 3 * 90
public class LC_37_Sudoku_Solver {

    // set this board as global so backtrack() method is able to access it
    char[][] board;

    // set rows, cols, 9x9 boxes to track used numbers in each of them
    int[][] rows;
    int[][] cols;
    int[][] boxes;

    public void solveSudoku(char[][] board) {
        this.board = board;

        // initialize rows, cols, 9x9 boxes. After initialization, we have already tracked the given numbers
        // by default, each cell is 0
        this.rows = new int[9][10];
        this.cols = new int[9][10];
        this.boxes = new int[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] != '.') {
                    int charNumber = this.board[i][j] - '0'; // charNumber: 1 ~ 9
                    int boxIndex = (i / 3) * 3 + j/3;

                    this.rows[i][charNumber] = 1;
                    this.cols[j][charNumber] = 1;
                    this.boxes[boxIndex][charNumber] = 1;
                }
            }
        }

        // we go through cell by cell with backtrack(), and iterate all possible numbers on each cell
        backtrack(0, 0);
    }

    // we need to stop execution right away, as soon as we have reach to the sole solution.
    // So, we return true to label we have found the solution
    private boolean backtrack(int row, int col) {
        /* base case - this means we have already obtained the solution successfully */
        if (row == 9) {
            return true;
        }

        /* recursion relation */
        // check if this cell has pre-filled number
        if (board[row][col] != '.') {
            // no need to add this number to rows, cols, boxes, because we have already added them during initialization
            // so simply go to next cell
            boolean res;
            if (col == 8) {
                res = backtrack(row + 1, 0);
            } else {
                res = backtrack(row, col + 1);
            }

            // check true/false when backtrack
            //   - true: stop this function by returning true
            //   - false: return false (ignore here, as false will be return in last line of method)
            // no need to delete tracked rows, cols a,d boxes under this condition, as it is given problem
            if (res) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 1; i <= 9; i++) { // iterate each number from 1~9 for this cell
                int boxIndex = (row/3)*3 + col/3;
                // if this number is already in tracking rows, cols, and boxes, then skip this iteration
                if (rows[row][i] == 0 && cols[col][i] == 0 && boxes[boxIndex][i] == 0) {
                    // add it to cell
                    this.board[row][col] = (char)(i + '0');

                    // add it to tracking rows, cols, and boxes
                    this.rows[row][i] = 1;
                    this.cols[col][i] = 1;
                    this.boxes[boxIndex][i] = 1;

                    // go to next cell further to compose a solution candidate
                    boolean res;
                    if (col == 8) {
                        res = backtrack(row + 1, 0);
                    } else {
                        res = backtrack(row, col + 1);
                    }

                    // check true/false when backtrack
                    //   - true: stop this function by returning true
                    //   - false: delete i from this cell, and from tracking rows, cols, and boxes
                    if (res) {
                        return true;
                    }

                    // if (res == false), then, remove number from this cell, and from tracking rows, cols, and boxes
                    this.board[row][col] = '.';

                    this.rows[row][i] = 0;
                    this.cols[col][i] = 0;
                    this.boxes[boxIndex][i] = 0;
                }
            }

            return false; // this means: all numbers are illegal for this cell. we need to stop forming this solution candidate further
        }
    }

    public static void main(String[] args) {
        char a = '0';
        char b = '3';

        System.out.println((int)'0'); // 48
        System.out.println(b + 7); // 58
        System.out.println((char)(b + 7)); // :

        int c = b - a;
        int d = a + b;
        System.out.println(c + " " + d); // 3 99

        int h = 5;
        char xx = (char)(h + '0');
        System.out.println(xx); // 5
    }
}

class Solution_from_leetcode_official {
    // box size
    int n = 3;
    // row size
    int N = n * n;

    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];

    char[][] board;

    boolean sudokuSolved = false;

    public void solveSudoku(char[][] board) {
        this.board = board;

        // initialize rows, columns, and boxes
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }

        backtrack(0, 0);
    }

    private void backtrack(int row, int col) {
        //If the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    // If sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised
                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        } else placeNextNumbers(row, col);
    }


    // Check if one could place a number d in (row, col) cell
    private boolean couldPlace(int d, int row, int col) {

        int idx = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    // Place a number d in (row, col) cell
    private void placeNumber(int d, int row, int col) {

        int idx = (row / n) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char) (d + '0');
    }

    // Remove a number that didn't lead to a solution
    private void removeNumber(int d, int row, int col) {

        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    // Call backtrack function in recursion to continue to place numbers till the moment we have a solution
    private void placeNextNumbers(int row, int col) {
        // if we're in the last cell, that means we have the solution
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        }
        // if not yet
        else {
            //If we're at the end of the row
            // go to the next row
            if (col == N - 1) backtrack(row + 1, 0);
                // go to the next column
            else backtrack(row, col + 1);
        }
    }
}
