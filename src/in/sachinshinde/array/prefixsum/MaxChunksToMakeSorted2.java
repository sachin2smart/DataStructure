package in.sachinshinde.array.prefixsum;

//  https://leetcode.com/problems/max-chunks-to-make-sorted-ii/

/*
        You are given an integer array arr.

        We split arr into some number of chunks (i.e., partitions), and individually sort each chunk.
        After concatenating them, the result should equal the sorted array.

        Return the largest number of chunks we can make to sort the array.

        Example 1:
        ---------
        Input: arr = [5,4,3,2,1]
        Output: 1
        Explanation:
        Splitting into two or more chunks will not return the required result.
        For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.

        Example 2:
        ---------
        Input: arr = [2,1,3,4,4]
        Output: 4
        Explanation:
        We can split into two chunks, such as [2, 1], [3, 4, 4].
        However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.

        Constraints:
        -----------
            1 <= arr.length <= 2000
            0 <= arr[i] <= 108
 */

public class MaxChunksToMakeSorted2 {

    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i - 1], arr[i]);
        }

        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1]) {
                res++;
            }
        }

        return res + 1;
    }

    public static void main(String[] args) {
        MaxChunksToMakeSorted2 chunks = new MaxChunksToMakeSorted2();
        System.out.println(chunks.maxChunksToSorted(new int[]{5,4,3,2,1}));     //  1
        System.out.println(chunks.maxChunksToSorted(new int[]{2,1,3,4,4}));     //  4
    }
}
