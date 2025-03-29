package in.sachinshinde.heap_priority_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

//	https://leetcode.com/problems/kth-largest-element-in-an-array/

/*
 	Given an integer array nums and an integer k, return the kth largest element in the array.
        Note that it is the kth largest element in the sorted order, not the kth distinct element.
        Can you solve it without sorting?
        
        Example 1:
        ---------
        Input: nums = [3,2,1,5,6,4], k = 2
        Output: 5
        
        Example 2:
        ---------
        Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
        Output: 4
         
        
        Constraints:
        -----------
            1 <= k <= nums.length <= 105
            -104 <= nums[i] <= 104
 */

public class KthLargestElementInAnArray {

    //	Method 1: Sorting
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    //	Method 2: MinHeap [Using PriorityQueue]
    public int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num: nums) {
            pq.add(num);
            if(pq.size() > k) {
                pq.remove();
            }
        }
        return pq.remove();
    }

    //	Method 2: MinHeap [Using PriorityQueue : Keep only k elements in pq]
    public int findKthLargest_22(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2)-> n1 - n2);

        for(int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }

        for(int i = k; i < nums.length; i++) {
            if(!pq.isEmpty() && nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.isEmpty() ? -1 : pq.peek();

    }
    
    //	Method 3: Quick Select Algorithm
    //  Video Link: https://youtu.be/XEmy13g1Qxc
    public int findKthLargest_3(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num: nums) {
            list.add(num);
        }
        return quickSelect(list, k);
    }
    
    public int quickSelect(List<Integer> nums, int k) {
        int pivotIndex = new Random().nextInt(nums.size());
        int pivot = nums.get(pivotIndex);
        
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int num: nums) {
            if (num > pivot) {
                left.add(num);
            } 
            else if (num < pivot) {
                right.add(num);
            } 
            else {
                mid.add(num);
            }
        }
        
        if (k <= left.size()) {
            return quickSelect(left, k);
        }
        
        if (left.size() + mid.size() < k) {
            return quickSelect(right, k - left.size() - mid.size());
        }
        
        return pivot;
    }
    
    public static void main(String[] args) {
        KthLargestElementInAnArray array = new KthLargestElementInAnArray();
        System.out.println(array.findKthLargest(new int[] {3,2,1,5,6,4}, 2));		// 5
        System.out.println(array.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));	// 4


        System.out.println(array.findKthLargest_2(new int[] {3,2,1,5,6,4}, 2));		// 5
        System.out.println(array.findKthLargest_2(new int[] {3,2,3,1,2,4,5,5,6}, 4));	// 4

        System.out.println(array.findKthLargest_22(new int[] {3,2,1,5,6,4}, 2));		// 5
        System.out.println(array.findKthLargest_22(new int[] {3,2,3,1,2,4,5,5,6}, 4));	// 4

        System.out.println(array.findKthLargest_3(new int[] {3,2,1,5,6,4}, 2));		// 5
        System.out.println(array.findKthLargest_3(new int[] {3,2,3,1,2,4,5,5,6}, 4));	// 4
    }
    
}
