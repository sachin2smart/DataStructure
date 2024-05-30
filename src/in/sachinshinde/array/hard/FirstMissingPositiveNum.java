package in.sachinshinde.array.hard;

//	https://leetcode.com/problems/first-missing-positive/

/*
 * 	Given an unsorted integer array nums, return the smallest missing positive integer.
 * 	You must implement an algorithm that runs in O(n) time and uses constant extra space.
 */

public class FirstMissingPositiveNum {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

    	if(nums == null || n == 0) 
    		return 1;

        for(int i = 0; i<n; i++) {
            int curr = nums[i];
            while(curr-1 >= 0 && curr-1 < n && curr != nums[curr-1]) {
                int next = nums[curr-1];
                nums[curr-1] = curr;
                curr = next;
            }
        }

        for(int i = 0; i<n; i++) {
            if(nums[i] != i+1) 
            	return i+1;
        }

        return n+1;  
    }


    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;

        boolean hasOne = false;

        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }

        if(!hasOne) {
            return 1;
        }

        // replace -ve numbers by 1, also if any num bigger than size
        for(int i=0; i<n; i++) {
            if(nums[i]<=0 || nums[i]>n) {
                nums[i] = 1;
            }
        }

        for(int i=0; i<n; i++) {
            int a = Math.abs(nums[i]);
            if(a == n) {
                nums[0] = -1 * Math.abs(nums[0]);
            }
            else {
                nums[a] = -1 * Math.abs(nums[a]);
            }
        }

        for(int i=1; i<n; i++) {
            if(nums[i] > 0) {
                return i;
            }
        }

        if(nums[0] > 0) {
            return n;
        }

        return n+1;
    }

    public int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        boolean[] seen = new boolean[n+1];


        for(int num : nums){
            if(num > 0 && num<= n){
                seen[num] = true;
            }
        }

        for(int i = 1; i<= n; i++){
            if(!seen[i]){
                return i;
            }
        }
        return n+1;
    }
}

/*
	
	Input: nums = [1,2,0]
	Output: 3
	
	Input: nums = [3,4,-1,1]
	Output: 2
*/