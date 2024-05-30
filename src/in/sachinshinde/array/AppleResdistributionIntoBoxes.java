package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/apple-redistribution-into-boxes/

/*
	You are given an array apple of size n and an array capacity of size m.

        There are n packs where the ith pack contains apple[i] apples. 
        There are m boxes as well, and the ith box has a capacity of capacity[i] apples.
        
        Return the minimum number of boxes you need to select 
        	to redistribute these n packs of apples into boxes.
        
        Note that, apples from the same pack can be distributed into different boxes.
        
        Example 1:
        ---------
        Input: apple = [1,3,2], capacity = [4,3,1,5,2]
        Output: 2
        Explanation: We will use boxes with capacities 4 and 5.
        It is possible to distribute the apples as the total capacity is greater than or 
        	equal to the total number of apples.
        
        Example 2:
        ---------
        Input: apple = [5,5,5], capacity = [2,4,2,7]
        Output: 4
        Explanation: We will need to use all the boxes.
        
        Constraints:
        -----------
        1 <= n == apple.length <= 50
        1 <= m == capacity.length <= 50
        1 <= apple[i], capacity[i] <= 50
        The input is generated such that it's possible to redistribute packs of apples into boxes.
 */

public class AppleResdistributionIntoBoxes {

    public int minimumBoxes(int[] apple, int[] capacity) {
		int sum = Arrays.stream(apple).sum();
		Arrays.sort(capacity); // ascending order (big at last)
		int res = 0;
		for(int i = capacity.length-1; i >= 0; i--){
			res++;
			sum -= capacity[i];
			if(sum <= 0){
				break;
			}
		}
		return res;
    }
	
    public static void main(String[] args) {
	AppleResdistributionIntoBoxes apples = new AppleResdistributionIntoBoxes();
	System.out.println(apples.minimumBoxes(new int[] {1,3,2}, new int[] {4,3,1,5,2}));	// 2
	System.out.println(apples.minimumBoxes(new int[] {5,5,5}, new int[] {2,4,2,7}));	// 4
    }
}
