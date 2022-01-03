package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//	https://leetcode.com/problems/permutations/

/*
 * 	Given an array nums of distinct integers, return all the possible permutations. 
 * 	You can return the answer in any order.
 */

public class Permutations {
		
	private static List<List<Integer>> subsetsWithAllPermutations(int[] nums, int n) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        constructSubsets(0, nums, n, new ArrayList<>(), subsets);
        return subsets;
    }
    
    private static void constructSubsets(int start, int[] nums, int n, List<Integer> tempList, List<List<Integer>> subsets) {
    	if(tempList.size() == n) {
    		subsets.add(new ArrayList<>(tempList));
    	}
    	else {
	        for(int i=0; i<n; i++){		// starts with zero
	        	if(tempList.contains(nums[i])) 
	        		continue; // element already exists, skip
	            tempList.add(nums[i]);
	            constructSubsets(i+1, nums, n, tempList, subsets);
	            tempList.remove(tempList.size()-1);
	        }
    	}
    }
    
    public static void main(String args[]) {
        int[] nums = new int[] {1,2,3};
        List<List<Integer>> ans = subsetsWithAllPermutations(nums, nums.length);
        
        System.out.println("The subsets with all permutations ");
        for(List<Integer> tupple: ans)
            System.out.println("[ "+tupple.get(0)+" , "+tupple.get(1)+" , "+tupple.get(2)+" ] ");
    }
}
