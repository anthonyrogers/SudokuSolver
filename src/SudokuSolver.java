import java.util.Arrays;

public class SudokuSolver {


    public static void solve(int[][] board) {
        solve(board, 0, 0);
    }


    private static boolean solve(int[][] puzzle, int row, int col) {
        if (col >= 9) {
            col = 0;
            row++;
            if (row >= 9) {
                return true;
            }
        }

        if (puzzle[row][col] != 0) {
            return solve(puzzle, row, col + 1);
        }

        for (int val = 1; val <= 9; val++) {
            if (isAvailable(puzzle, row, col, val)) {
                puzzle[row][col] = val;
                if (solve(puzzle, row, col + 1)) {
                    return true;
                }
            }

        }

        puzzle[row][col] = 0;
        return false;
    }


    private static boolean isAvailable(int[][] board, int row, int col, int val) {
        int squarestartrow = row / 3 * 3;
        int squarestartcol = col / 3 * 3;

        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (board[squarestartrow + i][squarestartcol + k] == val) {
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean checkIfBoardIsValid(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (board[i][k] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {


        int[][] puzzle = {
                {5, 0, 8, 0, 7, 3, 1, 9, 0},
                {9, 0, 0, 6, 0, 0, 4, 0, 8},
                {0, 0, 0, 9, 0, 8, 0, 3, 5},
                {0, 7, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 2, 0, 0, 0, 9, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 8, 0},
                {1, 9, 0, 3, 0, 6, 0, 0, 0},
                {2, 0, 3, 0, 0, 7, 0, 0, 9},
                {0, 8, 7, 1, 9, 0, 3, 0, 4}};


        solve(puzzle);
        if (checkIfBoardIsValid(puzzle)) {
            for (int[] row : puzzle) {
                System.out.println(Arrays.toString(row));
            }

        } else {
            System.out.println("Sudoku puzzle is not solvable please enter a valid puzzle.");
        }
    }
}
