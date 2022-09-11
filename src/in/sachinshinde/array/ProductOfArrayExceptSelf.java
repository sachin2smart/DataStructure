package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/product-of-array-except-self/

/*
 	Given an integer array nums, return an array answer such that 
 	answer[i] is equal to the product of all the elements of nums except nums[i].
	
	The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
	You must write an algorithm that runs in O(n) time and without using the division operation.
	
	Example 1:
		Input: nums = [1,2,3,4]
		Output: [24,12,8,6]
	
	Example 2:
		Input: nums = [-1,1,0,-3,3]
		Output: [0,0,9,0,0]
	 
	Constraints:
		2 <= nums.length <= 105
		-30 <= nums[i] <= 30
	
	The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class ProductOfArrayExceptSelf {
	//	Time Complexity : O(n), Space Complexity : O(1)
	public int[] productExceptSelf1(int[] nums) {
    	int n = nums.length;
    	int[] res = new int[n];
    	int right=1,left=1;
        for(int i=0;i<n;i++){
            res[i]=1;
        }
    	for (int i=0;i<n;i++) {
    		res[i]*=left;
    		left*=nums[i];
    	}
    	for(int i=n-1;i>=0;i--) {
    		res[i]*=right;
    		right*=nums[i];
    	}
    	return res;
    }
	
	/*
	 	Given numbers [2, 3, 4, 5], regarding the third number 4, the product of array except 4 is 2*3*5 
	 		which consists of two parts: left 2*3 and right 5. The product is left*right. 
	 		
	 	We can get lefts and rights:

		Numbers:     2    3    4     5
		Lefts:            2  2*3 2*3*4
		Rights:  3*4*5  4*5    5      
		
		Let’s fill the empty with 1:

		Numbers:     2    3    4     5
		Lefts:       1    2  2*3 2*3*4
		Rights:  3*4*5  4*5    5     1
		
		We can calculate lefts and rights in 2 loops. The time complexity is O(n).

		We store lefts in result array. If we allocate a new array for rights. The space complexity is O(n). 
	 */
	
	public int[] productExceptSelf2(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		
		int left = 1;
		for(int i=0; i<n ; i++) {
			if(i>0)
				left *= nums[i-1];
			res[i] = left;
		}
		
		int right = 1;
		for(int i=n-1; i>=0; i--) {
			if(i<n-1)
				right *= nums[i+1];
			res[i] *= right;
		}
		
		return res;
	}
	
	//	Time Complexity : O(n), Space Complexity : O(2)
	public int[] productExceptSelf3(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = 1;
        }
        int left = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] *= left;
            left *= nums[i];
        }
        int right = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
	
	public static void main(String[] args) {
		ProductOfArrayExceptSelf p = new ProductOfArrayExceptSelf();
		System.out.println(Arrays.toString(p.productExceptSelf1(
				new int[] {1,2,3,4})));	//	[24,12,8,6]
		System.out.println(Arrays.toString(p.productExceptSelf1(
				new int[] {-1,1,0,-3,3})));	//	[0,0,9,0,0]
		
		System.out.println(Arrays.toString(p.productExceptSelf2(
				new int[] {1,2,3,4})));	//	[24,12,8,6]
		System.out.println(Arrays.toString(p.productExceptSelf2(
				new int[] {-1,1,0,-3,3})));	//	[0,0,9,0,0]
		
		System.out.println(Arrays.toString(p.productExceptSelf3(
				new int[] {1,2,3,4})));	//	[24,12,8,6]
		System.out.println(Arrays.toString(p.productExceptSelf3(
				new int[] {-1,1,0,-3,3})));	//	[0,0,9,0,0]
	}
}
