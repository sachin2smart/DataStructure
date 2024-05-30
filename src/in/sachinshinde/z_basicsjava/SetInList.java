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
		System.out.println("Set : " +s);
		
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(8);
		System.out.println("List : "+l);

		System.out.println(l.containsAll(s));	// false

		l.set(1,4);
		System.out.println("Modified List : "+l);

		System.out.println(l.containsAll(s));	// false;

		l.add(7);
		l.add(5);
		System.out.println("Modified List : "+l);

		System.out.println(l.containsAll(s));	// true;

		//-----
		// the add() method return false, if element already exists in the list

		if(s.add(4)) {
			System.out.println("adding element in set since it does not exists");
		}
		else {
			System.out.println("the element already exists");
		}

	}
	
}
