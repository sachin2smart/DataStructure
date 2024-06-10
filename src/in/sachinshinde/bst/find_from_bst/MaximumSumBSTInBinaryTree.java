package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

//  https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/

/*
        Given a binary tree root, return the maximum sum of all keys of any sub-tree
        which is also a Binary Search Tree (BST).

        Assume a BST is defined as follows:

        The left subtree of a node contains only nodes with keys less than the node's key.
        The right subtree of a node contains only nodes with keys greater than the node's key.
        Both the left and right subtrees must also be binary search trees.

        Example 1:
        ---------
        Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
        Output: 20
        Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.

        Example 2:
        ---------
        Input: root = [4,3,null,1,2]
        Output: 2
        Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.

        Example 3:
        ---------
        Input: root = [-4,-2,-5]
        Output: 0
        Explanation: All values are negatives. Return an empty BST.

        Constraints:
        ------------
            The number of nodes in the tree is in the range [1, 4 * 104].
            -4 * 104 <= Node.val <= 4 * 104
 */

public class MaximumSumBSTInBinaryTree {

    private int maxSum;
    public int maxSumBST(Node root) {
        maxSum = 0;
        postOrderTraverse(root);
        return maxSum;
    }
    private int[] postOrderTraverse(Node root) {
        if (root == null) {
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
            // {min, max, sum}, initialize min=MAX_VALUE, max=MIN_VALUE
        }

        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);

        if (!(left != null             // the left subtree must be BST
                && right != null            // the right subtree must be BST
                && root.key > left[1]       // the root's key must greater than maximum keys of the left subtree
                && root.key < right[0])) {  // the root's key must lower than minimum keys of the right subtree
            return null;
        }

        int sum = root.key + left[2] + right[2]; // now it's a BST make `root` as root
        maxSum = Math.max(maxSum, sum);
        int min = Math.min(root.key, left[0]);
        int max = Math.max(root.key, right[1]);
        return new int[]{min, max, sum};
    }

    //  Second Method
    public int maxSumBST2(Node root) {
        maxSum = 0;
        preOrder2(root);
        return maxSum;

    }

    public int preOrder2(Node root) {
        if (root == null) {
            return 0;
        }

        int left = preOrder2(root.left);
        int right = preOrder2(root.right);

        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        if (root.left != null) {
            Node greatestLeft = root.left;
            while (greatestLeft.right != null) {
                greatestLeft = greatestLeft.right;
            }
            if (greatestLeft.key >= root.key) {
                return Integer.MIN_VALUE;
            }
        }

        if (root.right != null) {
            Node greatestRight = root.right;
            while (greatestRight.left != null) {
                greatestRight = greatestRight.left;
            }
            if (greatestRight.key <= root.key) {
                return Integer.MIN_VALUE;
            }
        }

        int sum = left + right + root.key;

        if (sum > maxSum) {
            maxSum = sum;
        }
        return sum;
    }
}
