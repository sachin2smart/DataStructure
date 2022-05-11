package in.sachinshinde.array;

import java.util.Arrays;

/*
 * 	https://leetcode.com/problems/reducing-dishes/
 * 
 */

public class ReducingDishes {
	public int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		
		int n = satisfaction.length;
		int start = n-1;
		int total = 0;
		
		for(int i = n-1; i>=0; i--) {
			total += satisfaction[i];
			if(total < 0) {
				break;
			}
			start--;
		}
		start++;
		
		int k = 1;
		total = 0;
		for(int i=start; i<n ; i++) {
			total += (k++) * (satisfaction[i]);
		}
		return total;
    }
	
	public static void main(String args[]) {
		ReducingDishes reducingDishes = new ReducingDishes();
		int[] satisfaction = new int[] {4,3,2};
		System.out.println("The maximum sum of like-time coefficient : "+ reducingDishes.maxSatisfaction(satisfaction));
		satisfaction = new int[] {-5,-1,5};
		System.out.println("The maximum sum of like-time coefficient : "+ reducingDishes.maxSatisfaction(satisfaction));
	}
}
