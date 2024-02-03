package in.sachinshinde.dp.maximize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/maximize-the-profit-as-the-salesman/

/*
 	You are given an integer n representing the number of houses on a number line, 
 		numbered from 0 to n - 1.

        Additionally, you are given a 2D integer array offers where 
        	offers[i] = [starti, endi, goldi], 
        		indicating that ith buyer wants to buy all the houses 
        		from starti to endi for goldi amount of gold.
        
        As a salesman, your goal is to maximize your earnings by strategically selecting and 
        	selling houses to buyers.
        Return the maximum amount of gold you can earn.
        Note that different buyers can't buy the same house, and some houses may remain unsold.
        
        
        Example 1:
        ---------
        Input: n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
        Output: 3
        Explanation: There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
        We sell houses in the range [0,0] to 1st buyer for 1 gold and houses in the range [1,3] to 3rd buyer for 2 golds.
        It can be proven that 3 is the maximum amount of gold we can achieve.
        
        Example 2:
        ---------
        Input: n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
        Output: 10
        Explanation: There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
        We sell houses in the range [0,2] to 2nd buyer for 10 golds.
        It can be proven that 10 is the maximum amount of gold we can achieve.
         
        
        Constraints:
        -----------
            1 <= n <= 105
            1 <= offers.length <= 105
            offers[i].length == 3
            0 <= starti <= endi <= n - 1
            1 <= goldi <= 103
 */

public class MaximizeTheProfitAsTheSalesman {
    
    // video solution : https://youtu.be/rIhkjuuUk24
    
    //	Method 1: using   List<List<List<Integer>>>
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        int[] dp = new int[n + 1];
        
        List<List<List<Integer>>> offersList = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            offersList.add(new ArrayList<List<Integer>>());
        }
        
        for (List<Integer> currOffer : offers) {
            offersList.get(currOffer.get(1)).add(currOffer);
        }
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (List<Integer> currOffer : offersList.get(i - 1)) {
                dp[i] = Math.max(dp[i], dp[currOffer.get(0)] + currOffer.get(2));
            }
        }
        
        return dp[n];
    }
    
    	// Method 2 : using  List<int[]>[]
    	public  int maximizeTheProfit2(int n, List<List<Integer>> offers) {
	    int[] maxGold = new int[n + 1];
	    List<int[]>[] endStartGold = new ArrayList[n];

	    for (int i = 0; i < n; i++)
		endStartGold[i] = new ArrayList<>();

	    for (List<Integer> offer : offers) {
		int start = offer.get(0);
		int end = offer.get(1);
		int gold = offer.get(2);
		endStartGold[end].add(new int[]{start, gold});
	    }

	    for (int end = 1; end <= n; end++) {
		maxGold[end] = maxGold[end - 1];

	      for (int[] startAndGold : endStartGold[end - 1]) {
		  int start = startAndGold[0];
		  int gold = startAndGold[1];
		  maxGold[end] = Math.max(maxGold[end], maxGold[start] + gold);
	      }
	    }

	    return maxGold[n];
	}
    
    public static void main(String[] args) {
	MaximizeTheProfitAsTheSalesman salesman = new MaximizeTheProfitAsTheSalesman();
	System.out.println(salesman.maximizeTheProfit(5, Arrays.asList(
		Arrays.asList(0,0,1),
		Arrays.asList(0,2,2),
		Arrays.asList(1,3,2))));	// ans : 3
	System.out.println(salesman.maximizeTheProfit2(5, Arrays.asList(
		Arrays.asList(0,0,1),
		Arrays.asList(0,2,10),
		Arrays.asList(1,3,2))));	// ans : 10
	
	System.out.println(salesman.maximizeTheProfit(5, Arrays.asList(
		Arrays.asList(0,0,1),
		Arrays.asList(0,2,2),
		Arrays.asList(1,3,2))));	// ans : 3
	System.out.println(salesman.maximizeTheProfit2(5, Arrays.asList(
		Arrays.asList(0,0,1),
		Arrays.asList(0,2,10),
		Arrays.asList(1,3,2))));	// ans : 10
    }
}
