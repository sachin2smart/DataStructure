package in.sachinshinde.sort;

//	Merge sort : Time O(nlogn) , Space O(n)
//	https://leetcode.com/submissions/detail/609361938/

public class MergeSort {
	
	public int[] sortArray(int[] nums) {
		mergeSort(nums, 0, nums.length -1);
        return nums;
    }
    
    public static void mergeSort(int[] nums, int low, int high){
        if (low >=  high) 
        	return;
        
        int mid = low + (high-low)/2;
        
        mergeSort(nums, low, mid);
        mergeSort(nums, mid+1, high);
        merge(nums, low, mid, high);
    }
    
    public static void merge(int[] nums, int low, int mid, int high){
        int[] arr = new int[high - low + 1]; // ** IMP : Note, how the size of arr is calculated 
        
        int i = low;
        int j = mid + 1;
        
        int k = 0;
        
        while (i <= mid && j <= high)
        	arr[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        
        
        while(i <= mid)
        	arr[k++] = nums[i++];
        
        
        while(j <= high)
        	arr[k++] = nums[j++];
        
        
        for(int x = low, y = 0; x <= high; x++)	// Remember the pointer x to store records from low 
            nums[x] = arr[y++];
        
    }
}
