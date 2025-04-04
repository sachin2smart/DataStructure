package in.sachinshinde.array.binarysearch;

//  https://leetcode.com/problems/search-in-rotated-sorted-array/

/*
        There is an integer array nums sorted in ascending order (with distinct values).

        Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k
            (1 <= k < nums.length) such that the resulting array is
                [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).

       For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

        Given the array nums after the possible rotation and an integer target,
        return the index of target if it is in nums, or -1 if it is not in nums.

        You must write an algorithm with O(log n) runtime complexity.

        Example 1:
        ---------
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4

        Example 2:
        ---------
        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1

        Example 3:
        ---------
        Input: nums = [1], target = 0
        Output: -1

        Constraints:
        ------------
            1 <= nums.length <= 5000
            -104 <= nums[i] <= 104
            All values of nums are unique.
            nums is an ascending array that is possibly rotated.
            -104 <= target <= 104
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                if(target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return nums[low] == target ? low : -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray arr = new SearchInRotatedSortedArray();
        System.out.println(arr.search(new int[]{4,5,6,7,0,1,2}, 0));    // 4
        System.out.println(arr.search2(new int[]{4,5,6,7,0,1,2}, 0));    // 4
    }

    /*
            Binary Search can be applied on a sorted array
            In each iteration determine where to navigate depending on the values
     */
    public int search2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[low] <= nums[mid]) {  //  consider mid as high for binary search
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {  //  consider mid as low for binary search
                if(target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
