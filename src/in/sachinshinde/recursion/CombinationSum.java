package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/combination-sum/

/*
 *	Given an array of distinct integers candidates and a target integer target, 
 *	Return a list of all unique combinations of candidates where the chosen numbers sum to target. 
 *	You may return the combinations in any order.
 *
 *	The same number may be chosen from candidates an unlimited number of times. 
 *	Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * 
 */

public class CombinationSum {
	
	public static List<List<Integer>> getCombinationSumWithDups(int[] nums, int target) {
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
	            tempList.add(nums[i]);
	            constructSubsets(i, nums, tempList, subsets, remain - nums[i]);
	            tempList.remove(tempList.size()-1);
	        }
        }
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {2,3,6,7};
        List<List<Integer>> ans = getCombinationSumWithDups(nums, 7);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println(" "+ tupple);
    }
}