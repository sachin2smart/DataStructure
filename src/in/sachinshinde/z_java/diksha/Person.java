package in.sachinshinde.z_java.diksha;

public class Person {
    String name;
    
    int age;
    
    String city;
    
    public Person() {
	
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public Person(String name, int age) {
	this.name = name;
	this.age = age;
    }
    
    public Person(String name, int age, String city) {
	this(name, age);
	this.city = city;
    }
}
