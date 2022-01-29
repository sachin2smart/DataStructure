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

	public static List<String> generateParenthisis(int n){
		List<String> res = new ArrayList<>();
		backtrack(res, "", 0, 0, n);
		return res;
	}

	private static void backtrack(List<String> res, String str, int open, int close, int max) {
		if(str.length() == max*2) {
			res.add(str);
			return;
		}
		
		if(open < max)	 
			backtrack(res, str+"(", open+1, close, max);
		
		if(close < open)  
			backtrack(res, str+")", open, close+1, max);
	}
	
	public static void main(String[] args) {
		List<String> res = generateParenthisis(2);
		System.out.println(res);
	}
}

/*
	Line 15 :  why max*2 = since for n=1, the result will be of length 2 ()
	Line 20 :  there will be a need of having max (i.e.=n) opening brackets
	Line 23 :  for each open bracket there should be a close bracket
*/
