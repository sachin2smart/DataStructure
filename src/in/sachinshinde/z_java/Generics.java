package in.sachinshinde.z_java;

import java.util.HashSet;
import java.util.Set;

class Test<T, U> {
    T obj1;  // An object of type T
    U obj2;  // An object of type U
  
    // constructor
    Test(T obj1, U obj2)
    {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
  
    // To print objects of T and U
    public void print()
    {
        System.out.println(obj1);
        System.out.println(obj2);
    }

    public T getObj1() {
        return obj1;
    }

    public void setObj1(T obj1) {
        this.obj1 = obj1;
    }

    public U getObj2() {
        return obj2;
    }

    public void setObj2(U obj2) {
        this.obj2 = obj2;
    }
}

public class Generics {    
    public static void main(String[] args) {
	//	Java specification doesn't allow you to declare an array of generics object.
	//	Hence use the wildcard; but this will loose the type-safety
	Set<?>[] rows = new HashSet<?>[9];
	
	Test <String, Integer> obj1 =
	            new Test<String, Integer>("GfG", 15);
	  
	obj1.print();
	
	Test <Integer, String> obj2 =
	            new Test<Integer, String>(15, "GfG");
	  
	obj2.print();
    }
}