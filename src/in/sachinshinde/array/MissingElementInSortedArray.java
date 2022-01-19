package in.sachinshinde.array;

//	https://leetcode.ca/all/1060.html

/*
 *	Given a sorted array A of unique numbers.
 *	Find the K-th missing number starting from the leftmost number of the array.
 *
	Example 1:
	---------
	Input: A = [4,7,9,10], K = 1
	Output: 5
	Explanation: 
	The first missing number is 5.
	
	Example 2:
	---------
	Input: A = [4,7,9,10], K = 3
	Output: 8
	Explanation: 
	The missing numbers are [5,6,8,...], hence the third missing number is 8.
	
	Example 3:
	---------
	Input: A = [1,2,4], K = 3
	Output: 6
	Explanation: 
	The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 */

public class MissingElementInSortedArray {
	
	public static int missingElement(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return k;
        
        int length = nums.length;
        
        if(k>=nums[length-1])
            return nums[0] + length-1 + k;
        
        int iVal = nums[0];
        int idx = 1;
        
        while(idx<length && k>0) {
        	iVal++;
            if(nums[idx]==iVal)
                idx++;
            else
                k--;
        }
        
        return iVal + k;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {4,7,9,10}; 
		int k = 1;
		System.out.println(missingElement(nums, k)); //	5
		
		nums = new int[] {4,7,9,10}; 
		k = 3;
		System.out.println(missingElement(nums, k)); //	8
		
		nums = new int[] {1,2,4}; 
		k = 3;
		System.out.println(missingElement(nums, k)); //	6
	}
}
