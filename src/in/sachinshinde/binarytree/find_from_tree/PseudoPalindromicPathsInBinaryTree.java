package in.sachinshinde.binarytree.find_from_tree;

import in.sachinshinde.binarytree.Node;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/

/*
 	Given a binary tree where node values are digits from 1 to 9. 
 	A path in the binary tree is said to be pseudo-palindromic if at least one permutation 
 		of the node values in the path is a palindrome.

        Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

        Example 1:
        ---------
        Input: root = [2,3,1,3,1,null,1]
        Output: 2 
        Explanation: The figure above represents the given binary tree. There are three paths 
        going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], 
        and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic
         paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path 
         [2,1,1] can be rearranged in [1,2,1] (palindrome).
         
        Example 2:
        ----------
        Input: root = [2,1,1,1,3,null,null,null,null,null,1]
        Output: 1 
        Explanation: The figure above represents the given binary tree. 
        There are three paths going from the root node to leaf nodes: the green path [2,1,1], 
        the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic 
        since [2,1,1] can be rearranged in [1,2,1] (palindrome).
        
        Example 3:
        ---------
        Input: root = [9]
        Output: 1
         
        
        Constraints:
        -----------
            The number of nodes in the tree is in the range [1, 105].
            1 <= Node.val <= 9
 */

public class PseudoPalindromicPathsInBinaryTree {
    public int pseudoPalindromicPaths (Node root) {
        return getPalindromicPathsCount(root,new HashSet<>());
    }

    private int getPalindromicPathsCount(Node root, Set<Integer> set) {
	if(root == null) {
	    return 0;
	}
	
	if(set.contains(root.key)) {
	    set.remove(root.key);
	}
	else {
	    set.add(root.key);
	}
	
	if(root.left == null && root.right == null) {
	    return set.size() <= 1 ? 1 : 0;
	}
	
	int left = getPalindromicPathsCount(root.left, new HashSet<>(set));
	int right = getPalindromicPathsCount(root.right, new HashSet<>(set));
	
	return left + right;
    }
    
    // -------------------------------------------------------------------------
    
    public void dfs(Node root, int[] numsCount, int[] res){
        if(root == null)
            return ;
        
        numsCount[root.key-1]++;

        if(root.left == null && root.right == null) {
            int evenCount = 0;
            int oddCount = 0;
            for(int i = 0;i < 9;i++){
                if(numsCount[i] > 0){
                    if(numsCount[i] % 2 == 1)
                	oddCount++;
                    else 
                	evenCount++;
                }
            }

            if(oddCount == 1 && evenCount >= 0)
        	res[0]++;
            else if(evenCount == 0 && evenCount > 0)
        	res[0]++;
        }

        dfs(root.left, numsCount, res);
        dfs(root.right, numsCount, res);
        
        numsCount[root.key-1]--;
    }

    public int pseudoPalindromicPaths2 (Node root) {
        int[] res = new int[1];
        int[] cnt = new int[9];
        
        res[0] = 0;
        dfs(root, cnt, res);

        return res[0];
    }
    // -------------------------------------------------------------------------
    
    public void dfs3(Node root, int[] numsCount, int[] res){
        if(root == null)
            return ;
        
        numsCount[root.key-1]++;

        if(root.left == null && root.right == null) {
            int count = 0;
            for(int i = 0;i < 9;i++){
        	if(numsCount[i]%2 != 0)
        	    count++;
            }
            if(count <= 1)
        	res[0]++;
        }

        dfs3(root.left, numsCount, res);
        dfs3(root.right, numsCount, res);
        
        numsCount[root.key-1]--;
    }

    public int pseudoPalindromicPaths3(Node root) {
        int[] res = new int[1];
        int[] numsCount = new int[9];
        
        res[0] = 0;
        dfs3(root, numsCount, res);

        return res[0];
    }

    
 // -------------------------------------------------------------------------
    
    public static void main(String[] args) {
	PseudoPalindromicPathsInBinaryTree btree = new PseudoPalindromicPathsInBinaryTree();
	Node root1 = new Node(2);
	root1.left = new Node(3);
	root1.left.left = new Node(3);
	root1.left.right = new Node(1);
	
	root1.right = new Node(1);
	root1.right.right = new Node(1);
	
	System.out.println(btree.pseudoPalindromicPaths(root1));	// 2
	System.out.println(btree.pseudoPalindromicPaths2(root1));	// 2
	System.out.println(btree.pseudoPalindromicPaths3(root1));	// 2
	
	Node root2 = new Node(2);
	root2.left = new Node(1);
	root2.left.left = new Node(1);
	root2.left.right = new Node(3);
	root2.left.right.right = new Node(1);
	
	root2.right = new Node(1);
	
	System.out.println(btree.pseudoPalindromicPaths(root2));	// 1
	System.out.println(btree.pseudoPalindromicPaths2(root2));	// 1
	System.out.println(btree.pseudoPalindromicPaths3(root2));	// 1
    }
    
}
