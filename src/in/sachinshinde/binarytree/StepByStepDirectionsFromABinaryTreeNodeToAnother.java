package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/

/*
 	You are given the root of a binary tree with n nodes. 
 	Each node is uniquely assigned a value from 1 to n. 
 	You are also given an integer startValue representing the value of the start node s, 
 		and a different integer destValue representing the value of the destination node t.

	Find the shortest path starting from node s and ending at node t. 
	Generate step-by-step directions of such path as a string consisting of only 
		the uppercase letters 'L', 'R', and 'U'. 
	Each letter indicates a specific direction:

	'L' 
		means to go from a node to its left child node.
		
	'R' 
		means to go from a node to its right child node.
		
	'U' 
		means to go from a node to its parent node.
		
	Return the step-by-step directions of the shortest path from node s to node t.

	---------
	Example 1:
	---------
	Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
	Output: "UURL"
	Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
	---------
	Example 2:
	---------
	Input: root = [2,1], startValue = 2, destValue = 1
	Output: "L"
	Explanation: The shortest path is: 2 → 1.
 */

public class StepByStepDirectionsFromABinaryTreeNodeToAnother {
	
	/* 
	 Steps:-
	 -----
	 	[1]	Build directions for both start and destination from the root.
			Say we get "LLRRL" and "LRL".
		[2]	Remove common prefix path.
			We remove "L", and now start direction is "LRRL", and destination - "RL"
		[3]	Replace all steps in the start direction to "U" and add reverse destination direction.
			The result is "UUUU" + "LR".
	 */
	
	public String getDirections(Node root, int startValue, int destValue) {
	    StringBuilder s = new StringBuilder();
	    StringBuilder d = new StringBuilder();
	    
	    isValueExists(root, startValue, s);	//	building start direction
	    isValueExists(root, destValue,  d);	//	building destination direction
	    
	    int i = 0;
	    int iMax = Math.min(d.length(), s.length());
	    
	    while (i < iMax && 
	    		s.charAt(s.length() - i - 1) == 
	    		d.charAt(d.length() - i - 1))		//	Removing the common prefix
	       
	    		i++;
	    
	    return "U".repeat(s.length() - i) 	//	Replace all steps in the start direction to "U"
	    		+ d.reverse().toString().substring(i);	//	add reverse destination direction
	}
	
	private boolean isValueExists(Node n, int val, StringBuilder sb) {
	    if (n.key == val) 
	        return true;
	    
	    if(n.left != null && isValueExists(n.left, val, sb))
	        sb.append("L");
	    else 
	    	if(n.right != null && isValueExists(n.right, val, sb))
	    		sb.append("R");
	    
	    return sb.length() > 0;
	}
	
	public static void main(String[] args) {
		StepByStepDirectionsFromABinaryTreeNodeToAnother dirs = 
				new StepByStepDirectionsFromABinaryTreeNodeToAnother();
		
		Node root = new Node(5);
		root.left = new Node(1);
		root.left.left = new Node(3);
		root.right = new Node(2);
		root.right.left = new Node(6);
		root.right.right = new Node(4);
		
		System.out.println(dirs.getDirections(root, 3, 6));	//	"UURL"
		
		Node n = new Node(2);
		n.left = new Node(1);
		
		System.out.println(dirs.getDirections(n, 2, 1));	//	"L"
	}
	
}