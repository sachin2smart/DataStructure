package in.sachinshinde.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//	https://leetcode.com/problems/flatten-nested-list-iterator/

/*
 *	
 You are given a nested list of integers nestedList. 
 Each element is either an integer or a list whose elements may also be integers or other lists. 
 Implement an iterator to flatten it.

  * Implement the NestedIterator class:
 	# NestedIterator(List<NestedInteger> nestedList) 
 		Initializes the iterator with the nested list nestedList.
	# int next() 
		Returns the next integer in the nested list.
	# boolean hasNext() 
		Returns true if there are still some integers in the nested list and false otherwise. 
 */

public class FlattenLinkedListIterator implements Iterator<Integer>  {
	
    Queue<Integer> q;
    
    public FlattenLinkedListIterator(List<NestedInteger> nestedList) {
        q=new LinkedList<Integer>();
        dfs(nestedList);
    }
    
    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger ni:nestedList)
            if(ni.isInteger()) 
                q.add(ni.getInteger());
            else 
                dfs(ni.getList());    
    }
    
    @Override
    public Integer next() {
        return q.poll(); 
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty(); 
    }
}
