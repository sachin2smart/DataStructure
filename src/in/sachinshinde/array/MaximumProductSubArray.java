package in.sachinshinde.array;

//	https://leetcode.com/problems/maximum-product-subarray/

/*
 	Given an integer array nums, find a contiguous non-empty subarray within the array 
 		that has the largest product, and return the product.
	A subarray is a contiguous subsequence of the array.
	
	 
	
	Example 1:
	---------
		Input: nums = [2,3,-2,4]
		Output: 6
		Explanation: [2,3] has the largest product 6.
	
	Example 2:
	---------
		Input: nums = [-2,0,-1]
		Output: 0
		Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

public class MaximumProductSubArray {
	public int maxProduct(int[] nums) {
		int n = nums.length, l = 0, r = 0;
		int res = nums[0];
		
        for (int i = 0; i < n; i++) {
            l =  (l == 0 ? 1 : l) * nums[i];	//	calculate prefix product
            r =  (r == 0 ? 1 : r) * nums[n - 1 - i];	//	calculate suffix product 
            res = Math.max(res, Math.max(l, r));
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		MaximumProductSubArray sum = new MaximumProductSubArray();
		System.out.println(sum.maxProduct(new int[] {2,3,-2,4}));	//	6
		System.out.println(sum.maxProduct(new int[] {-2,0,-1}));	//	0
		
		System.out.println(sum.maxProduct2(new int[] {2,3,-2,4}));	//	6
		System.out.println(sum.maxProduct2(new int[] {-2,0,-1}));	//	0
	}
	
	public int maxProduct2(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        
        int max = nums[0], min = nums[0], result = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            int prevMax = max;
            
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(prevMax * nums[i], min * nums[i]));
            
            if(max > result)
                result = max;
        }
        
        return result;
    }
}
