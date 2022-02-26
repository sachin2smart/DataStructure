package in.sachinshinde.stack;

import java.util.Stack;

//	https://leetcode.com/problems/basic-calculator/

/*
 	Given a string s representing a valid expression, 
 	implement a basic calculator to evaluate it, 
 	and return the result of the evaluation.

	Note: You are not allowed to use any built-in function 
			which evaluates strings as mathematical expressions, 
			such as eval().
 */

public class BasicCalculator {
	
    public int calculate(String s) {
        if(s == null) 
            return 0;
        
        int result = 0;
        int sign = 1;
        int num = 0;

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(sign);

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= '0' && c <= '9')
                num = num * 10 + (c - '0');
            else if(c == '(')
                stack.push(sign);
            else if(c == ')')
                stack.pop();
            else if(c == '+' || c == '-') {
                result += sign * num;	// calculation here 
                sign = stack.peek() * (c == '+' ? 1: -1); // **** IMP : resetting the sign as per char val 
                num = 0;
            }
        }
        
        result += sign * num;
        return result;
    }
    
    public static void main(String[] args) {
		BasicCalculator basicCalculator = new BasicCalculator();
		String s = new String(" (((1))) + - (-1 ) ");
		System.out.println(basicCalculator.calculate(s)); 	// 	2
	}
}

/*
Constraints:
------------
	1 <= s.length <= 3 * 105
	s consists of digits, '+', '-', '(', ')', and ' '.
	s represents a valid expression.
	'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
	'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
	There will be no two consecutive operators in the input.
	Every number and running calculation will fit in a signed 32-bit integer.
*/


/*
	Example 1:
		Input: s = "1 + 1"
		Output: 2
	
	Example 2:
		Input: s = " 2-1 + 2 "
		Output: 3
	
	Example 3:
		Input: s = "(1+(4+5+2)-3)+(6+8)"
		Output: 23
*/