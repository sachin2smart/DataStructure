package in.sachinshinde.greedy;

import java.util.Arrays;

//	https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#

//	We are given two arrays that represent the arrival and departure times of trains that stop at the platform. 
//	We need to find the minimum number of platforms needed at the railway station so that no train has to wait.

public class FindPlatforms {
	
	public static int findMinPlatformNeeded(int arr[], int dep[], int n)
    {
       Arrays.sort(arr);
       Arrays.sort(dep);
       int j=0;
       int ans=0;
       for(int i=0;i<n;i++){
          while(j<n && dep[i]>=arr[j]){
             j++; 
          }
          ans=Math.max(ans,j-i);
       }
       return ans;
    }
	
	public static void main (String[] args) {	
		int[] arr ={900,945,955,1100,1500,1800};
		int[] dep={920,1200,1130,1150,1900,2000};
		int n=arr.length;
		int totalCount=findMinPlatformNeeded(arr,dep,n);
		System.out.println("Minimum number of Platforms required "+totalCount);
	}
}
