package in.sachinshinde.stack;

import java.util.Stack;

//	https://leetcode.com/problems/basic-calculator-ii/

/*
 	Given a string s which represents an expression, evaluate this expression and return its value. 
	The integer division should truncate toward zero.
	You may assume that the given expression is always valid. 
	All intermediate results will be in the range of [-231, 231 - 1].

	Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, 
			such as eval().
 */

public class BasicCalculator2 {
	
	/*
	 Steps:
	 	1)	Keep first operator as +
	 	2)	calculate the num value - it is num (num can be > 10)
	 	3)	for every current char as operator value,
	 	 		i. 		if the last op is + or - then push current number to stack with that sign 
	 	 		ii. 	if the last op is * or / then pop the result and then perform the operation with num value.
	 	 				reset num and op value here
	 	4)	take a sum of all values from the stack
	 */
	
	public int calculate(String s) {
		int num = 0;
		char op = '+';
		int n = s.length();
		
		Stack<Integer> st = new Stack<>();

		for(int i=0; i<n; i++) {
			char ch = s.charAt(i);
			
			if(Character.isDigit(ch))
				num = num * 10 + ch - '0';
			
			if(!Character.isDigit(ch) && ch != ' ' || i==n-1) {
				if(op == '+')
					st.push(num);
				else if(op == '-')
					st.push(-num);
				else if(op == '*')
					st.push(st.pop() * num);
				else if(op == '/')
					st.push(st.pop() / num);
                
                num = 0;
			    op = ch;
			}
				
		}
		
		int res = 0;
		while(!st.isEmpty())
			res += st.pop();

		return res;
	}
	
	public static void main(String[] args) {
		BasicCalculator2 basicCalculator2 = new BasicCalculator2();
		String s;
		s = new String("3+2*2");	//	7
		System.out.println(basicCalculator2.calculate(s));
	}
}

/*
 Constraints:

	1 <= s.length <= 3 * 10^5
	s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
	s represents a valid expression.
	All the integers in the expression are non-negative integers in the range [0, 231 - 1].
	The answer is guaranteed to fit in a 32-bit integer.
*/

/*
 	Example 1:
		Input: s = "3+2*2"
		Output: 7

	Example 2:
		Input: s = " 3/2 "
		Output: 1

	Example 3:
		Input: s = " 3+5 / 2 "
		Output: 5
 */