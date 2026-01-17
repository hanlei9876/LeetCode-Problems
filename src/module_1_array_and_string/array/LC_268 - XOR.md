1. XOR (Exclusive OR) is a bitwise operation

2. Truth table of XOR (same-0, differnt-1):
Input A | Input B | Output
------- | ------- | ------
0       | 0       | 0
0       | 1       | 1
1       | 0       | 1
1       | 1       | 0

3. example of XOR operation
integer | binary
------- | -------
0       | 0000
1       | 0001
2       | 0010
3       | 0011
4       | 0100

Then, 2 ^ 3 = 1

3. The Fundamentals of XOR
XOR (exclusive OR) is a bitwise operator represented by the caret symbol (^) in most programming languages. It operates on binary numbers (bits).

4.The Golden Rules of XOR
(1) XORing a number with itself equals 0.
    5 ^ 5 = 0
    a ^ a = 0
Why? If bits are the same, the result is 0.

(2) XORing a number with 0 equals the number itself.
    5 ^ 0 = 5
    a ^ 0 = a
Why? 0 is the "identity" for XOR. It preserves the other value.

(3) Order doesn't matter (Commutative/Associative).
a ^ b ^ a is the same as a ^ a ^ b.

4. The "Cancellation" Trick
Because a ^ a = 0, if you XOR a list of numbers where every number appears twice except for one, 
the "pairs" will cancel each other out, leaving only the unique number.

Example:3 ^ 0 ^ 1 ^ 3 ^ 1
Rearrange it:(3 ^ 3)^ (1 ^ 1) ^ 0
Simplify: 0 ^ 0 ^ 0 = 0


5. This cancellation trick is useful for removing duplicates from an array.
Example: LC-286 - finding one missing number