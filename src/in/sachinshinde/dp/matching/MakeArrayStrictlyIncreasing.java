package in.sachinshinde.dp.matching;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

//	https://leetcode.com/problems/make-array-strictly-increasing/

/*
 	Given two integer arrays arr1 and arr2, return the minimum number of operations 
 		(possibly zero) needed to make arr1 strictly increasing.

        In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and 
        do the assignment arr1[i] = arr2[j].
        
        If there is no way to make arr1 strictly increasing, return -1.
        
        Example 1:
        ---------
            Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
            Output: 1
            Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
        
        Example 2:
        ---------
            Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
            Output: 2
            Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
        
        Example 3:
        ---------
            Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
            Output: -1
            Explanation: You can't make arr1 strictly increasing.
         
        Constraints:
        -----------
            1 <= arr1.length, arr2.length <= 2000
            0 <= arr1[i], arr2[i] <= 10^9
         
 */

public class MakeArrayStrictlyIncreasing {

    //	using TreeSet : 
    //		The higher(E ele) method of TreeSet class in Java is used to 
    //		return the least element in this set which is strictly greater than the given element ele. 
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return -1;
        }
        
        if (arr1.length == 1) { 
            return 0;
        }
        
        TreeSet<Integer> ts = new TreeSet<>();
        
        if (arr2 != null) {
            for (int i = 0; i < arr2.length; i++) {
        	ts.add(arr2[i]);
            }
        }
        
        int[][] dp = new int[arr1.length + 1][arr1.length + 1];
        for (int i = 0; i < dp.length; i++) { 
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[0][0] = Integer.MIN_VALUE;
        
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }
                if (i > 0 && ts.higher(dp[i - 1][j - 1]) != null) {
                    dp[i][j] = Math.min(dp[i][j], ts.higher(dp[i - 1][j - 1]));
                }
                if (j == dp.length - 1 && dp[i][j] != Integer.MAX_VALUE) {
                    return i; 
                }
            } 
        }
        
        return -1;
    }
    //	----------------------------------------------------------------------------------------------
    //	Using recursion with DP and Binary Search 
    
    public int makeArrayIncreasing2(int[] arr1, int[] arr2) {
	Arrays.sort(arr2);
	int m = 1;
	for (int i = 1; i < arr2.length; i++) {
		if (arr2[i] != arr2[m - 1]) {
			arr2[m++] = arr2[i];
		}
	}
	int n = arr1.length;
	int[] dp = new int[n];
	Arrays.fill(dp, -1);
	int ans = f1(arr1, arr2, n, m, 0, dp);
	return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    public static int f1(int[] arr1, int[] arr2, int n, int m, int i, int[] dp) {
	if (i == n) {
	    return 0;
	}
	if (dp[i] != -1) {
	    return dp[i];
	}
	int ans = Integer.MAX_VALUE;
	int pre = i == 0 ? Integer.MIN_VALUE : arr1[i - 1];
	int find = binarySearch(arr2, m, pre);
	for (int j = i, k = 0, next; j <= n; j++, k++) {
	    if (j == n) {
		ans = Math.min(ans, k);
	    } 
	    else {
		if (pre < arr1[j]) {
		    next = f1(arr1, arr2, n, m, j + 1, dp);
		    if (next != Integer.MAX_VALUE) {
			ans = Math.min(ans, k + next);
		    }
		}
		if (find != -1 && find < m) {
		    pre = arr2[find++];
		} 
		else {
		    break;
		}
	    }
	}
	
	dp[i] = ans;
	
	return ans;
    }

    public static int binarySearch(int[] arr2, int size, int num) {
	int l = 0, r = size - 1, m;
	int ans = -1;
	while (l <= r) {
	    m = (l + r) / 2;
	    if (arr2[m] > num) {
		ans = m;
		r = m - 1;
	    } 
	    else {
		l = m + 1;
	    }
	}
	return ans;
    }
    //	-------------------------------------------------------------------------------------------
    //	Method 3: Using PriorityQueue and TreeSet
    public int makeArrayIncreasing3(int[] arr1, int[] arr2) {
        if(arr1.length == 1) 
            return 0;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int num : arr2)
            treeSet.add(num);       
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((s1,s2)->{
            if(s1[1] != s2[1]) 
        	return s1[1] - s2[1];
            if(s1[0] != s2[0]) 
        	return s2[0] - s1[0];
            return s1[2] - s2[2];
        });
        priorityQueue.offer(new int[]{ 0, 0, arr1[0]});
        priorityQueue.offer(new int[]{ 0, 1, treeSet.first()});
        int[] ans = new int[arr1.length];
        Arrays.fill(ans,Integer.MAX_VALUE);
        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();
            int idx = curr[0], cnt = curr[1], val = curr[2];
            if(idx == arr1.length - 1) 
        	return cnt;
            if(ans[idx] <= val) 
        	continue;
            ans[idx] = val;
            if(arr1[++idx] > val) 
        	priorityQueue.offer(new int[]{ idx, cnt, arr1[idx]});
            Integer num = treeSet.higher(val);
            if(num != null) 
        	priorityQueue.offer(new int[]{ idx, cnt + 1, num});
        }
        return -1;
    }
    
    //	-------------------------------------------------------------------------------------------------
    //	Method 4 : Using 2D dp arrays
    public int makeArrayIncreasing4(int[] arr1, int[] arr2) {
	TreeSet<Integer> set=new TreeSet<>();
	for(int num:arr2) set.add(num);
	int m=arr1.length, n=set.size();
	int[][] dp=new int[m+1][Math.min(m,n)+1];
	for(int i=0;i<=m;i++)
	    Arrays.fill(dp[i],Integer.MAX_VALUE);
	dp[0][0]=-1;
	for(int i=1;i<=m;i++) {
	    for(int j=0;j<=Math.min(m,n);j++) {
		if(arr1[i-1]>dp[i-1][j]) {
		    dp[i][j]=arr1[i-1];
	        }
	        if(j>0 && dp[i-1][j-1]!=Integer.MAX_VALUE) {
	            Integer alt=set.higher(dp[i-1][j-1]);
	            if(alt!=null) {
	        	dp[i][j]=Math.min(dp[i][j],alt);
	            }
	        }
	        if(i==m && dp[i][j]!= Integer.MAX_VALUE) 
	            return j;
	    }
	} 
	return -1;
    } 
    
    //	------------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
	MakeArrayStrictlyIncreasing arr = new MakeArrayStrictlyIncreasing();
	System.out.println(arr.makeArrayIncreasing(new int[] {1,5,3,6,7}, new int[] {1,3,2,4})); // 1
	System.out.println(arr.makeArrayIncreasing(new int[] {1,5,3,6,7}, new int[] {4,3,1})); // 2
	System.out.println(arr.makeArrayIncreasing(new int[] {1,5,3,6,7}, new int[] {1,6,3,3})); // -1
	
	System.out.println(arr.makeArrayIncreasing2(new int[] {1,5,3,6,7}, new int[] {1,3,2,4})); // 1
	System.out.println(arr.makeArrayIncreasing2(new int[] {1,5,3,6,7}, new int[] {4,3,1})); // 2
	System.out.println(arr.makeArrayIncreasing2(new int[] {1,5,3,6,7}, new int[] {1,6,3,3})); // -1
	
	System.out.println(arr.makeArrayIncreasing3(new int[] {1,5,3,6,7}, new int[] {1,3,2,4})); // 1
	System.out.println(arr.makeArrayIncreasing3(new int[] {1,5,3,6,7}, new int[] {4,3,1})); // 2
	System.out.println(arr.makeArrayIncreasing3(new int[] {1,5,3,6,7}, new int[] {1,6,3,3})); // -1
	
	System.out.println(arr.makeArrayIncreasing4(new int[] {1,5,3,6,7}, new int[] {1,3,2,4})); // 1
	System.out.println(arr.makeArrayIncreasing4(new int[] {1,5,3,6,7}, new int[] {4,3,1})); // 2
	System.out.println(arr.makeArrayIncreasing4(new int[] {1,5,3,6,7}, new int[] {1,6,3,3})); // -1
    }
}
