package in.sachinshinde.sort;

// Heap Sort: https://www.geeksforgeeks.org/heap-sort/

//	Time : O(nlogn)
//	Space : O(1)

public class HeapSort {
	
	public void heapSort(int arr[])
    {
        int n = arr.length;

        // There are atmost n/2 nodes having either left/right child
        for (int i = n / 2 - 1; i >= 0; i--)	// terminating condition 
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {		// terminating condition 
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);					// look at the pointers, n changed to i, since we have reduced the heap
            									//	and swapping happens at the root node indexed at 0
        }
        
    }
	
    private void heapify(int arr[], int n, int i) {
        
    	int largest = i; 
        
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);			//	look at the pointers, heapify for the "largest" indexed node
            									// 		and the heapify process still needs n nodes to be processed
        }
    }
    
}
