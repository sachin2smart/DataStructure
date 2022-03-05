package in.sachinshinde.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

//	https://leetcode.com/problems/next-greater-element-ii

/*
	Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), 
	return the next greater number for every element in nums.

	The next greater number of a number x is the first greater number to its traversing-order next in the array, 
	which means you could search circularly to find its next greater number. 
	If it doesn't exist, return -1 for this number.
 */

public class NextGreaterElement2 {

	public int[] nextGreaterElements(int[] nums) {
		int[] res = new int[nums.length];
		
		//	stack with initial value as 0
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(0);
        
        //	set all results in arr to -1
        // 	-1 is default for values which does not have greater value 
        Arrays.fill(res, -1);
            
        //	why we need isReset? 
        //	we want to iterate through array twice to make sure 
        //	that each value get a chance to find it's grater value
        boolean isReset = false;
        
        for(int i=1; i<=nums.length; i++){
            //	if we reached end of the array and stack is not empty 
            //	it means not all values get a chance to find its grater value
            if(i == nums.length && !stack.isEmpty() && !isReset){
              isReset = true;	//	note: we don't want to iterate more than twice even if stack is not empty
              i = 0; 
            }
	  
            //	here we use monotonically decreasing stack 
            while(i < nums.length && !stack.isEmpty() && nums[i] > nums[stack.peek()]){
                int curr = stack.pop();
                res[curr] = nums[i];
            }
        	
            if(i < nums.length && !isReset) 
            	stack.push(i);
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,2,1};
		NextGreaterElement2 nextGreaterElement2 = new NextGreaterElement2();
		System.out.println(Arrays.toString(nextGreaterElement2.nextGreaterElements(nums)));	//	2,3,-1,3,2
		System.out.println(Arrays.toString(nextGreaterElement2.nextGreaterElements2(nums))); //	2,3,-1,3,2
		
	}
	
	public int[] nextGreaterElements2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }
	
}

/*
	Example 1:
	Input: nums = [1,2,1]
	Output: [2,-1,2]
	Explanation: The first 1's next greater number is 2; 
	The number 2 can't find next greater number. 
	The second 1's next greater number needs to search circularly, which is also 2.
	
	Example 2:
	Input: nums = [1,2,3,4,3]
	Output: [2,3,4,-1,4]
*/