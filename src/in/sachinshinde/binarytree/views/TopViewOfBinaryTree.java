package in.sachinshinde.binarytree.views;

import in.sachinshinde.binarytree.Node;

import java.util.*;

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


    Top View of Binary Tree : [4, 2, 1, 3, 7, 10]

    --------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of Node values viewed from Top

 */

public class TopViewOfBinaryTree {

    static class Pair {
        int hd; // horizontal distance
        Node node;

        public Pair(int hd, Node node) {
            this.hd = hd;
            this.node = node;
        }
    }

    private List<Integer> topView(Node root) {
        if(root == null) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> hdToValueMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        // to view the tree from Top with order from Right to Left declare map as :
        // Map<Integer, Integer> hdToValueMap = new TreeMap<>(Comparator.reverseOrder());
        //  the result will be: [10, 7, 3, 1, 2, 4]

        queue.add(new Pair(0, root));

        while(!queue.isEmpty()) {
            Pair currPair = queue.poll();
            int currHd = currPair.hd;
            Node currNode = currPair.node;

            hdToValueMap.computeIfAbsent(currHd, k -> currNode.key);

            if(currNode.left != null) {
                queue.add(new Pair(currHd - 1, currNode.left));
            }

            if(currNode.right != null) {
                queue.add(new Pair(currHd + 1, currNode.right));
            }
        }

        return new ArrayList<>(hdToValueMap.values());
    }

    public static void main(String[] args) {
        TopViewOfBinaryTree tree = new TopViewOfBinaryTree();

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

        List<Integer> result = tree.topView(root);
        System.out.println("Top view of a Binary Tree : " + result);
    }
}
