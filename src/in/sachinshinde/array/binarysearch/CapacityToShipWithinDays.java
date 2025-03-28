package in.sachinshinde.array.binarysearch;

//	https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

/*
 	A conveyor belt has packages that must be shipped from one port to another within days days.

        The ith package on the conveyor belt has a weight of weights[i]. 
        Each day, we load the ship with packages on the conveyor belt (in the order given by weights). 
        We may not load more weight than the maximum weight capacity of the ship.
        
        Return the least weight capacity of the ship that will result in 
        	all the packages on the conveyor belt being shipped within days days.

        Example 1:
        Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
        Output: 15
        Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
        1st day: 1, 2, 3, 4, 5
        2nd day: 6, 7
        3rd day: 8
        4th day: 9
        5th day: 10
        
        Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and 
        	splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
        
        
        Example 2:
        Input: weights = [3,2,2,4,1,4], days = 3
        Output: 6
        Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
        1st day: 3, 2
        2nd day: 2, 4
        3rd day: 1, 4
       
        
        Example 3:
        Input: weights = [1,2,3,1,1], days = 4
        Output: 3
        Explanation:
        1st day: 1
        2nd day: 2
        3rd day: 3
        4th day: 1, 1
         
        
        Constraints:
        
        1 <= days <= weights.length <= 5 * 104
        1 <= weights[i] <= 500
 */

/*
 	Video Solutions : 
 		[1] https://youtu.be/ER_oLmdc-nw
 		[2] https://youtu.be/MG-Ac4TAvTY
 	
 */

public class CapacityToShipWithinDays {
     public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;

        // find the lower bound and upper bound
        for (int weight: weights) {
            left = Math.max(weight, left);
            right += weight;
        }
        
        while (left < right) {
            int mid = (left + right) / 2;

            if(canShip(weights, days, mid)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
     }

     private boolean canShip(int[] weights, int days, int capacity) {
         int currLoad = 0;
         int dayCount = 1;

         for(int currWeight: weights) {
             currLoad += currWeight;
             if(currLoad > capacity) {
                 currLoad = currWeight;
                 dayCount++;
             }
         }

         return dayCount <= days;
     }
     
     public static void main(String[] args) {
	CapacityToShipWithinDays shipWithinDays = new CapacityToShipWithinDays();
	System.out.println(shipWithinDays.shipWithinDays(new int[] {1,2,3,4,5,6,7,8,9,10}, 5));		//	15
	System.out.println(shipWithinDays.shipWithinDays(new int[] {3,2,2,4,1,4}, 3));		//	6
	System.out.println(shipWithinDays.shipWithinDays(new int[] {1,2,3,1,1}, 4));		//	3
     }
}
