package in.sachinshinde.bst.operations_on_bst;

import in.sachinshinde.bst.Node;

//  https://leetcode.com/problems/delete-node-in-a-bst/

/*
        Given a root node reference of a BST and a key, delete the node with the given key in the BST.
        Return the root node reference (possibly updated) of the BST.

        Basically, the deletion can be divided into two stages:
            1. Search for a node to remove.
            2. If the node is found, delete the node.

        Example 1:
        ---------
        Input: root = [5,3,6,2,4,null,7], key = 3
        Output: [5,4,6,2,null,null,7]
        Explanation:
            Given key to delete is 3. So we find the node with value 3 and delete it.
            One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
            Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

        Example 2:
        ---------
        Input: root = [5,3,6,2,4,null,7], key = 0
        Output: [5,3,6,2,4,null,7]
        Explanation: The tree does not contain a node with value = 0.

        Example 3:
        ---------
        Input: root = [], key = 0
        Output: []


        Constraints:
        -----------
        The number of nodes in the tree is in the range [0, 10^4].
        -10^5 <= Node.val <= 10^5
        Each node has a unique value.
        root is a valid binary search tree.
        -10^5 <= key <= 10^5
 */

public class DeleteNodeInBST {

//    Video Solution : https://youtu.be/LFzAoJJt92M

    public Node deleteNode(Node root, int key) {
        if(root == null) {
            return null;
        }
        if(key < root.key) {
            root.left = deleteNode(root.left, key);
        }
        else if(key > root.key) {
            root.right = deleteNode(root.right, key);
        }
        else {
            if(root.left == null) {
                return root.right;
            }
            else if(root.right == null) {
                return root.left;
            }
            Node minNode = findMin(root.right);
            root.key = minNode.key;
            root.right = deleteNode(root.right, root.key);
        }
        return root;
    }

    private Node findMin(Node node){
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        DeleteNodeInBST bst = new DeleteNodeInBST();
        /*
                             5
                           /   \
                          3     6
                         / \     \
                        2   4     7
         */

        Node root = new Node(5);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.right = new Node(6);
        root.right.right = new Node(7);

        bst.preOrder(root); // 5 3 2 4 6 7
        bst.deleteNode(root, 3);
        System.out.println();
        bst.preOrder(root); // 5 4 2 6 7
        /*
                             5
                           /   \
                          4     6
                         /       \
                        2         7
         */

        bst.deleteNode(root, 8);    // no change in the bst
        System.out.println();
        bst.preOrder(root); // // 5 4 2 6 7
        bst.deleteNode(null, 0);    // null tree will be returned
    }

    private void preOrder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.key + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
}
