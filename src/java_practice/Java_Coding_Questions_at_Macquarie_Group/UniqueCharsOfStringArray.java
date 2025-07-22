package java_practice.Java_Coding_Questions_at_Macquarie_Group;

import java.util.Arrays;
import java.util.List;

public class UniqueCharsOfStringArray {
    // Given a list of strings, return the number of unique characters.
    // There is no distinction between upper and lower case
    // Please use Java Stream API
    //
    // For example,
    // input: ["Flamengo", "Vasco", "Fluminense"]
    // Output: 13
    // Reason: there are the unique char list [f, l, a, m, en, n, g, o, v, s, c, u, i]

    public static void main(String[] args) {
        String[] strings1 = {"Flamengo", "Vasco", "Fluminense"};
        List<String> strings2 = List.of("Flamengo", "Vasco", "Fluminense");

        System.out.println(getUniqueChars(strings2));
    }

    // input 1: Array
    private static int getUniqueChars(String[] strings) {
        return (int) Arrays.stream(strings)
                .flatMap(
                     s ->
                             s.toLowerCase()
                                     .chars()
                                     .mapToObj(c -> (char) c)
                )
                .distinct()
                .count();
    }

    // input 2: ArrayList
    private static int getUniqueChars(List<String> strings) {
        return (int) strings.stream()
                .flatMap(
                        s ->
                                s.toLowerCase()
                                        .chars()
                                        .mapToObj(c -> (char) c)
                )
                .distinct()
                .count();
    }
}
