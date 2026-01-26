In bitwise operation, the operation ~ is called Bitwise Complement Operator.
 - It works on all integer types (like int, long, short, byte)
 - It inverts every bit, where 0 becomes 1, 1 becomes 0

For example: the operation ~0 is as below:
 - Start with the bits: 0000...0000
 - Invert the bits: 1111...1111
In Java (amongst others), this is a representation of a negative number, using Two’s Complement system.

To know what negative number it is, simply apply Two’s System rule again on it, which is +1. Then, we know 1111...1111 is -1.

NOTE: in all cases, ~x = (-x) - 1

