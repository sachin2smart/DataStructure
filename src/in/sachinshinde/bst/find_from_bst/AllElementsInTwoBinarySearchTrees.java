package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//  https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

/*
        Given two binary search trees root1 and root2,
            return a list containing all the integers from both trees sorted in ascending order.

        Example 1:
        ---------
        Input: root1 = [2,1,4], root2 = [1,0,3]
        Output: [0,1,1,2,3,4]

        Example 2:
        ---------
        Input: root1 = [1,null,8], root2 = [8,1]
        Output: [1,1,8,8]

        Constraints:
        -----------
        The number of nodes in each tree is in the range [0, 5000].
        -10^5 <= Node.val <= 10^5
 */

public class AllElementsInTwoBinarySearchTrees {

    public List<Integer> getAllElements(Node root1, Node root2) {
        // Don't use HashSet or TreeSet here since duplicate values will not be preserved
        List<Integer> ls = new ArrayList<>();
        inorder(root1, ls);
        inorder(root2, ls);
        ls.sort(Comparator.naturalOrder());
        return ls;
    }

    private void inorder(Node root, List<Integer> ls) {
        if(root == null) {
            return;
        }
        inorder(root.left, ls);
        ls.add(root.key);
        inorder(root.right, ls);
    }

    public static void main(String[] args) {
        AllElementsInTwoBinarySearchTrees tree = new AllElementsInTwoBinarySearchTrees();

        /*
                     2
                   /  \
                  1    4
         */

        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(4);

        /*
                     1
                   /  \
                  0    3
         */

        Node root2 = new Node(1);
        root2.left = new Node(0);
        root2.right = new Node(3);

        System.out.println(tree.getAllElements(root1, root2));  // [0,1,1,2,3,4]

        /*
                     8
                   /
                  1
         */


        Node root3 = new Node(8);
        root3.left = new Node(1);

        /*
                     1
                      \
                       8
         */

        Node root4 = new Node(1);
        root4.right = new Node(8);

        System.out.println(tree.getAllElements(root3, root4));  // [1,1,8,8]
    }
}
