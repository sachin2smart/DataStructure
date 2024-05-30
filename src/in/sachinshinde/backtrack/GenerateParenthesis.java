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
}