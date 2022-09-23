package in.sachinshinde.array;

import java.util.Map;
import java.util.TreeMap;

//	Self prepared

/*
 	Given some array nums and a positive integer k, 
 	determine if its possible to divide this array into sets of k consecutive numbers.
 	Note: There is a possibility of repetition of numbers
 */

public class CheckForCountOfKConsecutiveNumbersInArrayHavingRepeatedElements {
	/*
	Solution:
	--------
		1. Use TreeMap to store array elements and their occurrence. 
				Treemap helps us to store the element in sorting orders.
	
		2. Iterate over treemap until it's not empty.
	
		3. Takeout the first key (firstKey) from treemap and 
				start searching next K consecutive element.
	*/	
	public boolean isConsucutiveSeqFound(int[] arr, int k) {

	    Map<Integer, Integer> map = new TreeMap<>();
	    
	    for (int i = 0; i < arr.length; i++)
	        map.put(arr[i],  map.getOrDefault(arr[i], 0) + 1);
	    
	    while(!map.isEmpty()) {
	        int firstKey = ((TreeMap<Integer, Integer>) map).firstKey();

	        for(int i = 0; i < k; i++) {
	            int key = firstKey + i;

	            if(!map.containsKey(key))
	                return false;
	            else {
	                map.put(key, map.get(key) - 1);

	                if (map.get(key) == 0)
	                    map.remove(key);
	            }
	        }
	    }
	    
		return true;
	}
	
	public static void main(String[] args) {
		CheckForCountOfKConsecutiveNumbersInArrayHavingRepeatedElements count = 
				new CheckForCountOfKConsecutiveNumbersInArrayHavingRepeatedElements();
		
		int[] nums = new int[] {1,2,1,3,3,2};
		System.out.println(count.isConsucutiveSeqFound(nums, 3));	// True
		
		nums = new int[] {1,2,2,3,3,4,4,5};
		System.out.println(count.isConsucutiveSeqFound(nums, 4));	// True
	}
}
