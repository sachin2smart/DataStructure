package in.sachinshinde.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//	https://leetcode.com/problems/permutations/

/*
 * 	Given an array nums of distinct integers, return all the possible permutations. 
 * 	You can return the answer in any order.
 */

/*
        Example 1:
        ---------
        Input: nums = [1,2,3]
        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        Example 2:
        ---------
        Input: nums = [0,1]
        Output: [[0,1],[1,0]]

        Example 3:
        ---------
        Input: nums = [1]
        Output: [[1]]


        Constraints:
        ---------
            1 <= nums.length <= 6
            -10 <= nums[i] <= 10
            All the integers of nums are unique.
 */

public class Permutations {
		
	private static List<List<Integer>> subsetsWithAllPermutations(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), subsets);
        return subsets;
    }
    
    private static void backtrack(int[] nums, List<Integer> currList, List<List<Integer>> subsets) {
        // search pruning
    	if(currList.size() == nums.length) {
    		subsets.add(new ArrayList<>(currList));
    	}
    	else {
            for (int num : nums) {
                // early termination
                if (currList.contains(num)) {
                    continue; // element already exists, skip
                }
                currList.add(num);  // set
                backtrack(nums, currList, subsets);
                currList.remove(currList.size() - 1); // unset (backtrack)
            }
    	}
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {1,2,3};
        List<List<Integer>> ans = subsetsWithAllPermutations(nums);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");
    }
}
