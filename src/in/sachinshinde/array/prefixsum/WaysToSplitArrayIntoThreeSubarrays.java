package in.sachinshinde.array.prefixsum;

//  https://leetcode.com/problems/ways-to-split-array-into-three-subarrays

/*
        A split of an integer array is good if:

        The array is split into three non-empty contiguous subarrays - named
            left, mid, right respectively from left to right.
        The sum of the elements in left is less than or equal to the sum of the elements in mid, and
            the sum of the elements in mid is less than or equal to the sum of the elements in right.

        Given nums, an array of non-negative integers, return the number of good ways to split nums.
        As the number may be too large, return it modulo 109 + 7.

        Example 1:
        ---------
        Input: nums = [1,1,1]
        Output: 1
        Explanation: The only good way to split nums is [1] [1] [1].

        Example 2:
        ---------
        Input: nums = [1,2,2,2,5,0]
        Output: 3
        Explanation: There are three good ways of splitting nums:
        [1] [2] [2,2,5,0]
        [1] [2,2] [2,5,0]
        [1,2] [2,2] [5,0]

        Example 3:
        ---------
        Input: nums = [3,2,1]
        Output: 0
        Explanation: There is no good way to split nums.

        Constraints:
        -----------
            3 <= nums.length <= 10^5
            0 <= nums[i] <= 10^4
 */

public class WaysToSplitArrayIntoThreeSubarrays {

    private static final int MOD = (int) (1e9 + 7);

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n]; // prefix array
        arr[0] = nums[0];
        int res = 0;

        for (int i = 1; i < n; ++i) {
            arr[i] = arr[i - 1] + nums[i];
        }

        for (int i = 1; i < n - 1; ++i) {
            if (arr[i - 1] <= (arr[n - 1] - arr[i - 1]) / 2) {
                int left = binarySearch(arr, arr[i - 1], i, true);
                int right = binarySearch(arr, arr[i - 1], i, false);
                if (left != -1 || right != -1) {
                    res = (res + (right - left + 1) % MOD) % MOD;
                }
            }
        }

        return res;
    }

    private int binarySearch(int[] arr, int leftSum, int curri, boolean isToSearchInLeft) {
        int n = arr.length;
        int l = curri, r = n - 2;
        int res = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midSum = arr[mid] - arr[curri - 1];
            int rightSum = arr[n - 1] - arr[mid];

            if (leftSum <= midSum && midSum <= rightSum) {
                res = mid;
                if (isToSearchInLeft) {
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
            else if (leftSum > midSum) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        WaysToSplitArrayIntoThreeSubarrays ways = new WaysToSplitArrayIntoThreeSubarrays();
        System.out.println(ways.waysToSplit(new int[]{1,1,1}));         //  1
        System.out.println(ways.waysToSplit(new int[]{1,2,2,2,5,0}));   //  3
        System.out.println(ways.waysToSplit(new int[]{3,2,1}));         //  0

        System.out.println(ways.waysToSplit2(new int[]{1,1,1}));         //  1
        System.out.println(ways.waysToSplit2(new int[]{1,2,2,2,5,0}));   //  3
        System.out.println(ways.waysToSplit2(new int[]{3,2,1}));         //  0
    }

    public int waysToSplit2(int[] nums) {
        int ways = 0;
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        int i = 0, j = 0, k = 0;

        while (i < n - 2 && 3 * nums[i] <= nums[n - 1]) {
            j = Math.max(j, i + 1);
            while (j < n - 1 && nums[j] - nums[i] < nums[i]) {
                j++;
            }
            k = Math.max(k, j);
            while (k < n - 1 && nums[k] - nums[i] <= nums[n - 1] - nums[k]) {
                k++;
            }
            ways = (ways + k - j) % MOD;
            i++;
        }
        return ways;
    }
}
