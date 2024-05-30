package in.sachinshinde.binarytree.checks_on_tree;

import in.sachinshinde.binarytree.Node;

import java.util.LinkedList;
import java.util.Queue;

//  https://leetcode.com/problems/check-completeness-of-a-binary-tree/

/*
        Given the root of a binary tree, determine if it is a complete binary tree.

        In a complete binary tree, every level, except possibly the last, is completely filled, and
            all nodes in the last level are as far left as possible.
        It can have between 1 and 2h nodes inclusive at the last level h.

        Example 1:
        ---------
        Input: root = [1,2,3,4,5,6]
        Output: true
        Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

        Example 2:
        ---------
        Input: root = [1,2,3,4,5,null,7]
        Output: false
        Explanation: The node with value 7 isn't as far left as possible.

        Constraints:
        -----------
            The number of nodes in the tree is in the range [1, 100].
            1 <= Node.val <= 1000
 */

public class CheckCompletenessOfABinaryTree {

    // q
    public boolean isCompleteTree(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (q.peek() != null) {
            Node node = q.poll();
            q.offer(node.left);
            q.offer(node.right);
        }
        while (!q.isEmpty() && q.peek() == null) {
            q.poll();
        }
        return q.isEmpty();
    }

    // simple
    public boolean isCompleteTree2(Node root) {
        boolean isNullNode = false;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr == null) {
                isNullNode = true;
            }
            else{
                if(isNullNode) {
                    return false;
                }
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        
    }
}
