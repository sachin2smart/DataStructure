package in.sachinshinde.design;

//  https://leetcode.com/problems/design-tic-tac-toe

/*
        Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
            A move is guaranteed to be valid and is placed on an empty block.
            Once a winning condition is reached, no more moves are allowed.
            A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

        Implement the TicTacToe class:
            TicTacToe(int n) Initializes the object the size of the board n.
            int move(int row, int col, int player)
                Indicates that the player with id player plays at the cell (row, col) of the board.
                The move is guaranteed to be a valid move, and the two players alternate in making moves.

                Return
                    0 if there is no winner after the move,
                    1 if player 1 is the winner after the move, or
                    2 if player 2 is the winner after the move.



        Example 1:
        ---------
        Input
            ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
            [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
        Output
            [null, 0, 0, 0, 0, 0, 0, 1]

            Explanation
                TicTacToe ticTacToe = new TicTacToe(3);
                Assume that player 1 is "X" and player 2 is "O" in the board.
                ticTacToe.move(0, 0, 1); // return 0 (no one wins)
                |X| | |
                | | | |    // Player 1 makes a move at (0, 0).
                | | | |

                ticTacToe.move(0, 2, 2); // return 0 (no one wins)
                |X| |O|
                | | | |    // Player 2 makes a move at (0, 2).
                | | | |

                ticTacToe.move(2, 2, 1); // return 0 (no one wins)
                |X| |O|
                | | | |    // Player 1 makes a move at (2, 2).
                | | |X|

                ticTacToe.move(1, 1, 2); // return 0 (no one wins)
                |X| |O|
                | |O| |    // Player 2 makes a move at (1, 1).
                | | |X|

                ticTacToe.move(2, 0, 1); // return 0 (no one wins)
                |X| |O|
                | |O| |    // Player 1 makes a move at (2, 0).
                |X| |X|

                ticTacToe.move(1, 0, 2); // return 0 (no one wins)
                |X| |O|
                |O|O| |    // Player 2 makes a move at (1, 0).
                |X| |X|

                ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
                |X| |O|
                |O|O| |    // Player 1 makes a move at (2, 1).
                |X|X|X|

        Constraints:
        -----------
            2 <= n <= 100
            player is 1 or 2.
            0 <= row, col < n
            (row, col) are unique for each different call to move.
            At most n2 calls will be made to move.

        Follow-up: Could you do better than O(n2) per move() operation?
 */

/*
        Q1. Will there be only 2 players ?
        --> Yes

 */

public class DesignTicTacToe {

    private final int[] rows;
    private final int[] cols;
    private int diagonal;
    private int antiDiagonal;
    private final int n;

    public DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        this.n = n;
    }

    //  player 1 will hold a positive count for row, col, diagonal and anti-diagonal values
    //  player 2 will hold a negative count for row, col, diagonal and anti-diagonal values
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;

        rows[row] += toAdd;
        cols[col] += toAdd;

        if (row == col) {
            diagonal += toAdd;
        }

        if (row + col == n - 1) {
            antiDiagonal += toAdd;
        }

        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagonal) == n  ||  Math.abs(antiDiagonal) == n) {
            return player;
        }

        return 0;
    }

    public static void main(String[] args) {
        DesignTicTacToe designTicTacToe1 = new DesignTicTacToe(3);
        designTicTacToe1.move(0, 0, 1);
        designTicTacToe1.move(0, 2, 2);
        designTicTacToe1.move(2, 2, 1);
        designTicTacToe1.move(1, 1, 2);
        designTicTacToe1.move(2, 0, 1);
        designTicTacToe1.move(1, 0, 2);
        int res = designTicTacToe1.move(2, 1, 1);

        System.out.println("Result: " + res);    //  1



    }

}
