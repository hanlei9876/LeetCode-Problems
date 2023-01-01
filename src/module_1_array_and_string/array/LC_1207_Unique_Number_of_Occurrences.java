package module_1_array_and_string.array;

import java.util.HashMap;
import java.util.Map;

public class LC_1207_Unique_Number_of_Occurrences {
    public boolean uniqueOccurrences(int[] arr) {

        return false;
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
