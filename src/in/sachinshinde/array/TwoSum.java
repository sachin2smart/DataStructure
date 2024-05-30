package in.sachinshinde.array;

import java.util.HashMap;

//	https://leetcode.com/problems/two-sum/

/*
 * 	Given an array of integers nums and an integer target, return indices of the two numbers such that 
 * 	they add up to target.
 * 	You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 	You can return the answer in any order.
 * 
 */

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
        // value to index map
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i = 0; i < nums.length; i++)
            if(hm.containsKey(target - nums[i])) {  // if the other value exists in the map
                return new int[] { hm.get(target - nums[i]), i };   // the first value gives index of other value
            }
            else {
                hm.put(nums[i], i);
            }
        
        return null;
    }
}
