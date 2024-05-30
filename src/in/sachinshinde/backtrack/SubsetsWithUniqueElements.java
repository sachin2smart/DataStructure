package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/subsets/

/*
 * 	Given an integer array nums of unique elements, return all possible subsets (the power set).
 * 	The solution set must not contain duplicate subsets. Return the solution in any order.

    Example 1:
    ---------
    Input: nums = [1,2,3]
    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

    Example 2:
    ---------
        Input: nums = [0]
        Output: [[],[0]]
 */


public class SubsetsWithUniqueElements {
	
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, nums, new ArrayList<>(), subsets);
        return subsets;
    }
    
    private void backtrack(int start, int[] nums, List<Integer> currList, List<List<Integer>> subsets) {
        // no search pruning, since we need to have all possible lists
        // hence, add whatever in currList into the result subsets
        subsets.add(new ArrayList<>(currList));

        for(int i=start; i<nums.length; i++) {	// starts with "start" index, not 0
            currList.add(nums[i]);  // set
            backtrack(i+1, nums, currList, subsets);
            currList.remove(currList.size()-1); // unset (backtrack)
        }
    }

    public static void main(String[] args) {
        SubsetsWithUniqueElements subsets = new SubsetsWithUniqueElements();
        System.out.println(subsets.subsets(new int[]{1,2,3}));
        // ans = [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    }
}