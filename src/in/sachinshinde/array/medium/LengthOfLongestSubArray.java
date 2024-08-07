package in.sachinshinde.array.medium;

import java.util.HashMap;

//	https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/

/*
 	Given an array of integers, find the length of the longest sub-array with a sum that equals 0.

	Examples: 
	
	Input: arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
	Output: 5
	Explanation: The longest sub-array with 
	elements summing up-to 0 is {-2, 2, -8, 1, 7}

 */

public class LengthOfLongestSubArray {

	int maxLen(int arr[]) {
		int n = arr.length;
        int max_len = 0;
        for (int i = 0; i < n; i++) {
            int curr_sum = 0;
            for (int j = i; j < n; j++) {
                curr_sum += arr[j];
                if (curr_sum == 0)
                    max_len = Math.max(max_len, j - i + 1);
            }
        }
        return max_len;
    }
	
	int maxLen2(int arr[]) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
 
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
 
            if (arr[i] == 0 && maxLen == 0) {
                maxLen = 1;
            }
 
            if (sum == 0) {
                maxLen = i + 1;
            }

            if (hm.get(sum) != null) {
                maxLen = Math.max(maxLen, i - hm.get(sum));
            }
            else {
                hm.put(sum, i);
            }
        }
 
        return maxLen;
    }
	
	public static void main(String[] args) {
		LengthOfLongestSubArray subArray = new LengthOfLongestSubArray();
		System.out.println(" {2,0,-2} : "+subArray.maxLen(new int[] {2,0,-2}));	//	3
		System.out.println(" {1,2,3,4,5} : "+subArray.maxLen(new int[] {1,2,3,4,5}));	//	0
		System.out.println(" {1,-1,2,0,-2,5,9} : "+subArray.maxLen(new int[] {1,-1,2,0,-2,5,9}));	//	5
		System.out.println(" {2,-2,5,-5,0,0,0,0,9} : "+subArray.maxLen(new int[] {2,-2,5,-5,0,0,0,0,9}));	//	8
		
		System.out.println("\n {2,0,-2} : "+subArray.maxLen2(new int[] {2,0,-2}));	//	3
		System.out.println(" {1,2,3,4,5} : "+subArray.maxLen2(new int[] {1,2,3,4,5}));	//	0
		System.out.println(" {1,-1,2,0,-2,5,9} : "+subArray.maxLen2(new int[] {1,-1,2,0,-2,5,9}));	//	5
		System.out.println(" {2,-2,5,-5,0,0,0,0,9} : "+subArray.maxLen2(new int[] {2,-2,5,-5,0,0,0,0,9}));	//	8

	}
}