package module_1_array_and_string.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// A group of duplicate files consists of at least two files that have the same content.
public class LC_609_Find_Duplicate_File_in_System {

    // Practice on Solution-1: given a 2D array as input, group elements according to the second info of each element
    // use a boolean array to mark elements
    public static List<List<Integer>> findDuplicate_0(int[][] arr) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[arr.length]; // initialized as false

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<Integer> temp = new ArrayList<>();
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i][1] == arr[j][1]) {
                    temp.add(arr[j][0]);
                    visited[j] = true;
                }
            }

            temp.add(arr[i][0]);
            visited[i] = true;

            results.add(temp);
        }

        return results;
    }


    // solution: One-pass + a HashMap
    // time: O(N*K), N - size of paths array, K - average number of files within a path
    // space: O(N*K + N*K) >> O(N*K)
    //      - 1st N*K is caused by the map, which holds the total number of files
    //      - 2nd N*K is caused by the results ArrayList, which holds the total number of files
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] arr = path.split(" ");

            String directory = arr[0];
            for (int i = 1; i < arr.length; i++) {
                String[] parts = arr[i].split("\\(");

                String fileName = parts[0];
                String contentKey = parts[1].replace(")", "");
                String value = directory + "/" + fileName;

                if (!map.containsKey(contentKey)) {
                    List<String> list = new ArrayList<>();
                    list.add(value);
                    map.put(contentKey, list);
                } else {
                    map.get(contentKey).add(value);
                }
            }
        }

        List<List<String>> results = new ArrayList<>();
        for (String key : map.keySet()) {
            // A group of duplicate files consists of at least two files that have the same content.
            if (map.get(key).size() > 1) {
                results.add(map.get(key));
            }
        }

        return results;
    }






    public static void main(String[] args) {

        String s = "1.txt(abcd)";
        String[] name_cont = s.split("\\("); // "\\" is the escape character

        for (String e : name_cont)
            System.out.println(e);
        // 1.txt
        // abcd)

        /***********************************************/



        String s1 = "900 google.mail.com";
        String[] arr = s1.split(" "); // "\\" is the escape character
        String[] arr1 = s1.split("\\.");
        System.out.println("the length of arr1 is: " + arr1.length); // 900 google, mail, com

        for (String e : arr1)
            System.out.println(e);


        /***********************************************/

        String s3 = "abc)";
        String s3_ = s3.replace(")", "");
        System.out.println("The replaced string is: " + s3_);




        /***********************************************/

        int[][] arr2 = new int[4][2];
        arr2[0] = new int[]{301, 2};
        arr2[1] = new int[]{754, 1};
        arr2[2] = new int[]{231, 2};
        arr2[3] = new int[]{876, 8};

        List<List<Integer>> result = findDuplicate_0(arr2);
        System.out.println(result); // [[231, 301], [754], [876]]
    }
}
