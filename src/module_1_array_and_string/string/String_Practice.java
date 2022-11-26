package module_1_array_and_string.string;

public class String_Practice {

    public static void main(String[] args) {

        String s1 = "";
        System.out.println(s1); //
        System.out.println(s1.length()); // 0
        System.out.println(s1.charAt(0)); // String index out of range: 0

        String s2 = null;
        System.out.println(s2); // null
        // System.out.println(s2.length()); // NullPointerException

        // String's Poll Mechanism in Java
        String s3 = "I am good";
        String s4 = "I am good";
        System.out.println("If s3 == s4: " + (s3 == s4)); // true

        String s5 = new String(s3);
        System.out.println(s5); // I am good
        System.out.println("If s3 == s5: " + (s3 == s5)); // false


        // to access a character in a string
        char c = s3.charAt(0);
        System.out.println(c); // I

        // == vs .equals()
        System.out.println("If s3 == s5: " + (s3 == s5)); // false
        System.out.println("If s3 equals to s5: " + (s3.equals(s5))); // true

        // string concatenation
        String s6 = "Hello World";
        s6 = s6 + "!"; // String is immutable in Java. So, under the hood, a new string object is created with content "Hello World!"
        String s7 = s6.substring(1, 3);
        String s8 = s6.substring(1);
        System.out.println("s7 is: " + s7);
        System.out.println("s8 is: " + s8);

        char[] chars = s6.toCharArray();
        System.out.println(chars); // Hello World!

        StringBuilder sb = new StringBuilder("abc");
        System.out.println(sb); // abc

        sb.append("xyz");
        System.out.println(sb); // abcxyz

        sb.reverse();
        System.out.println(sb); // zyxcba

    }

}
