package in.sachinshinde.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

//	https://leetcode.com/problems/top-k-frequent-elements/

/*
 	Given an integer array nums and an integer k, return the k most frequent elements. 
 	You may return the answer in any order.

        Example 1:
        ---------
            Input: nums = [1,1,1,2,2,3], k = 2
            Output: [1,2]
        
        Example 2:
        ---------
            Input: nums = [1], k = 1
            Output: [1]
         
        Constraints:
        -----------
            1 <= nums.length <= 105
            -104 <= nums[i] <= 104
            k is in the range [1, the number of unique elements in the array].
            It is guaranteed that the answer is unique.
         
        
        Follow up: Your algorithm's time complexity must be better than O(n log n), 
        	where n is the array's size.
 */

public class TopKFrequentElements {
    //	Method 1: using HashMap & stream functionality to sort
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        
        for(int num: nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        
        hm = hm.entrySet()
            .stream()
            .sorted((i1, i2) -> i2.getValue().compareTo(i1.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
        	    (e1,e2)-> e1, LinkedHashMap::new));
        
        int[] res = new int[k];
        
        int i=0;
        
        for(Map.Entry<Integer, Integer> en: hm.entrySet()) {
            if(i<k)
                res[i] = en.getKey();
            else
                break;
            
            i++;
        }
        
        return res;
    }

    //	-----------------------------------------------------------------------------------------------
    //	Method 2: using HashMap & PriorityQueue
    
    public int[] topKFrequent2(int[] nums, int k) {
	Map<Integer, Integer> map = new HashMap<>();
	
        for(int num: nums) 
            map.merge(num, 1, Integer::sum);
        
        Queue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet())
            q.offer(entry);
        
        int[] res = new int[k];
        
        for(int i=0; i<k; i++)
            res[i] = q.poll().getKey();

        return res;
    }
    
    //	---------------------------------------------------------------------------------------------
    //	Method 3: Without using HashMap
    
    public int[] topKFrequent3(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int size = max - min + 1;
        int[] counts = new int[size];

        for (int i = 0; i < nums.length; i++) {
            counts[nums[i] -min]++;
        }

        int[] result = new int[k];
        int idx = 0;
        for (int i = 0; i < k; i++) {
            int j = 0, maxFreq = 0, maxIdx = 0;
            for (; j < size; j++) {
                if(counts[j] > maxFreq) {
                    maxFreq = counts[j];
                    maxIdx = j;
                }
            }
            result[idx++] = maxIdx + min;
            counts[maxIdx] = 0;
        }
        return result;
    }
    //	---------------------------------------------------------------------------------------------
    public int[] topKFrequent4(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        
        for(int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));
        pq.addAll(hm.keySet());
        
        int[] res = new int[k];
        for(int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        
        return res;
    }

    //	---------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
	TopKFrequentElements ele = new TopKFrequentElements();
	System.out.println(Arrays.toString(ele.topKFrequent(new int[] {1,1,1,2,2,3}, 2)));	// [1,2]
	System.out.println(Arrays.toString(ele.topKFrequent(new int[] {1}, 1)));		// [1]
	
	System.out.println(Arrays.toString(ele.topKFrequent2(new int[] {1,1,1,2,2,3}, 2)));	// [1,2]
	System.out.println(Arrays.toString(ele.topKFrequent2(new int[] {1}, 1)));		// [1]
	
	System.out.println(Arrays.toString(ele.topKFrequent3(new int[] {1,1,1,2,2,3}, 2)));	// [1,2]
	System.out.println(Arrays.toString(ele.topKFrequent3(new int[] {1}, 1)));		// [1]
	
	System.out.println(Arrays.toString(ele.topKFrequent4(new int[] {1,1,1,2,2,3}, 2)));	// [1,2]
	System.out.println(Arrays.toString(ele.topKFrequent4(new int[] {1}, 1)));		// [1]
    }
}
