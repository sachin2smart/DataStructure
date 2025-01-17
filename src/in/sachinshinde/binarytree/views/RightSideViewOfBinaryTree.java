package in.sachinshinde.binarytree.views;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

Binary Tree:
-----------

           1
        /     \
       2       3
      / \    /   \
     4   5  6     7
        /       /   \
       8       9     10


    Right Side View of Binary Tree : [1,3,7,10]

    --------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of Node values viewed from Right Side

 */

public class RightSideViewOfBinaryTree {

    //  Recursive way in DFS manner
    private void rightView(Node root, List<Integer> result, int level) {
        if(root == null) {
            return;
        }

        if(level == result.size()) {	//	on each level we need right-most node
            result.add(root.key);
        }

        rightView(root.right, result, level+1);	//	right first recursive call
        rightView(root.left, result, level+1);

    }

    public List<Integer> rightViewUtil(Node root) {
        List<Integer> result = new ArrayList<>();
        int level = 0;
        rightView(root, result, level);
        return result;

        // if asked bottom to top view from right side-
        // return result.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        //  result will be : [10, 7, 3, 1]
    }

    public static void main(String[] args) {
        RightSideViewOfBinaryTree tree = new RightSideViewOfBinaryTree();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);

        List<Integer> result = tree.rightViewUtil(root);
        System.out.println("Right Side View of Binary Tree : " + result); // [1, 3, 7, 10]

        List<Integer> result_itr = tree.rightViewUtil_iterative(root);
        System.out.println("Right Side View of Binary Tree : " + result_itr); // [1, 3, 7, 10]
    }

    /*
         **  Simplify Level Tracking:  **
         * -----------------------------*
                - Use a simpler mechanism for level tracking, such as a Pair class to hold node and level information.
     */
    private static class Pair {
        Node node;
        int level;

        Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    //  Iterative way in BFS manner
    //  -   Iterative DFS: Avoids potential stack overflow with deep trees.
    public List<Integer> rightViewUtil_iterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 0));

        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            Node node = current.node;
            int level = current.level;

            if (level == result.size()) {
                result.add(node.key);
            }

            if (node.left != null) {
                stack.push(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                stack.push(new Pair(node.right, level + 1));
            }
        }

        return result;
    }
}
