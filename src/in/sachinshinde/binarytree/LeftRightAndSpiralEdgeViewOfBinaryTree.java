package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.List;

public class LeftRightAndSpiralEdgeViewOfBinaryTree {
	
	public static void main(String[] args) {
		Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);
        
        List<Integer> leftViewResult = new ArrayList<Integer>();
        leftView(root, leftViewResult, 0);
        System.out.print("\n Left View of Binary Tree :");
        leftViewResult.forEach(k -> {System.out.print(" "+k);});
        
        List<Integer> rightViewResult = new ArrayList<Integer>();
        rightView(root, rightViewResult, 0);
        System.out.print("\n Right View of Binary Tree :");
        rightViewResult.forEach(k -> {System.out.print(" "+k);});
        
        int spiralEdgeViewCount = Math.max(leftViewResult.size(), rightViewResult.size());
        List<Integer> spiralEdgeView = new ArrayList<>();
        System.out.print("\n Spiral Edge View of Binary Tree :");

        spiralEdgeView.add(leftViewResult.get(0));
        for(int i=1; i<spiralEdgeViewCount; i++) {
        	if(i < leftViewResult.size())
        		spiralEdgeView.add(leftViewResult.get(i));
        	if(i < rightViewResult.size())
        		spiralEdgeView.add(rightViewResult.get(i));
        }
        spiralEdgeView.forEach(k -> {System.out.print(" "+k);});
	}
	
	private static void leftView(Node root, List<Integer> result, int level) {
		if(root == null)
			return;
		
		if(level == result.size()) {	// on each level we need left-most node
			result.add(root.key);
		}
		
		leftView(root.left, result, level+1);	//	left first recursive call
		leftView(root.right, result, level+1);
		
	}
	
	private static void rightView(Node root, List<Integer> result, int level) {
		if(root == null)
			return;
		
		if(level == result.size()) {	//	on each level we need right-most node
			result.add(root.key);
		}
		
		rightView(root.right, result, level+1);	//	right first recursive call
		rightView(root.left, result, level+1);
		
	}
}

/*
 * Output:
 
  Left View of Binary Tree : 1 2 4 8
  Right View of Binary Tree : 1 3 7 10
  Spiral Edge View of Binary Tree : 1 2 3 4 7 8 10
 * 
*/