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
        /       /   \
       8       9     10


    Spiral Clockwise Edge View Of Binary Tree : [1, 3, 2, 7, 4, 10, 8]

    --------------------------------------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of Node values viewed from Spiral Clockwise Edge View

 */
public class SpiralClockwiseEdgeViewOfBinaryTree {

    public static void main(String[] args) {
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

        LeftSideViewOfBinaryTree leftSideTree = new LeftSideViewOfBinaryTree();
        List<Integer> leftViewResult = leftSideTree.leftViewUtil(root);

        RightSideViewOfBinaryTree rightSideTree = new RightSideViewOfBinaryTree();
        List<Integer> rightViewResult = rightSideTree.rightViewUtil(root);

        int spiralEdgeViewCount = Math.max(leftViewResult.size(), rightViewResult.size());
        List<Integer> spiralEdgeView = new ArrayList<>();
        spiralEdgeView.add(leftViewResult.get(0));

        for(int i=1; i<spiralEdgeViewCount; i++) {
            if(i < rightViewResult.size()) {
                spiralEdgeView.add(rightViewResult.get(i));
            }
            if(i < leftViewResult.size()) {
                spiralEdgeView.add(leftViewResult.get(i));
            }
        }

        System.out.print("Spiral Clockwise Edge View of Binary Tree is : " + spiralEdgeView);
        // [1, 3, 2, 7, 4, 10, 8]
    }
}
