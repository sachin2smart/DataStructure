package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 	Given an array of distinct integers nums and a target integer target, 
 	return the number of possible combinations that add up to target.
        
        The test cases are generated so that the answer can fit in a 32-bit integer.
        
         
        
        Example 1:
        ---------
        Input: nums = [1,2,3], target = 4
        Output: 7
        Explanation:
            The possible combination ways are:
            (1, 1, 1, 1)
            (1, 1, 2)
            (1, 2, 1)
            (1, 3)
            (2, 1, 1)
            (2, 2)
            (3, 1)
            Note that different sequences are counted as different combinations.
            
        Example 2:
        ---------
            Input: nums = [9], target = 3
            Output: 0
         
        
        Constraints:
        -----------
            1 <= nums.length <= 200
            1 <= nums[i] <= 1000
            All the elements of nums are unique.
            1 <= target <= 1000
         
        
        Follow up: 
        	What if negative numbers are allowed in the given array? 
        	How does it change the problem? 
        	What limitation we need to add to the question to allow negative numbers?
 */

public class CombinationSum4 {

    //	Method 1: Recursion : This give "Memory Limit Exceeded" if for example 1 is there and target is > 50
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> subsets = new ArrayList<>();
        constructSubsets(0, nums, target, subsets, new ArrayList<>());
        return subsets.size();
    }
    
    private void constructSubsets(int start, int[] candidates, int target, List<List<Integer>> subsets, List<Integer> currSumArray) {
        if(start == candidates.length) {
            if(target == 0) {
                subsets.add(new ArrayList<>(currSumArray));
            }
            return;
        }
        
        if(candidates[start] <= target) {
            currSumArray.add(candidates[start]);
            constructSubsets(0, candidates, target - candidates[start], subsets, currSumArray);
            currSumArray.remove(currSumArray.size() - 1);
        }
        constructSubsets(start + 1, candidates, target, subsets, currSumArray);
    }
    
    // Method 2: Recursion : This gives "Time Limit Exceeded"
    int res;
    public int combinationSum4_2(int[] nums, int target) {
        res = 0;
        constructSubsets(0, nums, target, new ArrayList<>());
        return res;
    }
    
    private void constructSubsets(int start, int[] candidates, int target, List<Integer> currSumArray) {
        if(start == candidates.length) {
            if(target == 0) {
                res++;
            }
            return;
        }
        
        if(candidates[start] <= target) {
            currSumArray.add(candidates[start]);
            constructSubsets(0, candidates, target - candidates[start], currSumArray);
            currSumArray.remove(currSumArray.size() - 1);
        }
        constructSubsets(start + 1, candidates, target, currSumArray);
    }
    
    // Method 3: Recursion :
    public int combinationSum4_3(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }
    
    // Method 4 : Using Memoization (Recursive) : Bottom Up approach : Works Fine
    public int combinationSum4_4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return topDownMemoization(nums, target, memo);
    }
    
    private int topDownMemoization(int[] nums, int target, int[] memo) {
        if(memo[target] != -1)
            return memo[target];
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += topDownMemoization(nums, target - nums[i], memo);
            }
        }
        memo[target] = res;
        return res;
    }
    
    
    // Method 5: Using Memoization (Non-Recursive) : Top Down Approach : Works fine
    public int combinationSum4_5(int[] nums, int target) {
	int[] memo = new int[target + 1];
	memo[0] = 1;
	for (int i = 1; i < memo.length; i++) {
	    for (int j = 0; j < nums.length; j++) {
		if (i - nums[j] >= 0) {
		    memo[i] += memo[i - nums[j]];
		}
	    }
	}
	return memo[target];
    }
    
    
    public static void main(String[] args) {
	CombinationSum4 array = new CombinationSum4();
	
	System.out.println(array.combinationSum4(new int[] {1,2,3}, 4));	// 7
	System.out.println(array.combinationSum4(new int[] {9}, 3));		// 0
	
	System.out.println(array.combinationSum4_2(new int[] {1,2,3}, 4));	// 7
	System.out.println(array.combinationSum4_2(new int[] {9}, 3));		// 0
	
	System.out.println(array.combinationSum4_3(new int[] {1,2,3}, 4));	// 7
	System.out.println(array.combinationSum4_3(new int[] {9}, 3));		// 0
	
	System.out.println(array.combinationSum4_4(new int[] {1,2,3}, 4));	// 7
	System.out.println(array.combinationSum4_4(new int[] {9}, 3));		// 0
	
	System.out.println(array.combinationSum4_5(new int[] {1,2,3}, 4));	// 7
	System.out.println(array.combinationSum4_5(new int[] {9}, 3));		// 0
    }
}
