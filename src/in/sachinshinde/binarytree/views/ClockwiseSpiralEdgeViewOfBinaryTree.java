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


    Clockwise Spiral Edge View Of Binary Tree : [1, 10, 8, 2, 3, 7, 4]

    --------------------------------------------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of Node values viewed from Clockwise Spiral Edge View Of Binary Tree

 */

public class ClockwiseSpiralEdgeViewOfBinaryTree {

    private int getHeight(Node root) {
        if(root == null) {
            return 0;
        }

        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        return Math.max(lHeight + 1, rHeight + 1);
    }

    private void collectLeftToRightNodesOnALevel(Node root, int level, List<Integer> nodesList) {
        if(root == null) {
            return;
        }

        if(level == 1) {
            nodesList.add(root.key);
        }
        else if(level > 1) {
            collectLeftToRightNodesOnALevel(root.left,  level-1, nodesList);
            collectLeftToRightNodesOnALevel(root.right, level-1, nodesList);
        }
    }

    private void collectRightToLeftNodesOnALevel(Node root, int level, List<Integer> nodesList) {
        if(root == null) {
            return;
        }

        if(level == 1) {
            nodesList.add(root.key);
        }
        else if(level > 1) {
            collectRightToLeftNodesOnALevel(root.right, level-1, nodesList);
            collectRightToLeftNodesOnALevel(root.left,  level-1, nodesList);
        }
    }

    private List<Integer> clockwiseSpiralEdgeView(Node root) {
        int i = 1;
        int j = getHeight(root);
        List<Integer> result = new ArrayList<>();
        int flag = 0;

        while(i <= j) {
            List<Integer> values = new ArrayList<>();
            if(flag == 0) {
                collectLeftToRightNodesOnALevel(root, i, values);
                if(values.size() > 1) {
                    result.add(values.get(0));
                    result.add(values.get(values.size()-1));
                }
                else {
                    result.add(values.get(0));
                }
                flag = 1;
                i++;
            }
            else {
                collectRightToLeftNodesOnALevel(root, j, values);
                if(values.size() > 1) {
                    result.add(values.get(0));
                    result.add(values.get(values.size()-1));
                }
                else {
                    result.add(values.get(0));
                }
                flag = 0;
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ClockwiseSpiralEdgeViewOfBinaryTree tree = new ClockwiseSpiralEdgeViewOfBinaryTree();

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

        List<Integer> result = tree.clockwiseSpiralEdgeView(root);
        System.out.println("Clockwise Spiral Edge View of Binary Tree : " + result);
        //  [1, 10, 8, 2, 3, 7, 4]
    }
}
