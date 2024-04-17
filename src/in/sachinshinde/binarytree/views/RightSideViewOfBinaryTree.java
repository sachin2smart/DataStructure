package in.sachinshinde.binarytree.views;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.List;

/*

Binary Tree:
-----------

           1
        /     \
       2       3
      / \    /   \
     4   5  6     7
             \   / \
              8 9  10


    Right Side View of Binary Tree : [1,3,7,10]

    --------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of Node values viewed from Right Side

 */

public class RightSideViewOfBinaryTree {

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
    }
}
