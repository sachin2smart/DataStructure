package in.sachinshinde.bst.custom;

//	https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

/*
 	Given a sorted array key [0.. n-1] of search keys and 
 		an array freq[0.. n-1] of frequency counts, 
 		where freq[i] is the number of searches for keys[i]. 
 		
 	Construct a binary search tree of all keys such that 
 		the total cost of all the searches is as small as possible.
 		
	Let us first define the cost of a BST. 
		The cost of a BST node is 
			the level of that node multiplied by its frequency. 
		The level of the root is 1.
 */

	/*
	 	Example 1:  
		---------
			Input:  keys[] = {10, 12}, freq[] = {34, 50}
			There can be following two possible BSTs 
			
			        10                       12
			          \                     / 
			           12                 10
			           
			           
			          I                     II
			          
			Frequency of searches of 10 and 12 are 34 and 50 respectively.
			The cost of tree I is 34*1 + 50*2 = 134
			The cost of tree II is 50*1 + 34*2 = 118
		
		
		Example 2:
		---------
			Input:  keys[] = {10, 12, 20}, freq[] = {34, 8, 50}
			There can be following possible BSTs
			
			    10                12                 20         10              20
			      \             /    \              /             \            /
			      12          10     20           12               20         10  
			        \                            /                 /           \
			         20                        10                12             12  
			         
			         
			     I               II             III             IV             V
			     
			     
			Among all possible BSTs, cost of the fifth BST is minimum.  
			Cost of the fifth BST is 1*50 + 2*34 + 3*8 = 142 
	 */

public class OptimalBST {
	//	The third approach is easy to understand
	
	//	1. If keys are in sorted order
	private int optimalSearchTree(int freq[]) {
		int n = freq.length;
        return optCost(freq, 0, n-1);
    }
	
	private int optCost(int freq[], int i, int j) {
       if(i > j)
         return 0;
       
       if(i == j)
         return freq[i];
      
       int sum = sum(freq, i, j);
       int min = Integer.MAX_VALUE;
      
       for(int r = i; r <= j; ++r) {
           int cost = optCost(freq, i, r-1) +
                          optCost(freq, r+1, j);
           if(cost < min)
              min = cost;
       }
       
       return min + sum;
    }
	
	private int sum(int freq[], int i, int j) {
        int s = 0;
        for(int k = i; k <= j; k++) {
            if(k >= freq.length)
                continue;
            s += freq[k];
        }
        return s;
    }

    public static void main(String args[]) {
        OptimalBST oBst = new OptimalBST();
        // Question : What is the need of having values of input[] ? 
        
        //	int[] input = new int[] {10,12};
        int[] freq = new int[] {34,50};
        
        // Answer: 118
        System.out.println(oBst.optimalSearchTree(freq));
        System.out.println(oBst.minCost(freq));
        System.out.println(oBst.minCostRec(freq));
        System.out.println(oBst.optimalSearchTree_dynamic(freq));
        
        //	input = new int[] {10,12,20};
        freq = new int[] {34,8,50};

        // Answer: 142
        System.out.println(oBst.optimalSearchTree(freq));
        System.out.println(oBst.minCost(freq));
        System.out.println(oBst.minCostRec(freq));
        System.out.println(oBst.optimalSearchTree_dynamic(freq));
        
        //	input = new int[] {10,12,20,35,46};
        freq = new int[] {34,8,50,21,16};
        
        // Answer: 232
        System.out.println(oBst.optimalSearchTree(freq));
        System.out.println(oBst.minCost(freq));
        System.out.println(oBst.minCostRec(freq));
        System.out.println(oBst.optimalSearchTree_dynamic(freq));
    }
    
    //	2. Non-recursive, Iterative, 2D DP
    public int minCost(int freq[]) {
    	int n = freq.length;
        int dp[][] = new int[n][n];
        
        for(int i=0; i < dp.length; i++)
            dp[i][i] = freq[i];
        
        for(int r = 2; r <= n; r++) {
            for(int i = 0; i <= n-r; i++) {
                int j = i + r -1;
                dp[i][j] = Integer.MAX_VALUE;
                int sum = sum(freq, i, j);
                
                for(int k = i; k <= j; k++) {
                     int val = sum + 
                    		 (k-1 < i ? 0 : dp[i][k-1]) +
                             (k+1 > j ? 0 : dp[k+1][j]) ;
                     
                     if(val < dp[i][j])
                         dp[i][j] = val;
                }
            }
        }
        
        return dp[0][freq.length-1];
    }
    
    //	3. Recursive approach 
    public int minCostRec(int freq[]) {
        return minCostRec(freq, 0, freq.length-1, 1);
    }
    
    private int minCostRec(int freq[], int i, int j, int level){
        if(i > j)
            return 0;
            
        int min = Integer.MAX_VALUE;
 
        for(int r = i; r <= j; r++) {	
            int sum = minCostRec(freq, i, r-1, level+1) + 
            			minCostRec(freq, r+1, j, level+1) +
                    	level * freq[r];
            
            if(sum < min)
                min = sum;
        }
        
        return min;
    }
    
    // 4. Dynamic Programming, 2D
    // video : https://www.youtube.com/watch?v=hgA4xxlVvfQ
    int optimalSearchTree_dynamic(int freq[]) {
    	int n = freq.length;
        int dp[][] = new int[n + 1][n + 1];
 
        for(int i = 0; i < n; i++)
        	dp[i][i] = freq[i];
        
        for(int k = 2; k <= n; k++) {
            for(int i = 0; i <= n - k + 1; i++) {
                int j = i + k - 1;
                int sum = sum(freq, i, j);
                dp[i][j] = Integer.MAX_VALUE;
                
                for(int r = i; r <= j; r++) {
                    int min = ((r > i) ? dp[i][r - 1] : 0)
                            + ((r < j) ? dp[r + 1][j] : 0) + sum;
                    
                    if(min < dp[i][j])
                    	dp[i][j] = min;
                }
            }
        }
        
        return dp[0][n - 1];
    }
}