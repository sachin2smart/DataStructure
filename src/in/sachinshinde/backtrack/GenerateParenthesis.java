package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/

/*
 * 	Given n pairs of parentheses, 
 * 	write a function to generate all combinations of well-formed parentheses.
 * 
 * 	Input: n = 1
 * 	Output: ["()"]
 * 
 * 	Input: n = 3
 * 	Output: ["((()))","(()())","(())()","()(())","()()()"]
 */


//	Approach : backtrack

public class GenerateParenthesis {

	public static List<String> generateParenthesis(int n){
		List<String> res = new ArrayList<>();
		backtrack(res, "", 0, 0, n);
		return res;
	}

	private static void backtrack(List<String> res, String str, int open, int close, int n) {
		if(str.length() == n*2) {	// since for n=1, the result will be of length 2 ()
			res.add(str);
			return;
		}

		// there should be n open brackets
		if(open < n) {
			backtrack(res, str + "(", open + 1, close, n);
		}

		// for every open bracket there should exist a closing bracket
		if(close < open) {
			backtrack(res, str + ")", open, close + 1, n);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(generateParenthesis(3));
	}

	public List<String> generateParenthesis2(int n) {
		List<String> list = new ArrayList<>();

		generateRec(list, "", n, n);

		return list;
	}

	private void generateRec (List<String> list, String curr, int left, int right) {
		// Base Case: ( and ) are ran out
		if (left == 0 && right == 0) {
			list.add(curr);
		}

		// Recurse
		// Only when there are more ( than ) in the curr, we can choose to add )
		if (left < right) {
			String newCurr = curr + ")";
			generateRec(list, newCurr, left, right - 1);
		}

		// If left is not 0, adding ( is always an option
		if (left != 0) {
			String newCurr = curr + "(";
			generateRec(list, newCurr, left - 1, right);
		}
	}
}