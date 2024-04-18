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
        /       /   \
       8       9     10


    Bottom View of Binary Tree : [4, 8, 6, 9, 7, 10]

    --------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of Node values viewed from Bottom

 */

public class BottomViewOfBinaryTree {

    static class Pair {
        int hd; // horizontal distance
        Node node;

        public Pair(int hd, Node node) {
            this.hd = hd;
            this.node = node;
        }
    }

    private List<Integer> bottomView(Node root) {
        if(root == null) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> hdToValueMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        // to view the tree from Bottom with order from Right to Left declare map as :
        // Map<Integer, Integer> hdToValueMap = new TreeMap<>(Comparator.reverseOrder());
        //  the result will be: [10, 7, 9, 6, 8, 4]

        queue.add(new Pair(0, root));

        while(!queue.isEmpty()) {
            Pair currPair = queue.poll();
            int currHd = currPair.hd;
            Node currNode = currPair.node;

            hdToValueMap.put(currHd, currNode.key);

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
        BottomViewOfBinaryTree tree = new BottomViewOfBinaryTree();

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

        List<Integer> result = tree.bottomView(root);
        System.out.println("Bottom view of a Binary Tree : " + result);
    }
}
