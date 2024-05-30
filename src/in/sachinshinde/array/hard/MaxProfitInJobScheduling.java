package in.sachinshinde.array.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

//	https://leetcode.com/problems/maximum-profit-in-job-scheduling/

/*	
  	We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

	You're given the startTime, endTime and profit arrays, 
	return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

	If you choose a job that ends at time X you will be able to start another job that starts at time X.
 */

/*
 	Example 1:
     	    Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
            Output: 120
            Explanation: The subset chosen is the first and fourth job. 
            Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
       
       Example 2:
            Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
            Output: 150
            Explanation: The subset chosen is the first, fourth and fifth job. 
            Profit obtained 150 = 20 + 70 + 60.
            
       Example 3:
       	    Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
	    Output: 6
 */

public class MaxProfitInJobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
	int n = startTime.length;
	int[][] jobs = new int[n][3];
	for(int i = 0; i < n; i++)
	    jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
	Arrays.sort(jobs, (a,b) -> a[1] - b[1]);
	TreeMap<Integer, Integer> dp = new TreeMap<>();
	dp.put(0, 0);
	for(int[] job: jobs) {
	    int curr = dp.floorEntry(job[0]).getValue() + job[2];
	    if(curr > dp.lastEntry().getValue())
		dp.put(job[1], curr);
	}
	return dp.lastEntry().getValue();
    }
    
    public static void main(String[] args) {
	MaxProfitInJobScheduling scheduling = new MaxProfitInJobScheduling();
	int[] startTime = new int[] {1,2,3,3};
	int[] endTime = new int[] {3,4,5,6};
	int[] profit = new int[] {50,10,40,70};
	System.out.println(scheduling.jobScheduling(startTime, endTime, profit));	//	120
	System.out.println(scheduling.jobScheduling2(startTime, endTime, profit));	//	120
	System.out.println(scheduling.jobScheduling3(startTime, endTime, profit));	//	120
	
	startTime = new int[] {1,2,3,4,6};
	endTime = new int[] {3,5,10,6,9};
	profit = new int[] {20,20,100,70,60};
	System.out.println(scheduling.jobScheduling(startTime, endTime, profit));	//	150
	System.out.println(scheduling.jobScheduling2(startTime, endTime, profit));	//	150
	System.out.println(scheduling.jobScheduling3(startTime, endTime, profit));	//	150
	
	startTime = new int[] {1,1,1};
	endTime = new int[] {2,3,4};
	profit = new int[] {5,6,4};
	System.out.println(scheduling.jobScheduling(startTime, endTime, profit));	//	6
	System.out.println(scheduling.jobScheduling2(startTime, endTime, profit));	//	6
	System.out.println(scheduling.jobScheduling3(startTime, endTime, profit));	//	6
    }
    
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
	int n = startTime.length;
	
	int[][] jobs = new int[n][3];
	for (int i = 0; i < n; i++) {
	    jobs[i] = new int[] { startTime[i], endTime[i], profit[i] };
	}
	
	Arrays.sort(jobs, (a, b) -> {
	    return Integer.compare(a[0], b[0]);
	});
	
	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
	
	int max = 0;
        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && jobs[i][0] >= pq.peek()[0]) {
                max = Math.max(max, pq.poll()[1]);
            }
            pq.offer(new int[] {jobs[i][1], max + jobs[i][2]});
        }
        
	int maxProfit = 0;
	while(!pq.isEmpty()) {
	    maxProfit = Math.max(maxProfit, pq.poll()[1]);
        }
	
	return maxProfit;
    }
    
    
    // Method 3
    
    private class Job {
        int startTime;
        int endTime; 
        int profit;
        
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for(int i=0;i<n;i++) {
            jobs[i] = new Job(startTime[i],endTime[i],profit[i]);
        }
        return schedule(jobs);
    }

    private int schedule(Job[] jobs) {
	Arrays.sort(jobs, Comparator.comparingInt(a -> a.endTime));
	int n = jobs.length;
	int[] dp = new int[n];
	dp[0] = jobs[0].profit;
	for (int i = 1; i < n; i++) {
	    int profit = jobs[i].profit;
	    int maxProfitIndex = search(jobs, i);
	    if (maxProfitIndex != -1)
		profit += dp[maxProfitIndex];
	    dp[i] = Math.max(profit, dp[i - 1]);
	}
	return dp[n - 1];
    }

    private int search(Job[] jobs, int index) {
        int start = 0, end = index - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (jobs[mid].endTime <= jobs[index].startTime) {
                if (jobs[mid + 1].endTime <= jobs[index].startTime)
                    start = mid + 1;
                else
                    return mid;
            }
            else
                end = mid - 1;
        }
        return -1;
    }
}