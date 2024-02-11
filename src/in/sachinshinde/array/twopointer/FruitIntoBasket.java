package in.sachinshinde.array.twopointer;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/fruit-into-baskets/

/*
 	You are visiting a farm that has a single row of fruit trees arranged from left to right. 
 	The trees are represented by an integer array fruits where 
 	fruits[i] is the type of fruit the ith tree produces.

        You want to collect as much fruit as possible. 
        However, the owner has some strict rules that you must follow:
        
        	-	You only have two baskets, and each basket can only hold a single type of fruit. 
        		There is no limit on the amount of fruit each basket can hold.
        	-	Starting from any tree of your choice, you must pick exactly one fruit from every tree 
        		(including the start tree) while moving to the right. 
        		The picked fruits must fit in one of your baskets.
        	-	Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
        	
        Given the integer array fruits, return the maximum number of fruits you can pick.
        
        Example 1:
        ---------
            Input: fruits = [1,2,1]
            Output: 3
            Explanation: We can pick from all 3 trees.
        
        Example 2:
        ---------
            Input: fruits = [0,1,2,2]
            Output: 3
            Explanation: We can pick from trees [1,2,2].
            If we had started at the first tree, we would only pick from trees [0,1].
        
        Example 3:
        ---------
            Input: fruits = [1,2,3,2,2]
            Output: 4
            Explanation: We can pick from trees [2,3,2,2].
            If we had started at the first tree, we would only pick from trees [1,2].
        
        Constraints:
        -----------
            1 <= fruits.length <= 105
            0 <= fruits[i] < fruits.length
 */

public class FruitIntoBasket {

    public int totalFruit2(int[] fruits) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int max = 0, i = 0;
        
        for(int j = 0; j < fruits.length; ++j) {
            hm.put(fruits[j], hm.getOrDefault(fruits[j], 0) + 1);
            
            while(hm.size() > 2) {
        	hm.put(fruits[i], hm.get(fruits[i]) - 1);
        	
                if(hm.get(fruits[i]) == 0) 
                    hm.remove(fruits[i]);
                
                i++;
            }
            
            max = Math.max(max, j - i + 1);
        }
        
        return max;
    }
    
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int[] count = new int[n];
        int left = 0;
        int i = 0;
        int max = 0;

        for(int right = 0; right < n; right++) {
            count[fruits[right]]++;
            
            if(count[fruits[right]] == 1) 
        	i++;
            
            while(left < right && i > 2) {
                count[fruits[left]]--;
                
                if(count[fruits[left]] == 0) {
                    i--;
                }
                
                left++;
            }
            
            max = Math.max(max, right - left + 1);
        }

        return max;   
    }
    
    public int totalFruit3(int[] fruits) {
        int max = 0;
        int curMax = 0;
        int prev = -1;
        int prev2 = -1;
        int prevCount = 0;

        for (int fruit: fruits) {
            if (fruit == prev || fruit == prev2) {
                curMax++;
            } 
            else {
                max = Math.max(max, curMax);
                curMax = prevCount + 1;
            }
            
            if (fruit == prev) {
                prevCount++;
            } 
            else {
                prevCount = 1;
                prev2 = prev;
                prev = fruit;
            }
        }
        return Math.max(max, curMax);
    }
    
    public int totalFruit4(int[] fruits) {
        int max = 0, currMax = 0, next = 0, a = 0, b = 0;
        for (int c :  fruits) {
            currMax = (c == a || c == b) ? currMax+1 : next+1;
            next = (c == b) ? next+1 : 1;
            
            if (b != c) {
        	a = b; 
        	b = c;
            }
            
            max = Math.max(max, currMax);
        }
        return max;
    }
    
    public static void main(String[] args) {
	FruitIntoBasket fruits = new FruitIntoBasket();
	
	System.out.println(fruits.totalFruit(new int[] {1,2,1}));	//	3
	System.out.println(fruits.totalFruit(new int[] {0,1,2,2}));	//	3
	System.out.println(fruits.totalFruit(new int[] {1,2,3,2,2}));	//	4
	
	System.out.println(fruits.totalFruit2(new int[] {1,2,1}));	//	3
	System.out.println(fruits.totalFruit2(new int[] {0,1,2,2}));	//	3
	System.out.println(fruits.totalFruit2(new int[] {1,2,3,2,2}));	//	4
	
	System.out.println(fruits.totalFruit3(new int[] {1,2,1}));	//	3
	System.out.println(fruits.totalFruit3(new int[] {0,1,2,2}));	//	3
	System.out.println(fruits.totalFruit3(new int[] {1,2,3,2,2}));	//	4
	
	System.out.println(fruits.totalFruit4(new int[] {1,2,1}));	//	3
	System.out.println(fruits.totalFruit4(new int[] {0,1,2,2}));	//	3
	System.out.println(fruits.totalFruit4(new int[] {1,2,3,2,2}));	//	4
    }
}
