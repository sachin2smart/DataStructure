package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/combination-sum-ii/

/*
 *	Given a collection of candidate numbers (candidates) and a target number (target), 
 *	Find all unique combinations in candidates where the candidate numbers sum to target.
 *
 *	Each number in candidates may only be used once in the combination.
 *	
 *	Note: The solution set must not contain duplicate combinations. 
 * 
 */

public class CombinationSumNoDups {
	
	public static List<List<Integer>> getCombinationSumNoDups(int[] nums, int target) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        constructSubsets(0, nums, new ArrayList<>(), subsets, target);
        return subsets;
    }
    
    private static void constructSubsets(int start, int[] nums, List<Integer> tempList, List<List<Integer>> subsets, int remain) {
        if(remain < 0)
        	return;
        else if(remain == 0) {
        	subsets.add(new ArrayList<>(tempList));
        }
        else {
	        for(int i=start; i<nums.length; i++){	// starts with "start" index
	        	if(i > start && nums[i] == nums[i-1]) 
	        		continue; // skip duplicates
	            tempList.add(nums[i]);
	            constructSubsets(i+1, nums, tempList, subsets, remain - nums[i]);
	            tempList.remove(tempList.size()-1);
	        }
        }
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {2,5,2,1,2};
        List<List<Integer>> ans = getCombinationSumNoDups(nums, 5);
        
        System.out.println("The subsets with sum 5 :");
        for(List<Integer> tupple: ans)
            System.out.println(" "+ tupple);
    }
    
}
