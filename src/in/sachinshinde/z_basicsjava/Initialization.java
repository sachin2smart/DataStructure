package in.sachinshinde.z_basicsjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initialization {
	
	//	1. List of List
	List<List<String>> accounts = Arrays.asList(
			Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
			Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
			Arrays.asList("Mary","mary@mail.com"),
			Arrays.asList("John","johnnybravo@mail.com"));

	//	2. Array of ArrayList
	ArrayList<Integer>[] graph = new ArrayList[10];
	
}