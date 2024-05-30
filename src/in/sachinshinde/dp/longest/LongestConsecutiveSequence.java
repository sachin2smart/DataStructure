package in.sachinshinde.dp.longest;

import java.util.*;

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

	// Watch Striver's video : https://www.youtube.com/watch?v=oO5uLE7EUlM

	// Brute Force Approach : gives TLE
	public int longestConsecutiveBruteForce(int[] nums) {
		int maxLength = 0;

		for (int num : nums) {
			int currNum = num;
			int currMax = 1;

			while(linearSearch(nums, currNum+1)) {
				currNum++;
				currMax++;
			}

			maxLength = Math.max(currMax, maxLength);
		}

		return maxLength;
	}

	private boolean linearSearch(int[] nums, int x) {
		for(int num: nums) {
			if(num == x) {
				return true;
			}
		}
		return false;
	}

	// Better Approach with Sorting
	public int longestConsecutiveBySorting(int[] nums) {
		int max = 1;
		int lastSmaller = Integer.MIN_VALUE;
		int currMax = 1;

		nums = Arrays.stream(nums).sorted().toArray();

		for (int num : nums) {
			if (num - 1 == lastSmaller) {
				currMax++;
				lastSmaller = num;
			} else if (num != lastSmaller) {
				currMax = 1;
				lastSmaller = num;
			}
			max = Math.max(currMax, max);
		}
		return max;
	}


	//	Using Set<> Approach : HashSet (it is better than TreeSet)
	private int longestConsecutiveByHashSet(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}

		int max = 0;
		Set<Integer> hs = new HashSet<>(); // property: do not have duplicates
		
		for(int num: nums) {
			hs.add(num);
		}
		
		for(int num: hs) {
			// Here, we need to find the starting number of any possible sequence.
			// Starting number will be the one which does not have the previous number
			if(!hs.contains(num-1)) {
				int currNum = num;
				int currMax = 1; // since first number found for a resultant sequence
				
				// check for next numbers
				while(hs.contains(currNum + 1)) {
					currNum++;
					currMax++;
				}
				
				max = Math.max(currMax, max);
			}
		}
		
		return max;
	}

	//	Using Set<> Approach : TreeSet (better than HashSet)
	private int longestConsecutiveByTreeSet(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}

		int max = 1;
		Set<Integer> ts = new TreeSet<>(); // property: do not have duplicates

		/*
			------ other ways using java streams api --------- //
			Set<Integer> ts = Arrays.stream(nums).boxed().collect(Collectors.toCollection(TreeSet::new));
			Set<Integer> ts = new TreeSet<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		*/

		for(int num: nums) {
			ts.add(num);
		}

		int currMax = 1;
		for(int num: ts) {
			if(ts.contains(num-1)) {
				max = Math.max(++currMax, max);
			}
			else {
				currMax = 1;
			}
		}

		return max;
	}


	//	Using Map : HashMap (if you replace it by TreeMap that gives bad performance)
	private int longestConsecutiveByHashMap(int[] nums) {
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			if (!map.containsKey(n)) {
				int left = map.getOrDefault(n - 1, 0); // expand on left & get the map value if exists
				int right = map.getOrDefault(n + 1, 0); // expand on left & get the map value if exists

				// sum: length of the sequence n is in
				int sum = left + right + 1;

				map.put(n, sum);
				map.put(n - left, sum);		// expand to left
				map.put(n + right, sum);	// expand to right

				// keep track of the max length
				res = Math.max(res, sum);

			}
		}
		return res;
	}

	public static void main(String[] args) {
		LongestConsecutiveSequence seq = new LongestConsecutiveSequence();

		System.out.println(seq.longestConsecutiveBruteForce(new int[] {100,4,200,1,3,2}));	// 4

		System.out.println(seq.longestConsecutiveBruteForce(new int[] {0,3,7,2,5,8,4,6,0,1}));	// 9

		System.out.println(seq.longestConsecutiveBySorting(new int[] {100,4,200,1,3,2}));	// 4

		System.out.println(seq.longestConsecutiveBySorting(new int[] {0,3,7,2,5,8,4,6,0,1}));	// 9

		System.out.println(seq.longestConsecutiveByHashSet(new int[] {100,4,200,1,3,2}));	//	4
		
		System.out.println(seq.longestConsecutiveByHashSet(new int[] {0,3,7,2,5,8,4,6,0,1}));	//	9

		System.out.println(seq.longestConsecutiveByTreeSet(new int[] {100,4,200,1,3,2}));	//	4

		System.out.println(seq.longestConsecutiveByTreeSet(new int[] {0,3,7,2,5,8,4,6,0,1}));	//	9

		System.out.println(seq.longestConsecutiveByHashMap(new int[] {1,3,2}));	//	4
		
		System.out.println(seq.longestConsecutiveByHashMap(new int[] {0,3,7,2,5,8,4,6,0,1}));	//	9
	}

}