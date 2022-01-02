package in.sachinshinde.array;

// https://leetcode.com/problems/max-consecutive-ones/
	
public class MaxConsecutiveOnes {
	private static int maxtConsecutiveOnes(int[] nums) {
		int cnt = 0;
        int maxi = 0;
        for(int i = 0;i<nums.length;i++) {
        	cnt = nums[i] == 1 ? cnt+1: 0; 
            maxi = Math.max(maxi, cnt); 
        }
        return maxi; 
	}
}
