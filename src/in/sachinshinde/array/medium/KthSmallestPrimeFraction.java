package in.sachinshinde.array.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

//  https://leetcode.com/problems/k-th-smallest-prime-fraction/

/*
        You are given a sorted integer array arr containing 1 and prime numbers,
            where all the integers of arr are unique. You are also given an integer k.

        For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

        Return the kth smallest fraction considered. Return your answer as an array of integers of size 2,
            where answer[0] == arr[i] and answer[1] == arr[j].

        Example 1:
        ---------
        Input: arr = [1,2,3,5], k = 3
        Output: [2,5]
        Explanation: The fractions to be considered in sorted order are:
        1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
        The third fraction is 2/5.

        Example 2:
        ---------
        Input: arr = [1,7], k = 1
        Output: [1,7]


        Constraints:
        -----------
            2 <= arr.length <= 1000
            1 <= arr[i] <= 3 * 104
            arr[0] == 1
            arr[i] is a prime number for i > 0.
            All the numbers of arr are unique and sorted in strictly increasing order.
            1 <= k <= arr.length * (arr.length - 1) / 2


        Follow up: Can you solve the problem with better than O(n2) complexity?
 */

public class KthSmallestPrimeFraction {

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // Priority queue to store fractions with comparator to sort by fraction value
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(arr[a[0]] * arr[b[1]], arr[a[1]] * arr[b[0]]));

        // Initialize the heap with the first possible fraction from each numerator
        for (int i = 0; i < arr.length - 1; i++) {
            pq.add(new int[] { i, arr.length - 1 });
        }

        // Extract the smallest fraction k-1 times to reach the k-th smallest
        for (int i = 1; i < k; i++) {
            int[] fraction = pq.poll();
            int numerator = fraction[0];
            int denominator = fraction[1];

            // Push the next fraction with the same numerator and the next smaller denominator
            if (denominator - 1 > numerator) {
                pq.add(new int[] { numerator, denominator - 1 });
            }
        }

        // The k-th smallest fraction's indices after popping k-1 elements
        int[] res = pq.poll();
        return new int[] { arr[res[0]], arr[res[1]] };
    }

    public static void main(String[] args) {
        KthSmallestPrimeFraction smallestPairs = new KthSmallestPrimeFraction();
        System.out.println(Arrays.toString(smallestPairs.kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3)));  //  [2,5]
        System.out.println(Arrays.toString(smallestPairs.kthSmallestPrimeFraction(new int[]{1,7}, 1)));  //  [1,7]
    }

}
