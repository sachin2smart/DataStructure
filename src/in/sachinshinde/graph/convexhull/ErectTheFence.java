package in.sachinshinde.graph.convexhull;

import java.util.*;

//  https://leetcode.com/problems/erect-the-fence/


/*
        You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

        Fence the entire garden using the minimum length of rope, as it is expensive.
        The garden is well-fenced only if all the trees are enclosed.

        Return the coordinates of trees that are exactly located on the fence perimeter.
        You may return the answer in any order.

        Example 1:
        ---------
        Input: trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
        Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
        Explanation: All the trees will be on the perimeter of the fence except the tree at [2, 2], which will be inside the fence.

        Example 2:
        ---------
        Input: trees = [[1,2],[2,2],[4,2]]
        Output: [[4,2],[2,2],[1,2]]
        Explanation: The fence forms a line that passes through all the trees.

        Constraints:
        -----------
            1 <= trees.length <= 3000
            trees[i].length == 2
            0 <= xi, yi <= 100
            All the given positions are unique.
 */

public class ErectTheFence {

    public int getSlope(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) -
                (c[0] - a[0]) * (b[1] - a[1]);
    }

    public int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees,(a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        List<int[]> upper=new ArrayList<>();
        List<int[]> lower=new ArrayList<>();
        Set<int[]> result=new HashSet<>();

        for (int[] tree : trees) {
            int lastUpper = upper.size() - 1;
            int lastLower = lower.size() - 1;

            while (upper.size() >= 2 && getSlope(upper.get(lastUpper - 1), upper.get(lastUpper), tree) > 0) {
                upper.remove(lastUpper);
                lastUpper--;
            }

            while (lower.size() >= 2 && getSlope(lower.get(lastLower - 1), lower.get(lastLower), tree) < 0) {
                lower.remove(lastLower);
                lastLower--;
            }

            upper.add(tree);
            lower.add(tree);
        }

        result.addAll(lower);
        result.addAll(upper);

        return result.toArray(new int[result.size()][]);
    }

}
