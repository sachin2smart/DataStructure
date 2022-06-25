package in.sachinshinde.sort;

//	Sort an array - QuickSort Algorithm
//	https://leetcode.com/problems/sort-an-array/

public class QuickSort {
	
	public int[] sortArray(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot);
            quickSort(arr, pivot + 1, right);  
        }
    }
    
    private int partition(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        
        while (left <= right) {
            while (arr[left] < arr[mid])
                left++;
            
            while (arr[right] > arr[mid])
                right--;
            
            if (left <= right)
                swap(arr, left++, right--);
        }
        
        return left - 1;	// ** IMP - Left Pointer will be a pivot 
    }
    
    private void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}
