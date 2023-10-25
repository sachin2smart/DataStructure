package in.sachinshinde.graph.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//	https://leetcode.com/problems/bus-routes/


/*
 * 	You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

        For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
        You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
        All the values of routes[i] are unique.
        
        Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
        
        Example 1:
        Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
        Output: 2
        Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
        
        Example 2:
        Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
        Output: -1
 */

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int k = 0; k < routes.length; k++) {
            int[] route = routes[k];
            int n = route.length;
            for (int i = 0; i < n; i++) {
                int stop = route[i];
                if (!graph.containsKey(stop))
                    graph.put(stop, new ArrayList<>());
                graph.get(stop).add(k);
            }
        }
        
        if (!graph.containsKey(source) || !graph.containsKey(target)) 
            return -1;
        
        if (source == target) 
            return 0;
        
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> busTaken = new HashSet<>();
        Set<Integer> stopVisited = new HashSet<>();
        
        q.add(source);
        
        int cnt = 0;
        
        while (!q.isEmpty()) {
            cnt++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curStop = q.poll();
                for (int bus : graph.get(curStop)) {
                    if (busTaken.contains(bus)) 
                        continue;
                    busTaken.add(bus);
                    for (int nextStop : routes[bus]) {
                        if (stopVisited.contains(nextStop)) 
                            continue;
                        if (nextStop == target) 
                            return cnt;
                        q.add(nextStop);
                        stopVisited.add(nextStop);
                    }
                }
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
	BusRoutes busRoutes = new BusRoutes();
	int numBusRequired = busRoutes.numBusesToDestination(new int[][] {{1,2,7},{3,6,7}}, 1, 6);
	System.out.println(numBusRequired);	//	2
	
	numBusRequired = busRoutes.numBusesToDestination(new int[][] {{7,12},{4,5,15},{6},{15,19},{9,12,13}}, 15, 12);
	System.out.println(numBusRequired);	//	-1
    }
}
