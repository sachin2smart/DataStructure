package in.sachinshinde.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

//	https://leetcode.com/problems/ipo/

/*
 	Suppose LeetCode will start its IPO soon. 
 	In order to sell a good price of its shares to Venture Capital, 
 	LeetCode would like to work on some projects to increase its capital before the IPO. 
 	Since it has limited resources, 
 		it can only finish at most k distinct projects before the IPO. 
 	Help LeetCode design the best way to maximize its total capital 
 		after finishing at most k distinct projects.

        You are given n projects where the ith project has a pure profit profits[i] and 
        	a minimum capital of capital[i] is needed to start it.
        
        Initially, you have w capital. 
        When you finish a project, you will obtain its pure profit and 
        the profit will be added to your total capital.
        
        Pick a list of at most k distinct projects from given projects to maximize your final capital, and 
        return the final maximized capital.
        
        The answer is guaranteed to fit in a 32-bit signed integer.
        
        Example 1:
        ---------
            Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
            Output: 4
            Explanation: Since your initial capital is 0, you can only start the project indexed 0.
            After finishing it you will obtain profit 1 and your capital becomes 1.
            With capital 1, you can either start the project indexed 1 or the project indexed 2.
            Since you can choose at most 2 projects, you need to finish the project indexed 2 
            	to get the maximum capital.
            Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
        
        Example 2:
        ---------
            Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
            Output: 6
        
        Constraints:
        -----------
            1 <= k <= 105
            0 <= w <= 109
            n == profits.length
            n == capital.length
            1 <= n <= 105
            0 <= profits[i] <= 104
            0 <= capital[i] <= 109
 */

public class IPO {

    //	Method 1: Creating Pair class  
    
    class Pair implements Comparable<Pair> {
        int capital, profit;

        public Pair(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        public int compareTo(Pair pair) {
            return capital - pair.capital;
        }
    }
    
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Pair [] projects = new Pair[n];
        for(int i=0; i<n; i++) {
            projects[i] = new Pair(capital[i], profits[i]);
        }
        
        Arrays.sort(projects);
        PriorityQueue<Integer> priority = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        int j = 0;
        int res = w;
        
        for(int i=0; i<k; i++){
            while(j<n && projects[j].capital <= res){
                priority.add(projects[j++].profit);
            }
            if(priority.isEmpty()){
                break;
            }
            res += priority.poll();
        }
        return res;
    }
    
    //	-----------------------------------------------------------------------------------------------------
    
    //	Method 2 : Two Heaps 
    
    public int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        
	int n = profits.length;
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>(n, (a, b) -> capital[a] - capital[b]);
        PriorityQueue<Integer> maxProfitsHeap = new PriorityQueue<>(n, (a, b) -> profits[b] - profits[a]);
        
        for (int i=0; i<n; i++) {
           minCapitalHeap.offer(i); 
        }
        
        for (int i=0; i<k; i++) {
            while (!minCapitalHeap.isEmpty() && capital[minCapitalHeap.peek()] <= w) {
        	maxProfitsHeap.add(minCapitalHeap.poll());
            }
            
            if (maxProfitsHeap.isEmpty()) {
                break;
            }
            
            w += profits[maxProfitsHeap.poll()];
        }
        
        return w;
    }
    
    //	---------------------------------------------------------------------------------------------------
    
    //	Method 3: Using 2D arrays with 2 columns & PriorityQueue (MaxHeap on profits)
    
    public int findMaximizedCapital3(int k, int w, int[] profits, int[] capital) {
        int n = capital.length;
        
        int[][] pairs = new int[n][2];

        for(int j=0;j<n;j++) {
            pairs[j][0] = capital[j];
            pairs[j][1] = profits[j];
        }
        
        // Use minimum capital for maximum profit
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);	// sorted pairs on capital values increasing order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);  // profits in decreasing order
        
        int currIpo = 0;
        while(k > 0) {
            while(currIpo < n && pairs[currIpo][0] <= w) {
                maxHeap.offer(pairs[currIpo][1]);
                currIpo++;
            }

            if(maxHeap.isEmpty()) {
                break;
            }

            w += maxHeap.poll();
            k--;
        }
        
        return w;
    }
    //	---------------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
	IPO ipo = new IPO();
	
	System.out.println(ipo.findMaximizedCapital(2, 0, new int[] {1,2,3}, new int[] {0,1,1}));	// 4
	System.out.println(ipo.findMaximizedCapital(3, 0, new int[] {1,2,3}, new int[] {0,1,2}));	// 6
	
	System.out.println(ipo.findMaximizedCapital2(2, 0, new int[] {1,2,3}, new int[] {0,1,1}));	// 4
	System.out.println(ipo.findMaximizedCapital2(3, 0, new int[] {1,2,3}, new int[] {0,1,2}));	// 6
	
	System.out.println(ipo.findMaximizedCapital3(2, 0, new int[] {1,2,3}, new int[] {0,1,1}));	// 4
	System.out.println(ipo.findMaximizedCapital3(3, 0, new int[] {1,2,3}, new int[] {0,1,2}));	// 6
    }
}
