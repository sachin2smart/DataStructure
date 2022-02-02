package in.sachinshinde.tree.fenwick_tree;

/*
 *  ---------------------
 * |	Fenwick Tree	 |
 *  ---------------------
 */

//	https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/FenwickTree.java
//	https://www.youtube.com/watch?v=CWDQJGaN1gY&t=532s
//	https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
	
public class FenwickTree {

	public int[] createTree(int[] nums) {
		int[] binaryIndexedTree = new int[nums.length +1];
		for(int i=1; i<=nums.length; i++)
			updateBinaryIndexedTree(binaryIndexedTree, nums[i-1], i);
		return binaryIndexedTree;
	}

	private void updateBinaryIndexedTree(int[] binaryIndexedTree, int val, int index) {
		while(index < binaryIndexedTree.length) {
			binaryIndexedTree[index] += val;
			index = getNext(index);
		}
	}

	private int getNext(int index) {
		return index + (index & -index);
	}
	
	public int getSum(int[] binaryIndexedTree, int index) {
		index = index + 1;
		int sum = 0;
		while(index > 0) {
			sum += binaryIndexedTree[index];
			index = getParent(index);
		}
		return sum;
	}

	private int getParent(int index) {
		return index - (index & -index);
	}
	
	public static void main(String[] args) {
		int input[] = {1,2,3,4,5,6,7};
        FenwickTree ft = new FenwickTree();
        int binaryIndexedTree[] = ft.createTree(input);
        System.out.println(ft.getSum(binaryIndexedTree, 0)); 	//	1
        System.out.println(ft.getSum(binaryIndexedTree, 1)); 	//	3
        System.out.println(ft.getSum(binaryIndexedTree, 3)); 	//	10
        System.out.println(ft.getSum(binaryIndexedTree, 6)); 	//	28
	}
	
}
