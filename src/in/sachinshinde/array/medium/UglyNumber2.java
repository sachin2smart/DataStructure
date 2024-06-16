package in.sachinshinde.array.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//  https://leetcode.com/problems/ugly-number-ii/

/*
        An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
        Given an integer n, return the nth ugly number.

        Example 1:
        -----------
        Input: n = 10
        Output: 12
        Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.

        Example 2:
        -----------
        Input: n = 1
        Output: 1
        Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.


        Constraints:
        -----------
            1 <= n <= 1690
 */

public class UglyNumber2 {

    public int[] nums = new int[1690];

    public UglyNumber2() {
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;

        for(int i = 1; i < 1690; i++) {
            int ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;

            if(ugly == nums[i2] * 2) {
                i2++;
            }

            if(ugly == nums[i3] * 3) {
                i3++;
            }

            if(ugly == nums[i5] * 5) {
                i5++;
            }

        }
    }

    public static void main(String[] args) {
        UglyNumber2 uglyNumber2 = new UglyNumber2();
        System.out.println(uglyNumber2.nums[9]);    // ans = 12 // this gives 10'th ugly number
        System.out.println(uglyNumber2.nums[0]);    // ans = 1  // this gives  0'th ugly number

        System.out.println(uglyNumber2.nthUglyNumber(10));    // ans = 12 // this gives 10'th ugly number
        System.out.println(uglyNumber2.nthUglyNumber(1));     // ans = 0  // this gives  0'th ugly number
    }


    //  Using PriorityQueue
    public int nthUglyNumber(int n) {
        // Set to store unique ugly numbers
        Set<Long> set = new HashSet<>();
        set.add(1L); // Start with the first ugly number (1)

        // Priority queue (min heap) to keep track of the next ugly numbers efficiently
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L); // Add the first ugly number to the priority queue

        // Variable to store the current ugly number
        long uglyNumber = 1;

        // Generate ugly numbers until the required count (n)
        while (n > 0) {
            uglyNumber = queue.poll(); // Retrieve the smallest ugly number from the heap

            // Generate and add multiples of the retrieved ugly number to the priority queue and set
            if (!set.contains(uglyNumber * 2)) {
                queue.offer(uglyNumber * 2);
                set.add(uglyNumber * 2);
            }

            if (!set.contains(uglyNumber * 3)) {
                queue.offer(uglyNumber * 3);
                set.add(uglyNumber * 3);
            }

            if (!set.contains(uglyNumber * 5)) {
                queue.offer(uglyNumber * 5);
                set.add(uglyNumber * 5);
            }

            n--;
        }

        // Return the nth ugly number
        return (int) uglyNumber;
    }
}
