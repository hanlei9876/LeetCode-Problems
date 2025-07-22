package java_practice.Java_Coding_Questions_at_Macquarie_Group;

public class DecodeStringFromAscii {

    // given an encoded string of numbers, decode it following the steps below:
    //  1 - reverse this encoded string
    //  2 - map the string to the range

    // NOTE: the decoded string is composed of ONLY
    //   - upper_case letters A ~ Z << >> 65 ~ 90
    //   - lower_case letters a ~ z << >> 97 ~ 122
    //   - space " " << >> 32

    // key String-related operations:
    //   - convert String to char array: char[] chars = s.toCharArray()
    //   - convert char array to string: String s = new String(chars)

    public static void main(String[] args) {
        String s = "7010117928411101701997927";

        System.out.println(decodeString(s)); // decoded as "HackerRank"
    }

    private static String decodeString(String s) {
        // reverse string
        String reversedString = new StringBuilder(s).reverse().toString(); // key

        // iterate through the reverse string:
        //  - if char is 6, 7, 8, or 9,
        //    then:
        //        substring(i, i+2) >> parse to int (Integer.parseInt(s)) >> cast to char
        //        i = i + 2
        //  - if char 1
        //    then:
        //        substring(i, i+3) >> parse to int (Integer.parseInt(s)) >> cast to char
        //        i = i + 3
        //  - if char 3
        //    then:
        //        stringBuilder.append(" ")
        //        i = i + 1
        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (i < reversedString.length()) {
            char c = reversedString.charAt(i);

            if (c == '6' || c == '7' || c == '8' || c == '9') {
                char character = (char) Integer.parseInt(reversedString.substring(i, i + 2)); // key
                sb.append(character);
                i = i + 2;
            } else if (c == '1') {
                char character = (char) Integer.parseInt(reversedString.substring(i, i + 3));
                sb.append(character);
                i = i + 3;
            } else if (c == '3') {
                sb.append(" ");
                i = i + 1;
            }
        }

        return sb.toString();
    }
}
