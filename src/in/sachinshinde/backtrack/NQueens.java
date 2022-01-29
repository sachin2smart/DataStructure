package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.List;

//	https://leetcode.com/problems/n-queens/

/*
 * 	N-Queens
 * 		The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
 * 		such that no two queens attack each other.

		Given an integer n, 
		return all distinct solutions to the n-queens puzzle. 
		
		You may return the answer in any order.

		Each solution contains a distinct board configuration of the n-queens' placement,
		 where 'Q' and '.' both indicate a queen and an empty space, respectively.
 */



//	Approach : Backtracking 


public class NQueens {

	public static List<List<String>> solveNQueens(int n) {
	  List<List<String>> result = new ArrayList<>();
	  List<Integer> queens = new ArrayList<>(); // store (i, j) where to place queens
	  int[][] attack = new int[n][n];  // > 0 -> could be attacked
	  backtrack(0, n, queens, attack, result);
	  return result;
	}

	// d is the depth (here it means the current row)
	// queens stores the col of a placed queen
	private static void backtrack(int d, int n, List<Integer> queens, int[][] attack, List<List<String>> result) {
	  
	  // base case
	  if (d == n) {
	    // Init dot builder
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < n; ++i) 
	    	sb.append(".");
	    // Set queen
	    List<String> strList = new ArrayList<>();
	    for (int row = 0; row < n; ++row) {
	      int col = queens.get(row);
	      sb.setCharAt(col, 'Q');
	      strList.add(sb.toString());
	      sb.setCharAt(col, '.');
	    }
	    result.add(strList);
	    return;
	  }
		  
	  // backtrack
	  for (int i = 0; i < n; ++i) {
	    if (attack[d][i] == 0) {
	      // pick
	      queens.add(i);
	      updateAttack(d, i, n, attack);
	      backtrack(d + 1, n, queens, attack, result);
	      // restore
	      queens.remove(queens.size() - 1);
	      restoreAttack(d, i, n, attack);
	    }
	  }
	  
	}
	
	private static void updateAttack(int i, int j, int n, int[][] attack) {
	  // update all below/hill/dale positions by +1
	  for (int k = i + 1, offset = 1; k < n; ++k, ++offset) {
	    attack[k][j] += 1; // mid
	    if (j - offset >= 0) 
	    	attack[k][j - offset] += 1; // left
	    if (j + offset < n) 
	    	attack[k][j + offset] += 1; // right
	  }
	}

	private static void restoreAttack(int i, int j, int n, int[][] attack) {
	  // restore all below/hill/dale positions by -1
	  for (int k = i + 1, offset = 1; k < n; ++k, ++offset) {
	    attack[k][j] -= 1; // mid
	    if (j - offset >= 0) 
	    	attack[k][j - offset] -= 1; // left
	    if (j + offset < n) 
	    	attack[k][j + offset] -= 1; // right
	  }
	}
	
	public static void main(String[] args) {
		System.out.println(solveNQueens(1));
		System.out.println(solveNQueens(2));
		System.out.println(solveNQueens(3));
		System.out.println(solveNQueens(4));
		System.out.println(solveNQueens(8));
	}
}
