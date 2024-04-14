package in.sachinshinde.z_basicsjava.cloning;

public class TestCloneByDeepCopy {

    static class Test2 implements Cloneable {
        int a, b;

        Test c = new Test();

        public Object clone() throws CloneNotSupportedException {
            // Assign the shallow copy to new reference variable t
            Test2 t = (Test2) super.clone();

            // Creating a deep copy for c
            t.c = new Test();
            t.c.x = c.x;    // assign shallow copy field value to the deep copy field
            t.c.y = c.y;    // assign shallow copy field value to the deep copy field

            return t;   // t ==> deep copy object
        }

        public static void main(String args[]) throws CloneNotSupportedException {
            Test2 t2 = new Test2();

            t2.a = 10;
            t2.b = 20;
            t2.c.x = 30;
            t2.c.y = 40;

            Test2 t2Clone = (Test2) t2.clone();
            t2Clone.a = 100;
            t2Clone.b = 200;

            // Change in primitive type of t2Clone will not be reflected in t2 field
            t2Clone.c.x = 300;
            t2Clone.c.y = 400;

            // Change in object type field of t2Clone will not be reflected in t2 (deep copy)
            System.out.println(t2.a + " " + t2.b + " " + t2.c.x + " " + t2.c.y);
            System.out.println(t2Clone.a + " " + t2Clone.b + " " + t2Clone.c.x + " " + t2Clone.c.y);

            /*
                    10  20      30   40
                    100 200     300  400
             */
        }
    }

}
