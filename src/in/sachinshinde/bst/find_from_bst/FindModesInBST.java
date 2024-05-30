package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 	https://leetcode.com/problems/find-mode-in-binary-search-tree/
 * 
 *  Given the root of a binary search tree (BST) with duplicates, return all the mode(s) 
 *  (i.e., the most frequently occurred element) in it.
 * 
 * 	If the tree has more than one mode, return them in any order.
 * 
 * 	Example
 * 			 3
 * 			/ \
 * 		   2   4
 * 			\
 * 			 2
 * 
 * 	Ans : [2]
 * 
 */

public class FindModesInBST {
	
    public int[] findMode(Node root) {
    	Map<Integer,Integer> map=new HashMap<Integer,Integer>();
    	
        inorder(root, map); 
        
        int max=0;
        for(int value : map.values()) 
        	max = Math.max(max, value);
        
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer,Integer> m : map.entrySet())
            if(m.getValue() == max) 
            	list.add(m.getKey());
       
        int ans[] = new int[list.size()];
        for(int i=0;i<list.size();i++)
            ans[i] = list.get(i);
        
        return ans;
    }
    
    public void inorder(Node root, Map<Integer,Integer> map) {
       if(root != null) {
	        inorder(root.left, map);
	        map.put(root.key,map.getOrDefault(root.key,0)+1);
	        inorder(root.right, map);
       }
    }
    
    public static void main(String[] args) {
		FindModesInBST bst = new FindModesInBST();
		Node root = new Node(3);
		root.left = new Node(2);
		root.left.right = new Node(2);
		
		System.out.println(Arrays.toString(bst.findMode(root)));	//	[2]
		
		root.right = new Node(4);
		root.right.right = new Node(4);
		
		System.out.println(Arrays.toString(bst.findMode(root)));	//	[2,4]
		
		root.right.right.right = new Node(4);
		
		System.out.println(Arrays.toString(bst.findMode(root)));	//	[4]
	}
    
    public int[] findMode2(Node root) {
    	Map<Integer,Integer> map=new HashMap<Integer,Integer>();
    	
        inorder(root, map); 
        
        int val1 = Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        int maxVal = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

        return map.entrySet().stream()
        		.filter(s -> s.getValue()==maxVal)
        		.map(Map.Entry::getKey)
        		.mapToInt(i->i)
        		.toArray();
    }
	
}

