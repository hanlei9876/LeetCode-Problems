package java_practice.Java_Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// link: https://www.youtube.com/watch?v=t1-YZ6bF-g0&list=LL&index=4
public class Java_Streams_Practices_on_YouTube {

    public static void main(String[] args) throws IOException {

        // 1. integer stream
        IntStream
                .range(1, 10)
                .forEach(System.out::print);
        System.out.println();

        // 2. integer stream with skip
        IntStream.range(1, 10)
                .skip(5)
                .forEach(x -> System.out.println(x));
        System.out.println();

        // 3. integer stream with sum
        int sum = IntStream
                .range(1, 5)
                .sum();
        System.out.println(sum);
        System.out.println();

        // 4. Stream.of, sorted and findFirst with Optional
        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println();

        // 5. Stream from Array, filter, sort and print
        String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
        Arrays.stream(names)
                .filter(s -> s.startsWith("A"))
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        // 6. average of squares of an int array
        Arrays.stream(new int[]{2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);
        System.out.println();

        // 7. Stream from List, filter and print
        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
        people.stream()
                .map(String::toLowerCase)
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println();

        // 8. Stream rows from text file, sort, filter, and print
        Path path = Paths.get("src", "Java_Streams", "bands.txt");
        // System.out.println(path.toAbsolutePath());
        Stream<String> bands1 = Files.lines(path);
        bands1
                .sorted()
                .filter(s -> s.length() > 13)
                .forEach(System.out::println);
        bands1.close(); // we MUST close the stream, because it opens a file and must be properly closed to release system resources (avoid resource leak).
        System.out.println(8.1);

        // 8.1 reverse sort string stream
        Stream<String> bands11 = Files.lines(path);
        bands11
                .sorted((s1, s2) -> s2.compareTo(s1))
                .forEach(s -> System.out.print(s + " "));

        // 9. Stream rows from text file and save to List
        List<String> bands2 = Files.lines(Paths.get("src", "Java_Streams", "bands.txt"))
                .filter(s -> s.contains("jit"))
                .collect(Collectors.toList()); // .collect() will automatically close the Stream, once the stream is fully consumed
        bands2.forEach(System.out::println);
        System.out.println();

        // 10. Stream row from CSV file and count
        Stream<String> rows1 = Files.lines(Paths.get("src", "Java_Streams", "data.txt"));
        int rowCount = (int) rows1
                .map(s -> s.split(","))
                .filter(s -> s.length == 3)
                .count();
        System.out.println(rowCount);
        rows1.close();
        System.out.println();

        // 11. Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("src", "Java_Streams", "data.txt"));
        rows2
                .map(s -> s.split(","))
                .filter(sArr -> sArr.length == 3)
                .filter(sArr -> Integer.valueOf(sArr[1]) > 15)
                .forEach(sArr -> System.out.println(sArr[0] + " " + sArr[1] + " " + sArr[2]));
        rows2.close();
        System.out.println();

        // 12. Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("src", "Java_Streams", "data.txt"));
        Map<String, Integer> map = rows3
                .map(s -> s.split(","))
                .filter(sArr -> sArr.length == 3)
                .filter(sArr -> Integer.valueOf(sArr[1]) > 15)
                .collect(
                        Collectors.toMap(sArr -> sArr[0], sArr -> Integer.valueOf(sArr[1]))
                );
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
        System.out.println();

        // 13. reduction, sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (a, b) -> a + b); // a is running total, b is the new element to add
        System.out.println(total);

        // 14. Reduction - summary statistics
        IntSummaryStatistics intSummaryStatistics = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics(); // this only works for integers
        System.out.println(intSummaryStatistics);


        // 15. String.chars() - an IntStream
        String name = "aleyton";
        name.chars()
                .forEach(System.out::println);
        name.chars()
                .forEach(c -> System.out.println((char) c));


        // 16. boxed(), mapToObject()
        //   - mapToObject() - a method used on primitive streams (like IntStream, DoubleStream, etc.) to convert each primitive value into an object
        //   - Primitive streams (IntStream, DoubleStream, etc.) are more efficient for numeric operations, but they don’t support generic object operations.
        //   - So mapToObj() is your bridge to convert them into Stream<T> when needed.
        IntStream.range(1, 4)
                .boxed() // Stream<Integer>
                .forEach(System.out::println); // recommended

        // above is equivalent to below
        IntStream.range(1, 4)
                .mapToObj(i -> i) // Stream<Integer>
                .forEach(System.out::println);

        IntStream.range(1, 4)
                .mapToObj(i -> "Number is: " + i) // Stream<String>
                .forEach(System.out::println);

        name.chars()
                .mapToObj(i -> (char) i) // Stream<Character>
                .forEach(System.out::println);

        name.chars()
                .mapToObj(i -> String.valueOf((char) i)) // Stream<String>
                .forEach(System.out::println);

        // 17. sort int stream
        IntStream.of(3, 1, 9, 6)
                .sorted()
                .forEach(i -> System.out.print(i + " "));

        System.out.println();

        // 18. reverse sort int stream
        IntStream.of(3, 1, 6, 9)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i.intValue())
                .forEach(i -> System.out.print(i + " "));

        IntStream.of(3, 1, 6, 9)
                .boxed()
                .sorted((i1, i2) -> i2 - i1)
                .mapToInt(i -> i.intValue())
                .forEach(i -> System.out.print(i + " "));
    }
}
