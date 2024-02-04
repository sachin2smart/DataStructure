package in.sachinshinde.z_java;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetNameCount {

    public static void main(String[] args) {
	Employee emp1 = new Employee(1,"Ajay",1000);
        Employee emp2 = new Employee(2,"Payal",1100);
        Employee emp3 = new Employee(3,"Ajay",1700);
        Employee emp4 = new Employee(4,"Payal",2000);
        Employee emp5 = new Employee(5,"Ajay",25000);
        List<Employee> empList = Arrays.asList(emp1,emp2,emp3,emp4,emp5);
        
        Map<String,Long> answer = empList
        				.stream()
        				.collect(Collectors.groupingBy(
        						Employee::getName,
        						Collectors.counting())
        					);
        System.out.println(answer);
    }
}
