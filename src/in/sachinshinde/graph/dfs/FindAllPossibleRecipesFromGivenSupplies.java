package in.sachinshinde.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

//	https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies

/*
 	You have information about n different recipes. 
 	You are given a string array recipes and a 2D string array ingredients. 
 	The ith recipe has the name recipes[i], and you can create it 
 		if you have all the needed ingredients from ingredients[i]. 
 	Ingredients to a recipe may need to be created from other recipes, i.e., 
 		ingredients[i] may contain a string that is in recipes.

	You are also given a string array supplies containing all the ingredients 
		that you initially have, and you have an infinite supply of all of them.
	
	Return a list of all the recipes that you can create. 
	You may return the answer in any order.
	
	Note that two recipes may contain each other in their ingredients.

 
	---------
	Example 1:
	---------
	Input: 
		recipes = ["bread"], 
		ingredients = [["yeast","flour"]], 
		supplies = ["yeast","flour","corn"]
	Output: ["bread"]
	Explanation:
		We can create "bread" since we have the ingredients "yeast" and "flour".
	---------
	Example 2:
	---------
	Input: 
		recipes = ["bread","sandwich"], 
		ingredients = [["yeast","flour"],["bread","meat"]], 
		supplies = ["yeast","flour","meat"]
	Output: ["bread","sandwich"]
	Explanation:
		We can create "bread" since we have the ingredients "yeast" and "flour".
		We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
	---------
	Example 3:
	---------
	Input: 
		recipes = ["bread","sandwich","burger"], 
		ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], 
		supplies = ["yeast","flour","meat"]
	Output: ["bread","sandwich","burger"]
	Explanation:
		We can create "bread" since we have the ingredients "yeast" and "flour".
		We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
		We can create "burger" since we have the ingredient "meat" and 
			can create the ingredients "bread" and "sandwich".
 */
public class FindAllPossibleRecipesFromGivenSupplies {

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		List<String> createdRecipes = new ArrayList<>();
		Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));
		Map<String, List<String>> recipeDependencies = new HashMap<>();
		Map<String, Integer> dependency = new HashMap<>();

		// build graph
		for (int i = 0; i < recipes.length; i++) {
			for (String ingredient : ingredients.get(i)) {
				if (!suppliesSet.contains(ingredient)) {
					recipeDependencies.putIfAbsent(ingredient, new ArrayList<>());
					recipeDependencies.get(ingredient).add(recipes[i]);
					dependency.merge(recipes[i], 1, Integer::sum);
				}
			}
		}

		// topology
		Queue<String> recipeSequence = Arrays.stream(recipes)
				.filter(recipe -> dependency.getOrDefault(recipe, 0) == 0)
				.collect(Collectors.toCollection(ArrayDeque::new));

		while (!recipeSequence.isEmpty()) {
			String recipeMadeSoFar = recipeSequence.poll();
			createdRecipes.add(recipeMadeSoFar);

			if (!recipeDependencies.containsKey(recipeMadeSoFar)) {
				continue;
			}

			for (String dependentRecipe : recipeDependencies.get(recipeMadeSoFar)) {
				dependency.merge(dependentRecipe, -1, Integer::sum);
				if (dependency.get(dependentRecipe) == 0) {
					recipeSequence.offer(dependentRecipe);
				}
			}
		}

		return createdRecipes;
    }

    public List<String> findAllRecipes2(String[] recipes, List<List<String>> ingredients, String[] supplies) {
	Set<String> ingredientSet = new HashSet<String>();
	for (String s : supplies)
	    ingredientSet.add(s);

	Map<String, List<String>> recipeGraph = new HashMap<String, List<String>>();
	for (int i = 0; i < recipes.length; i++)
	    recipeGraph.put(recipes[i], ingredients.get(i));

	Set<String> ingredientTaken = new HashSet<String>();
	List<String> createdRecipes = new LinkedList<String>();

	for (String recipe : recipes)
	    if (isPossibleToMakeRecipe(ingredientSet, ingredientTaken, recipeGraph, recipe))
		createdRecipes.add(recipe);

	return createdRecipes;
    }

    private boolean isPossibleToMakeRecipe(Set<String> ingredientSet, Set<String> ingredientTaken,
	    Map<String, List<String>> recipeGraph, String currIngredient) {
	if (ingredientSet.contains(currIngredient))
	    return true;

	if (ingredientTaken.contains(currIngredient))
	    return false;

	ingredientTaken.add(currIngredient);

	if (recipeGraph.get(currIngredient) == null)
	    return false;

//        If the ingredient is not present in supplies i.e., it's a recipe:
//        Check whether that recipe can be created, 
	for (String nextIngredient : recipeGraph.get(currIngredient))
	    if (!isPossibleToMakeRecipe(ingredientSet, ingredientTaken, recipeGraph, nextIngredient))
		return false; // if no, the current recipe also cannot be created.

//        if yes, proceed forward to another ingredient
	ingredientSet.add(currIngredient);
	return true;
    }

    // Using Kahn's algorithm
    public List<String> findAllRecipes3(String[] recipes, List<List<String>> ingredients, String[] supplies) {
	HashMap<String, Integer> recMap = new HashMap<>();
	HashMap<String, ArrayList<String>> graph = new HashMap<>();
	HashSet<String> sup = new HashSet<>();
	int n = recipes.length;
	int[] indegree = new int[n];
	
	for (int i = 0; i < supplies.length; i++) {
	    sup.add(supplies[i]);
	}

	for (int i = 0; i < n; i++) {
	    recMap.put(recipes[i], i);
	}

	
	for (int i = 0; i < n; i++) {
	    for (String str : ingredients.get(i)) {
		if (sup.contains(str) == false) {
		    graph.putIfAbsent(str, new ArrayList<>());
		    ArrayList<String> list = graph.get(str);
		    list.add(recipes[i]);
		    indegree[i]++;
		}
	    }
	}

	//	KAHN'S ALGORITHM
	Queue<String> q = new LinkedList<>();
	for (int i = 0; i < n; i++) {
	    if (indegree[i] == 0) {
		q.add(recipes[i]);
	    }
	}
	
	List<String> ans = new ArrayList<>();
	while (!q.isEmpty()) {
	    String node = q.peek();
	    q.poll();
	    ans.add(node);
	    if (graph.containsKey(node)) {
		for (String str : graph.get(node)) {
		    indegree[recMap.get(str)]--;
		    if (indegree[recMap.get(str)] == 0)
			q.add(str);
		}
	    }
	}

	return ans;
    }
}