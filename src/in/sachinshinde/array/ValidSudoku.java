package in.sachinshinde.array;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/valid-sudoku/
  
/*
 	Determine if a 9 x 9 Sudoku board is valid. 
 	Only the filled cells need to be validated according to the following rules:

	- Each row must contain the digits 1-9 without repetition.
	- Each column must contain the digits 1-9 without repetition.
	- Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        
        Note:
        	A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        	Only the filled cells need to be validated according to the mentioned rules.
 */

public class ValidSudoku {
    private void test() {
	for (int r = 0; r < 9; r++) {
	    for (int c = 0; c < 9; c++) {
		System.out.print((r / 3) * 3 + c / 3 + " ");
	    }
	    System.out.println();
	}
    }


    public boolean isValidSudoku3(char[][] board) {
		Set<Character>[] rows = new HashSet[9];
		Set<Character>[] cols = new HashSet[9];
		Set<Character>[] boxes = new HashSet[9];

		for (int r = 0; r < 9; r++) {
			rows[r] = new HashSet<>();
			cols[r] = new HashSet<>();
			boxes[r] = new HashSet<>();
		}

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				char currNum = board[r][c];

				if (currNum == '.')
					continue;

				int boxIndex = (r / 3) * 3 + c / 3;

				if (rows[r].contains(currNum) ||
					cols[c].contains(currNum) ||
					boxes[boxIndex].contains(currNum)) {
					return false;
				}
				else {
					rows[r].add(currNum);
					cols[c].add(currNum);
					boxes[boxIndex].add(currNum);
				}
	    	}
		}
		return true;
    }

					/*
						boxes indices
						[	using (r/3)*3 + c/3	]
						-----------------
								0 0 0 1 1 1 2 2 2
								0 0 0 1 1 1 2 2 2
								0 0 0 1 1 1 2 2 2
								3 3 3 4 4 4 5 5 5
								3 3 3 4 4 4 5 5 5
								3 3 3 4 4 4 5 5 5
								6 6 6 7 7 7 8 8 8
								6 6 6 7 7 7 8 8 8
								6 6 6 7 7 7 8 8 8
								-----------------
					 */

    
    public boolean isValidSudoku(char[][] board) {
	Set<String> seen = new HashSet<>();
	for (int i = 0; i < 9; ++i) {
	    for (int j = 0; j < 9; ++j) {
		char number = board[i][j];
		if (number != '.')
		    if (!seen.add(number + " in row " + i) || !seen.add(number + " in column " + j)
			    || !seen.add(number + " in block " + i / 3 + "-" + j / 3))
			return false;
	    }
	}
	return true;
    }
    
    public static void main(String[] args) {
	char[][]  board = new char[][]
		{{'5','3','.','.','7','.','.','.','.'}
		,{'6','.','.','1','9','5','.','.','.'}
		,{'.','9','8','.','.','.','.','6','.'}
		,{'8','.','.','.','6','.','.','.','3'}
		,{'4','.','.','8','.','3','.','.','1'}
		,{'7','.','.','.','2','.','.','.','6'}
		,{'.','6','.','.','.','.','2','8','.'}
		,{'.','.','.','4','1','9','.','.','5'}
		,{'.','.','.','.','8','.','.','7','9'}};
	
	ValidSudoku sudoku = new ValidSudoku();
	System.out.println(sudoku.isValidSudoku3(board));	// true
	System.out.println(sudoku.isValidSudoku2(board));	// true
	System.out.println(sudoku.isValidSudoku(board));	// true
	
	board = new char[][]
		{{'5','8','.','.','7','.','.','.','.'}
		,{'6','.','.','1','9','5','.','.','.'}
		,{'.','9','8','.','.','.','.','6','.'}
		,{'8','.','.','.','6','.','.','.','3'}
		,{'4','.','.','8','.','3','.','.','1'}
		,{'7','.','.','.','2','.','.','.','6'}
		,{'.','6','.','.','.','.','2','8','.'}
		,{'.','.','.','4','1','9','.','.','5'}
		,{'.','.','.','.','8','.','.','7','9'}};
		
	System.out.println(sudoku.isValidSudoku3(board));	// false
	System.out.println(sudoku.isValidSudoku2(board));	// false
	System.out.println(sudoku.isValidSudoku(board));	// false
	
	sudoku.test();
    }
    
    
    
       /*Collect the set of things we see, encoded as strings. For example:
         
        	'4' in row 7 is encoded as "(4)7".
        	'4' in column 7 is encoded as "7(4)".
        	'4' in the top-right block is encoded as "0(4)2".
        	
        	Scream false if we ever fail to add something because 
        		it was already added (i.e., seen before).
        */
    
	public boolean isValidSudoku2(char[][] board) {
	    Set<String> seen = new HashSet<>();
	    for (int i=0; i<9; ++i) {
	        for (int j=0; j<9; ++j) {
	            if (board[i][j] != '.') {
	                String b = "(" + board[i][j] + ")";
	                if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
	                    return false;
	            }
	        }
	    }
	    return true;
	}
}