package in.sachinshinde.dp.longest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/longest-consecutive-sequence/

/*
 	Given an unsorted array of integers nums, 
 		return the length of the longest consecutive elements sequence.

	You must write an algorithm that runs in O(n) time.

	Example 1:
	----------
		Input: nums = [100,4,200,1,3,2]
		Output: 4
		Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
					 Therefore its length is 4.
	
	Example 2:
	---------
		Input: nums = [0,3,7,2,5,8,4,6,0,1]
		Output: 9
 */

public class LongestConsecutiveSequence {

	//	Approach:
	//		1. Add all elements in the set
	//		2. for each element, if previuos element exists in set,
	//			check all previous elements exists in set
	
	private int longestConsecutive(int[] nums) {
		int longSeqLen = 0;
		Set<Integer> hs = new HashSet<Integer>();
		
		for(int num: nums)
			hs.add(num);
		
		for(int ele: hs) {
			// since we do not need to backward traverse
			// any sequence having length > 2 
			//		can be traversed at most once
			if(!hs.contains(ele-1)) {
				int currNum = ele;
				int currSeqLen = 1; // since first number found
				
				// check for next numbers
				while(hs.contains(currNum+1)) {
					currNum++;
					currSeqLen++;
				}
				
				longSeqLen = Math.max(currSeqLen, longSeqLen);
			}
		}
		
		return longSeqLen;
	}
	
	public static void main(String[] args) {
		LongestConsecutiveSequence seq = 
				new LongestConsecutiveSequence();
		
		System.out.println(seq.longestConsecutive(
				new int[] {100,4,200,1,3,2}));	//	4
		
		System.out.println(seq.longestConsecutive(
				new int[] {0,3,7,2,5,8,4,6,0,1}));	//	9
		
		System.out.println(seq.longestConsecutive2(
				new int[] {100,4,200,1,3,2}));	//	4
		
		System.out.println(seq.longestConsecutive2(
				new int[] {0,3,7,2,5,8,4,6,0,1}));	//	9
	}
	
	//	Using Memoization
	private int longestConsecutive2(int[] nums) {
		int res = 0;
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int n : nums) {
	        if (!map.containsKey(n)) {
	            int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
	            int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
	            // sum: length of the sequence n is in
	            int sum = left + right + 1;
	            map.put(n, sum);
	            
	            // keep track of the max length 
	            res = Math.max(res, sum);
	            
	            // extend the length to the boundary(s)
	            // of the sequence
	            // will do nothing if n has no neighbors
	            map.put(n - left, sum);
	            map.put(n + right, sum);
	        }
	        else {
	            continue;	// duplicates
	        }
	    }
	    return res;
	}
}
