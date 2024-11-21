package module_4_binary_search;

import java.util.Random;

public class LC_528_Random_Pick_with_Weight {

    public static void main(String[] args) {
        Random random= new Random();

        for (int i = 0; i < 20; i++) {
            int ranNum = random.nextInt(); // randomly pick an integer from INT range, with equal probability
            System.out.println(ranNum);
        }

        System.out.println("next run");

        for (int i = 0; i < 20; i++) {
            int ranNum = random.nextInt(15); // randomly pick an integer from range [0, 14), with equal probability
            System.out.println(ranNum);
        }




        int[] nums = new int[]{1, 4, 2, 7};
        int sum = 0;
        int[] cumSumArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cumSumArray[i] = sum;
        }
        for (int num : cumSumArray) {
            System.out.print(num + ", ");
        }
    }
}

// overall strategy: 用纍積的距離表示權重 -  see my note
//   step 1: create cumulative sum array, according to input array
//   step 2: obtain a random number in range [1, sum]
//   step 3: search cumulative sum array, to find which sub-scope the random number falls in >> return the corresponding index


// solution 1: linear search
// time: O(N)
// space: O(N) - cumSumArray's size
class Solution_1 {

    Random random;
    int[] nums;

    public Solution_1(int[] w) {
        random = new Random();
        nums = w;
    }

    public int pickIndex() {
        // step 1 - cumulative sum array
        int sum = 0;
        int[] cumSumArray = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cumSumArray[i] = sum;
        }

        // step 2: obtain random number in range [1, sum]
        int randomNum = random.nextInt(sum); // alternatively: int randomNum = sum * Math.random();
        // In Java, Math.random() is a method in the java.lang.Math class that generates a pseudo-random double between 0.0 (inclusive) and 1.0 (exclusive)
        randomNum++; // adjust random range from [0, sum - 1] to [1, sum]

        // step 3: search from the sub-range where the random num locates in the cumulative sum array
        for (int i = 0; i < cumSumArray.length; i++) {
            if (randomNum <= cumSumArray[i]) {
                return i;
            }
        }

        return -1; // we will never reach here
    }
}


// solution 2: binary search
// search goal: find the value from cumSumArray, which is on target's right & closest to target
// time: O(logN)
// space: O(N) - cumSumArray's size
class Solution_2 {

    Random random;
    int[] nums;

    public Solution_2(int[] w) {
        random = new Random();
        nums = w;
    }

    public int pickIndex() {
        // step 1 - cumulative sum array
        int sum = 0;
        int[] cumSumArray = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cumSumArray[i] = sum;
        }

        // step 2: obtain random number in range [1, sum]
        int randomNum = random.nextInt(sum); // alternatively: int randomNum = sum * Math.random();
        randomNum++; // adjust random range from [0, sum - 1] to [1, sum]

        // step 3: binary search from the sub-range where the random num locates in the cumulative sum array
        int L = 0;
        int R = cumSumArray.length - 1;

        while (L < R) {
            int mid = (L + R) / 2;

            if (cumSumArray[mid] == randomNum) {
                // return mid;   // - if no duplicates in cumSumArray
                R = mid;         // - if there are duplicates in cumSumArray
            } else if (cumSumArray[mid] > randomNum) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return L; // L == R
    }
}