package in.sachinshinde.z_basics_ds;

import in.sachinshinde.bst.Node;
import in.sachinshinde.bst.operations_on_bst.SerializeAndDeserializeBST;

import java.util.*;

public class DataTransformation {

    public static void main(String[] args) {
        /*
            Given : [[1,2,7],[3,6,7]]
            Transform:
                1 -> [0]        1 exists in 0'th index array
                2 -> [0]        2 exists in 0'th index array
                3 -> [1]        3 exists in 1'st index array
                6 -> [1]        6 exists in 1'st index array
                7 -> [0,1]      7 exists in 0'th and 1'st index array
         */

        int[][] arr = new int [][] {
                {1,2,7},
                {3,6,7}};

        Map<Integer, List<Integer>> transformed = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int[] rowData = arr[i];
            for (int ele : rowData) {
                if (!transformed.containsKey(ele)) {
                    transformed.put(ele, new ArrayList<>());
                }
                transformed.get(ele).add(i);
            }
        }

        System.out.println(transformed);    // {1=[0], 2=[0], 3=[1], 6=[1], 7=[0, 1]}
        
        
        /*
         	Given : this is directed graph : graph[][] 
         	Convert into Adjacency List & calculate the in-degree and out-degree of each node
         */
        
        int[][] graph = new int[][] {
            			{1,2},
            			{3,4}, 
            			{2,4},
            			{1,3}};
        
        int[] inDegree = new int[5];
        int[] outDegree = new int[5];
            			
        Map<Integer, List<Integer>> adjList = new HashMap();
        for(int i=0; i < graph.length; i++) {
            int src = graph[i][0];
            int dest = graph[i][1];
            List<Integer> currList = adjList.getOrDefault(src, new ArrayList<>());
            currList.add(dest);
            adjList.put(src, currList);
            outDegree[src]++;
            inDegree[dest]++;
        }
        System.out.println(adjList);	//	{1=[2, 3], 2=[4], 3=[4]}
        System.out.println(Arrays.toString(inDegree));	// [0, 0, 1, 1, 2]
        System.out.println(Arrays.toString(outDegree));	// [0, 2, 1, 1, 0]

        /*
                check if the current character is a uppercase or a lowercase ?
         */
        char[] chArr = new char[]{'A','b','c','D','1'};
        for(int i = 0; i < chArr.length; i++) {
           if(Character.isLetter(chArr[i])) {
               if(Character.isLowerCase(chArr[i])) {
                   System.out.println(chArr[i] + " : Lowercase");
               }
               if(Character.isUpperCase(chArr[i])) {
                   System.out.println(chArr[i] + " : Uppercase");
               }
           }
           else {
               System.out.println(chArr[i] + " : Number");
           }
        }


        String data = "1,2,3";
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        System.out.println(q);
    }
}
