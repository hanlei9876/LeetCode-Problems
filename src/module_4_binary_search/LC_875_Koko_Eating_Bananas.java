package module_4_binary_search;

// constraints:
//   1 <= piles.length <= 10^4
//   piles.length <= h <= 10^9
//   1 <= piles[i] <= 10^9


public class LC_875_Koko_Eating_Bananas {
    // point 1: piles.length <= h
    // point 2: The eating order does NOT affect the overall time, as Koko will stop eating for the rest of the hour, even if there are no more bananas left in the current pile
    // point 3: the time she spends eating a particular pile is given as currTime=⌈NumberOfBananas/speed⌉,
    //             regardless of the order of this pile in her eating plan (⌈x⌉ denotes ceil(x)).
    // point 4: the range of eating speed k - [1, max(piles[i])]


    // solution 1: brute force (time limit exceeded)
    // linear search speed range from 1 >> for each speed, iterate array piles to calculate total time spent
    // no need to traverse array piles first to find max(piles[i]) as speed range's upper bound
    // time: O(MN) - M is max(piles[i])
    // space: O(1)
    public int minEatingSpeed_1(int[] piles, int h) {
        int k = 1;

        while (true) {
            long totalTime = 0; // if int totalTime, it could be overflow

            for (int i = 0; i < piles.length; i++) {
                totalTime =  totalTime + (int) Math.ceil( (float) piles[i] / k );

                // optimize time
                if (totalTime > h) {
                    break;
                }
            }

            if (totalTime <= h) {
                return k;
            } else {
                k++;
            }
        }
    }

    // solution 2: binary search
    // find max(piles[i]) as speed range's upper bound >> binary search range [1, max(piles[i])] >> for each speed, iterate array piles to calculate total time spent
    // time: O(NlogM) - M is max(piles[i])
    // space: O(1)
    public int minEatingSpeed_2(int[] piles, int h) {
        // determine upper bound of search range
        int max = Integer.MIN_VALUE; // int max = piles[0]
        for (int pile : piles) {
            if (pile > max) {
                max = pile;
            }
        }

        // binary search
        int L = 1;
        int R = max;

        while (L < R) {
            int mid = L + (R - L) / 2; // mid is the speed to check

            long totalTime = 0; // totalTime might overflow when int
            for (int i = 0; i < piles.length; i++) {
                totalTime = totalTime + (long) Math.ceil((double) piles[i] / mid);

                // optimize time
                if (totalTime > h) {
                    break;
                }
            }

            if (totalTime > h) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L; // L==R
    }

    // how to use Math.ceil() - the argument must be double. Cannot be int
    public static void main(String[] args) {

        System.out.println( Math.ceil(4/4) ); // 1.0
        System.out.println( Math.ceil(4.0/4) ); // 1.0

        System.out.println (Math.ceil(5/4) ); // 1.0
        System.out.println (Math.ceil(5.0/4) ); // 2.0

        System.out.println( Math.ceil(3/4) ); // 0.0
        System.out.println( Math.ceil(3.0/4) ); // 1.0
    }
}
