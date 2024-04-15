package in.sachinshinde.binarytree;

//  https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/

/*
        Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

        For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18],
            then it should become [18,29,11,7,4,3,1,2].

        Return the root of the reversed tree.

        A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

        The level of a node is the number of edges along the path between it and the root node.


        Example 1:
        ---------
        Input: root = [2,3,5,8,13,21,34]
        Output: [2,5,3,8,13,21,34]
        Explanation:
        The tree has only one odd level.
        The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.

        Example 2:
        ---------
        Input: root = [7,13,11]
        Output: [7,11,13]
        Explanation:
        The nodes at level 1 are 13, 11, which are reversed and become 11, 13.

        Example 3:
        ---------
        Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
        Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
        Explanation:
        The odd levels have non-zero values.
        The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
        The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.

        Constraints:
        -----------
            The number of nodes in the tree is in the range [1, 214].
            0 <= Node.val <= 105
            root is a perfect binary tree.
 */

public class ReverseOddLevelsOfBinaryTree {
    public Node reverseOddLevels(Node root) {
        if(root == null) {
            return null;
        }
        int level = 1;
        reverseLevelWise(root.left, root.right, level);
        return root;
    }

    private void reverseLevelWise(Node leftNode, Node rightNode, int level) {
        if(leftNode == null || rightNode == null) {
            return;
        }

        if(level % 2 == 1) {
            int leftNodeKey = leftNode.key;
            leftNode.key = rightNode.key;
            rightNode.key = leftNodeKey;
        }

        reverseLevelWise(leftNode.left, rightNode.right, level + 1);
        reverseLevelWise(leftNode.right, rightNode.left, level + 1);
    }

    public static void main(String[] args) {
        ReverseOddLevelsOfBinaryTree tree = new ReverseOddLevelsOfBinaryTree();

        Node n = new Node(2);
        n.left = new Node(1);
        n.right = new Node(4);
        n.right.left = new Node(3);
        n.right.right = new Node(5);

        tree.inOrder(n);    // 2 1 4 3 5
        tree.reverseOddLevels(n);
        System.out.println("\n");
        tree.inOrder(n);    // 2 4 1 3 5

        System.out.println("\n");

        Node n2 = new Node(1);
        n2.left = new Node(2);
        n2.right = new Node(3);
        n2.right.left = new Node(4);
        n2.right.right = new Node(5);
        n2.right.left.left = new Node(6);
        n2.right.right.right = new Node(7);

        tree.inOrder(n2); // 1 2 3 4 6 5 7
        tree.reverseOddLevels(n2); // this is not a perfect binary tree though
        System.out.println("\n");
        tree.inOrder(n2); // 1 3 2 4 6 5 7
    }

    private void inOrder(Node root) {
        if(root == null) {
            return;
        }

        System.out.print(" " + root.key);
        inOrder(root.left);
        inOrder(root.right);
    }
}
