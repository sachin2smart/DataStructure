package in.sachinshinde.bst.operations_on_bst;

import in.sachinshinde.bst.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//  https://leetcode.com/problems/serialize-and-deserialize-bst

/*
        Serialization is converting a data structure or object into a sequence of bits so that
            it can be stored in a file or memory buffer, or
            transmitted across a network connection link
                to be reconstructed later in the same or another computer environment.

        Design an algorithm to serialize and deserialize a binary search tree.
        There is no restriction on how your serialization/deserialization algorithm should work.
        You need to ensure that a binary search tree can be serialized to a string, and
            this string can be deserialized to the original tree structure.

        The encoded string should be as compact as possible.

        Example 1:
        ---------
        Input: root = [2,1,3]
        Output: [2,1,3]

        Example 2:
        ----------
        Input: root = []
        Output: []

        Constraints:
        -----------
            The number of nodes in the tree is in the range [0, 10^4].
            0 <= Node.val <= 10^4
            The input tree is guaranteed to be a binary search tree.
 */

public class SerializeAndDeserializeBST {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public void preOrder(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.key).append(",");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Node buildTree(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) {
            return null;
        }
        String s = q.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) {
            return null;
        }
        q.poll();
        Node root = new Node(val);
        root.left = buildTree(q, lower, val);
        root.right = buildTree(q, val, upper);
        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBST bst = new SerializeAndDeserializeBST();
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);

        String tree = bst.serialize(root);
        System.out.println(tree);
        Node newNode = bst.deserialize(tree);
        bst.preOrderPrint(newNode);
    }

    private void preOrderPrint(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.key + " ");
        preOrderPrint(root.left);
        preOrderPrint(root.right);
    }
}
