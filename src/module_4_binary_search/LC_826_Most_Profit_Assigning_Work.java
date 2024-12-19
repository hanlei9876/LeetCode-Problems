package module_4_binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC_826_Most_Profit_Assigning_Work {

    // key points:
    // - Constraints on n <= 10000 indicates our solution's time complexity must be <= log linear (nlogn)
    // - since we aim to maximize profit, we need to iterate over each worker O(M), so each one has a job

    // greedy strategy:
    //   1) iterate over each worker
    //   2) for each worker, find the all the jobs he can do
    //   3) for all the jobs he can do, choose the profit-maximized job
    // Since each worker's profit is maximized, total profit is also maximized

    // the key to use greedy - for example:
    //    difficulty [2, 1, 5, 4]
    //       $$      [4, 2, 3, 7]
    //
    // (1) 1st way of greedy is to create an array (see solution 1 & 2 & 4)
    // - sort by difficulty (or by profit),
    // - then update each profit to max profit up to this difficulty level
    //    difficulty [1, 2, 4, 5]       >>       difficulty [1, 2, 4, 5]
    //       $$      [2, 4, 7, 3]       >>          $$      [2, 4, 7, 7]
    //                                             index     0  1  2  3
    //  e.g. at index 3, if work do job with difficulty level <= 5, the max profit he can get is 7
    //
    //
    // (2) 2nd way of greedy is to (see solution 3)
    //     - sort worker by skill level
    //     - sort job pairs by difficulty level
    //
    //    worker   job pair (difficulty, $$)
    //      1       (1, 2)
    //      2       (2, 4)
    //      3       (4, 7)
    //              (5, 3)
    // >> linearly iterate worker from 1 ~ 3
    // >> for each worker, linearly traverse each job pair while record max profit so far
    // >> if worker[i] >= difficulty, obtain the max profit for this worker[i]


    // solution 1: binary search + greedy (sort by job difficulty)
    // time: O(M) + O(MlogM) + O(M) + O(NlogM) >> O((M+N)logM)
    // space: O(M) + O(logM) >> O(M)
    // where M - size of difficult, N - size of worker
    public int maxProfitAssignment_1(int[] difficulty, int[] profit, int[] worker) {
        // create job pair array (difficulty, profit) + fill it
        List<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < difficulty.length; i++) {
            int[] job = new int[]{difficulty[i], profit[i]};
            jobs.add(job);
        }

        // sort jobs by difficult level in ascending order
        Collections.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        // iterates through jobs & updates the profit of each job to be the maximum profit achievable up to that difficulty level
        // under any difficulty[i], we consider the most profitable job
        for (int i = 0; i < jobs.size() - 1; i++) {
            if (jobs.get(i)[1] > jobs.get(i + 1)[1]) {
                jobs.get(i + 1)[1] = jobs.get(i)[1];
            }
        }

        // for each worker, binary search to find max difficulty he can do - obtain profit
        int totalProfit = 0;

        for (int i = 0; i < worker.length; i++) {
            int L = 0;
            int R = jobs.size() - 1;
            while (L < R) {
                int mid = L + (R - L + 1) / 2;

                if (jobs.get(mid)[0] <= worker[i]) {
                    L = mid;
                } else {
                    R = mid - 1;
                }
            }

            // post processing L==R
            if (jobs.get(L)[0] <= worker[i]) {
                totalProfit += jobs.get(L)[1];
            }
        }

        return totalProfit;
    }

    // solution 2: binary search + greedy (sort by profit) - skip
    //    difficulty [1, 2, 4, 5]       >>       difficulty [4, 2, 5, 1]   >>       difficulty [4, 2, 2, 1]
    //       $$      [2, 4, 7, 3]       >>          $$      [7, 4, 3, 2]   >>          $$      [7, 4, 3, 2]
    //                                             index     0  1  2  3
    //  e.g. at index 0, if worker want to gain  at most 7$, he should do at least level-4 work


    // solution 3: greedy + two pointers
    // - see 1st solution in 花花酱 LeetCode 826 - https://www.youtube.com/watch?v=hh1hF2hS3C4&t=194s
    // time: O(M) + O(MlogM) + O(NlogN) + O(M + N) >> O(MlogM) + O(NlogN)
    // space: O(M) + O(logM) + O(logN) >> O(M + logN) >> O(M)
    // where M - size of difficult, N - size of worker
    public int maxProfitAssignment_3(int[] difficulty, int[] profit, int[] worker) {
        // create job pair array (difficulty, profit) + fill it
        List<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < difficulty.length; i++) {
            jobs.add(new int[]{difficulty[i], profit[i]});
        }

        // sort job pair array by difficulty in ascending order
        Collections.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        // sort workers array in ascending order
        Arrays.sort(worker);

        // use two-pointers to iterate the two arrays: workers & job pairs
        // we must iterate all workers
        // time: O(M + N)
        int totalProfit = 0;
        int maxProfit = 0;

        int i = 0; // worker
        int j = 0; // job
        while (i < worker.length) {
            while (j < jobs.size() && worker[i] >= jobs.get(j)[0]) {
                    maxProfit = Math.max(maxProfit, jobs.get(j)[1]);
                    j++;
            }

            totalProfit += maxProfit;
            i++;
        }

        return totalProfit;
    }


    // solution 4: bucket (memoization) + DP
    // create an array, where index = difficulty, value = maxProfit of this difficulty - holding
    // -see 2nd solution in 花花酱 LeetCode 826 - https://www.youtube.com/watch?v=hh1hF2hS3C4&t=194s
    // time: O(N) + O(M) + O(maxWorkerAbility) + O(N) >> O(maxWorkerAbility + M + N)
    // space: O(maxWorkerAbility)
    // where M - size of difficult, N - size of worker
    public int maxProfitAssignment_4(int[] difficulty, int[] profit, int[] worker) {
        // create the bucket
        int maxAbility = 0;
        for (int capability : worker) {
            maxAbility = Math.max(maxAbility, capability);
        }

        int[] jobs = new int[maxAbility + 1]; // use index 1 ~ len - 1 to represent each possible difficulty

        // fill bucket with given profits
        for (int i = 0; i < difficulty.length; i++) {
            if (difficulty[i] <= maxAbility) {
                int index = difficulty[i];
                int value = profit[i];
                jobs[index] = Math.max(jobs[index], value);
            }
        }

        // fill bucket so each value at i is max profit until i
        for (int i = 0; i < jobs.length - 1; i++) {
            if (jobs[i] > jobs[i + 1]) {
                jobs[i + 1] = jobs[i];
            }
        }

        // calculate max profit
        int maxProfit = 0;
        for (int ability : worker) {
            maxProfit += jobs[ability];
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        LC_826_Most_Profit_Assigning_Work solution = new LC_826_Most_Profit_Assigning_Work();

        int[] difficulty = {1, 3, 4, 7, 3, 4, 8};
        int[] profit = {4, 6, 5, 7, 5, 3, 8};
        int[] workers = {3, 6, 5, 8};

        int res = solution.maxProfitAssignment_1(difficulty, profit, workers);

        System.out.println(res);
    }
}
