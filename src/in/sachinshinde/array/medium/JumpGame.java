package in.sachinshinde.array.medium;

//	https://leetcode.com/problems/jump-game/

/*
		You are given an integer array nums. You are initially positioned at the array's first index, and
		 	each element in the array represents your maximum jump length at that position.

		Return true if you can reach the last index, or false otherwise.

		Example 1:
		---------
		Input: nums = [2,3,1,1,4]
		Output: true
		Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

		Example 2:
		---------
		Input: nums = [3,2,1,0,4]
		Output: false
		Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
		which makes it impossible to reach the last index.

		Constraints:
		-----------
			1 <= nums.length <= 104
			0 <= nums[i] <= 105
 */

public class JumpGame {

	public boolean canJumpGoodOne(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}

	public static boolean canJump(int[] nums) {
        int lastMaxJumpPos = 0;

        for (int currIndex = 0; currIndex < nums.length; currIndex++) {
            if (currIndex > lastMaxJumpPos) { // it means with previous max value we can't jump to current index
				return false;
			}
			lastMaxJumpPos = Math.max(nums[currIndex]+currIndex, lastMaxJumpPos);
        }
        
        return true; 
	}
	
	public static void main(String[] args) {
		System.out.println(canJump(new int[] {2,4,4,4,8}));	//	true
		System.out.println(canJump(new int[] {3,2,1,0,4}));	//	false
	}
}

/* 
 * 	2,3,1,1,4
 * 		i=0 max=0	i=max	max = MAX(2+0,0) = 2
 * 		i=1 max=2   i<max   max = MAX(3+1,2) = 4
 * 		i=2 max=4	i=max	max = MAX(1+2,4) = 4
 * 		i=3 max=4   i<max   max = MAX(1+3,4) = 4
 * 		i=4 max=4   i=max	max	= MAX(4+4,4) = 8
 * 	==> TRUE
 * 
 * 	3,2,1,0,4
 * 		i=0 max=0	i=max	max = MAX(3+0,0) = 3
 * 		i=1 max=3  	i<max   max = MAX(2+1,3) = 3
 * 		i=2 max=3	i<max	max = MAX(1+2,3) = 3
 * 		i=3 max=3  	i=max   max = MAX(0+3,3) = 3
 * 		i=4 max=3  	i>max	
 * ==> FALSE 
 */
