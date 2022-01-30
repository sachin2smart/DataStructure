package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://leetcode.com/problems/different-ways-to-add-parentheses/

/*
 * 	Given a string expression of numbers and operators, 
 * 	return all possible results from computing all the different possible ways to group numbers and operators. 
 * 	You may return the answer in any order.
 */

/*
	Input: expression = "2-1-1"
	Output: [0,2]
	Explanation:
		((2-1)-1) = 0 
		(2-(1-1)) = 2

	Input: expression = "2-1-1"
	Output: [0,2]
	Explanation:Input: expression = "2*3-4*5"
	Output: [-34,-14,-10,-10,10]
	Explanation:
		(2*(3-(4*5))) = -34 
		((2*3)-(4*5)) = -14 
		((2*(3-4))*5) = -10 
		(2*((3-4)*5)) = -10 
		(((2*3)-4)*5) = 10
			((2-1)-1) = 0 
			(2-(1-1)) = 2

 */

public class DifferentWaysToAddParenthesis {

	//	Using recursion
	public static List<Integer> diffWaysToCompute(String expression) {
		List<Integer> res = new ArrayList<>();
		
		for(int i=0; i < expression.length(); i++) {
			if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
				String s1 = expression.substring(0,i);
				String s2 = expression.substring(i+1);
				
				List<Integer> s1Rec = diffWaysToCompute(s1);
				List<Integer> s2Rec = diffWaysToCompute(s2);
				
				for(Integer n1: s1Rec) {
					for(Integer n2: s2Rec) {
						int c = 0;
						switch(expression.charAt(i)) {
							case '+': c = n1+n2; break;
							case '-': c = n1-n2; break;
							case '*': c = n1*n2; break;
						}
						res.add(c);
					}
				}
			}
		}
		
		if(res.size() == 0)
			res.add(Integer.valueOf(expression));
		
		return res;
    }

	public static void main(String[] args) {
		System.out.println(diffWaysToCompute("2-1-1"));
		System.out.println(diffWaysToCompute_mem("2-1-1"));
	}
	
	
	//		Using memoization
	
	private static final Map<String, List<Integer>> memory = new HashMap<>();  // Expression --> Result of Expression
	
	public static List<Integer> diffWaysToCompute_mem(String expression) {
		List<Integer> res = new ArrayList<>();
		
		if(expression.length() == 0)
			return res;
		
		if(memory.containsKey(expression))
			return memory.get(expression);
		
		for(int i=0; i < expression.length(); i++) {
			if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
				String s1 = expression.substring(0,i);
				String s2 = expression.substring(i+1);
				
				List<Integer> s1Rec = diffWaysToCompute_mem(s1);
				List<Integer> s2Rec = diffWaysToCompute_mem(s2);
				
				for(Integer n1: s1Rec) {
					for(Integer n2: s2Rec) {
						int c = 0;
						switch(expression.charAt(i)) {
							case '+': c = n1+n2; break;
							case '-': c = n1-n2; break;
							case '*': c = n1*n2; break;
						}
						res.add(c);
					}
				}
			}
		}
		
		if(res.size() == 0)
			res.add(Integer.valueOf(expression));
		
		memory.put(expression, res); // Memoization
		
		return res;
    }
	
}
