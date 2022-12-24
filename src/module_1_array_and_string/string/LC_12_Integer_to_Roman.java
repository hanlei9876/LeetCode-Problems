package module_1_array_and_string.string;

// constraint: the input integer <= 3999
public class LC_12_Integer_to_Roman {

    // solution-1: hardcode the table mapping from integers to roman symbols
    // drawback: not flexible to maintain when extending the constraint to 10000, or larger
    // time: O(1)
    // space: O(1)
    private static final String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; // 0 ~ 9
    private static final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; // 0 ~ 9
    private static final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; // 0 ~ 9
    private static final String[] thousands = {"", "M", "MM", "MMM"}; // 0 ~ 3

    public String intToRoman(int num) {
        int thousand = num / 1000;
        int hundred = (num % 1000) / 100;
        int ten = (num % 100) / 10;
        int one = num % 10;

        String result = thousands[thousand] + hundreds[hundred] + tens[ten] + ones[one];

        return result;
    }


    // solution-2: Greedy Algorithm
    // use double arrays to maintain mapping, order, index
    // time: O(1)
    // space: O(1)
    private static final int[] integers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman_2(int num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int i = 0;
            for (; i < integers.length; i++) {
                if (integers[i] <= num)
                    break;
            }

            sb.append(symbols[i]);
            num = num - integers[i];
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        // print each digit from high to low
        int n = 3291;
        int divider = 1000;
        while (divider != 0) {
            int digit  = n / divider;
            System.out.println(digit);

            n = n % divider;
            divider = divider / 10;
        }
        // above will print 3 2 9 1 in sequence
    }
}
