package java_practice.Java_Optional;

import java.util.Optional;

public class Optional_FlatMap {

    // flatMap >> used to flatten the nested Optional<Optional<T>>
    public static void main(String[] args) {
        // scenario 1
        Optional<String> res1 = Optional.of("1").map(s -> getOutput(s));
        System.out.println(res1.get());

        // scenario 2
        Optional<Optional<String>> res2 = Optional.of("2").map(s -> getOutputOptional(s));
        System.out.println(res2.get().get());

        // scenario 3
        Optional<String> res3 = Optional.of("3").flatMap(s -> getOutputOptional(s));
        System.out.println(res3.get());
    }

    public static String getOutput(String input) {
        String output = false ? null : "Output is: " + input;
        return output;
    }

    public static Optional<String> getOutputOptional(String input) {
        Optional<String> output = false ? Optional.empty() : Optional.of("Output is: " + input);
        return output;
    }
}
