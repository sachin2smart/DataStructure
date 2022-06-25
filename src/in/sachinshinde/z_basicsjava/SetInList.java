package in.sachinshinde.z_basicsjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetInList {

	public static void main(String[] args) {
		Set<Integer> s = new TreeSet<>();
		s.add(4);
		s.add(5);
		s.add(7);
		s.add(1);
		s.add(5);
		
		System.out.println(s);
		
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(8);
		
		l.containsAll(s);
	}
	
}
