package in.sachinshinde.array.twopointer;

//  https://leetcode.com/problems/sliding-window-maximum/

/*
    You are given an array of integers nums,
        there is a sliding window of size k which is moving from the very left of the array to the very right.
        You can only see the k numbers in the window.
        Each time the sliding window moves right by one position.

    Return the max sliding window.



    Example 1:
    ---------
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    Explanation:
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

    Example 2:
    ---------
    Input: nums = [1], k = 1
    Output: [1]


    Constraints:
    -----------
        1 <= nums.length <= 105
        -104 <= nums[i] <= 104
        1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        int[] ans = new int[n - k + 1];

        // Fill left
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i % k == 0) {
                max = Integer.MIN_VALUE;
            }
            if (nums[i] > max) {
                max = nums[i];
            }
            left[i] = max;
        }

        // Fill right
        max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (i % k == 0) {
                max = Integer.MIN_VALUE;
            }
            if (nums[i] > max) {
                max = nums[i];
            }
            right[i] = max;
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.max(left[i + k - 1], right[i]);
        }

        return ans;
    }
}
