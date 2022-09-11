package in.sachinshinde.stack;

import java.util.Stack;

//	https://leetcode.com/problems/evaluate-reverse-polish-notation/

/*
 	Evaluate the value of an arithmetic expression in Reverse Polish Notation.

	Valid operators are +, -, *, and /. 
	Each operand may be an integer or another expression.

	Note that division between two integers should truncate toward zero.

	It is guaranteed that the given RPN expression is always valid. 
	That means the expression would always evaluate to a result, and 
		there will not be any division by zero operation.


	Example 1:
		Input: tokens = ["2","1","+","3","*"]
		Output: 9
		Explanation: ((2 + 1) * 3) = 9
	
	Example 2:
		Input: tokens = ["4","13","5","/","+"]
		Output: 6
		Explanation: (4 + (13 / 5)) = 6
	
	Example 3:
		Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
		Output: 22
		Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
		= ((10 * (6 / (12 * -11))) + 17) + 5
		= ((10 * (6 / -132)) + 17) + 5
		= ((10 * 0) + 17) + 5
		= (0 + 17) + 5
		= 17 + 5
		= 22
	
	Constraints:
		1 <= tokens.length <= 104
		tokens[i] is either an operator: "+", "-", "*", or "/", 
			or an integer in the range [-200, 200].
 */

public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
        int a, b;
        Stack<Integer> st = new Stack<Integer>();
        
        for(String token: tokens) {
        	if(token.equals("+")) {
        		st.add(st.pop() + st.pop());
        	} 
        	else if(token.equals("*")) {
        		st.add(st.pop() * st.pop());
        	}
        	else if(token.equals("/")) {
        		b = st.pop();
        		a = st.pop();
        		st.add(a/b);
        	} 
        	else if(token.equals("-")) {
        		b = st.pop();
        		a = st.pop();
        		st.add(a-b);
        	} else {
        		st.add(Integer.parseInt(token));
        	}
        }
        
        return st.pop();
    }
	
	public static void main(String[] args) {
		EvaluateReversePolishNotation pN = new EvaluateReversePolishNotation();
		System.out.println(pN.evalRPN(new String[] {"2","1","+","3","*"}));	//	9
		System.out.println(pN.evalRPN(new String[] {"4","13","5","/","+"}));	//	6	
		System.out.println(pN.evalRPN(new String[] {
				"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));	//	22
		
		System.out.println(pN.evalRPN2(new String[] {"2","1","+","3","*"}));	//	9
		System.out.println(pN.evalRPN2(new String[] {"4","13","5","/","+"}));	//	6	
		System.out.println(pN.evalRPN2(new String[] {
				"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));	//	22
	}
	
    String[] tokens;
    int endIndex;
    
    public int evalRPN2(String[] tokens) {
        this.tokens = tokens;
        endIndex = tokens.length - 1;
        return evaluate();
    }
    
    private int evaluate() {
        while(endIndex >= 0) {
            String token = tokens[endIndex--];
            
            if(token.equals("*")) {
                return evaluate() * evaluate();
            }
            else if(token.equals("+")) {
                return evaluate() + evaluate();
            } 
            else if(token.equals("/")) {
                int right = evaluate();
                int left = evaluate();
                return left / right;
            }
            else if(token.equals("-")) {
                int right = evaluate();
                int left = evaluate();
                return left - right;
            } 
            else {
                return Integer.valueOf(token);
            }
        }
        return -1;
    }
}
