package in.sachinshinde.array.twopointer;

//	https://leetcode.com/problems/longest-mountain-in-array/

/*
 * 	You may recall that an array arr is a mountain array if and only if:
        	arr.length >= 3
        	There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
        		arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        		arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
        
        Given an integer array arr, return the length of the longest subarray, 
        which is a mountain. Return 0 if there is no mountain subarray.
        
        
        Example 1:
        ----------
        Input: arr = [2,1,4,7,3,2,5]
        Output: 5
        Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
        
        Example 2:
        ----------
        Input: arr = [2,2,2]
        Output: 0
        Explanation: There is no mountain.
 	
 */

public class LongestMountain {
    
    public int longestMountain(int[] arr) {
        int ans = 0;
        int n = arr.length;
        
        int i = 1; 
        while(i <= n-2) {
            // a point from which we can go downward on left side and right side
            if(arr[i] > arr[i-1] && arr[i] > arr[i+1]) {	// identify the peak point
                int j = i;
                int count = 1; // this for point i

                while(j>0 && arr[j-1] < arr[j]) {	// deep on left side
                    j--;
                    count++; // this is for leftside, since j is already being decremented go till j>0 not j>=0
                }

                while(i<n-1 && arr[i] > arr[i+1]) {	// deep on right side
                    i++; 
                    count++; // this is for rightSide, since "i" is being incremented go till i<n-1 not i<n
                }
                // new start point of "i" is either a flat surface or start of another mountain
                ans = Math.max(ans, count);
            }
            else {
                 i++;               
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args) {
	LongestMountain longestMountain = new LongestMountain();
	System.out.println(longestMountain.longestMountain(new int[] {2,1,4,7,3,2,5}));	// 5
	System.out.println(longestMountain.longestMountain(new int[] {2,2,2}));	// 0
        System.out.println(longestMountain.longestMountain(new int[] {1,3,2}));	// 3
    }
}
