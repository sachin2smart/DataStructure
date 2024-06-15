package in.sachinshinde.array.hard;

import java.util.PriorityQueue;

//  https://leetcode.com/problems/minimize-deviation-in-array/

/*
    You are given an array nums of n positive integers.

    You can perform two types of operations on any element of the array any number of times:

    - If the element is even, divide it by 2.
         For example, if the array is [1,2,3,4], then you can do this operation on the last element, and
            the array will be [1,2,3,2].
    - If the element is odd, multiply it by 2.
         For example, if the array is [1,2,3,4], then you can do this operation on the first element, and
            the array will be [2,2,3,4].
    The deviation of the array is the maximum difference between any two elements in the array.

    Return the minimum deviation the array can have after performing some number of operations.

    Example 1:
    ----------
    Input: nums = [1,2,3,4]
    Output: 1
    Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.

    Example 2:
    ----------
    Input: nums = [4,1,5,20,3]
    Output: 3
    Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.

    Example 3:
    ----------
    Input: nums = [2,10,8]
    Output: 3


    Constraints:
    ----------
        n == nums.length
        2 <= n <= 5 * 104
        1 <= nums[i] <= 109
 */

public class MinimizeDeviationInArray {

    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));   // maxHeap
        int minValue = Integer.MAX_VALUE;

        for(int num : nums){
            if(num % 2 != 0){
                num = 2 * num;
            }
            minValue = Math.min(minValue, num);
            pq.add(num);
        }

        int diff = Integer.MAX_VALUE;

        while(pq.peek() % 2 == 0){
            int currMax = pq.poll();
            diff = Math.min(diff, currMax - minValue);

            int halfMax = currMax / 2;
            minValue = Math.min(minValue, halfMax);
            pq.add(halfMax);
        }

        return Math.min(diff, pq.peek() - minValue);
    }

    public static void main(String[] args) {
        MinimizeDeviationInArray deviation = new MinimizeDeviationInArray();
        System.out.println(deviation.minimumDeviation(new int[]{1,2,3,4}));     //  1
        System.out.println(deviation.minimumDeviation(new int[]{4,1,5,20,3}));  //  3
        System.out.println(deviation.minimumDeviation(new int[]{2,10,8}));      //  3

        System.out.println(deviation.minimumDeviation2(new int[]{1,2,3,4}));    //  1
        System.out.println(deviation.minimumDeviation2(new int[]{4,1,5,20,3})); //  3
        System.out.println(deviation.minimumDeviation2(new int[]{2,10,8}));     //  3
    }

    public int minimumDeviation2(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue< >();
        PriorityQueue<Integer> maxHeap = new PriorityQueue< >((a, b) -> b - a);

        for (int num: nums) {
            minHeap.add(num);
            maxHeap.add(num);
        }

        int min = maxHeap.peek() - minHeap.peek();

        if (min == 0) {
            return 0;
        }

        // odd case : from minHeap
        while (minHeap.peek() % 2 == 1) {
            int num = minHeap.poll();
            num *= 2;
            minHeap.add(num);
            maxHeap.add(num);
            min = Math.min(min, Math.abs(maxHeap.peek() - minHeap.peek()));
            if (min == 0) {
                return 0;
            }
        }

        //  even case : from maxHeap
        while (maxHeap.peek() % 2 == 0) {
            int num = maxHeap.poll();
            num /= 2;
            minHeap.add(num);
            maxHeap.add(num);
            min = Math.min(min, Math.abs(maxHeap.peek() - minHeap.peek()));
            if (min == 0) {
                return 0;
            }
        }

        return min;
    }
}
