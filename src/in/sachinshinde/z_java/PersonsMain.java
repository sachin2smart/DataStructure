package in.sachinshinde.z_java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonsMain {

	public static void main(String[] args) {
		Person p1 = new Person("Sachin", 36, "pune");
		Person p2 = new Person("Sandeep", 35, "new york");
		Person p3 = new Person("Pravin", 33, "toronto");
		
		List<Person> persons= new ArrayList<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		
		List<String> cities = getCities(persons);
		System.out.println(cities);
	}

	private static List<String> getCities(List<Person> persons) {
			return persons.stream()
					.sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
//					.sorted(Comparator.comparing(Person::getName))
					.map(p -> Character.toUpperCase(p.getCity().charAt(0)) + p.getCity().substring(1))
					.collect(Collectors.toList());
	}
	
	
}
