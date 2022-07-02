package in.sachinshinde.stack;

//	https://leetcode.com/problems/min-stack/

/*
 	Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

	Implement the MinStack class:
	
		MinStack() : initializes the stack object.
		void push(int val) : pushes the element val onto the stack.
		void pop() : removes the element on the top of the stack.
		int top() : gets the top element of the stack.
		int getMin() : retrieves the minimum element in the stack.
		
	You must implement a solution with O(1) time complexity for each function.
 */

public class MinStack {

	private StackNode topNode;
	
	public MinStack() {
        
    }
    
    public void push(int val) {
        if(topNode == null)
        	topNode = new StackNode(val, val, null);
        else
        	topNode = new StackNode(val, Math.min(val, topNode.min), topNode);
    }
    
    public void pop() {
        topNode = topNode.next;
    }
    
    public int top() {
        return topNode.val;
    }
    
    public int getMin() {
        return topNode.min;
    }
    
    private class StackNode {
        int val;
        int min;
        StackNode next;
            
        private StackNode(int val, int min, StackNode next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
