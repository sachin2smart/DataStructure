package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/subsets-ii/

/*
 * 	Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * 	The solution set must not contain duplicate subsets. Return the solution in any order.
 */

public class SubsetsWithDuplicateElements {
	public List<List<Integer>> subsetsWithDups(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        constructSubsets(0, nums, new ArrayList<>(), subsets);
        return subsets;
    }
    
    private void constructSubsets(int start, int[] nums, List<Integer> tempList, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(tempList));
        for(int i=start; i<nums.length; i++){
        	if(i > start && nums[i] == nums[i-1])	// skipping the duplicates
        		continue;
            tempList.add(nums[i]);
            constructSubsets(i+1, nums, tempList, subsets);
            tempList.remove(tempList.size()-1);
        }
    }
}
