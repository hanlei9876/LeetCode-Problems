package java_practice.Java_Streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NestedStreams {
    public static void main(String[] args) {

        // flatMap in Java Streams
        //   - first transforms each element into a stream
        //   - then flattens all those streams into one single stream

        // example: say you have a list of strings and you want to turn each string into a list of characters
        List<String> words = List.of("hi", "bye");

        // 1 - using map to get a stream of streams
        Stream<Stream<Character>> nestedStream = words.stream()
                .map(
                        word -> word.chars()
                                .mapToObj(i -> (char) i)
                );

        // 2 - using flatMap
        Stream<Character> charStream = words.stream()
                .flatMap(
                        word -> word.chars()
                                .mapToObj(i -> (char) i)
                );

        // another example
        List<List<String>> nestedList = List.of(
                List.of("apple", "orange"),
                List.of("pen", "rubber")
        );

        List<String> stringList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(stringList); // [apple, orange, pen, rubber]

        // how to Stream.ofNullable()
        main2();

        NestedStreams nestedStreams = new NestedStreams();
        nestedStreams.main3();
    }

    // Stream.ofNullable() - create a stream of one element that is either 0 or 1, depending on whether input is null
    // Use Stream.ofNullable() inside stream pipelines where you might have a nullable value but want to keep the flow functional and avoid if conditions
    static void main2() {
        String s1 = null;
        String s2 = "abc";

        Stream.ofNullable(s2)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    // flatMap(Stream.ofNullable(...))
    //   - for example, you have a list of Person objects (people), and each person might have a Spouse
    //   - You want to collect the names of all spouses into a list, skipping nulls
    void main3() {
        List<Person> people = List.of(
                new Person("Jack", new Spouse("Annie")),
                new Person("Mike", null),
                new Person("Rock", new Spouse("Mary"))
        );

        // flatMap(Stream.ofNullable(...)) filters out empty stream (null) cleanly
        List<String> spouseNames = people.stream()
                .flatMap(person -> Stream.ofNullable(person.getSpouse()))
                .map(spouse -> spouse.getName())
                .collect(Collectors.toList());
        System.out.println(spouseNames); // [Annie, Mary]
    }

    // inner class must be associated with an instance of outer class. So, inner class cannot be instantiated inside static methods
    class Person {
        private String name;
        private Spouse spouse;

        Person(String name, Spouse spouse) {
            this.name = name;
            this.spouse = spouse;
        }

        public Spouse getSpouse() { return spouse; }
    }

    class Spouse {
        private String name;

        Spouse(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }
}
