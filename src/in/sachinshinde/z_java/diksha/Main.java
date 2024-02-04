package in.sachinshinde.z_java.diksha;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	Developer dev = new Developer.Builder(1)
    		.first("Sachin")
    		.last("Shinde")
    		.email("sachin2smart@gmail.com")
    		.skills(List.of("Java", "Spring"))
    		.build();
    }
}
