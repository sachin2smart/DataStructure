package in.sachinshinde.sort;

//	Insertion Sort : https://www.geeksforgeeks.org/insertion-sort/

//	Time : O(n2)
//	Space : O(1)

public class InsertionSort {
	
	public void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int currVal = arr[i];
            int j = i - 1;
 
            while (j >= 0 && arr[j] > currVal) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = currVal;
        }
    }
	
}
