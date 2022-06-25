package in.sachinshinde.graph.dfs;

import java.util.Arrays;

//	https://leetcode.com/problems/flood-fill/

/*
 *	
 	An image is represented by an m x n integer grid image 
 		where image[i][j] represents the pixel value of the image.
	You are also given three integers sr, sc, and newColor. 
	You should perform a flood fill on the image starting from the pixel image[sr][sc].

	To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, 
		plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. 
	Replace the color of all of the aforementioned pixels with newColor.

	Return the modified image after performing the flood fill.

 */

public class FloodFill {
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //	If the same color exists at the given pixel 
		if (image[sr][sc] == newColor) 
        	return image;
		
        fill(image, sr, sc, image[sr][sc], newColor);
        
        return image;
    }
    
    private void fill(int[][] image, int sr, int sc, int color, int newColor) {
        //	boundary conditions and validation of current pixel color
    	if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) 
        	return;
        
        image[sr][sc] = newColor;	// actual painting 
        
        // recursively paint the left, right, up, down pixel 
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }
    
    public static void main(String[] args) {
    	FloodFill floodFill = new FloodFill();
    	int[][] image = {{1,1,1},{1,2,0},{1,0,1}};
    	int sr = 1, sc = 1, newColor = 2;
    	
    	System.out.println(Arrays.deepToString(image));
    	floodFill.floodFill(image, sr, sc, newColor);
    	System.out.println(Arrays.deepToString(image));	//	[[1, 1, 1], [1, 2, 0], [1, 0, 1]]
    	
    	image = new int[][]{{0,0,0},{0,0,0}}; 
    	sr = 0; sc = 0; newColor = 2;
    	
    	System.out.println(Arrays.deepToString(image));
    	floodFill.floodFill(image, sr, sc, newColor);
    	System.out.println(Arrays.deepToString(image));	//	[[2, 2, 2], [2, 2, 2]]
	}
}
