package in.sachinshinde.dp.trap_rainwater;

//	https://leetcode.com/problems/container-with-most-water/

/*
 	You are given an integer array height of length n. 
 	There are n vertical lines drawn such that 
 		the two endpoints of the i'th line are (i, 0) and (i, height[i]).
	Find two lines that together with the x-axis form a container, such that 
		the container contains the most water.
	Return the maximum amount of water a container can store.
	Notice that you may not slant the container.
	
	Example:
		Input: height = [1,8,6,2,5,4,8,3,7]
		Output: 49 
		Explanation : The max area will exists between first 8 and last 7
					height = 7, 
					width = position[7] - position[8]  = 7
					--> Area = height * width = 7 * 7 = 49
 */

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
        int maxArea = 0;
        int startPtr = 0;
        int endPtr = height.length - 1;
        
        // Area of rectangle = height * width
        while(startPtr < endPtr) {
        	int width = endPtr - startPtr;	//	always
            
        	//	pick the height whichever is minimum, for area calculation
        	if(height[startPtr] < height[endPtr]) {
                maxArea = Math.max(maxArea, height[startPtr] * width);
                startPtr++;
            }
            else {
                maxArea = Math.max(maxArea, height[endPtr] * width);
                endPtr--;
            }
        	
        }
        
        return maxArea;
    }
	
	public static void main(String[] args) {
		ContainerWithMostWater container = new ContainerWithMostWater();
		System.out.println(container.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));	//	49
	}
}
