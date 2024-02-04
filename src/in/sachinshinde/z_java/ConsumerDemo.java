package in.sachinshinde.z_java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo implements Consumer<String>{

//    public static void main(String[] args) {
//	    List<String> str = new ArrayList<>();
//	    str.add("DEMO");
//	    str.add("DEMO2");
//	    str.add("DEMO3");
//	
//	    Consumer<String> consumer = new Consumer<String>() {
//	        @Override
//	        public void accept(String t) {
//	        	System.out.println(t);
//	        }
//	    };
	
//	    str.forEach(consumer);

//    }

	@Override
	public void accept(String t) {
		System.out.println(t);
	}

}
