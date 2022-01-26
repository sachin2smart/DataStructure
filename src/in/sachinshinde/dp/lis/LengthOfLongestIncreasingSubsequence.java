package in.sachinshinde.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//	https://leetcode.com/problems/longest-increasing-subsequence/

//	Longest Increasing Subsequence
/* Given an integer array nums, return the length of the longest strictly increasing subsequence.
	
	A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. 
	For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
*/

public class LengthOfLongestIncreasingSubsequence {

	public static void main(String[] args) {
		System.out.println(getLengthOfLIS(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS(new int[] {7,7,7,7,7,7})); //1
		
		System.out.println();
		
		System.out.println(getLengthOfLIS_DP_Mem(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS_DP_Mem(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS_DP_Mem(new int[] {7,7,7,7,7,7})); //1
		
		System.out.println();
		
		System.out.println(getLengthOfLIS_DP_Mem_SO(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS_DP_Mem_SO(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS_DP_Mem_SO(new int[] {7,7,7,7,7,7})); //1
		
		System.out.println();
		
		System.out.println(getLengthOfLIS_BinarySearch(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS_BinarySearch(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS_BinarySearch(new int[] {7,7,7,7,7,7})); //1
		
//		System.out.println();
//		
//		System.out.println(getLengthOfLIS_basic(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
//		System.out.println(getLengthOfLIS_basic(new int[] {0, 1 , 0, 3, 2, 3})); //4
//		System.out.println(getLengthOfLIS_basic(new int[] {7,7,7,7,7,7})); //1
		
		System.out.println();
		
		System.out.println(getLengthOfLIS_tabulation(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS_tabulation(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS_tabulation(new int[] {7,7,7,7,7,7})); //1
		
//		System.out.println();
//		
//		System.out.println(getLengthOfLIS_dp2D_LCS(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
//		System.out.println(getLengthOfLIS_dp2D_LCS(new int[] {0, 1 , 0, 3, 2, 3})); //4
//		System.out.println(getLengthOfLIS_dp2D_LCS(new int[] {7,7,7,7,7,7})); //1
		
		System.out.println();
		
		System.out.println(getLengthOfLIS_nlogn(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS_nlogn(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS_nlogn(new int[] {7,7,7,7,7,7})); //1
		
		System.out.println();
		
		System.out.println(getLengthOfLIS_nlogn_optimal(new int[] {10, 9, 2, 5, 3 , 7, 101, 18})); //4
		System.out.println(getLengthOfLIS_nlogn_optimal(new int[] {0, 1 , 0, 3, 2, 3})); //4
		System.out.println(getLengthOfLIS_nlogn_optimal(new int[] {7,7,7,7,7,7})); //1
	}
	
	// 1. Naive approach. TC = O(2^N), SC = O(N)
	
	public static int getLengthOfLIS(int[] nums) {
		return getLength(nums, 0, Integer.MIN_VALUE);
	}
	
	private static int getLength(int[] nums, int i, int prev) {
		if(i >= nums.length)
			return 0;
		
		int dontTake = getLength(nums, i+1, prev);
		int take = 0;
		
		// If the current element is greater than the previous element, then pick it 
		if(nums[i] > prev)
			take = 1 + getLength(nums, i+1, nums[i]);
		
		return Math.max(dontTake, take);
	}
	

	// 2.  DP : Memoization.   TC = O(N^2),  SC = O(N^2)
	
	static int[][] dp;
	public static int getLengthOfLIS_DP_Mem(int[] nums) {
		dp = new int[nums.length][nums.length+1];
		for(int[] dpArr : dp)
			Arrays.fill(dpArr, -1);
		
		return getLength_DP_Mem(nums, 0, -1);
	}
	
	private static int getLength_DP_Mem(int[] nums, int i, int prev) {
		if(i >= nums.length)
			return 0;
		
		if(dp[i][prev+1] != -1)
			return dp[i][prev+1];
		
		int dontTake = getLength_DP_Mem(nums, i+1, prev);
		int take = 0;
		
		if(prev == -1 || nums[i] > nums[prev])
			take = 1 + getLength_DP_Mem(nums, i+1, i);
		
		return dp[i][prev+1] = Math.max(dontTake, take);
	}
	
	
	// 3. DP : Memoization, Space Optimized.   TC = O(N^2)   SC = O(N)
	
	static int[] dpSO;
	public static int getLengthOfLIS_DP_Mem_SO(int[] nums) {
		dpSO = new int[nums.length+1];
		Arrays.fill(dpSO, -1);
		return getLength_DP_Mem_SO(nums, 0, -1);
	}
	
	private static int getLength_DP_Mem_SO(int[] nums, int i, int prev) {
		if(i >= nums.length)
			return 0;
		
		if(dpSO[prev+1] != -1)
			return dpSO[prev+1];
		
		int dontTake = getLength_DP_Mem_SO(nums, i+1, prev);
		int take = 0;
		
		if(prev == -1 || nums[i] > nums[prev])
			take = 1 + getLength_DP_Mem_SO(nums, i+1, i);
		
		return dpSO[prev+1] = Math.max(dontTake, take);
	}
	
	//	4. Binary Search 	TC = O(NlogN), SC = O(N)
		
	public static int getLengthOfLIS_BinarySearch(int[] nums) {
	    int[] tails = new int[nums.length];
	    int size = 0;
	    for (int x : nums) {
	        int i = 0, j = size;
	        while (i != j) {
	            int m = (i + j) / 2;
	            if (tails[m] < x)
	                i = m + 1;
	            else
	                j = m;
	        }
	        tails[i] = x;
	        if (i == size) 
	        	++size;
	    }
	    return size;
	}
	
	//	5. 	Iteration Approach 	TC = O(N^2),	SC = O(N^2)
	
//	public static int getLengthOfLIS_basic(int[] nums) {
//		List<Integer> list = new ArrayList<>();
//        int currentMax;
//        int highestCount = 0;
//        for(int i = 0; i < nums.length;i++) {
//            currentMax = Integer.MIN_VALUE;
//            for(int j = i;j < nums.length; j++) {
//                if(nums[j] > currentMax) {
//                    list.add(nums[j]);
//                    currentMax = nums[j];
//                }
//            }
//            if(highestCount < list.size())
//                highestCount = list.size();
//        }
//        return highestCount;
//	}
	
	//	6. Tabulation	TC = O(N^2), SC = O(N)
	
	public static int getLengthOfLIS_tabulation(int[] nums) {
		int max = 0;
		int n = nums.length;
		
		int[] lis = new int[n];
		
		for(int i=0; i<n ; i++)
			lis[i] = 1;
		
		// Bottom-up approach
		for(int i=0; i<n; i++)
			for(int j=0; j<i; j++)
				if(nums[i] > nums[j] && lis[i] < lis[j]+1)
					lis[i] = lis[j]+1;
		
		for(int i=0; i< n; i++)
			if(max<lis[i])
				max = lis[i];
		
		return max;
	}
	
	//	7. Using 2D DP array, Longest Common Subsequence,  TC = O(N^2), SC = O(N^2)
	
//    public static int getLengthOfLIS_dp2D_LCS(int nums[]) {
//        Set<Integer> ts = new TreeSet<Integer>();
//        int n = nums.length;
//        int i, j, k;
//        
//        // Storing and Sorting unique elements.
//        for (i = 0; i < n; i++)
//            ts.add(nums[i]);
//        
//        int lis[] = new int[ts.size()+1];
//        
//        // Storing all the unique values in a sorted manner.
//        k=0;
//        for (int val : ts) {
//            lis[k] = val;
//            k++;
//        }
//        int m = k;
//        
//        int dp[][] = new int[m + 1][n + 1];
//  
//        // Storing -1 in dp 2-dimensional array.
//        for (i = 0; i < m+1; i++)
//            for (j = 0; j < n+1; j++)
//                dp[i][j] = -1;
//            
//  
//        // Finding the Longest Common Subsequence (LCS) of the two arrays
//        for (i = 0; i < m+1; i++)
//            for (j = 0; j < n+1; j++)
//                if (i == 0 || j == 0)
//                    dp[i][j] = 0;
//                else if (nums[i-1] == lis[j-1])
//                    dp[i][j] = 1 + dp[i-1][j-1];
//                else
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
//         
//        return dp[m][n];
//    }
	
	
	//	8. Using Clone, Extend, discard methods.  TC = O(NlogN), SC = O(N)
	//	Reference : https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	
	public static int getLengthOfLIS_nlogn(int nums[]) {
        if (nums.length == 0) // boundary case
            return 0;
 
        int[] tail = new int[nums.length];
        int currTarget = 1;
        tail[0] = nums[0];
 
        for (int i = 1; i < nums.length; i++) {
 
            if (nums[i] > tail[currTarget - 1]) {
                tail[currTarget++] = nums[i]; 
            }
            else {
                int idx = Arrays.binarySearch(tail, 0, currTarget-1, nums[i]);	// Find largest value just smalller than nums[i] in tail
                if (idx < 0)
                    idx = -1 * idx - 1; // idx can be -ve, if element is not found by binary search, hence update index
                tail[idx] = nums[i];
            }
        }
        
        return currTarget;
    }
	
	//	9. Optimal for above solution. TC = O(NlogN), SC = O(N)
	
	public static int getLengthOfLIS_nlogn_optimal(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int idx = Arrays.binarySearch(dp, 0, len, x);
            if(idx < 0) 
            	idx = -idx - 1;
            dp[idx] = x;
            if(idx == len) 
            	len++;
        }

        return len;
    }
	
}