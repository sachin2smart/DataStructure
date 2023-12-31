package in.sachinshinde.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://leetcode.com/problems/cheapest-flights-within-k-stops/

/*
 	There are n cities connected by some number of flights. 
 	You are given an array flights where 
 		flights[i] = [fromi, toi, pricei] 
 			indicates that there is a flight from city fromi to city toi with cost pricei.

        You are also given three integers src, dst, and k, 
        	return the cheapest price from src to dst with at most k stops. 
        If there is no such route, return -1.
        
        
        Example 1:
        ---------        
        Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
        Output: 700
        Explanation:
        The optimal path with at most 1 stop from city 0 to 3 has cost 100 + 600 = 700.
        Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
        
        Example 2:
        ---------
        Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
        Output: 200
        Explanation:
        The optimal path with at most 1 stop from city 0 to 2 has cost 100 + 100 = 200.
        
        Example 3:
        ---------
        Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
        Output: 500
        Explanation:
        The optimal path with no stops from city 0 to 2 has cost 500.
         
        
        Constraints:
        -----------
            1 <= n <= 100
            0 <= flights.length <= (n * (n - 1) / 2)
            flights[i].length == 3
            0 <= fromi, toi < n
            fromi != toi
            1 <= pricei <= 104
            There will not be any multiple flights between two cities.
            0 <= src, dst, k < n
            src != dst
 */
public class CheapestFlightsWithinKStops {
    
//------------------------------------------------------------------------------------------------------------------
    //	Method 1: DFS
    
    int totalCost;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
	totalCost = Integer.MAX_VALUE;
	Map<Integer, List<int[]>> hm = new HashMap<>();
	
	//	src	---->	[dest,	cost]
	for(int[] flight: flights) {
	    hm.putIfAbsent(flight[0], new ArrayList<>());
	    hm.get(flight[0]).add(new int[] {flight[1], flight[2]});
	}
	
	dfs(hm, src, dst, k+1, 0);
	
	return totalCost == Integer.MAX_VALUE ? -1 : totalCost;
    }
    
    public void dfs(Map<Integer, List<int[]>> hm, int src, int dst, int k, int currCost) {
	if(k < 0)
	    return;
	
	if(src == dst) {
	    totalCost = currCost;
	    return;
	}
	
	if(!hm.containsKey(src))
	    return;
	
	for(int[] flight: hm.get(src))
	    if(flight[1] + currCost < totalCost)	//  flight[1] is the cost to reach destination flight[0]
		dfs(hm, flight[0], dst, k-1, flight[1] + currCost);	// flight[0] is the next stop from src
	
    }

//------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
	CheapestFlightsWithinKStops flights = new CheapestFlightsWithinKStops();
	//----------------------------------------------------------------------------------
	int[][] flights1 = new int[][] {{0,1,100}, 
	    				{1,2,100},
	    				{2,0,100},
	    				{1,3,600},
	    				{2,3,200}};
	System.out.println(flights.findCheapestPrice(4, flights1, 0, 3, 1));	//	700
	//----------------------------------------------------------------------------------	
	int[][] flights2 = new int[][] {{0,1,100}, 
	    				{1,2,100},
	    				{0,2,500}};
	System.out.println(flights.findCheapestPrice(3, flights2, 0, 2, 1));	//	200
	//----------------------------------------------------------------------------------
	int[][] flights3 = new int[][] {{0,1,100}, 
                        		{1,2,100},
                        		{0,2,500}};
	System.out.println(flights.findCheapestPrice(3, flights3, 0, 2, 0));	//	500
	//----------------------------------------------------------------------------------
    }
}