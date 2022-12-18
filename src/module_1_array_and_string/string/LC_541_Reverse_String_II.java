package module_1_array_and_string.string;

public class LC_541_Reverse_String_II {

    // time: O(N + N/2) = O((N)
    // space: O(N), caused by char array
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray(); // time: O(N)

        int reverseCount = s.length() / (2 * k);
        int remainder = s.length() % (2 * k);

        // deal the repetitive sub arrays
        int i = 0;
        while (i < reverseCount) {
            int L = i * (2 * k);
            int R = L + (k - 1);
            while (L < R) {
                char temp = arr[L];
                arr[L] = arr[R];
                arr[R] = temp;

                L++;
                R--;
            }

            i++;
        }

        // deal with reminder
        int L = reverseCount * (2 * k);
        int R = remainder > k ? L + k - 1 : arr.length - 1;
        while (L < R) {
            char temp = arr[L];
            arr[L] = arr[R];
            arr[R] = temp;

            L++;
            R--;
        }

        // This uses the String constructor. It first creates and fills a char array of 8 characters. It then converts char ranges to Strings.
        // time: O(N)
        return new String(arr);
    }

    public static void main(String[] args) {

        // Example: from char array to String
        char[] values = new char[8];
        values[0] = 'W';
        values[1] = 'e';
        values[2] = 'l';
        values[3] = 'c';
        values[4] = 'o';
        values[5] = 'm';
        values[6] = 'e';
        values[7] = '!';

        // Create a string with the entire char array.
        String result = new String(values);
        System.out.println(result); // Welcome!

        // Use first 7 characters for a new String.
        String result2 = new String(values, 0, 7);
        System.out.println(result2); // Welcome

        // Create a string with an offset.
        String result3 = new String(values, 3, 4);
        System.out.println(result3); // come
    }

}
