package module_1_array_and_string.array;

import java.util.*;


// constraints:
// 1 <= arr.length <= 1000
// -1000 <= arr[i] <= 1000
public class LC_1207_Unique_Number_of_Occurrences {

    // Solution-1: frequency array + sorting the array to find duplicates
    // time: O(N + klogk + 2k) >> O(N + klogk)
    // space: O(2k + 1) >> O(k)
    public boolean uniqueOccurrences(int[] arr) {
        int k = 1000;
        int[] freqs = new int[2 * k + 1];

        for (int num : arr) {
            freqs[num + k]++;
        }


        Arrays.sort(freqs);

        for (int i = 0; i < 2 * k; i++) {
            if (freqs[i] != 0 && freqs[i] == freqs[i + 1]) {
                return false;
            }
        }

        return true;
    }


    // Solution-2: hash map + hash set
    // time: O(N)
    // space: O(N + N) >> O(N), caused by HashMap + HashSet
    public boolean uniqueOccurrences_2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int freq = map.get(num);
                freq++;
                map.put(num, freq);
            }
        }

        // Time: O(1)
        Set<Integer> set = new HashSet<>(map.values());

        if (set.size() != map.size()) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        // In a hashmap, key is unique, while value could be duplicate
        Map<String, String> capitalCities = new HashMap<String, String>();
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        capitalCities.put("USA", "xxxxxxxx");
        capitalCities.put("Agggggggg", "Oslo");

        System.out.println(capitalCities);
        System.out.println();

        for (String i : capitalCities.values()) {
            System.out.println(i);
        }
    }

}
