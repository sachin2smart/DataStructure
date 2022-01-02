package in.sachinshinde.greedy;

import java.util.Arrays;

public class JobScheduling {
	
	public static int[] getJobScheduling(Job arr[], int n) {
		
		//	Sort the jobs according to the profit gain
		Arrays.sort(arr, (a,b)-> b.profit-a.profit );
		
		//	Get the maximum deadline 
		int maxDeadline = 0;
		for(int i=0; i<n; i++)
			if(arr[i].deadline > maxDeadline)
				maxDeadline = arr[i].deadline;
		
		//	result jobs will have number of jobs = maximum deadline
		int result[] = new int[maxDeadline +1];
		for(int i=1; i<n; i++)
			result[i] = -1;		//	initially assign the job index as -1
		
		int countJobs = 0, jobProfit = 0;
		
		for(int i=0; i<n && countJobs < maxDeadline; i++)
			for(int j=arr[i].deadline; j>0; j--)	//	Now, we array arr with descending order of profit
				if(result[j] == -1) {	
					result[j] = i;
					countJobs++;
					jobProfit += arr[i].profit;
					break;
				}
				
		int ans[] = new int[2];
	    ans[0] = countJobs;
	    ans[1] = jobProfit;
	    return ans;
	}
	
	public static void main(String[] args) {
	      Job[] arr = new Job[4];
	      arr[0] = new Job(1, 4, 20);
	      arr[1] = new Job(2, 1, 10);
	      arr[2] = new Job(3, 2, 40);
	      arr[3] = new Job(4, 2, 30);

	      int[] res = getJobScheduling(arr, 4);
	      System.out.println(res[0] + " " + res[1]);

	   }
}
