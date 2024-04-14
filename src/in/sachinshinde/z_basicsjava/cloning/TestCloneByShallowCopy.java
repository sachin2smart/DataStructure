package in.sachinshinde.z_basicsjava.cloning;

public class TestCloneByShallowCopy {

    static class Test2 implements Cloneable {
        int a, b;

        Test c = new Test();

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String args[]) throws CloneNotSupportedException {
        Test2 t2 = new Test2();

        t2.a = 10;
        t2.b = 20;
        t2.c.x = 30;
        t2.c.y = 40;

        Test2 t2Clone = (Test2) t2.clone();

        // Creating a copy of object t2 and passing it to t2Clone
        t2Clone.a = 100;
        t2Clone.b = 200;

        // Change in primitive type of t2Clone will not be reflected in t2 field
        t2Clone.c.x = 300;
        t2Clone.c.y = 400;

        // Change in object type field will be reflected in both t2Clone and t2 (shallow copy)
        System.out.println(t2.a + " " + t2.b + " " + t2.c.x + " " + t2.c.y);
        System.out.println(t2Clone.a + " " + t2Clone.b + " " + t2Clone.c.x + " " + t2Clone.c.y);

        /*
            10  20   300 400
            100 200  300 400
         */
    }


}

/*
        Points:-
        ------
            [1] Shallow copy is the method of copying an object and is followed by default in cloning.
                In this method, the fields of an old object X are copied to the new object Y.
                While copying the object type field the reference is copied to Y i.e
                    object Y will point to the same location as pointed out by X.
                If the field value is a primitive type it copies the value of the primitive type.

            [2] Any changes made in referenced objects in object X or Y will be reflected in other objects.

            [3] Shallow copies are cheap and simple to make.
 */