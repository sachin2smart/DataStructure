package in.sachinshinde.array;

//  https://leetcode.ca/all/1133.html

/*
        Given an array of integers A, return the largest integer that only occurs once.
        If no integer occurs once, return -1.

        Example 1:
        ---------
        Input: [5,7,3,9,4,9,8,3,1]
        Output: 8
        Explanation:
        The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it's the answer.

        Example 2:
        ---------
        Input: [9,9,8,8]
        Output: -1
        Explanation:
        There is no number that occurs only once.


        Note:
        ---------
        1 <= A.length <= 2000
        0 <= A[i] <= 1000
 */

public class LargestUniqueNumber {
    public int largestUniqueNumber(int[] nums) {
        int[] cnt = new int[1001];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x = 1000; x >= 0; --x) {
            if (cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LargestUniqueNumber num = new LargestUniqueNumber();
        System.out.println(num.largestUniqueNumber(new int[]{5,7,3,9,4,9,8,3,1}));  // 8
        System.out.println(num.largestUniqueNumber(new int[]{9,9,8,8}));    // -1
    }
}
