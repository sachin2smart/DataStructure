package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://leetcode.com/problems/all-possible-full-binary-trees/

/*
 * 	All Possible Full Binary Trees
 * --------------------------------
 * 	Given an integer n, return a list of all possible full binary trees with n nodes. 
 * 	Each node of each tree in the answer must have Node.val == 0.
 * 	Each element of the answer is the root node of one possible tree. 
 * 	You may return the final list of trees in any order.

	A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 */


public class AllPossibleFullBinaryTrees {

	//	method 1: using recursion 
	public List<Node> allPossibleFBT(int n) {
		List<Node> res = new ArrayList<>();
		
		// final recursion will stop here once the n reaches to 1
		if(n==1) {
			 res.add(new Node(0));
			 return res;
		}
		
		// Excluding current node, there will be (left + right = n-1) child nodes
		n = n-1;
		
		// increment by 2 since left,right childs are being formed
		for(int i=1; i<n; i=i+2) {	 
			List<Node> left = allPossibleFBT(i);	//	i= number of children on left
			List<Node> right = allPossibleFBT(n-i);	//	n-i= number of children on right
			
			for(Node nl: left) {
				for(Node nr: right) {
					Node curr = new Node(0);
					curr.left = nl;
					curr.right = nr;
					res.add(curr);
				}
			}
		}
		
		return res;
	}
	
	// Using Memoization
	
	// this map : to store a list of nodes at it's n'th place
	Map<Integer,List<Node>> cache= new HashMap<>();
	
    public List<Node> allPossibleFBT_mem(int n) {
        List<Node> res = new ArrayList<>();
        
        if(n%2 == 0) {
            return res;
        }
        
        if(cache.containsKey(n)) {
            return cache.get(n);
        }
        
        if(n==1) {
            res.add(new Node(0));
            return res;
        }
        
        n=n-1;
        
        for(int i=1; i<n;i+=2) {
            List<Node> left = allPossibleFBT(i);
            List<Node> right = allPossibleFBT(n-i);
        
            for(Node nl: left) {
                for(Node nr:right){
                	Node cur = new Node(0);
                    cur.left=nl;
                    cur.right=nr;
                    res.add(cur);
                }
            }
        }
        
        cache.put(n+1,res);	// Storing n'th result in memory cache
        
        return res;
    }
    
    public static void main(String[] args) {
    	AllPossibleFullBinaryTrees trees = new AllPossibleFullBinaryTrees();
		List<Node> result = trees.allPossibleFBT(7);
		
		for(Node n: result) {
			System.out.println(" ");
			trees.print(n);		//	0000000
		}
		
		System.out.println("\n");
		for(int i=0; i<10; i++) {
			List<Node> r = trees.allPossibleFBT(i);
			System.out.println(i+ " nodes, FBTs : "+r.size());
		}
	}
    
    private long fact(int i) {
        if(i <= 1) {
           return 1;
        }
        return i * fact(i - 1);
     }
    
	private void print(Node n) {
		if(n == null)
			return;
		System.out.print(" "+n.key);
		print(n.left);
		print(n.right);
	}
	
}
