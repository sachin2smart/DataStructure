package in.sachinshinde.array.twopointer;

//	https://leetcode.com/problems/find-k-closest-elements/

/*
 	Given a sorted integer array arr, two integers k and x, 
 		return the k closest integers to x in the array. 
 	The result should also be sorted in ascending order.

        An integer a is closer to x than an integer b if:
        
        	|a - x| < |b - x|, or
        	|a - x| == |b - x| and a < b
         
        
        Example 1:
        Input: arr = [1,2,3,4,5], k = 4, x = 3
        Output: [1,2,3,4]
        
        Example 2:
        Input: arr = [1,2,3,4,5], k = 4, x = -1
        Output: [1,2,3,4]
         
        
        Constraints:
            1 <= k <= arr.length
            1 <= arr.length <= 104
            arr is sorted in ascending order.
            -104 <= arr[i], x <= 104
 */

/*
 	Video Link;
 		https://youtu.be/o-YDQzHoaKM
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKClosestElements {

    //	Two Pointer Approach : 4ms
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
	int i=0, j=arr.length-1;
        
	while(j-i >= k)
            if(Math.abs(arr[i] - x) > Math.abs(arr[j] - x)) 
        	i++;
            else
        	j--;
        
        List<Integer> res = new ArrayList<>();
        
        for(int p=i; p<=j; p++)
            res.add(arr[p]);
        
        return res;
    }
    
    //	Sliding Window Approach (Binary Search) : 3ms
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
	int i = 0, j = arr.length - k;
	while (i < j) {
	    int mid = (i + j) / 2;
	    if(x - arr[mid] > arr[mid + k] - x) 
		i = mid + 1;
	    else
		j = mid;
	    
	}
	
	List<Integer> res = new ArrayList<>();
	for(int p = i; p < i+k; p++)
	    res.add(arr[p]);
	
	return res;
    }
    
    // Heap Approach ()using priority Queue : 27ms
    //	First: add all the smallest integers up to k number
    //	Second: if found a closer integer to x, remove the smallest integer from the priority queue, and add the new integer 
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
	
        for(int i: arr) {
            if(k > 0) {	
                pq.offer(i);
                k--;
            }
            else if(Math.abs(i - x) < Math.abs(pq.peek() - x)) {
                pq.remove();
                pq.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while(!pq.isEmpty())
            res.add(pq.poll());

        return res;
    }
    
    public static void main(String[] args) {
	FindKClosestElements kClosestElements = new FindKClosestElements();
	System.out.println(kClosestElements.findClosestElements(new int[] {1,2,3,4,5}, 4, 3));	// [1,2,3,4]
	System.out.println(kClosestElements.findClosestElements(new int[] {1,2,3,4,5}, 4, -1));	// [1,2,3,4]
	
	System.out.println(kClosestElements.findClosestElements2(new int[] {1,2,3,4,5}, 4, 3));	// [1,2,3,4]
	System.out.println(kClosestElements.findClosestElements2(new int[] {1,2,3,4,5}, 4, -1));// [1,2,3,4]
	
	System.out.println(kClosestElements.findClosestElements3(new int[] {1,2,3,4,5}, 4, 3));	// [1,2,3,4]
	System.out.println(kClosestElements.findClosestElements3(new int[] {1,2,3,4,5}, 4, -1));// [1,2,3,4]
    }
}
