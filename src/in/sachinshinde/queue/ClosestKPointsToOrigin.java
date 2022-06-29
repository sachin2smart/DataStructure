package in.sachinshinde.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

//	https://leetcode.com/problems/k-closest-points-to-origin/

/*
 	Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, 
	return the k closest points to the origin (0, 0).
	
	The distance between two points on the X-Y plane is the Euclidean distance (i.e., squareRoot[ (x1 - x2)^2 + (y1 - y2)^2)].
	
	You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */

public class ClosestKPointsToOrigin {
	public int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][2];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) ->  square(a) - square(b));
        
        for(int[] point : points)
            pq.offer(point);
        
        for(int i=0; i<k ; i++ )
            res[i] = pq.poll();
    
        return res;
    }
    
    private int square(int[] a) {
        return a[0]*a[0] + a[1]*a[1];
    }
    
    public static void main(String[] args) {
		ClosestKPointsToOrigin closestKPointsToOrigin = new ClosestKPointsToOrigin();
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest(new int[][] {{1,3},{-2,2}}, 1)));	//	[[-2, 2]]
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest(new int[][] {{3,3},{5,-1},{-2,4}}, 2)));	//	[[3, 3], [-2, 4]]
	}
}
