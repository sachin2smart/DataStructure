package in.sachinshinde.dp.distribute_candies;

import java.util.Arrays;

//	https://leetcode.com/problems/put-marbles-in-bags/

/*
 	You have k bags. You are given a 0-indexed integer array weights where 
 		weights[i] is the weight of the ith marble. 
 	You are also given the integer k.

        Divide the marbles into the k bags according to the following rules:
        
        -	No bag is empty.
        -	If the ith marble and jth marble are in a bag, then all marbles with an index 
        	between the ith and jth indices should also be in that same bag.
        -	If a bag consists of all the marbles with an index from i to j inclusively, 
        	then the cost of the bag is weights[i] + weights[j].
        
        The score after distributing the marbles is the sum of the costs of all the k bags.
        Return the difference between the maximum and minimum scores among marble distributions.
        
        Example 1:
        ---------
            Input: weights = [1,3,5,1], k = 2
            Output: 4
            Explanation: 
                The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6. 
                The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10. 
                Thus, we return their difference 10 - 6 = 4.
        
        Example 2:
        ---------
            Input: weights = [1, 3], k = 2
            Output: 0
            Explanation: The only distribution possible is [1],[3]. 
            	Since both the maximal and minimal score are the same, we return 0.
         
        
        Constraints:
        
        1 <= k <= weights.length <= 105
        1 <= weights[i] <= 109
 */

//	video Solution: 	https://youtu.be/K0opAL-J07U

public class PutMarblesInBags {
    
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] pairs = new int[n-1];
        
        for(int i=1; i<n; i++) {
            pairs[i-1] = weights[i] + weights[i-1];
        }
        
        Arrays.sort(pairs);
        
        long max = 0, min = 0;
        
        for(int i=0; i<k-1; i++) {
            min += pairs[i];
            max += pairs[n-i-2];
        }
        
        return max-min;
    }
    
    public long putMarbles2(int[] weights, int k) {
        int n = weights.length;
        int[] pairs = new int[n-1];
        
        for(int i=1; i<n; i++) {
            pairs[i-1] = weights[i] + weights[i-1];
        }
        
        Arrays.sort(pairs);
        
        long res = 0;
        
        for(int i=0; i<k-1; i++) {
            res += pairs[n-i-2] - pairs[i];
        }
        
        return res;
    }
    
    public static void main(String[] args) {
	PutMarblesInBags marbles = new PutMarblesInBags();
	System.out.println(marbles.putMarbles(new int[] {1,3,5,1}, 2));		// 4
	System.out.println(marbles.putMarbles(new int[] {1,2,5,1}, 3));		// 4
	System.out.println(marbles.putMarbles(new int[] {1,3}, 2));		// 0
	
	System.out.println(marbles.putMarbles2(new int[] {1,3,5,1}, 2));	// 4
	System.out.println(marbles.putMarbles2(new int[] {1,2,5,1}, 3));	// 4
	System.out.println(marbles.putMarbles2(new int[] {1,3}, 2));		// 0
    }
}

/*
 	1,3,5,1    2

[1]       2    6
[3,5,1]   4

[1,3]     4    10
[5,1]     6

[1,3,5]   6    8			[10-6]=4
[1]       2
-----------------
pairs		=	[4,8,6]
sort(pairs)	=	[4,6,8]

iterations 0 (i.e < k-1)
(i)		min(i)			max(n-2-i) :: n=4
0		4				8

====[8-4] = 4
-------------------------------------------------
1,2,5,1		3

[1]		2	12
[2]		4
[5,1]		6

[1,2]		3	15
[5]		10
[1]		2

[1]		2	11		[15-11]=4
[2,5]		7
[1]		2

-----
pairs
[3]
[7]
[6]

sort(pairs)
[3]
[6]
[7]

iterations 0 to 1
(i)		min(i)			max(n-2-i) :: n=4
0		3			7
1		3+6=9			7+6=13

====[13-9] = 4
-------------------------------------------------

1,3		2

[1]		2		8	[8-8]=0
[3]		6

--------
pairs
[4]

sort(pairs) = [4]

iterations 0 

(i)		min(i)			max(n-2-i) :: n=2
0		4				4

==== [4-4]	= 0
-------------------------------------------------
*/