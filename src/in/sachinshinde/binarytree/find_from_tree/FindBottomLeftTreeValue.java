package in.sachinshinde.binarytree.find_from_tree;

import in.sachinshinde.binarytree.Node;

import java.util.LinkedList;
import java.util.Queue;

//  https://leetcode.com/problems/find-bottom-left-tree-value/

public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.key;
    }
}
