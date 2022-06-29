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
		//	MinHeap Solution, PriorityQueue : O(NlogK)
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest(new int[][] {{1,3},{-2,2}}, 1)));	//	[[-2, 2]]
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest(new int[][] {{3,3},{5,-1},{-2,4}}, 2)));	//	[[3, 3], [-2, 4]]
		
		//	Arrays.sort, Arrays.copyOfRange : O(NlogN)
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest2(new int[][] {{1,3},{-2,2}}, 1)));	//	[[-2, 2]]
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest2(new int[][] {{3,3},{5,-1},{-2,4}}, 2)));	//	[[3, 3], [-2, 4]]
		
		//	QuickSort : O(N)
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest3(new int[][] {{1,3},{-2,2}}, 1)));	//	[[-2, 2]]
		System.out.println(Arrays.deepToString(closestKPointsToOrigin.kClosest3(new int[][] {{3,3},{5,-1},{-2,4}}, 2)));	//	[[3, 3], [-2, 4]]
	}
    
    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }
    
    public int[][] kClosest3(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while(l <= r) {
            int mid = pivotPos(points, l, r);
            if(mid == K) 
            	break;
            if(mid < K)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int pivotPos(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while(l < r) {
            while(l < r && compare(A[r], pivot) >= 0) 
            	r--;
            
            A[l] = A[r];
            
            while(l < r && compare(A[l], pivot) <= 0)
            	l++;
            
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
