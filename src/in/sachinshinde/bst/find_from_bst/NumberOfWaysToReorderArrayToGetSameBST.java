package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

import java.util.HashMap;
import java.util.Map;

//  https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/

/*
        Given an array nums that represents a permutation of integers from 1 to n.
        We are going to construct a binary search tree (BST)
            by inserting the elements of nums in order into an initially empty BST.
        Find the number of different ways to reorder nums so that
            the constructed BST is identical to that formed from the original array nums.

        For example, given nums = [2,1,3],
            we will have 2 as the root, 1 as a left child, and 3 as a right child.
            The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.

        Return the number of ways to reorder nums such that
            the BST formed is identical to the original BST formed from nums.

        Since the answer may be very large, return it modulo 10^9 + 7.

        Example 1:
        ---------
        Input: nums = [2,1,3]
        Output: 1
        Explanation: We can reorder nums to be [2,3,1] which will yield the same BST.
            There are no other ways to reorder nums which will yield the same BST.

        Example 2:
        ---------
        Input: nums = [3,4,5,1,2]
        Output: 5
        Explanation: The following 5 arrays will yield the same BST:
        [3,1,2,4,5]
        [3,1,4,2,5]
        [3,1,4,5,2]
        [3,4,1,2,5]
        [3,4,1,5,2]

        Example 3:
        ---------
        Input: nums = [1,2,3]
        Output: 0
        Explanation: There are no other orderings of nums that will yield the same BST.


        Constraints:
        -----------
            1 <= nums.length <= 1000
            1 <= nums[i] <= nums.length
            All integers in nums are distinct.
 */

public class NumberOfWaysToReorderArrayToGetSameBST {

    private Map<Node, Integer> hm;
    private final int MOD = 1000000007;

    public int numOfWays(int[] nums) {
        hm = new HashMap<>();
        Node root = buildTree(nums);
        return (int) (numOfWays(root) % MOD) - 1;
    }

    private long numOfWays(Node node) {
        if (node == null) {
            return 1;
        }

        int n = numOfNodes(node.left);
        int m = numOfNodes(node.right);

        return ((((binomialCoefficient(n + m, n) % MOD) * numOfWays(node.left)) % MOD) * numOfWays(node.right)) % MOD;
    }

    private int numOfNodes(Node node) {
        if (hm.containsKey(node)) {
            return hm.get(node);
        }

        if (node == null) {
            return 0;
        }

        int num = 1 + numOfNodes(node.left) + numOfNodes(node.right);
        hm.put(node, num);
        return num;
    }

    private int binomialCoefficient(int n, int k) {
        int[] c = new int[k + 1];
        c[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                c[j] = (c[j] + c[j - 1]) % MOD;
            }
        }
        return c[k] % MOD;
    }


    private Node buildTree(int[] nums) {
        Node tree = null;
        for (int num : nums) {
            tree = insert(tree, num);
        }
        return tree;
    }

    private Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }

        if (val < root.key) {
            root.left = insert(root.left, val);
        }
        else if (val > root.key) {
            root.right = insert(root.right, val);
        }

        return root;
    }

    public static void main(String[] args) {
        NumberOfWaysToReorderArrayToGetSameBST numBSTs = new NumberOfWaysToReorderArrayToGetSameBST();
//        System.out.println(numBSTs.numOfWays(new int[]{1,2,3}));        // 0
//        System.out.println(numBSTs.numOfWays(new int[]{2,1,3}));        // 1
//        System.out.println(numBSTs.numOfWays(new int[]{3,4,5,1,2}));    // 5
        System.out.println(numBSTs.binomialCoefficient(2,1));
    }
}
