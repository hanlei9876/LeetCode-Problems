package module_x_hash_table;

import java.util.HashMap;
import java.util.Map;

public class HashMap_practice {

    public static void main(String[] args) {
        // In a hashmap, key is unique, while value could be duplicate
        Map<String, String> capitalCities = new HashMap<String, String>();
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        capitalCities.put("USA", "xxxxxxxx");
        capitalCities.put("Agggggggg", "Oslo");

        System.out.println(capitalCities); // {USA=xxxxxxxx, Norway=Oslo, England=London, Agggggggg=Oslo, Germany=Berlin}
        System.out.println();

        for (String i : capitalCities.values()) {
            System.out.println(i);
        }
        /*
            xxxxxxxx
            Oslo
            London
            Oslo
            Berlin
        */
    }

}
