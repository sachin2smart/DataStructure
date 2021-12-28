package in.sachinshinde.bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 	https://leetcode.com/problems/find-mode-in-binary-search-tree/
 * 
 *  Given the root of a binary search tree (BST) with duplicates, return all the mode(s) 
 *  (i.e., the most frequently occurred element) in it.
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
	
}

