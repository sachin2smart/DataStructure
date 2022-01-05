package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/sort-colors/

/*
 * 	Given an array nums with n objects colored red, white, or blue.
 * 	Sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

	We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
	You must solve this problem without using the library's sort function.
 */

public class SortColors {
	
	public static void sortColors(int[] nums) {
        int len = nums.length;
        
        if(len < 2)
            return;

        int left = 0;
        int right = len - 1;
        
        int current = 0;
        
        while(current <= right) {
            if(nums[current] == 0){
                swap(nums, left, current);
                left++;
                current++;
            }
            else if(nums[current] == 1) {
                current++;
            }
            else {
                swap(nums, right, current);
                right--;
            }
        }
    }
    
    private static void swap(int[] nums, int first, int second){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[] {2,0,1,0,1,2};
    	sortColors_2pass(nums);
    	
    	System.out.println(Arrays.toString(nums)); // [0, 0, 1, 1, 2, 2]
    }
    
    public static void sortColors_2pass(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) 
            	count0++;
            
            if (nums[i] == 1) 
            	count1++;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if (i < count0) 
            	nums[i] = 0;
            else if (i < count0 + count1) 
            	nums[i] = 1;
            else 
            	nums[i] = 2;
        }
    }
	
}
