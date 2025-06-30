package java_practice.Java_Streams;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Check_Songs {
    public static void main(String[] args) {

        List<Song> songList = Songs.getSongs();

        // alternative 1: use Lambda expression
        List<String> songNameList1 = songList.stream()
                .map(song -> Integer.toString(song.getYear()))
                .collect(Collectors.toList());
        System.out.println(songNameList1);


        // alternative 2: implement the Function interface myself
        MyFunction myFunction = new MyFunction();

        List<String> songNameList2 = songList.stream()
                .map(song -> myFunction.apply(song))
                .collect(Collectors.toList());
        System.out.println(songNameList2);

        List<String> songNameList3 = songList.stream()
                .map(MyFunction::applyStatic)
                .collect(Collectors.toList());
        System.out.println(songNameList3);
    }
}

class MyFunction implements Function<Song, String> {
    @Override
    public String apply(Song song) {
        return Integer.toString(song.getYear());
    }

    // static method can be referenced in the placeholder for Lambda expression
    public static String applyStatic(Song song) {
        return Integer.toString(song.getYear());
    }
}

