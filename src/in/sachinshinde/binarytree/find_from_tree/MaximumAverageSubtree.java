package in.sachinshinde.binarytree.find_from_tree;

/*
        Given the root of a binary tree, find the maximum average value of any subtree of that tree.
        (A subtree of a tree is any node of that tree plus all its descendants.
            The average value of a tree is the sum of its values, divided by the number of nodes.)

        Example 1:
        ---------
        Input: [5,6,1]
        Output: 6.00000

        Explanation:
            For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
            For the node with value = 6 we have an average of 6 / 1 = 6.
            For the node with value = 1 we have an average of 1 / 1 = 1.
            So the answer is 6 which is the maximum.

       Note:
      ------
        The number of nodes in the tree is between 1 and 5000.
        Each node will have a value between 0 and 100000.
        Answers will be accepted as correct if they are within 10^-5 of the correct answer.

 */

import in.sachinshinde.binarytree.Node;

public class MaximumAverageSubtree {

    public double maximumAverageSubtree(Node root) {
        return dfsUtil(root);
    }

    private double dfsUtil(Node root) {
        double[] res = new double[]{0.0};
        dfs(root, res);
        return res[0];
    }

    private int[] dfs(Node root, double[] res) {
        if(root == null) {
            return new int[2];  // it returns [0, 0]
        }

        int[] leftSubtree = dfs(root.left, res);
        int[] rightSubtree = dfs(root.right, res);

        int sum = root.key + leftSubtree[0] + rightSubtree[0];
        int num = 1 + leftSubtree[1] + rightSubtree[1];

        res[0] = Math.max(res[0], sum * 1.0 / num);
        return new int[]{sum, num};
    }

    public static void main(String[] args) {
        MaximumAverageSubtree tree = new MaximumAverageSubtree();

        Node root = null;
        System.out.println(tree.maximumAverageSubtree(root));   // 0.0

        /*
                     5
                    / \
                   6  10
         */
        root = new Node(5);
        root.left = new Node(6);
        root.right = new Node(10);
        System.out.println(tree.maximumAverageSubtree(root));   // 10.0


        /*
                     5
                    / \
                   6  10
                      / \
                     5   5
         */
        root.right.left = new Node(5);
        root.right.right = new Node(5);
        System.out.println(tree.maximumAverageSubtree(root));   // 6.667
    }

}

/*
        Solution: Recursion
        -------------------
        For each node, we calculate the sum and count of the nodes in the subtree rooted at that node,
            then calculate the average, compare it with the current maximum, and update the maximum if necessary.

        Therefore, we design a function
            dfs(root) : that represents the sum and count of nodes in the subtree rooted at root.
        The return value is an array of length 2, where the first element represents the sum of nodes,
            and the second element represents the count of nodes.

        The recursive process of the function dfs(root) is as follows:

            - If root is null, return [0, 0];
            - Otherwise,
                    calculate the sum and count of nodes in the left subtree of root,
                    calculate the sum and count of nodes in the right subtree of root,
                    The sum of nodes in the subtree rooted at root is root.key + leftSubtree[0] + rightSubtree[0],
                    and the count of nodes is 1 + leftSubtree[1] + rightSubtree[1].
                    Calculate the average, compare it with the current maximum, and update the maximum if necessary;

        Return [root.key + leftSubtree[0] + rightSubtree[0], 1 + leftSubtree[1] + rightSubtree[1]].

        Finally, return the maximum value.

        The time complexity is O(n) and the space complexity is O(n).
        Here, n is the number of nodes in the binary tree.
 */