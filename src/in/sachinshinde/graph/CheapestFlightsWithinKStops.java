package in.sachinshinde.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
    
    public int findCheapestPrice_dfs(int n, int[][] flights, int src, int dst, int k) {
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
	
	if(hm.containsKey(src))
    	    for(int[] flight: hm.get(src))
    		if(flight[1] + currCost < totalCost)	//  flight[1] is the cost to reach destination flight[0]
    		    dfs(hm, flight[0], dst, k-1, flight[1] + currCost);	// flight[0] is the next stop/layover (dst) 
									//	from flight[1] (src)
    }

//------------------------------------------------------------------------------------------------------------------
    
    //	Method 1: BFS
    public int findCheapestPrice_bfs(int n, int[][] flights, int src, int dst, int k) {
	Map<Integer, List<int[]>> hm = new HashMap<>();
	
	for(int[] flight: flights) {
	    hm.putIfAbsent(flight[0], new ArrayList<>());
	    hm.get(flight[0]).add(new int[] {flight[1], flight[2]});
	}
	
	int step = 0;
	int ans = Integer.MAX_VALUE;
	
	Queue<int[]> q = new LinkedList<>();
	q.offer(new int[] {src, 0});
	
	while(!q.isEmpty()) {
	    int qSize = q.size();
	    for(int i = 0; i < qSize; i++) {
        	int[] curr = q.poll();
        	    
        	if(curr[0] == dst)
        	    ans = Math.min(ans, curr[1]);
        	    
        	if(hm.containsKey(curr[0]))
        	    for(int[] flight: hm.get(curr[0]))
        		if(curr[1] + flight[1] < ans)
        		    q.offer(new int[] {flight[0], curr[1] + flight[1]});
	    }
	    if(step++ > k)
		break;
	 }
	
	return ans == Integer.MAX_VALUE ? -1 : ans;
    }
//------------------------------------------------------------------------------------------------------------------
    //	Method 3: Bellmon Ford
    
    public int findCheapestPrice_BellmonFord(int n, int[][] flights, int src, int dst, int k) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        
        for(int i=0; i<=k; i++) {
            int[] currCosts = Arrays.copyOf(costs, n);
            for(int[] flight: flights) {
                int currFlight = flight[0];
                int nextFlight = flight[1];
                int price = flight[2];
                
                if(costs[currFlight] == Integer.MAX_VALUE)
                    continue;
                
                currCosts[nextFlight] = Math.min(currCosts[nextFlight], costs[currFlight] + price);
            }
            costs = currCosts;
        }
        return costs[dst] == Integer.MAX_VALUE?-1 : costs[dst];
    }
//------------------------------------------------------------------------------------------------------------------
    //	Method 3: Dijkstra
    
    public int findCheapestPrice_Dijkstra(int n, int[][] flights, int src, int dst, int k) {
	//	[src --> [dst --> price]]
	Map<Integer, Map<Integer, Integer>> srcToDstPriceMap = new HashMap<>();
	
	for(int[] flight: flights) {
	    srcToDstPriceMap.putIfAbsent(flight[0], new HashMap<>());
	    srcToDstPriceMap.get(flight[0]).put(flight[1], flight[2]);
	}
	
	Queue<int[]> pq = new PriorityQueue<>(
		(a,b) -> (Integer.compare(a[1], b[1])));
	pq.add(new int[] {src, 0, k+1});
	
	
	while(!pq.isEmpty()) {
	    int[] currTravelSpot = pq.poll();
	    
	    int currCity = currTravelSpot[0];
	    int currPrice = currTravelSpot[1];
	    int numStopToPass = currTravelSpot[2];
	    
	    if(currCity == dst)
		return currPrice;
	    
	    if(numStopToPass > 0) {
		Map<Integer, Integer> dstToPriceMap = 
			srcToDstPriceMap.getOrDefault(currCity, new HashMap<>());
		
		for(int destCity: dstToPriceMap.keySet())
		    pq.add(new int[] {
			    destCity, 
			    currPrice + dstToPriceMap.get(destCity), 
			    numStopToPass - 1});
	    }
	}
	
	return -1;
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
	System.out.println(flights.findCheapestPrice_dfs(4, flights1, 0, 3, 1));	//	700
	System.out.println(flights.findCheapestPrice_bfs(4, flights1, 0, 3, 1));	//	700
	System.out.println(flights.findCheapestPrice_BellmonFord(4, flights1, 0, 3, 1));//	700
	System.out.println(flights.findCheapestPrice_Dijkstra(4, flights1, 0, 3, 1));	//	700
	//----------------------------------------------------------------------------------	
	int[][] flights2 = new int[][] {{0,1,100}, 
	    				{1,2,100},
	    				{0,2,500}};
	System.out.println(flights.findCheapestPrice_dfs(3, flights2, 0, 2, 1));	//	200
	System.out.println(flights.findCheapestPrice_bfs(3, flights2, 0, 2, 1));	//	200
	System.out.println(flights.findCheapestPrice_BellmonFord(3, flights2, 0, 2, 1));//	200
	System.out.println(flights.findCheapestPrice_Dijkstra(3, flights2, 0, 2, 1));	//	200
	//----------------------------------------------------------------------------------
	int[][] flights3 = new int[][] {{0,1,100}, 
                        		{1,2,100},
                        		{0,2,500}};
	System.out.println(flights.findCheapestPrice_dfs(3, flights3, 0, 2, 0));	//	500
	System.out.println(flights.findCheapestPrice_bfs(3, flights3, 0, 2, 0));	//	500
	System.out.println(flights.findCheapestPrice_BellmonFord(3, flights3, 0, 2, 0));//	500
	System.out.println(flights.findCheapestPrice_Dijkstra(3, flights3, 0, 2, 0));	//	500
	//----------------------------------------------------------------------------------
    }
}