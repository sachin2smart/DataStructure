package in.sachinshinde.sort;

//	https://www.javatpoint.com/bubble-sort

//	How it works ? 
	// :-> repeatedly swapping of adjacent elements until they are not in the intended order.

//	Time : O(n2)
//	Space : O(1)

public class BubbleSort {
	
	public void bubbleSort (int arr[])  
    {  
        int n = arr.length;  
        int i, j, temp;  
        for (i = 0; i < n; i++)  
        {  
            for (j = i + 1; j < n; j++)  
            {  
                if (arr[j] < arr[i])  
                {  
                    temp = arr[i];  
                    arr[i] = arr[j];  
                    arr[j] = temp;  
                }  
            }  
        }  
    }  
}
