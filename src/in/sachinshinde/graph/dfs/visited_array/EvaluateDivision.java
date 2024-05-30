package in.sachinshinde.graph.dfs.visited_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
    
    public class Pair{
        String key;
        double value;
    
        Pair(String num, double value) {
            this.key = num;
            this.value = value;
        }
    }
    
    Map<String, ArrayList<Pair>> graph;
    
    public EvaluateDivision() {
	graph = new HashMap<>();
    }
    
    public double dfs(String src, String dest, HashSet<String> visited){
	    visited.add(src);
        if(src.equals(dest)) {
            return 1;
        }
        
        for(Pair pair : graph.get(src)) {
            if(!visited.contains(pair.key)) {
                double res = dfs(pair.key, dest, visited);
                if(res > 0) {
                    return pair.value * res;
                }
            }
        }
        
        return -1.0;
    }
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        
        for(int i = 0; i < equations.size(); i++) {
            String src = equations.get(i).get(0);
            String dest = equations.get(i).get(1);
            double w = values[i];
            
            graph.putIfAbsent(src, new ArrayList<>());
            graph.putIfAbsent(dest, new ArrayList<>());
            
            graph.get(src).add(new Pair(dest, w));
            graph.get(dest).add(new Pair(src, 1/w));
        }
        
        for(int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);
            
            if(graph.containsKey(src)) {
            	res[i] = dfs(src, dest, new HashSet<>());
            }
            else {
        	    res[i] = -1.0;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        EvaluateDivision div = new EvaluateDivision();
        System.out.println(Arrays.toString( div.calcEquation(
            Arrays.asList(Arrays.asList("a","b"), Arrays.asList("b","c")),
            new double[] {2.0,3.0},
            Arrays.asList(Arrays.asList("a","c"), Arrays.asList("b","a"), Arrays.asList("a","e"), Arrays.asList("a","a"), Arrays.asList("x","x")))
		));	//	answer: [6.0, 0.5, -1.0, 1.0, -1.0]
	
        div = new EvaluateDivision();
        System.out.println(Arrays.toString( div.calcEquation(
            Arrays.asList(Arrays.asList("a","b"), Arrays.asList("b","c"), Arrays.asList("bc","cd")),
            new double[] {1.5, 2.5, 5.0}, Arrays.asList(Arrays.asList("a","c"), Arrays.asList("c","b"), Arrays.asList("bc","cd"), Arrays.asList("cd","bc")))
        ));	//	answer: [3.75, 0.4, 5.0, 0.2]
	
	    div = new EvaluateDivision();
	    System.out.println(Arrays.toString( div.calcEquation(
		    Arrays.asList(Arrays.asList("a","b")),
		    new double[] {0.5},
		    Arrays.asList(Arrays.asList("a","b"), Arrays.asList("b","a"), Arrays.asList("a","c"), Arrays.asList("x","y")))
		));	//	answer: [0.5, 2.0, -1.0, -1.0]
    }
}