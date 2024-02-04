package in.sachinshinde.z_java;

public class Employee {
    int id;
    String name;
    int managerId;
    
    public Employee(int id, String name, int managerId) {
	this.id = id;
	this.name = name;
	this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    
}
