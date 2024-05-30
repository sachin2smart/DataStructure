package in.sachinshinde.dp.maximize;

//	https://leetcode.com/problems/uncrossed-lines/

public class UncroseedLines {
	
	private int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] prev = new int[m+1];
        int[] curi = new int[m+1];
        
        for(int j = n-1; j >= 0; j--){
            for(int i = m-1; i >=0; i--){
                if(nums1[i] == nums2[j])
                    curi[i] = 1 + prev[i+1];
                else
                    curi[i] = Math.max(prev[i],curi[i+1]);
            }
            int[] temp = prev;
            prev = curi;
            curi = temp;
        }
        
        return prev[0];  
    }
	
	public static void main(String[] args) {
		int[] nums1 = new int[] {2,5,1,2,5};
		int[] nums2 = new int[] {10,5,2,1,5,2};
		
		UncroseedLines lines = new UncroseedLines();
		System.out.println(lines.maxUncrossedLines(nums1, nums2));
	}
}
