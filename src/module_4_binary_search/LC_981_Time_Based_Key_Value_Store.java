package module_4_binary_search;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LC_981_Time_Based_Key_Value_Store {
    // similar to LC-362

    // for set(key, value, timestamp):
    //    key      :   value     : timestamp
    // non-unique    non-unique     unique (strictly increasing)
    //  string        string         int (>=1)

    // overall strategy:
    // since in get(key, timestamp), key is non-unique, while timestamp is unique,
    //   >> we assign a bucket for each unique key
    //   >> we group pair <timestamp, value> by key (put all these pairs in same key's bucket)


    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */

    public static void main(String[] args) {
        Map<Integer, String>map = new HashMap<>();
        map.put(1, "abc");
        System.out.println(map);
        map.put(1, "xyz");
        System.out.println(map);
    }
}



// solution 1: HashMap<key, HashMap<timestamp, value>> + linear search (Time Limit Exceeded)
// overall complexity - consider taking M calls for function set & N calls for function get
// (1) set()
//     - time: O(ML)
//     - space: O(ML)
// (2) get()
//     - time: O(N*timestamp*L)
//     - space: O(N)
class TimeMap_1 {

    Map<String, Map<Integer, String>> timeMap;

    public TimeMap_1() {
        timeMap = new HashMap<>();
    }

    // single call's complexity - L is average length of key and value strings
    // time: O(L) - takes O(L) time to hash the string
    // space: O(L)
    public void set(String key, String value, int timestamp) {
        if (!timeMap.containsKey(key)) {
            Map<Integer, String> bucket = new HashMap<>();
            bucket.put(timestamp, value);

            timeMap.put(key, bucket);
        } else {
            Map<Integer, String> existingBucket = timeMap.get(key); // existingBucket is a reference to bucket object
            existingBucket.put(timestamp, value);

            // timeMap.get(key).put(timestamp, value);
        }
    }

    // single call's complexity - L is average length of key and value strings
    // time: O(timestamp*L)
    // space: O(1)
    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) {
            return "";
        }

        Map<Integer, String> bucket = timeMap.get(key);
        for (int i = timestamp; i >= 1; i--) {
            if (bucket.containsKey(i)) { // takes O(L) time to hash the string
                return bucket.get(i);
            }
        }

        return "";
    }
}

// solution 2: HashMap<key, TreeMap<timestamp, value>> (sorted map) + binary search
// overall complexity - consider taking M calls for function set & N calls for function get
// (1) set()
//     - time: O(LMlogM)
//     - space: O(LM)
// (2) get()
//     - time: O(N * (L + logM))
//     - space: O(1)
class TimeMap_2 {

    Map<String, TreeMap<Integer, String>> timeMap;

    public TimeMap_2() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!timeMap.containsKey(key)) {
            TreeMap<Integer, String> bucket = new TreeMap<>();
            bucket.put(timestamp, value);

            timeMap.put(key, bucket);
        } else {
            TreeMap<Integer, String> existingBucket = timeMap.get(key);
            existingBucket.put(timestamp, value);  // time: O(logN)
        }
    }

    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) {
            return "";
        }

        TreeMap<Integer, String> existingBucket = timeMap.get(key); // time: O(L)
        Integer floor = existingBucket.floorKey(timestamp); // time: O(logM)

        if (floor == null) {
            return "";
        }

        return existingBucket.get(floor);
    }
}

// solution 3: HashMap<key, ArrayList<Map.Entry<timestamp, value>>> + binary search on arraylist
// considering "All the timestamps of set() are strictly increasing", we can use arrayList.add()
// overall complexity - consider taking M calls for function set & N calls for function get
// (1) set()
//     - time: O(LM)
//     - space: O(LM)
// (2) get()
//     - time: O(N * (L + logM))
//     - space: O(1)
class TimeMap_3 {

    Map<String, List<Map.Entry<Integer, String>>> timeMap;

    public TimeMap_3() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!timeMap.containsKey(key)) {
            List<Map.Entry<Integer, String>> bucket = new ArrayList<>();
            bucket.add(new AbstractMap.SimpleEntry<>(timestamp, value));

            timeMap.put(key, bucket);
        } else {
            List<Map.Entry<Integer, String>> existingBucket = timeMap.get(key);
            existingBucket.add(new AbstractMap.SimpleEntry<>(timestamp, value));
        }
    }

    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) {
            return "";
        }

        // binary search the key's bucket
        List<Map.Entry<Integer, String>> bucketList = timeMap.get(key);

        int L = 0;
        int R = bucketList.size() - 1;
        while (L < R) {
            int mid = L + (R - L + 1) / 2;
            int time = bucketList.get(mid).getKey();

            if (time == timestamp) {
                return bucketList.get(mid).getValue();
            } else if (time < timestamp) {
                L = mid;
            } else {
                R = mid - 1;
            }
        }

        // L == R - post-processing
        if (bucketList.get(L).getKey() <= timestamp) {
            return bucketList.get(L).getValue();
        } else {
            return "";
        }
    }
}