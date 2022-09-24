package in.sachinshinde.array;

//	https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/

/*
 	Given an array of integers arr and two integers k and threshold, 
 	return the number of sub-arrays of size k and average greater than or equal to threshold.

	Example 1:
	---------
	Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
	Output: 3
	Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. 
			All other sub-arrays of size 3 have averages less than 4 (the threshold).
	
	Example 2:
	---------
	Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
	Output: 6
	Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. 
				Note that averages are not integers.
 */

public class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
	public int numOfSubarrays(int[] arr, int k, int threshold) {
		int sum = 0, count = 0;
        for(int i = 0; i < arr.length; ++i) {  
        	sum += arr[i];
            
        	//	this will keep the sum window for k elements
            if(i >= k)	
            	sum -= arr[i - k];
            
            if(i + 1 >= k && sum / k >= threshold)
            	count++;
        }
        return count;
	}
	
	public static void main(String[] args) {
		NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold count =
				new NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold();
		
		int[] nums = new int[] {2,2,2,2,5,5,5,8};
		System.out.println(count.numOfSubarrays(nums, 3, 4));	//	3
		System.out.println(count.numOfSubarrays2(nums, 3, 4));	//	3
		
		nums = new int[] {1,1,1,1,1};
		System.out.println(count.numOfSubarrays(nums, 1, 0));	//	5
		System.out.println(count.numOfSubarrays2(nums, 1, 0));	//	5
		
		nums = new int[] {11,13,17,23,29,31,7,5,2,3};
		System.out.println(count.numOfSubarrays(nums, 3, 5));	//	6
		System.out.println(count.numOfSubarrays2(nums, 3, 5));	//	6
		
		nums = new int[] {7,7,7,7,7,7,7};
		System.out.println(count.numOfSubarrays(nums, 7, 7));	//	1
		System.out.println(count.numOfSubarrays2(nums, 7, 7));	//	1
		
		nums = new int[] {4,4,4,4};
		System.out.println(count.numOfSubarrays(nums, 4, 1));	//	1
		System.out.println(count.numOfSubarrays2(nums, 4, 1));	//	1
	}
	
	public int numOfSubarrays2(int[] arr, int k, int threshold) {
        int count = 0;
        int sumThreshold = threshold * k;
        int sum = 0;
        
        //	for first k elements
        for(int i = 0; i < k; i++)
            sum += arr[i];
        
        if(sum >= sumThreshold)
            count++;
        
        int length = arr.length;
        
        for(int i = k; i < length; i++) {
            sum -= arr[i - k];	// remove first element from last sum window
            sum += arr[i];	// add current element in the sum window
         
            if(sum >= sumThreshold)
                count++;
        }
        return count;
    }
	
    public int numOfSubarrays3(int[] arr, int k, int threshold) {
        int c = 0, s = arr.length, t = k * threshold, curr = 0;
        
        //	for first k
        for(int i = 0; i < k; ++i)
            curr += arr[i];
        
        for(int i = k; i < s; ++i) {
            if(curr >= t)		//	check 
            	c++;
            
            curr -= arr[i-k];	//	leaving first element from sum window
            curr += arr[i];		//	adding current element into sum window
        }
        
        if(curr >= t)
        	c++;
        
        return c;
    }
	
}
