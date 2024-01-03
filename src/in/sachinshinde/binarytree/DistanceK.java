package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//	https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

//	video solution : https://youtu.be/i9ORlEy6EsI

/*
 	Given the root of a binary tree, the value of a target node target, and an integer k, 
 		return an array of the values of all nodes that have a distance k from the target node.

        You can return the answer in any order.
        
        Example 1:
        ---------
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
        
        			  3
        		       /    \
        		      5      1
        		    /  \    / \
        		   6    2  0   8
        		       / \
        		      7   4
        		      
        Output: [7,4,1]
        Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

        Example 2:
        ---------
        Input: root = [1], target = 1, k = 3
        Output: []
        
        Constraints:
        -----------
            The number of nodes in the tree is in the range [1, 500].
            0 <= Node.val <= 500
            All the values Node.val are unique.
            target is the value of one of the nodes in the tree.
            0 <= k <= 1000
 */

public class DistanceK {
//------------------------------------------------------------------------------------------------------
    //	Method 1: Using BFS
    
    public List<Integer> distanceK(Node root, Node target, int k) {
        Map<Node, Node> parentTracker = new HashMap<>();
        markParents(root, parentTracker);
        
        Map<Node, Boolean> visited = new HashMap<>();
        visited.put(target, true);
        
        Queue<Node> q = new LinkedList<Node>();
        q.offer(target);
        
        int currLevel = 0;
        
        while(!q.isEmpty()) {
            if(currLevel == k) 
        	break;
            
            int size = q.size();
            
            for(int i=0; i<size; i++) {
        	Node currNode = q.poll();
                if(currNode.left != null && visited.get(currNode.left) == null) {
                    visited.put(currNode.left, true);
            		q.offer(currNode.left);
                }
                
                if(currNode.right != null && visited.get(currNode.right) == null) {
                    visited.put(currNode.right, true);
                    q.offer(currNode.right);
                }
                
                if(parentTracker.get(currNode) != null && visited.get(parentTracker.get(currNode)) == null) {
                    visited.put(parentTracker.get(currNode), true);
                    q.offer(parentTracker.get(currNode));
                }
            }
            currLevel++;
        }
        
        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()) {
            Node currNode = q.poll();
            result.add(currNode.key);
        }
        return result;
    }
    
    private void markParents(Node root, Map<Node, Node> parentTracker) {
	Queue<Node> q = new LinkedList<Node>();
	q.offer(root);
	
	while(!q.isEmpty()) {
	    Node currNode = q.poll();
	    if(currNode.left != null) {
		parentTracker.put(currNode.left, currNode);
		q.add(currNode.left);
	    }
	    if(currNode.right != null) {
		parentTracker.put(currNode.right, currNode);
		q.add(currNode.right);
	    }
	}
    }

//---------------------------------------------------------------------------------------------------------
    //	Method 2: Recursion
    
    public List<Integer> distanceK2(Node root, Node target, int k) {
        Map<Node, Node> parentTracker = new HashMap<>();
        markParent2(root, parentTracker);
        
        Map<Node, Boolean> visited = new HashMap<>();
        visited.put(target, true);
        
        List<Integer> result = new ArrayList<>();
        accumulateKDistanceNodes(target, parentTracker, visited, k, result);
        return result;
    }
        
    private void accumulateKDistanceNodes(Node target, Map<Node, Node> parentTracker, 
	    Map<Node, Boolean> visited, int k, List<Integer> result) {
	if (k == 0) {
	    result.add(target.key);
	}
	
	visited.put(target, true);
        
	if (target.left != null && !visited.getOrDefault(target.left, false)) {
	    accumulateKDistanceNodes(target.left, parentTracker, visited, k - 1, result);
        }
	
        if (target.right != null && !visited.getOrDefault(target.right, false)) {
            accumulateKDistanceNodes(target.right, parentTracker, visited, k - 1, result);
        }
        
        if (parentTracker.containsKey(target) && parentTracker.get(target) != null &&
        	!visited.getOrDefault(parentTracker.get(target), false)) {
            accumulateKDistanceNodes(parentTracker.get(target), parentTracker, visited, k - 1, result);
        }
    }

    private void markParent2(Node root, Map<Node, Node> parent) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parent.put(root.left, root);
            markParent2(root.left, parent);
        }
        if (root.right != null) {
            parent.put(root.right, root);
            markParent2(root.right, parent);
        }
    }
    
//---------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
	DistanceK binaryTree = new DistanceK();
	
	Node root = new Node(3);
	Node target = new Node(5);
	root.left = target;
	root.left.left = new Node(6);
	root.left.right = new Node(2);
	root.left.right.left = new Node(7);
	root.left.right.right = new Node(4);
	
	root.right = new Node(1);
	root.right.left = new Node(0);
	root.right.right = new Node(8);
	
	System.out.println(binaryTree.distanceK(root, target, 2));	//	[7, 4, 1]
	System.out.println(binaryTree.distanceK2(root, target, 2));	//	[7, 4, 1]
	
	System.out.println(binaryTree.distanceK(root, target, 3));	//	[0, 8]
	System.out.println(binaryTree.distanceK2(root, target, 3));	//	[0, 8]
	
	Node root2 = new Node(1);
	System.out.println(binaryTree.distanceK(root2, root2, 3));	//	[]
	System.out.println(binaryTree.distanceK2(root2, root2, 3));	//	[]

    }
}
