package in.sachinshinde.z_java.diksha;

public class DataAccess {
    
    public static void main(String[] args) {
	Person p1 = new Person();
	
	Person p2 = new Person();
	p2.setName("Sachin");
	p2.setAge(33);
	
	Person p2_1 = new Person("Sachin", 33);
	Person p3 = new Person("Diksha", 29, "Montreal");
	
	int a[] = {1,2,3};
	System.out.println(a.length);
    }
    
}
