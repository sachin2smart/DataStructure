package in.sachinshinde.array;

//	https://leetcode.com/problems/largest-rectangle-in-histogram/

/*
 * 	Given an array of integers heights representing the histogram's bar heights 
 * 	where the width of each bar is 1, 
 * 	return the area of the largest rectangle in the histogram.
 */

/*
       Example 1:
     	    Input: heights = [2,1,5,6,2,3]
            Output: 10
            Explanation: The above is a histogram where width of each bar is 1.
            The largest rectangle is shown in the red area, which has an area = 10 units.
        
       Example 2:
            Input: heights = [2,4]
	    Output: 4
 */

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
	int n = heights.length;
	
	if (heights == null || n == 0)
		return 0;
	
	int[] lessValueIndexFromLeft = new int[n];
	int[] lessValueIndexFromRight = new int[n];
	
	lessValueIndexFromRight[n - 1] = n;
	lessValueIndexFromLeft[0] = -1;

	for (int i = 1; i < n; i++) {
	    int p = i - 1;

	    while (p >= 0 && heights[p] >= heights[i])
		p = lessValueIndexFromLeft[p];
		
	    lessValueIndexFromLeft[i] = p;
	}

	for (int i = n - 2; i >= 0; i--) {
	    int p = i + 1;

	    while (p < n && heights[p] >= heights[i])
		p = lessValueIndexFromRight[p];
		
	    lessValueIndexFromRight[i] = p;
	}

	int maxArea = 0;
	for (int i = 0; i < n; i++)
		maxArea = Math.max(maxArea, heights[i] * 
			(lessValueIndexFromRight[i] - lessValueIndexFromLeft[i] - 1)); // width
	
	return maxArea;
    }
    
    public static void main(String[] args) {
	LargestRectangleInHistogram histogram = new LargestRectangleInHistogram();
	System.out.println(histogram.largestRectangleArea(new int[] {2,1,5,6,2,3}));	// 10
	System.out.println(histogram.largestRectangleArea(new int[] {2,4}));	// 4
    }
}