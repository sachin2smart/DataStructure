package in.sachinshinde.tree.segment_tree;

public class RangeSumQuery {

	SegmentTreeNode root = null;
    
    public RangeSumQuery(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }
    
    public void update(int index, int val) {
        update(root, index, val);        
    }
    
    public int sumRange(int left, int right) {
        return sumRange(root, left, right);
    }
    
    private SegmentTreeNode buildTree(int[] nums, int start, int end){
        if(start > end)
            return null;
        else {
            SegmentTreeNode result = new SegmentTreeNode(start, end);
            if(start == end)
                result.sum = nums[start];
            else {
                int mid = start + (end-start) /2;
                
                result.left = buildTree(nums, start, mid);
                result.right = buildTree(nums, mid+1, end);
                result.sum = result.left.sum + result.right.sum;    
            }
            return result;
        }
    }
    
    void update(SegmentTreeNode root, int pos, int val){
        if(root.start == root.end)
            root.sum = val;
        else {
            int mid = root.start + (root.end - root.start) / 2;
            
            if(pos <= mid)
                update(root.left, pos, val);
            else
                update(root.right, pos, val);
            
            root.sum = root.left.sum + root.right.sum;
        }
    }
    
    private int sumRange(SegmentTreeNode root, int start, int end) {
        if(root.start == start && root.end == end)
            return root.sum;
        else{
            int mid = root.start + (root.end - root.start) / 2;
            
            if(end <= mid)
                return sumRange(root.left, start, end);
            else if( start >= mid+1)
                return sumRange(root.right, start, end);
            else
                return sumRange(root.left, start, mid) + 
                        sumRange(root.right, mid+1, end);
        }
    }
    
    public static void main(String[] args) {
    	RangeSumQuery query = new RangeSumQuery(new int[] {1,3,5});
    	query.update(0, 2);
    	System.out.println(query.sumRange(1, 2));	// 8
	}
}
