package in.sachinshinde.graph.union_find;

//	https://leetcode.com/problems/satisfiability-of-equality-equations/

/*
 * 	You are given an array of strings equations that represent relationships between variables 
 * 		where each string equations[i] is of length 4 and takes one of two different forms: 
 * 			"xi==yi" or "xi!=yi".
 * 		Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

	Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, 
	or false otherwise.
 */

//	Approach : Union Find Algorithm

public class SatisfiabilityOfEqualityEquations {
	
	int[] uf = new int[26];	//	Since we have 26 characters
	
	public boolean doesEquationsSatisfyEquality(String[] equations) {
		// default values
		for (int i = 0; i < 26; ++i) 
			uf[i] = i;
		
		//	for [u,v] pair, on equality uf[u] = v; 
		for (String e : equations)
            if (e.charAt(1) == '=')
                uf[find(e.charAt(0) - 'a')] = find(e.charAt(3) - 'a');
		
		//	for [u,v] pair, on inequality if found u,v as equal -> return false; 
		for (String e : equations)
            if (e.charAt(1) == '!' && find(e.charAt(0) - 'a') == find(e.charAt(3) - 'a'))
                return false;
		
		return true;
    }
	
	// find function for only one param 
	public int find(int x) {
        if (x != uf[x]) 
        	uf[x] = find(uf[x]);
        
        return uf[x];
    }
	
}
