package in.sachinshinde.sort;

//	https://www.geeksforgeeks.org/selection-sort/
//	Time O(n2), Space O(1)

/*	
 * 
 * 	The selection sort algorithm sorts an array by 
 *  repeatedly finding the minimum element 
 *  from unsorted part and 
 *  putting it at the beginning.
 *  
 */

public class SelectionSort {
	
	public void sort(int arr[]) {
        int n = arr.length;
 
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
 
	        // Swap this minimum index element with the i'th element
	        int temp = arr[min_idx];
	        arr[min_idx] = arr[i];
	        arr[i] = temp;
        }
  }
	
}
