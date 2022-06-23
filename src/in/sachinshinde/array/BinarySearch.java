package in.sachinshinde.array;

//	https://leetcode.com/problems/binary-search/

/*
 	Given an array of integers nums which is sorted in ascending order, and an integer target,
 	 write a function to search target in nums. 
 	 If target exists, then return its index. Otherwise, return -1.

	You must write an algorithm with O(log n) runtime complexity.
 */

public class BinarySearch {

	public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
	
	public static void main(String[] args) {
		BinarySearch binarySearch = new BinarySearch();
		System.out.println(binarySearch.search(new int[] {-1,0,3,5,9,12}, 9));	//	4
	}
}
