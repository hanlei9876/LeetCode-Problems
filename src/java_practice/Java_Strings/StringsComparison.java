package java_practice.Java_Strings;

public class StringsComparison {

    public static void main(String[] args) {
        String s1 = "d";
        String s2 = "d";

        System.out.println(s1.compareTo(s2));
        // > 0 - s1 is lexicographically larger than s2
        // = 0 - s1 has the same content as s2
        // < 0 - s1 is lexicographically smaller than s2

        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));

        System.out.println(s1 == s2);

        int len1 = s1.length();

        String[] sArr = new String[] {"as", "lei"};
        int arrLen = sArr.length;

        basicOperations();
    }

    // Given 2 strings as input: hello, java
    // Output Format
    //
    // There are two lines of output:
    //  - For the second line, write Yes if A is lexicographically larger than B or No if it is not.
    //  - For the third line, capitalize the first letter in both A and B and print them on a single line, separated by a space.
    public static void basicOperations() {
        String s1 = "hello";
        String s2 = "java";

        System.out.println(s1.compareTo(s2)); // < 0

        String s1_1 = s1.substring(0, 1).toUpperCase();
        String s1_2 = s1.substring(1);
        String s1_update = s1_1 + s1_2;

        String s2_update = s2.substring(0, 1).toUpperCase() + s2.substring(1);

        System.out.println(s1_update + " " + s2_update);
    }
}
