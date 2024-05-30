package in.sachinshinde.array.medium;

//  https://leetcode.com/problems/minimum-adjacent-swaps-to-make-a-valid-array/

/*
        You are given a 0-indexed integer array nums.
        Swaps of adjacent elements are able to be performed on nums.

        A valid array meets the following conditions:
            - The largest element (any of the largest elements
                if there are multiple) is at the rightmost position in the array.
            - The smallest element (any of the smallest elements
                if there are multiple) is at the leftmost position in the array.

        Return the minimum swaps required to make nums a valid array.

        Example 1:
        ----------
        Input: nums = [3,4,5,5,3,1]
        Output: 6
        Explanation: Perform the following swaps:
        - Swap 1: Swap the 3rd and 4th elements, nums is then [3,4,5,3,5,1].
        - Swap 2: Swap the 4th and 5th elements, nums is then [3,4,5,3,1,5].
        - Swap 3: Swap the 3rd and 4th elements, nums is then [3,4,5,1,3,5].
        - Swap 4: Swap the 2nd and 3rd elements, nums is then [3,4,1,5,3,5].
        - Swap 5: Swap the 1st and 2nd elements, nums is then [3,1,4,5,3,5].
        - Swap 6: Swap the 0th and 1st elements, nums is then [1,3,4,5,3,5].
        It can be shown that 6 swaps is the minimum swaps required to make a valid array.

        Example 2:
        Input: nums = [9]
        Output: 0
        Explanation: The array is already valid, so we return 0.

        Constraints:
        -----------
            1 <= nums.length <= 10^5
            1 <= nums[i] <= 10^5

 */
public class MinimumAdjacentSwapsToMakeAValidArray {

    public int minimumSwaps(int[] nums) {
        int n = nums.length;

        // example : {}  or {3}
        if(n == 0 || n == 1) {
            return 0;
        }

        // example : {2,3}
        if(n == 2 && nums[0] < nums[1]) {
            return 0;
        }

        // example : {3,2}
        if(n == 2 && nums[0] > nums[1]) {
            return 1;
        }

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        int maxValIndex = 0;
        int minValIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
                minValIndex = i;
            }
            if (nums[i] >= maxVal) {
                maxVal = nums[i];
                maxValIndex = i;
            }
        }

        int countNumSwapsForMinValue = minValIndex;
        int countNumSwapsForMaxValue = n - 1 - maxValIndex;

        // one less swap required as both min and max value gets swapped in one particular pass
        if (maxValIndex < minValIndex) {
            return countNumSwapsForMinValue + countNumSwapsForMaxValue - 1;
        }

        return countNumSwapsForMinValue + countNumSwapsForMaxValue;

    }

    public static void main(String[] args) {
        MinimumAdjacentSwapsToMakeAValidArray arr = new MinimumAdjacentSwapsToMakeAValidArray();
        System.out.println(arr.minimumSwaps(new int[] {3,4,5,3,1,1})); // 6
        System.out.println(arr.minimumSwaps(new int[] {9}));    // 0
    }

}

/*
    3,4,5,3,1,1
    3,4,3,5,1,1     : 1
    3,4,3,1,5,1     : 2
    3,4,3,1,1,5     : 3
    3,4,1,3,1,5     : 4
    3,1,4,3,1,5     : 5
    1,3,4,3,1,5     : 6
 */