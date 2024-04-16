package in.sachinshinde.binarytree.find_from_tree;

import in.sachinshinde.binarytree.Node;

import java.util.HashMap;

//	https://leetcode.com/problems/path-sum-iii/

/*
 	Given the root of a binary tree and an integer targetSum, 
 		return the number of paths where 
 			the sum of the values along the path equals targetSum.

	The path does not need to start or end at the root or a leaf, 
		but it must go downwards 
			(i.e., traveling only from parent nodes to child nodes).
 */

/*
 	Example 1:
 	---------
 	Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
        Output: 3 
        
        			 10
        			/  \
        		       5   -3
        		      / \    \
        		     3   2   11
        		    / \   \
        		   3  -2   1
        		   
        	
		Paths : 
			[5,3]
			[5,2,1]
			[-3, 11]
        	
        		   
        Example 2:
        ----------
        Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        Output: 3
        
        			 5
        		       /   \
        		      4     8
        		     /     / \
        		    11    13  4
        		   /  \      / \
        		  7    2    5   1
        		    
        	
        	Paths :
        		[5,4,11,2]
        		[4,11,7]
        		[5,8,4,5]
 */

public class PathSum3 {
    int numPaths = 0;
    public int pathSum(Node root, int targetSum) {
	if(root == null)
	    return 0;
	
	pathSumHelper(root, targetSum, 0);
	
	pathSum(root.left, targetSum);
	pathSum(root.right, targetSum);
	
	return numPaths;
    }

    private void pathSumHelper(Node root, int targetSum, int currentSum) {
	if(root == null)
	    return;
	
	currentSum += root.key;
	
	if(currentSum == targetSum)
	    numPaths++;
	
	pathSumHelper(root.left, targetSum, currentSum);
	pathSumHelper(root.right, targetSum, currentSum);
    }
    
    public static void main(String[] args) {
	PathSum3 pathSum3 = new PathSum3();
	Node root1 = new Node(10);
	root1.left = new Node(5);
	root1.left.left = new Node(3);
	root1.left.left.left = new Node(3);
	root1.left.left.right = new Node(-2);
	root1.left.right = new Node(2);
	root1.left.right.right = new Node(1);
	
	root1.right = new Node(-3);
	root1.right.right = new Node(11);
	
	System.out.println(pathSum3.pathSum(root1, 8));		//	3
	System.out.println(pathSum3.pathSum2(root1, 8));	//	3
	System.out.println(pathSum3.pathSum3(root1, 8));	//	3
	pathSum3.numPaths = 0;
	System.out.println(pathSum3.pathSum4(root1, 8));	//	3
	
	Node root2 = new Node(5);
	root2.left = new Node(4);
	root2.left.left = new Node(11);
	root2.left.left.left = new Node(7);
	root2.left.left.right = new Node(2);
	
	root2.right = new Node(8);
	root2.right.left = new Node(13);
	root2.right.right = new Node(4);
	root2.right.right.left = new Node(5);
	root2.right.right.right = new Node(1);
	
	pathSum3.numPaths = 0;
	System.out.println(pathSum3.pathSum(root2, 22));	//	3
	System.out.println(pathSum3.pathSum2(root2, 22));	//	3
	System.out.println(pathSum3.pathSum3(root2, 22));	//	3
	pathSum3.numPaths = 0;
	System.out.println(pathSum3.pathSum4(root2, 22));	//	3
    }
    
    //	Using local variable
    public int pathSum2(Node root, int sum) {
	if (root == null)
            return 0;
        
        int curCount = pathSumHelper2(root, sum);
        return curCount + pathSum2(root.left, sum) + pathSum2(root.right, sum);
    }

    public int pathSumHelper2(Node root, int sum) {
        if (root == null)
            return 0;
        
        int count = 0;
        if (root.key == sum)
            count++;
        
        return count + pathSumHelper2(root.left, sum - root.key) + pathSumHelper2(root.right, sum - root.key);
    }
    
    
    //	Using Hashmap
    public int pathSum3(Node root, int targetSum) {
        if (root == null)
            return 0;
        
        return getCount(root, new HashMap<Long, Integer>(), 0, 0, targetSum);
    }

    private int getCount(Node node, HashMap<Long, Integer> sum, long currSum, int count, int targetSum) {
	currSum = node.key + currSum;
        long target = currSum - targetSum;
        
        if (target == 0)
            count++;
        
        if (sum.containsKey(target))
            count += sum.get(target);
        
        if (node.left == null && node.right == null)
            return count;

        if (sum.containsKey(currSum))
            sum.put(currSum, sum.get(currSum)+1);
        else
            sum.put(currSum, 1);

        if (node.left != null)
            count = getCount(node.left, sum, currSum, count, targetSum);
        
        if (node.right != null)
            count = getCount(node.right, sum, currSum, count, targetSum);

        // remove itself so that it won't be involved for further calculation
        int actualSum = sum.get(currSum) - 1;
        if (actualSum > 0)
            sum.put(currSum, actualSum);
        else
            sum.remove(currSum);

        return count;
    }
    
    //	Using Prefix sum
    public int pathSum4(Node root, int targetSum) {
	HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
	preOrder(root, 0, targetSum, prefixSumMap);
	return numPaths;
    }
    
    public void preOrder(Node root, int currSum, int targetSum, HashMap<Integer, Integer> prefixSumMap) {
	if(root == null)
	    return;
	
	currSum += root.key;
	
	if(currSum == targetSum) 
	    numPaths++;
	
	numPaths += prefixSumMap.getOrDefault(currSum - targetSum, 0);
	
	prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum,  0) + 1);
	preOrder(root.left, currSum, targetSum, prefixSumMap);
	preOrder(root.right, currSum, targetSum, prefixSumMap);
	prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);
    }
}