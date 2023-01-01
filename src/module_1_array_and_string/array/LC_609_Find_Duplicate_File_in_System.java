package module_1_array_and_string.array;

import java.util.List;

public class LC_609_Find_Duplicate_File_in_System {

    public List<List<String>> findDuplicate(String[] paths) {


        return null;
    }

    public static void main(String[] args) {

        String s = "1.txt(abcd)";
        String[] name_cont = s.split("\\("); // "\\" is the escape character

        for (String e : name_cont)
            System.out.println(e);
        // 1.txt
        // abcd)



        String s1 = "900 google.mail.com";
        String[] arr = s1.split(" "); // "\\" is the escape character
        String[] arr1 = s1.split("\\.");
        System.out.println("the length of arr1 is: " + arr1.length);

        for (String e : arr1)
            System.out.println(e);
    }
}
