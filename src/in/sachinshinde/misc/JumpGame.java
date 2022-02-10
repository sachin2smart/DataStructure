package in.sachinshinde.misc;

//	https://leetcode.com/problems/jump-game/

public class JumpGame {

	public static boolean canJump(int[] nums) {
        int maxVal = 0;

        for (int currIndex = 0; currIndex < nums.length; currIndex++) {
            if (currIndex > maxVal) 
            	return false;
            maxVal = Math.max(nums[currIndex]+currIndex, maxVal); 
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
