package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

import java.util.*;

//  https://leetcode.com/problems/closest-nodes-queries-in-a-binary-search-tree

/*
        You are given the root of a binary search tree and
            an array queries of size n consisting of positive integers.

        Find a 2D array answer of size n where answer[i] = [mini, maxi]:
            - mini is the largest value in the tree that is smaller than or equal to queries[i].
                If a such value does not exist, add -1 instead.
            - maxi is the smallest value in the tree that is greater than or equal to queries[i].
                If a such value does not exist, add -1 instead.

        Return the array answer.

        Example 1:
        -----------
        Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
        Output: [[2,2],[4,6],[15,-1]]
        Explanation: We answer the queries in the following way:
        - The largest number that is smaller or equal than 2 in the tree is 2, and
            the smallest number that is greater or equal than 2 is still 2.
            So the answer for the first query is [2,2].
        - The largest number that is smaller or equal than 5 in the tree is 4, and
            the smallest number that is greater or equal than 5 is 6.
            So the answer for the second query is [4,6].
        - The largest number that is smaller or equal than 16 in the tree is 15, and
            the smallest number that is greater or equal than 16 does not exist.
            So the answer for the third query is [15,-1].

        Example 2:
        -----------
        Input: root = [4,null,9], queries = [3]
        Output: [[-1,4]]
        Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and
            the smallest number that is greater or equal to 3 is 4.
            So the answer for the query is [-1,4].


        Constraints:
        -----------
        The number of nodes in the tree is in the range [2, 10^5].
        1 <= Node.val <= 10^6
        n == queries.length
        1 <= n <= 10^5
        1 <= queries[i] <= 10^6
 */

public class ClosestNodesQueriesInABinarySearchTree {
    //  Checking ceil and floor for each query
    public List<List<Integer>> closestNodes(Node root, List<Integer> queries) {
        TreeSet<Integer> ts = new TreeSet<>();
        /*
                Need to use TreeSet<Integer> instead of Set<Integer>
                -- since the methods floor() and ceiling() are available with TreeSet,
                --      but not with the Set interface
         */
        inorder(root, ts);
        List<List<Integer>> res = new ArrayList<>();
        for(int query: queries) {
            int smallest = -1;
            if(ts.floor(query) != null) {
                smallest = ts.floor(query);
            }
            int largest = -1;
            if(ts.ceiling(query) != null) {
                largest = ts.ceiling(query);
            }
            List<Integer> currRes = new ArrayList<>();
            currRes.add(smallest);
            currRes.add(largest);
            res.add(currRes);
        }
        return res;
    }

    private void inorder(Node root, Set<Integer> ts) {
        if(root == null) {
            return;
        }
        inorder(root.left, ts);
        ts.add(root.key);
        inorder(root.right, ts);
    }

    //  this is fastest, since it used the binary search to find the elements
    public List<List<Integer>> closestNodes2(Node root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        int[] nodeValues = arr.stream().mapToInt(i->i).toArray();

        List<List<Integer>> res = new ArrayList<>(queries.size());
        int n = arr.size();

        for (int currQuery: queries) {
            int lowerBoundVal = binarySearch(nodeValues, currQuery);
            int mx = (lowerBoundVal == n) ? -1 : nodeValues[lowerBoundVal];
            if (lowerBoundVal == n || nodeValues[lowerBoundVal] != currQuery) {
                lowerBoundVal--;
            }
            int mn = (lowerBoundVal < 0) ? -1 : nodeValues[lowerBoundVal];
            res.add(Arrays.asList(mn, mx));
        }
        return res;
    }

    private void inorder(Node node, List<Integer> arr) {
        if (node == null) {
            return;
        }
        inorder(node.left, arr);
        arr.add(node.key);
        inorder(node.right, arr);
    }

    private int binarySearch(int[] nodeValues, int currNodeValue) {
        int left = -1;
        int right = nodeValues.length;

        while (left + 1 < right) {
            int mid = left + (right -left) / 2;
            if (nodeValues[mid] >= currNodeValue) {
                right = mid;
            }
            else {
                left = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        ClosestNodesQueriesInABinarySearchTree tree = new ClosestNodesQueriesInABinarySearchTree();
        /*
                       6
                    /    \
                   2     13
                  / \   /  \
                 1  4   9   15
                           /
                         14

         */
        Node root = new Node(6);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        root.right = new Node(13);
        root.right.left = new Node(9);
        root.right.right = new Node(15);
        root.right.right.left = new Node(14);

        System.out.println(tree.closestNodes(root, Arrays.asList(2,5,16)));     // [[2, 2], [4, 6], [15, -1]]
        System.out.println(tree.closestNodes2(root, Arrays.asList(2,5,16)));     // [[2, 2], [4, 6], [15, -1]]


        /*
                   4
                    \
                     9
         */
        Node root2 = new Node(4);
        root2.right = new Node(9);

        System.out.println(tree.closestNodes(root2, Arrays.asList(3)));     // [[-1, 4]]
        System.out.println(tree.closestNodes2(root2, Arrays.asList(3)));     // [[-1, 4]]
    }
}
