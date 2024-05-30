package in.sachinshinde.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//	https://leetcode.ca/all/1213.html

/*
 *	Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, 
 *	return a sorted array of only the integers that appeared in all three arrays.
 *
 
 	Example 1:
	----------
	Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
	Output: [1,5]
	Explanation: Only 1 and 5 appeared in the three arrays.
	
 */

public class IntersectionOfThreeSortedArrays {

	// second approach is good to simple to understand
	public static List<Integer> getIntersectingElements(int[] arr1, int[] arr2, int[] arr3) {
		List<Integer> intersectingElements = new ArrayList<Integer>();
		
		int l1 = arr1.length, l2 = arr2.length, l3 = arr3.length, i1 = 0, i2 = 0, i3 = 0;
		
		while(i1<l1 && i2<l2 && i3<l3) {
			int n1 = arr1[i1], n2 = arr2[i2], n3 = arr3[i3];
			if(n1==n2 && n2==n3) {
				intersectingElements.add(n1);
				i1++; i2++; i3++;
			}
			else {
				int c1=0, c2=0, c3=0;
				if(n1<n2 || n1<n3) c1=1;
				if(n2<n1 || n2<n3) c2=1;
				if(n3<n1 || n3<n2) c3=1;
				
				i1+=c1; i2+=c2; i3+=c3;
			}
		}
		
		return intersectingElements;
	}
	
	public static void main(String[] args) {
		int[] arr1 = new int[] {1,2,3,4,5};
		int[] arr2 = new int[] {1,2,5,7,9};
		int[] arr3 = new int[] {1,3,4,5,8};
		
		List<Integer> intersectingElements = getIntersectingElements(arr1, arr2, arr3);
		System.out.println(intersectingElements);	//[1,5]
		
		arr1 = new int[]{1, 5, 5}; 
		arr2 = new int[]{3, 4, 5, 5, 10}; 
		arr3 = new int[]{5, 5, 10, 20};
		intersectingElements = getIntersectingElements2(arr1, arr2, arr3);
		System.out.println(intersectingElements);	//[5,5]
	}
	
	
	// Method 2:
	public static List<Integer> getIntersectingElements2(int[] arr1, int[] arr2, int[] arr3) {
		int i = 0, j = 0, k = 0;
		List<Integer> res = new ArrayList<>();
			
		while(i<arr1.length && j<arr2.length && k<arr3.length) {
		   if(arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
			   res.add(arr1[i]);
			   i++; j++; k++;
		   }
		   else if(arr1[i] < arr2[j]) {
			   i++;
		   }
		   else if(arr2[j]< arr3[k]) {
			   j++;
		   }
		   else {
			   k++;
		   }
		}

		return res;
	}

	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
		List<Integer> ans = new ArrayList<>();

		// note that HashMap won't work here as it does not guarantee the key orders
		Map<Integer, Integer> counter = new TreeMap<>();

		// iterate through arr1, arr2, and arr3 to count the frequencies
		for (Integer e: arr1) {
			counter.put(e, counter.getOrDefault(e, 0) + 1);
		}
		for (Integer e: arr2) {
			counter.put(e, counter.getOrDefault(e, 0) + 1);
		}
		for (Integer e: arr3) {
			counter.put(e, counter.getOrDefault(e, 0) + 1);
		}

		for (Integer item: counter.keySet()) {
			if (counter.get(item) == 3) {
				ans.add(item);
			}
		}
		return ans;

	}

	public List<Integer> arraysIntersection2(int[] arr1, int[] arr2, int[] arr3) {
		List<Integer> ans = new ArrayList <>();
		// prepare three pointers to iterate through three arrays
		// p1, p2, and p3 point to the beginning of arr1, arr2, and arr3 accordingly
		int p1 = 0, p2 = 0, p3 = 0;

		while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {

			if (arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
				ans.add(arr1[p1]);
				p1++;
				p2++;
				p3++;
			} else {
				if (arr1[p1] < arr2[p2]) {
					p1++;
				} else if (arr2[p2] < arr3[p3]) {
					p2++;
				} else {
					p3++;
				}

			}
		}
		return ans;
	}
}