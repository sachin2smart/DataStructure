package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 	Find all valid combinations of k numbers that 
 	   sum up to n such that the following conditions are true:
        	* Only numbers 1 through 9 are used.
        	* Each number can be used multiple times. (different than CombinationSum3)

        Return a list of all possible valid combinations. 
        The list must not contain the same combination twice, and 
        	the combinations may be returned in any order.
        
        Example 1:
        ---------
        Input: k = 3, n = 7
        Output: [[1, 2, 4], [1, 3, 3], [2, 2, 3]]
        
        Example 2:
        ---------
        Input: k = 3, n = 9
        Output: [[1, 2, 6], [1, 3, 5], [1, 4, 4], [1, 5, 3], [2, 2, 5], 
        	[2, 3, 4], [2, 4, 3], [3, 2, 4], [3, 3, 3], [4, 2, 3]]
        
        Example 3:
        ---------
        Input: k = 4, n = 1
        Output: []
         
        
        Constraints:
        -----------
            2 <= k <= 9
            1 <= n <= 60
 */

public class CombinationSum5 {

    public List<List<Integer>> combinationSum5(int k, int n) {
	List<List<Integer>> subsets = new ArrayList<>();
	constructSubsets(k, n, 1, new ArrayList<>(), subsets);
	return subsets;
    }
    
    private void constructSubsets(int k, int n, int start, 
	    List<Integer> currSet, List<List<Integer>> subsets) {
	if(k == currSet.size()) {
	    if(n == 0) {
		subsets.add(new ArrayList<>(currSet));
	    }
	    return;
	}
	for(int i=start; i<=9; i++) {
	    currSet.add(i);
	    constructSubsets(k, n-i, start+1, currSet, subsets);	// start from start+1 for next recursion
	    currSet.remove(currSet.size() - 1);
	}
    }

    public static void main(String[] args) {
	CombinationSum5 array = new CombinationSum5();
	System.out.println(array.combinationSum5(3, 7));	// [[1, 2, 4], [1, 3, 3], [2, 2, 3]]

	System.out.println(array.combinationSum5(3, 9));
//	[[1, 2, 6], [1, 3, 5], [1, 4, 4], [1, 5, 3], [2, 2, 5], [2, 3, 4], [2, 4, 3], [3, 2, 4], [3, 3, 3], [4, 2, 3]]
	
	System.out.println(array.combinationSum5(4, 1));	// []
    }
}
