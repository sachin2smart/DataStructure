package in.sachinshinde.graph.bfs;

import java.util.*;

//  https://leetcode.com/problems/find-eventual-safe-states/

/*
        There is a directed graph of n nodes with each node labeled from 0 to n - 1.
        The graph is represented by a 0-indexed 2D integer array graph where
            graph[i] is an integer array of nodes adjacent to node i,
            meaning there is an edge from node i to each node in graph[i].

        A node is a terminal node if there are no outgoing edges.
        A node is a safe node if every possible path starting from that node leads to a terminal node
            (or another safe node).

        Return an array containing all the safe nodes of the graph.
        The answer should be sorted in ascending order.

        Example 1:
        ---------
        Illustration of graph
        Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
        Output: [2,4,5,6]
        Explanation: The given graph is shown above.
        Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
        Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

        Example 2:
        ---------
        Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
        Output: [4]
        Explanation:
        Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


        Constraints:
        -----------
            n == graph.length
            1 <= n <= 104
            0 <= graph[i].length <= n
            0 <= graph[i][j] <= n - 1
            graph[i] is sorted in a strictly increasing order.
            The graph may contain self-loops.
            The number of edges in the graph will be in the range [1, 4 * 104].
 */

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new LinkedList<>();

        if(graph == null || graph.length == 0) {
            return res;
        }

        int nodes = graph.length;

        // initialize adjacency list
    List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<>());
        }

        // calculate inDegree and prepare adjacency list
        int[] inDegree = new int[nodes];
        for(int currNode = 0; currNode < nodes; currNode++) {
            for(int connectedNode : graph[currNode]) {
                adj.get(connectedNode).add(currNode);
                inDegree[currNode]++;
            }
        }

        // all 0 degree nodes to be added into the q
        Queue<Integer> q = new LinkedList<>();
        for(int node = 0; node < nodes; node++) {
            if(inDegree[node] == 0) {
                q.add(node);
            }
        }

        // basic BFS traversal
        while(!q.isEmpty()) {
            int currNode = q.poll();
            res.add(currNode);
            for(int connectedNode : adj.get(currNode)) {
                inDegree[connectedNode]--;
                if(inDegree[connectedNode] == 0) {
                    q.add(connectedNode);
                }
            }
        }

        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        FindEventualSafeStates states = new FindEventualSafeStates();
        System.out.println(states.eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));   // [2,4,5,6]
        System.out.println(states.eventualSafeNodes(new int[][]{{1,2,3,4},{1,2},{3,4},{0,4},{}}));   // [4]
    }
}
