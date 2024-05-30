package in.sachinshinde.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 	Given an integer array nums and an integer k, 
 	you are asked to construct the array ans of size n-k+1 
 		where ans[i] is the number of distinct numbers in the subarray 
 			nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]].

	Return the array ans.

	Example 1:
	---------
		Input: nums = [1,2,3,2,2,1,3], k = 3
		Output: [3,2,2,2,3]
		Explanation: The number of distinct elements in each subarray goes as follows:
		- nums[0:2] = [1,2,3] so ans[0] = 3
		- nums[1:3] = [2,3,2] so ans[1] = 2
		- nums[2:4] = [3,2,2] so ans[2] = 2
		- nums[3:5] = [2,2,1] so ans[3] = 2
		- nums[4:6] = [2,1,3] so ans[4] = 3
	
	Example 2:
	---------
		Input: nums = [1,1,1,1,2,3,4], k = 4
		Output: [1,2,3,4]
		Explanation: The number of distinct elements in each subarray goes as follows:
		- nums[0:3] = [1,1,1,1] so ans[0] = 1
		- nums[1:4] = [1,1,1,2] so ans[1] = 2
		- nums[2:5] = [1,1,2,3] so ans[2] = 3
		- nums[3:6] = [1,2,3,4] so ans[3] = 4

 */

//	https://leetcode.ca/all/1852.html

public class DistinctNumbersInEachSubarray {
	public int[] distinctNumbers(int[] nums, int k) {
		int n = nums.length;
		int resultSize = n - k + 1;
		int[] distinctNums = new int[resultSize]; 
		
		for(int i = 0; i < resultSize; i++) {
			Set<Integer> hs = new HashSet<Integer>();
			
			// j should check for next i+k-1 elements starting from i 
			for(int j = i; j <= i+k-1 && j < n; j++)
				hs.add(nums[j]);
			
			distinctNums[i] = hs.size();
		}
		return distinctNums;
	}
	
	public static void main(String[] args) {
		DistinctNumbersInEachSubarray nums = 
				new DistinctNumbersInEachSubarray();
		System.out.println(Arrays.toString(
				nums.distinctNumbers(new int[] {1,2,3,2,2,1,3}, 3)));	//	[3, 2, 2, 2, 3]
		System.out.println(Arrays.toString(
				nums.distinctNumbers(new int[] {1,1,1,1,2,3,4}, 4)));	//	[1, 2, 3, 4]
		
		System.out.println(Arrays.toString(
				nums.distinctNumbers2(new int[] {1,2,3,2,2,1,3}, 3)));	//	[3, 2, 2, 2, 3]
		System.out.println(Arrays.toString(
				nums.distinctNumbers2(new int[] {1,1,1,1,2,3,4}, 4)));	//	[1, 2, 3, 4]
	}
	
	 public int[] distinctNumbers2(int[] nums, int k) {
        int n = nums.length;
        int[] distinctNums = new int[n - k + 1];
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < k; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        
        distinctNums[0] = map.size();
        
        for(int i = k; i < n; i++) {
        	//	decrement i-k value by 1
            map.put(nums[i - k], map.get(nums[i - k]) - 1);
            
            //	check for need to remove
            if(map.get(nums[i - k]) == 0)
                map.remove(nums[i - k]);
            
            //	add/increment i value by 1
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            
            distinctNums[i - k + 1] = map.size();
        }
        
        return distinctNums;
    }
}
