package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	// second solution is better
	public List<List<String>> solveNQueens(int n) {
	  List<List<String>> result = new ArrayList<>();
	  List<Integer> queens = new ArrayList<>(); // store (i, j) where to place queens
	  int[][] attack = new int[n][n];  // > 0 -> could be attacked
	  backtrack(0, n, queens, attack, result);
	  return result;
	}

	// d is the depth (here it means the current row)
	// queens stores the col of a placed queen
	private void backtrack(int d, int n,
								  List<Integer> queens, int[][] attack,
								  List<List<String>> result) {
	  
	  // base case
	  if (d == n) {
	    // Init dot builder
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < n; ++i) {
			sb.append(".");
		}
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
	
	private void updateAttack(int i, int j, int n, int[][] attack) {
	  // update all below/hill/dale positions by +1
	  for (int k = i + 1, offset = 1; k < n; ++k, ++offset) {
	    attack[k][j] += 1; // mid
	    if (j - offset >= 0) {
			attack[k][j - offset] += 1; // left
		}
	    if (j + offset < n) {
			attack[k][j + offset] += 1; // right
		}
	  }
	}

	private void restoreAttack(int i, int j, int n, int[][] attack) {
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
		NQueens nQueens = new NQueens();
		System.out.println(nQueens.solveNQueens(1));
		System.out.println(nQueens.solveNQueens(2));
		System.out.println(nQueens.solveNQueens(3));
		System.out.println(nQueens.solveNQueens(4));
		System.out.println(nQueens.solveNQueens(8));

		System.out.println(nQueens.solveNQueens2(4));
	}

	// 	Video Solution : https://youtu.be/Ph95IHmRp5M
	// Simple - easy to understand
	public List<List<String>> solveNQueens2(int n) {
		List<List<String>> result = new ArrayList<>();
		List<String> currResRow = new ArrayList<>();

		if (n <= 0) {
			return result;
		}

		Set<Integer> posDiagSet = new HashSet<>(); // diag \ row + col
		Set<Integer> negDiagSet = new HashSet<>(); // diag / row - col
		Set<Integer> colSet = new HashSet<>(); // column | col

		dfs(n, result, currResRow, posDiagSet, negDiagSet, colSet);

		return result;
	}

	private void dfs(int n, List<List<String>> result, List<String> currResRow, Set<Integer> posDiagSet,
					 Set<Integer> negDiagSet, Set<Integer> colSet) {

		if (currResRow.size() == n) {
			result.add(new ArrayList(currResRow));
			return;
		}

		int row = currResRow.size();

		for (int col = 0; col < n; col++) {
			if (!posDiagSet.contains(row + col) && !negDiagSet.contains(row - col) && !colSet.contains(col)) {
				// set
				currResRow.add(convert(n, col));
				posDiagSet.add(row + col);
				negDiagSet.add(row - col);
				colSet.add(col);

				// dfs
				dfs(n, result, currResRow, posDiagSet, negDiagSet, colSet);

				// unset (backtracking)
				currResRow.remove(currResRow.size() - 1);
				posDiagSet.remove(row + col);
				negDiagSet.remove(row - col);
				colSet.remove(col);
			}
		}
	}

	private String convert(int n, int col) {
		StringBuilder res = new StringBuilder();
		for (int row = 0; row < n; row++) {
			if (row == col) {
				res.append("Q");
			} else {
				res.append(".");
			}
		}
		return res.toString();
	}
}
