package module_1_array_and_string.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_811_Subdomain_Visit_Count {

    // solution: use HashMap
    // time: O(N), N - the length of the input array
    // space: O(N), caused by HashMap
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String cpdomain : cpdomains) {
            String[] arr = cpdomain.split(" ");

            int count = Integer.valueOf(arr[0]);

            String[] domainFrags = arr[1].split("\\."); // "\\" is the escape character
            // given the array: ["www", "google", "com"],
            // below is to produce: "com", "google.com", "www.google.com"
            String curr = "";
            for (int i = domainFrags.length - 1; i >= 0; i--) {
                if (curr.equals("")) {
                    curr = domainFrags[i] + curr;
                } else {
                    curr = domainFrags[i] + "." + curr;
                }

                if (!map.containsKey(curr)) {
                    map.put(curr, count);
                } else {
                    int c = map.get(curr);
                    c += count;
                    map.put(curr, c);
                }
            }
        }

        List<String> results = new ArrayList<>();
        for (String key : map.keySet()) {
            String count = Integer.toString(map.get(key));
            String result = count + " " + key;
            results.add(result);
        }

        return results;
    }





    public static void main(String[] args) {
        String s = "998";
        System.out.println(Integer.parseInt(s)); // 998
        System.out.println(Integer.valueOf(s)); // 998


        int a = 927;
        String sa = Integer.toString(a);
        System.out.println(sa);
    }

}
