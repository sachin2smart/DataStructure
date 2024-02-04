package in.sachinshinde.z_java.diksha;

import java.util.ArrayList;
import java.util.List;

public record Developer(Integer id, String first, String last, String email, List<String> skills) {
    
    public static class Builder {
	    Integer id;
	    String first;
	    String last;
	    String email;
	    List<String> skills = new ArrayList<String>();
	    
	    public Builder(int id) {
		this.id = id;
	    }

	    public Builder first(String first) {
		this.first = first;
		return this;
	    }

	    public Builder last(String last) {
		this.last = last;
		return this;
	    }

	    public Builder email(String email) {
		this.email = email;
		return this;
	    }

	    public Builder skills(List<String> skills) {
		this.skills = skills;
		return this;
	    }
	    
	    public Developer build() {
		return new Developer(id, first, last, email, skills);
	    }
	    
	}
}