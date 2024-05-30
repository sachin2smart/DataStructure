package in.sachinshinde.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/intersection-of-two-arrays/

/*
 	Given two integer arrays nums1 and nums2, return an array of their intersection. 
 	Each element in the result must be unique and you may return the result in any order.

        Example 1:
        ---------
        Input: nums1 = [1,2,2,1], nums2 = [2,2]
        Output: [2]
        
        Example 2:
        ---------
            Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
            Output: [9,4]
        Explanation: [4,9] is also accepted.
        
        Constraints:
        -----------
            1 <= nums1.length, nums2.length <= 1000
            0 <= nums1[i], nums2[i] <= 1000
 */

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersectionSet = new HashSet<>();
        
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
        	    intersectionSet.add(nums2[i]);
            }
        }
        
        int[] result = new int[intersectionSet.size()];
        
        int i = 0;
        for (Integer num : intersectionSet) {
            result[i++] = num;
        }
        
        return result;
    }
    
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } 
            else if (nums1[i] > nums2[j]) {
                j++;
            } 
            else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
    
    public static void main(String[] args) {
	IntersectionOfTwoArrays arr = new IntersectionOfTwoArrays();
	System.out.println(Arrays.toString(arr.intersection(new int[] {1,2,2,1}, new int[] {2,2})));
	System.out.println(Arrays.toString(arr.intersection2(new int[] {1,2,2,1}, new int[] {2,2})));
	
	System.out.println(Arrays.toString(arr.intersection(new int[] {4,9,5}, new int[] {9,4,9,8,4})));
	System.out.println(Arrays.toString(arr.intersection2(new int[] {4,9,5}, new int[] {9,4,9,8,4})));
	
	/*
	 	[2]
            	[2]
            	[4, 9]
            	[4, 9]

	 */
    }
}