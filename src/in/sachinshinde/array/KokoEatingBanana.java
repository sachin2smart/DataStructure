package in.sachinshinde.array;

//	https://leetcode.com/problems/koko-eating-bananas/

/*
 	Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. 
 	The guards have gone and will come back in h hours.

        Koko can decide her bananas-per-hour eating speed of k. 
        Each hour, she chooses some pile of bananas and eats k bananas from that pile. 
        If the pile has less than k bananas, 
        	she eats all of them instead and will not eat any more bananas during this hour.
        
        Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
        Return the minimum integer k such that she can eat all the bananas within h hours.
        
         
        
        Example 1:
        Input: piles = [3,6,7,11], h = 8
        Output: 4
        
        Example 2:
        Input: piles = [30,11,23,4,20], h = 5
        Output: 30
        
        Example 3:
        Input: piles = [30,11,23,4,20], h = 6
        Output: 23
        
        Constraints:
        	1 <= piles.length <= 104
        	piles.length <= h <= 109
        	1 <= piles[i] <= 109
 */

/*
 	Video Solution:
 		[1] https://youtu.be/U2SozAs9RzA
 		[2] https://youtu.be/qyfekrNni90
 */

public class KokoEatingBanana {
    
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000000;
        while (left < right) {
            int mid = (left + right) / 2;
            int totalPiles = 0;
            for (int pile: piles)
                 totalPiles += (mid +pile-1) / mid;
            if (totalPiles > h)
                left = mid + 1;
            else
                right = mid;
        }
        
        return left;
    }
    
    public int minEatingSpeed2(int[] piles, int h) {
        long sumPiles = 0;
        for (int pile : piles)
            sumPiles += pile;

        int numPiles = piles.length;
        int left = (int) ((sumPiles - 1) / h) + 1;
        int right = (int) ((sumPiles - numPiles) / (h - numPiles + 1) + 1);
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            long totalTime = 0;
            for (int pile : piles)
                totalTime += ((pile - 1) / mid + 1);
            if (totalTime <= h)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
    
    public static void main(String[] args) {
	KokoEatingBanana kokoEatingBanana = new KokoEatingBanana();
	System.out.println(kokoEatingBanana.minEatingSpeed(new int[] {3,6,7,11}, 8));		//	4
	System.out.println(kokoEatingBanana.minEatingSpeed(new int[] {30,11,23,4,20}, 5));	//	30
	System.out.println(kokoEatingBanana.minEatingSpeed(new int[] {30,11,23,4,20}, 6));	//	23
	
	System.out.println(kokoEatingBanana.minEatingSpeed2(new int[] {3,6,7,11}, 8));		//	4
	System.out.println(kokoEatingBanana.minEatingSpeed2(new int[] {30,11,23,4,20}, 5));	//	30
	System.out.println(kokoEatingBanana.minEatingSpeed2(new int[] {30,11,23,4,20}, 6));	//	23
    }
}
