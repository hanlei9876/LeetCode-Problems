package module_1_array_and_string.array;

public class LC_1295_Find_Numbers_with_Even_Number_of_Digits {

    // time: O(M*N), where M is the length of array, N is the number of digits for each element, not fixed. consider the worst case
    // space: O(1)
    public int findNumbers(int[] nums) {
        int number = 0;

        for (int i = 0; i < nums.length; i++) {
            // we are not allowed to modify the input array
            int num = nums[i];

            // Key point-1: How to compute the number of digits for a number?
            int digits = 1;
            while (num >= 10) {
                digits++;
                num = num / 10;
            }

            // Key point-2: evaluate if number of digit is even
            if (digits % 2 == 0)
                number++;
        }

        return number;
    }

    public static void main(String[] args) {
        int a = 3;

        int digits = 1;
        while (a >= 10) {
            digits++;
            a = a / 10;
        }
        System.out.println("the number of digits is: " + digits);

        // Key point-3: division & remainder for a number less than 10
        int b = 7;
        System.out.println("b/10 is: " + b/10); // 0
        System.out.println("b%10 is: " + b%10); // 7

        int c = 738;
        System.out.println("c/10 is: " + c/10); // 73
        System.out.println("c%10 is: " + c%10); // 8
    }
}
