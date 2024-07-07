package in.sachinshinde.array.medium;

import java.util.HashSet;
import java.util.Set;

//  https://leetcode.com/discuss/interview-question/1435821/Robinhood-or-OA-or-longestSubarrayCheck

/*
        You are given two integer arrays a, b and one array of distinct integers c.

        Your task is to check whether b is the longest contiguous subarray of a consisting only of elements from c, i.e.
            -   Each of the elements of b belong to c,
            -   a contains b as a contiguous subarray,
            -   b is the longest of the contiguous subarrays of a which satisfy the first two conditions.
        Return true if all the above conditions are met, and false otherwise.

        NOTE: If there is a tie for the longest contiguous subarrays of a consisting of elements from c,
            the answer is still considered true if b is one of these longest contiguous subarrays.

        Example 1:
        ---------
        For a = [1, 1, 5, 1, 2], b = [1, 2], and c = [2, 1], the output should be
        longestSubarrayCheck(a, b, c) = true.

        All three conditions are met:
            All of the elements of b belong to c,
            a contains b as a contiguous subarray (a[3..4] = b),
            b is the longest of these contiguous subarrays.

            To prove this, let's look at all the contiguous subarrays of length greater than 2:
                a[0..2] = [1, 1, 5] contains 5, which doesn't belong to c.
                a[0..3] = [1, 1, 5, 1] contains 5, which doesn't belong to c.
                a[0..4] = [1, 1, 5, 1, 2] contains 5, which doesn't belong to c.
                a[1..3] = [1, 5, 1] contains 5, which doesn't belong to c.
                a[1..4] = [1, 5, 1, 2] contains 5, which doesn't belong to c.
                a[2..4] = [5, 1, 2] contains 5, which doesn't belong to c.
           Therefore b is the longest contiguous subarray of a consisting only of elements from c,
            so the answer is true.

        Example 2:
        ---------
        For a = [1, 2, 3, 6, 1, 1, 1], b = [1, 2, 3], and c = [2, 1], the output should be
        longestSubarrayCheck(a, b, c) = false.

        Although b is a contiguous subarray of a, it contains the element b[2] = 3 which does not appear in c, therefore it does not meet the conditions. So the answer is false.

        Example 3:
        ---------
        For a = [1, 2, 2, 3, 2, 1, 3], b = [3, 2, 1, 3], and c = [2, 1, 3], the output should be
        longestSubarrayCheck(a, b, c) = false.

        All of the elements of a belong to c, and b.length < a.length, so b couldn't possibly be the longest contiguous subarray consisting of elements from c. So, the answer is false.

------------------------------------------------------------------------------------
        Input/Output

        [execution time limit] 3 seconds (java)

        [1]     [input] array.integer a :   An array of integers.
                ----------------------------------------------------------
                  Guaranteed constraints:
                    1 ≤ a.length ≤ 10^5,
                    0 ≤ a[i] ≤ 10^9.

        [2]     [input] array.integer b :   An array of integers.
                ----------------------------------------------------------
                Guaranteed constraints:
                    1 ≤ b.length ≤ 10^5,
                    0 ≤ b[i] ≤ 10^9.

        [3]     [input] array.integer c :   An array of distinct integers.
                ----------------------------------------------------------
                Guaranteed constraints:
                    1 ≤ c.length ≤ 10^5,
                    0 ≤ c[i] ≤ 10^9.
----------------------------------------------------------------------------------------------------
        [output] boolean

        Return true if b is the longest contiguous subarray of a consisting of elements from c,
            otherwise return false
----------------------------------------------------------------------------------------------------
 */

public class CheckLongestSubArray {

    public boolean isLongestSubArray(int[] a, int[] b, int[] c) {

        //  add elements of c to a Set; so we can check whether b contains any elements that aren't in c
        Set<Integer> elements = new HashSet<>();
        for (int k : c) {
            elements.add(k);
        }

        // if anything in b is not in c then no more processing needed, we return false
        for (int k : b) {
            if (!elements.contains(k)) {
                return false;
            }
        }

        //  find the max length contiguous subarray that contains elements in c
        int left = 0;
        int right = 0;
        int maxLength = 0;
        while (right < a.length) {
            if (!elements.contains(a[right])) {
                left = right + 1;
            }
            else {
                maxLength = Math.max(maxLength, right - left + 1);
                if (maxLength > b.length) {
                    return false;
                }
            }
            right++;
        }

        //  check whether b is in a
        int i = 0;
        int j = 0;
        int count = 0;
        while (j < a.length) {
            if (a[j] == b[i]) {
                count++;
                i++;
            }
            else {
                count = 0;
                i = 0;
            }
            j++;
            if (count == b.length) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckLongestSubArray subArray = new CheckLongestSubArray();
        System.out.println(subArray.isLongestSubArray(new int[] {1, 1, 5, 1, 2},
                new int[] {1, 2}, new int[] {2, 1}));   // true

        System.out.println(subArray.isLongestSubArray(new int[] {1, 2, 3, 6, 1, 1, 1},
                new int[] {1, 2, 3}, new int[] {2, 1}));   // false

        System.out.println(subArray.isLongestSubArray(new int[] {1, 2, 2, 3, 2, 1, 3},
                new int[] {3, 2, 1, 3}, new int[] {2, 1, 3}));   // false
    }
}
