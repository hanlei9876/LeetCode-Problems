package module_4_binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_1268_Search_Suggestions_System {
    // string manipulation - lexicographic order:
    //  - Arrays.sort(String[])
    //  - s1.compareTo(s2)
    //
    // int result = string1.compareTo(string2);
    // Return Values
    //   - 0: if string1 is equal to string2
    //   - positive value: if string1 is lexicographically greater than string2
    //   - negative value: if string1 is lexicographically less than string2
    //
    // time: O(N) - N is the length of string
    // space: O(1)
    public static void main(String[] args) {
        // sort String array
        String[] s = {"bc", "bg", "a", "bcd", "bdef", "bcxf"};
        Arrays.sort(s);
        for (String a : s) {
            System.out.println(a);
        }
        // ["a", "bc", "bcd", "bcxf", "bdef", "bg"]


        // String.compareTo(String)
        // - lexicographically compare two strings. It compares the strings character by character, based on their Unicode values.
        String s1 = "b";
        String s2 = "bd";
        System.out.println(s1.compareTo(s2));


        // string concatenation
        String s3 = "";
        String s4 = "ab";
        String s5 = s3 + s4;
        System.out.println(s5);

        String s6 = s4 + 'c';
        System.out.println(s6);


        LC_1268_Search_Suggestions_System solution = new LC_1268_Search_Suggestions_System();
        List<List<String>> result = solution.suggestedProducts(new String[]{"havana"}, "tatiana");
    }


    // solution 1: binary search
    // 1) How to determine if a given string s contains a prefix?
    //    - use prefix.compareTo(s) to find left-most boundary (using binary search)
    //    - check if products[i].substring(0, prefix.length()).equals(prefix)
    // 2) Use a separate string "prefix" to contain search target
    //
    // edge case:
    //   Input: products = ["havana"], searchWord = "tatiana"
    //   Expected: [[],[],[],[],[],[],[]]
    //
    // time: O(NlogN) + O(M) + O(MlogN) >> O(NlogN)+ O(MlogN) - we treat string comparison as O(1)
    // space: O(logN) + O(M) - result doesn't count
    //   where M - searchWord, N - products.size()
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();

        Arrays.sort(products); // sort string array in lexicographical order

        String prefix = "";
        int leftSearchBoundary = 0;
        char[] chars = searchWord.toCharArray(); // time: O(M), space: O(M)
        for (char c : chars) {
            prefix = prefix + c;
            int currLeftBound = binarySearch(products, prefix, leftSearchBoundary);

            // cater to edge case
            if (currLeftBound  == -1) {
                result.add(new ArrayList<>());
                continue;
            }

            leftSearchBoundary = currLeftBound;

            // there are potential matched products
            List<String> currentResult = new ArrayList<>();
            for (int i = leftSearchBoundary; i < Math.min(leftSearchBoundary + 3, products.length); i++) {
                if (products[i].length() < prefix.length() || !products[i].substring(0, prefix.length()).equals(prefix)) {
                    break;
                }

                currentResult.add(products[i]);
            }

            result.add(currentResult);
        }

        return result;
    }

    // in product array, fint left-most product that >= target
    // result might not exist >> return -1
    private int binarySearch(String[] products, String target, int left) {
        int L = left;
        int R = products.length - 1;

        while (L < R) {
            int mid = L + (R - L) / 2;

            if (products[mid].compareTo(target) >= 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        // L == R - post-processing
        if (products[L].compareTo(target) < 0) {
            return -1;
        }

        return L;
    }
}
