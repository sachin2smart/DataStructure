package in.sachinshinde.array;

import java.util.LinkedList;
import java.util.Queue;

//	https://leetcode.ca/2017-03-31-487-Max-Consecutive-Ones-II/

/*
 	Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

	 Example 1:
	 ---------
		 Input: [1,0,1,1,0]
		 Output: 4
		 Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
		 		After flipping, the maximum number of consecutive 1s is 4.
	
	 Note:
		 The input array will only contain 0 and 1.
		 The length of input array is a positive integer and will not exceed 10,000
	
	 Follow up:
	 ---------
	 	What if the input numbers come in one by one as an infinite stream? 
	 	In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. 
	 	Could you solve it efficiently?

 */
public class MaxConsuecutiveOnes2 {
	/*
	 	Solution:
	 		A window [left,right] can be maintained to hold at least k zeros.
	 		When it encounters 0, it accumulates the number of zero, 
	 			and then judges if the number of 0 is greater than k at this time, 
	 			then shifts the left boundary left to the right, 
	 				and if the removed nums[left] is 0, then zero decrements by 1.
	 		If it is not greater than k, use the number of digits in the window to update result.
	 */
	//	this is good solution, if elements are less
	public int findMaxConsecutiveOnes(int[] nums) {
        int maxWindowSize = 0, numsZeros = 0, left = 0, k = 1;

        for(int right = 0; right < nums.length; ++right) {
            if(nums[right] == 0) 
            	++numsZeros;
            
            while(numsZeros > k)
                if(nums[left++] == 0)
                	--numsZeros;
            
            int currWindowSize = right - left + 1;
            maxWindowSize = Math.max(maxWindowSize, currWindowSize);
        }

        return maxWindowSize;
    }
	
	public static void main(String[] args) {
		MaxConsuecutiveOnes2 maxConsuecutiveOnes = 
				new MaxConsuecutiveOnes2();
		
		System.out.println(maxConsuecutiveOnes.findMaxConsecutiveOnes(new int[] {1,0,1,1,0})); //	4
		System.out.println(maxConsuecutiveOnes.findMaxConsecutiveOnes_forLargeData(new int[] {1,0,1,1,0})); //	4
		System.out.println(maxConsuecutiveOnes.findMaxConsecutiveOnes2(new int[] {1,0,1,1,0})); //	4
	}
	
	public int findMaxConsecutiveOnes_forLargeData(int[] nums) {
        int maxOnes = 0, left = 0, k = 1;

        //	Using Queue can helps us to hold elements of size 10,000
        Queue<Integer> q = new LinkedList<>();

        for(int right = 0; right < nums.length; ++right) {
            if(nums[right] == 0) 
            	q.offer(right);
            
            //	shift left pointer to it's next when the qSize exceeds k
            if(q.size() > k)
                left = q.poll() + 1;
            
            maxOnes = Math.max(maxOnes, right - left + 1);
        }
        
        return maxOnes;

    }
	
    public int findMaxConsecutiveOnes2(int[] nums) {
        int totalNums = nums.length;
        int previousWindowSize = 0;
        int maxSoFar = 1;
        int index = 0;
        
        while(index < totalNums) {
            if(nums[index] == 1) {
                int currentWindowSize = 0;
                
                //	increase the window size till we have element as 1
                while(index < totalNums && nums[index++] == 1)
                    currentWindowSize++;
                
                int sumHere = currentWindowSize + previousWindowSize;
                
                if(currentWindowSize != index || index < totalNums) 
                	++sumHere;
                
                maxSoFar = Math.max(maxSoFar, sumHere);
                previousWindowSize = currentWindowSize;
            }
            else {
                previousWindowSize = 0;
                index++;
            }
        }
        return maxSoFar;
    }
}
