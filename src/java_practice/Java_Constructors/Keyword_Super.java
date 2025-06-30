package java_practice.Java_Constructors;

class Keyword_Super {
}

abstract class Animal {
    private String name;

    public int age;

    public Animal(String theName) {
        name = theName;
    }

    public String getName() {
        return name;
    }
}

class Hippo extends Animal {

    public Hippo(String name) {
        super(name); // if we don't add this statement, the compiler will complain, as there is no no-arg version constructor for compiler to add.
    }

    public void printInfo() {
        // name = "Jack";
        getName();

        short s1 = 1;
        s1 = (short) (s1 + 1);
        age = 5;
    }
}




class MakeHippo {
    public static void main(String[] args) {
        Hippo h = new Hippo("Buffy");
        System.out.println(h.getName());

        // complier automatically add Integer.valueOf(), which has a constant cache pool (-128 ~127)
        Integer i1 = 100, i2 = 100, i3 = 500, i4 = 500;
        System.out.println(i1 == i2); // true
        System.out.println(i3 == i4); // false
    }
}
