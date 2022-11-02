package in.sachinshinde.backtrack;

import java.util.Arrays;

//	https://leetcode.com/problems/sudoku-solver/
//	video link: https://youtu.be/SprpWBHgkOI

/*
 * 	Write a program to solve a Sudoku puzzle by filling the empty cells.

        A sudoku solution must satisfy all of the following rules:
        
        Each of the digits 1-9 must occur exactly once in each row.
        Each of the digits 1-9 must occur exactly once in each column.
        Each of the digits 1-9 must occur exactly once in each of 
        	the 9 3x3 sub-boxes of the grid.
        The '.' character indicates empty cells.
 */

/*
 * 	Example:
 * 		Input: 
 * 		       board = [["5","3",".",".","7",".",".",".","."],
 * 				["6",".",".","1","9","5",".",".","."],
 * 				[".","9","8",".",".",".",".","6","."],
 * 				["8",".",".",".","6",".",".",".","3"],
 * 				["4",".",".","8",".","3",".",".","1"],
 * 				["7",".",".",".","2",".",".",".","6"],
 * 				[".","6",".",".",".",".","2","8","."],
 * 				[".",".",".","4","1","9",".",".","5"],
 * 				[".",".",".",".","8",".",".","7","9"]]
 * 
		Output: 
			       [["5","3","4","6","7","8","9","1","2"],
			       	["6","7","2","1","9","5","3","4","8"],
			       	["1","9","8","3","4","2","5","6","7"],
			       	["8","5","9","7","6","1","4","2","3"],
			       	["4","2","6","8","5","3","7","9","1"],
			       	["7","1","3","9","2","4","8","5","6"],
			       	["9","6","1","5","3","7","2","8","4"],
			       	["2","8","7","4","1","9","6","3","5"],
			       	["3","4","5","2","8","6","1","7","9"]]
 */

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
	System.out.println(Arrays.deepToString(board));
	if(board == null || board.length == 0)
	    return;
	solve(board);
	System.out.println(Arrays.deepToString(board));
    }
    
    private boolean solve(char[][] board) {
	for(int i = 0; i < board.length; i++) {
	    for(int j = 0; j < board[0].length; j++) {
		if(board[i][j] == '.') {
		    for(char c = '1'; c <= '9'; c++) {	// from '1' to '9' not from 1 to 9 (num vs char) 
			if(isValid(board, i, j, c)) {
			    board[i][j] = c;	// set 
			    
			    if(solve(board)) 
				return true;
			    else
				board[i][j] = '.';	// backtrack	
			}
		    }
		    return false;
		}
	    }
	}
	
	return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
	int nRow = (row / 3) * 3;	// normalized row
	int nCol = (col / 3) * 3;	// normalized col
	
	for(int i = 0; i < 9; i++) {
	    if(board[i][col] != '.' && board[i][col] == c)	// search in same row
		return false;
	    
	    if(board[row][i] != '.' && board[row][i] == c)	// search in same col
		return false;
	    
	    int nRowIndex = nRow + i / 3;
	    int nColIndex = nCol + i % 3;
	    
	    if(board[nRowIndex][nColIndex] != '.' && board[nRowIndex][nColIndex] == c) // search in triplet
		return false;
	    
	    // How the triplet is traversed ?
	    /*
	     * 		There are total 9 blocks of 3*3 elements
	     * 		the top left starting index of the block can be determined by
	     * 			(nRow, nCol)
	     *          where nRow = (row / 3) * 3
	     *            &   nCol = (col / 3) * 3     
	     *          
	     *          Now we need nRow + 0, nRow + 1, nRow + 2 as x value for first col
	     *          and nCol + 0, nCol + 1, nCol + 2 as y value for first row
	     *          
	     *          [nRow + 0, nCol + 0], [nRow + 0, nCol + 1], [nRow + 0, nCol + 2]
	     *          [nRow + 1, nCol + 0], [nRow + 1, nCol + 1], [nRow + 1, nCol + 2]
	     *          [nRow + 2, nCol + 0], [nRow + 2, nCol + 1], [nRow + 2, nCol + 2]
	     *          
	     *          i.e. for i = 0 to 8
	     *          
	     *          nRow to be increased by i / 3
	     *          nCol to be increased by i % 3 
	     */
	}
	return true;
    }
    
    public static void main(String[] args) {
	SudokuSolver solver = new SudokuSolver();
	char[][] board = new char[][] {
	    {'5','3','.','.','7','.','.','.','.'},
	    {'6','.','.','1','9','5','.','.','.'},
	    {'.','9','8','.','.','.','.','6','.'},
	    {'8','.','.','.','6','.','.','.','3'},
	    {'4','.','.','8','.','3','.','.','1'},
	    {'7','.','.','.','2','.','.','.','6'},
	    {'.','6','.','.','.','.','2','8','.'},
	    {'.','.','.','4','1','9','.','.','5'},
	    {'.','.','.','.','8','.','.','7','9'}
	};
	solver.solveSudoku(board);
    }
}