1. Basic bitwise operation
    XOR (Exclusive OR)
    AND
    OR

2. Truth tables of basic bitwise operations
(1) Truth table of XOR (同0，异1): A ^ B
Input A | Input B | Output
------- | ------- | ------
0       | 0       | 0
0       | 1       | 1
1       | 0       | 1
1       | 1       | 0


(2) Truth table of AND (都是1，才能是1): A & B
Input A | Input B | Output
------- | ------- | ------
0       | 0       | 0
0       | 1       | 0
1       | 0       | 0
1       | 1       | 1


(3) Truth table of OR （有一个是1，就是1）: A | B 
Input A | Input B | Output
------- | ------- | ------
0       | 0       | 0
0       | 1       | 1
1       | 0       | 1
1       | 1       | 1


example of XOR operation
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


6. What is a set bit?
A set bit is a bit that is 1 in some position of a binary number.

For example, in binary, 5 is written as 101.
* We say the 0th bit is set.
* We say the 1st bit is not set.
* We say the 2nd bit is set.



7. How to create a **mask** of the right-most set bit for a binary number?
For example, let's say number = 6 (0110), the mask should be 0010

Formula: diffBitMask = number & (-number)


8. Understand Negative Number: -num
How does a computer turn 6 into -6? It uses a system called **Two's Complement**, which has two mini-steps:
  1) Flip all the bits (0 becomes 1, 1 becomes 0). 
  2) Add 1 to the result.

For example:
   A. Start with 6: 0000 0110
   B. Flip all bits (Invert): 1111 1001
   C. Add 1: 1111 1001 + 1 = 1111 1010
So, inside the computer memory, -6 is actually stored as 1111 1010

9. In Java, the equality operator (==) has a higher precedence than the bitwise AND operator (&). 
So, we should add parentheses around the bitwise operation:
   if (nums[i] & setBitMask == 0) // wrong
   if ((nums[i] & setBitMask) == 0) // correct

5 ~ 8 are applied to the AlgoExpert problem: Missing Numbers