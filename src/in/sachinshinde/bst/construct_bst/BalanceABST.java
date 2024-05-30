package in.sachinshinde.bst.construct_bst;

import in.sachinshinde.bst.Node;

import java.util.ArrayList;
import java.util.List;

public class BalanceABST {

    public Node balanceBST(Node root) {
        List<Node> sortedArr = new ArrayList<>();
        inorderTraverse(root, sortedArr);
        return sortedArrayToBST(0, sortedArr.size() - 1, sortedArr);
    }

    void inorderTraverse(Node root, List<Node> sortedArr) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left, sortedArr);
        sortedArr.add(root);
        inorderTraverse(root.right, sortedArr);
    }
    Node sortedArrayToBST(int start, int end, List<Node> sortedArr) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = sortedArr.get(mid);
        root.left = sortedArrayToBST(start, mid - 1, sortedArr);
        root.right = sortedArrayToBST(mid + 1, end, sortedArr);
        return root;
    }
}
