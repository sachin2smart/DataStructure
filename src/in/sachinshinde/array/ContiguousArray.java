package in.sachinshinde.array;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/contiguous-array/

/*
 	Given a binary array nums, return the maximum length of a contiguous subarray 
 		with an equal number of 0 and 1.

        Example 1:
        Input: nums = [0,1]
        Output: 2
        Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
        
        Example 2:
        Input: nums = [0,1,0]
        Output: 2
        Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
	Map<Integer, Integer> hm = new HashMap<>();
	hm.put(0,-1);
	int maxLength = 0, count = 0;
	for(int i=0; i<nums.length; i++) {
	    if(nums[i] == 0) 
                count = count-1;
            else
                count = count+1;
	    if(hm.containsKey(count))
		maxLength = Math.max(maxLength, i-hm.get(count));
	    else
		hm.put(count, i);
	}
	return maxLength;
    }
    
    public int findMaxLength2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int maxLength = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i=0;i<n;i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
	ContiguousArray array = new ContiguousArray();
	System.out.println(array.findMaxLength(new int[] {0,1}));	//	2
	System.out.println(array.findMaxLength(new int[] {0,1,0}));	//	2
	System.out.println(array.findMaxLength(new int[] {0,1,0,1}));	//	4
	
	System.out.println(array.findMaxLength2(new int[] {0,1}));	//	2
	System.out.println(array.findMaxLength2(new int[] {0,1,0}));	//	2
	System.out.println(array.findMaxLength2(new int[] {0,1,0,1}));	//	4
    }
}
