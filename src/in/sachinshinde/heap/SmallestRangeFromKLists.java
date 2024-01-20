package in.sachinshinde.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

//	https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/

/*
 	You have k lists of sorted integers in non-decreasing order. 
 	Find the smallest range that includes at least one number from each of the k lists.

        We define the range [a, b] is smaller than range [c, d] if 
        	b - a < d - c or a < c if b - a == d - c.
        
         
        
        Example 1:
        ---------
        Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
        Output: [20,24]
        Explanation: 
        List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
        List 2: [0, 9, 12, 20], 20 is in range [20,24].
        List 3: [5, 18, 22, 30], 22 is in range [20,24].
        
        Example 2:
        ---------
        Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
        Output: [1,1]
        
        Constraints:
        -----------
            nums.length == k
            1 <= k <= 3500
            1 <= nums[i].length <= 50
            -105 <= nums[i][j] <= 105
            nums[i] is sorted in non-decreasing order.
 */
public class SmallestRangeFromKLists {
    //	Method 1: Using Arrays 
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[]{0, Integer.MAX_VALUE};
        int max = Integer.MIN_VALUE;
        
        // next[i] represents the next element need to be consider in list nums.get(i)
        int[] next = new int[nums.size()];
        
        // Find the max value from all lit, i.e. nums.get(i).get(0) where i belongs to [0, nums.size()-1]
        for(int i=0; i<nums.size(); i++) {
            max = Math.max(max, nums.get(i).get(0));
        }

        while(true) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for(int i=0; i<nums.size(); i++) {
                while(next[i]+1 < nums.get(i).size() && 
                	nums.get(i).get(next[i]+1) <= max) {
                    next[i]++;
                }
                
                if(nums.get(i).get(next[i]) < min) {
                    min = nums.get(i).get(next[i]);
                    minIdx = i;
                }
            }
            if(res[1] - res[0] > max - min) {
                res[0] = min;
                res[1] = max;
            }
            next[minIdx]++;
            if(next[minIdx] >= nums.get(minIdx).size()) {
                return res;
            }
            max = nums.get(minIdx).get(next[minIdx]);
        }
    }
    
    // Method 2 : Using TreeMap
    public int[] smallestRange_2(List<List<Integer>> nums) {
	TreeMap<Integer, List<Integer>> tm = new TreeMap<>();
	int n = nums.size();
	int[] index = new int[n];
	    
	for(int i=0; i<n; i++) {
	    int num = nums.get(i).get(index[i]);
	    if(!tm.containsKey(num)) {
		tm.put(num, new ArrayList<>());
	    }
	    tm.get(num).add(i);
	}
	    
	int min = tm.firstKey();
	int max = tm.lastKey();
	    
	while(true) {
	    int currMin = tm.firstKey();
	    List<Integer> groups = tm.get(currMin);
	    tm.remove(currMin);
	        
	    boolean reachesEnd = false;
	    for(int group: groups) {
		if(index[group] == nums.get(group).size() - 1) {
		    reachesEnd = true;
	            break;
	        }
	        index[group]++;
	        int num = nums.get(group).get(index[group]);
	        if(!tm.containsKey(num)) {
	            tm.put(num, new ArrayList<>());
	        }
	        tm.get(num).add(group);
	    }
	        
	    if(reachesEnd) {
		break;
	    }
	        
	    currMin = tm.firstKey();
	    int currMax = tm.lastKey();
	        
	    if(currMax - currMin < max - min) {
		max = currMax;
	        min = currMin;
	    }
	}
	
	return new int[]{min, max};
    }
    
    // Method 3 : Using PriorityQueue
    public int[] smallestRange_3(List<List<Integer>> nums) {
        int minx = 0;
        int miny = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[] next = new int[nums.size()];
        boolean flag = true;
        
        PriorityQueue <Integer> min_queue = new PriorityQueue <Integer> (
        	(i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
        
        for (int i = 0; i < nums.size(); i++) {
            min_queue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        
        for (int i = 0; i < nums.size() && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                int min_i = min_queue.poll();
                if (miny - minx > max - nums.get(min_i).get(next[min_i])) {
                    minx = nums.get(min_i).get(next[min_i]);
                    miny = max;
                }
                next[min_i]++;
                if (next[min_i] == nums.get(min_i).size()) {
                    flag = false;
                    break;
                }
                min_queue.offer(min_i);
                max = Math.max(max, nums.get(min_i).get(next[min_i]));
            }
        }
        return new int[] { minx, miny};
    }
    
    // Method 4 : Using PriorityQueue<int[]>
    public int[] smallestRange_4(List<List<Integer>> nums) {
	PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
	int max = Integer.MIN_VALUE;
	
	for(int i=0; i<nums.size(); i++) {
	    List<Integer> currList = nums.get(i);
	    int firstEle = currList.get(0);
	    max = Math.max(max, firstEle);
	    pq.offer(new int[] {i, 0, firstEle});
	}
	
	int a = 0, b = Integer.MAX_VALUE;
	
	while(true) {
	    int[] currQueue = pq.poll();
	    int c = currQueue[2], d = max;
	    
	    if(d-c < b-a ||(d-c == b-a) && c<a) {
		a = c;
		b = d;
	    }
	    
	    List<Integer> currList = nums.get(currQueue[0]); 
	    if(++currQueue[1] == currList.size()) {
		break;
	    }
	    
	    currQueue[2] = currList.get(currQueue[1]);
	    max = Math.max(max, currQueue[2]);
	    pq.offer(currQueue);
	}
	
	return new int[] {a, b};
    }
    
    public static void main(String[] args) {
	SmallestRangeFromKLists range = new SmallestRangeFromKLists();
	System.out.println(Arrays.toString(
		range.smallestRange(
			Arrays.asList(
				Arrays.asList(4,10,15,24,26),
				Arrays.asList(0,9,12,20),
				Arrays.asList(5,18,22,30)
				))));		// ans :  [20, 24]
	
	System.out.println(Arrays.toString(
		range.smallestRange_2(
			Arrays.asList(
				Arrays.asList(4,10,15,24,26),
				Arrays.asList(0,9,12,20),
				Arrays.asList(5,18,22,30)
				))));		// ans :  [20, 24]
	
	System.out.println(Arrays.toString(
		range.smallestRange_3(
			Arrays.asList(
				Arrays.asList(4,10,15,24,26),
				Arrays.asList(0,9,12,20),
				Arrays.asList(5,18,22,30)
				))));		// ans :  [20, 24]
	
	System.out.println(Arrays.toString(
		range.smallestRange_4(
			Arrays.asList(
				Arrays.asList(4,10,15,24,26),
				Arrays.asList(0,9,12,20),
				Arrays.asList(5,18,22,30)
				))));		// ans :  [20, 24]
    }
}