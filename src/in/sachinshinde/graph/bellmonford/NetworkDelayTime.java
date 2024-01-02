package in.sachinshinde.graph.bellmonford;

import java.util.Arrays;

//	https://leetcode.com/problems/network-delay-time/

/*
 * 	You are given a network of n nodes, labeled from 1 to n. 
 * 	You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * 	 where ui is the source node, 
 * 		   vi is the target node, 
 * 	       wi is the time it takes for a signal to travel from source to target.
 * 
 * 	We will send a signal from a given node k. 
 * 	Return the time it takes for all the n nodes to receive the signal. 
 * 	If it is impossible for all the n nodes to receive the signal, return -1.
 */


//	Approach : Bellman-Ford Algorithm

public class NetworkDelayTime {

	public static int getnetworkDelayTime(int[][] times, int n, int k) {
            double[] dist = new double[n];
            Arrays.fill(dist, Double.POSITIVE_INFINITY);
            
            dist[k - 1] = 0;
            
            for (int i = 1; i < n; i++) {
                for (int[] edge : times) {
                    int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                    dist[v] = Math.min(dist[v], dist[u] + w);
                }
            }
            double res = Double.MIN_VALUE;	
            for (double i: dist) {
                res = Math.max(i, res);
            }	
            return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
	}
	
	public static void main(String[] args) {
	    int[][] times = new int[][] {{1,2,1}};
	    System.out.println(getnetworkDelayTime(times, 2,1));
	}
}
