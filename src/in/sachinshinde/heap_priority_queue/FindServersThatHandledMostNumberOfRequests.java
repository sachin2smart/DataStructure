package in.sachinshinde.heap_priority_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

//  https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/

/*
        You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously.
        Each server has infinite computational capacity but cannot handle more than one request at a time.

        The requests are assigned to servers according to a specific algorithm:
            -   The ith (0-indexed) request arrives.
            -   If all servers are busy, the request is dropped (not handled at all).
            -   If the (i % k)th server is available, assign the request to that server.
            -   Otherwise, assign the request to the next available server
                (wrapping around the list of servers and starting from 0 if necessary).
                For example, if the ith server is busy, try to assign the request to the (i+1)th server,
                    then the (i+2)th server, and so on.

        You are given a strictly increasing array arrival of positive integers,
            where arrival[i] represents the arrival time of the ith request, and
            another array load, where load[i] represents the load of the ith request (the time it takes to complete).

       Your goal is to find the busiest server(s).
       A server is considered busiest if it handled the most number of requests successfully among all the servers.

       Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.

        Example 1:
        ---------
        Input: k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
        Output: [1]
        Explanation:
        All of the servers start out available.
        The first 3 requests are handled by the first 3 servers in order.
        Request 3 comes in. Server 0 is busy, so it's assigned to the next available server, which is 1.
        Request 4 comes in. It cannot be handled since all servers are busy, so it is dropped.
        Servers 0 and 2 handled one request each, while server 1 handled two requests. Hence server 1 is the busiest server.

        Example 2:
        ---------
        Input: k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
        Output: [0]
        Explanation:
        The first 3 requests are handled by first 3 servers.
        Request 3 comes in. It is handled by server 0 since the server is available.
        Server 0 handled two requests, while servers 1 and 2 handled one request each. Hence server 0 is the busiest server.

        Example 3:
        ---------
        Input: k = 3, arrival = [1,2,3], load = [10,12,11]
        Output: [0,1,2]
        Explanation: Each server handles a single request, so they are all considered the busiest.


        Constraints:
        -----------
            1 <= k <= 105
            1 <= arrival.length, load.length <= 105
            arrival.length == load.length
            1 <= arrival[i], load[i] <= 109
            arrival is strictly increasing.
 */

public class FindServersThatHandledMostNumberOfRequests {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //  minHeap
        TreeSet<Integer> availableServers = new TreeSet<>();

        for(int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        int[] count = new int[k];
        int maxSlot = 0;

        for(int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            int end = start + load[i];

            while(!pq.isEmpty() && pq.peek()[0] <= start) {
                int[] removedRequest =  pq.poll();
                availableServers.add(removedRequest[1]);
            }

            if(!availableServers.isEmpty()) {
                Integer availableSlot = availableServers.ceiling(i % k);

                if(availableSlot == null ) {
                    availableSlot = availableServers.first();
                }

                pq.add(new int[]{end, availableSlot});
                availableServers.remove(availableSlot);
                count[availableSlot]++;
                maxSlot = Math.max(count[availableSlot], maxSlot);
            }

        }

        List<Integer>res=new ArrayList<>();
        for(int i = 0;i < k; i++) {
            if(count[i] == maxSlot) {
                res.add(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        FindServersThatHandledMostNumberOfRequests servers = new FindServersThatHandledMostNumberOfRequests();
        System.out.println(servers.busiestServers(3, new int[]{1,2,3,4,5}, new int[]{5,2,3,3,3}));  //  [1]
        System.out.println(servers.busiestServers(3, new int[]{1,2,3,4}, new int[]{1,2,1,2}));  //  [0]
        System.out.println(servers.busiestServers(3, new int[]{1,2,3}, new int[]{10,12,11}));  //  [0,1,2]
    }
}
