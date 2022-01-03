package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/subsets/

/*
 * 	Given an integer array nums of unique elements, return all possible subsets (the power set).
 * 	The solution set must not contain duplicate subsets. Return the solution in any order.
 */

public class SubsetsWithUniqueElements {
	
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        constructSubsets(0, nums, new ArrayList<>(), subsets);
        return subsets;
    }
    
    private void constructSubsets(int start, int[] nums, List<Integer> tempList, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(tempList));
        for(int i=start; i<nums.length; i++){
            tempList.add(nums[i]);
            constructSubsets(i+1, nums, tempList, subsets);
            tempList.remove(tempList.size()-1);
        }
    }
    
}
