package Java_Exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Exception_2_Sub extends Exception_1_Super {

    @Override
    // public void method_throws_exception() throws IOException {    // !!! compiler complains
    public void method_throws_exception() throws FileNotFoundException {
        // throw new IOException();
        throw new FileNotFoundException();
    }

    /*
    Java's rule for overriding with checked exceptions:

    When you override a method, the overriding method cannot throw broader checked exceptions than the method it overrides.
    That means:
    If superclass method declares FileNotFoundException, the overriding method in subclass can:
      - Throw no exception,
      - Or throw FileNotFoundException,
      - Or throw a subclass of FileNotFoundException.

     But cannot throw a broader checked exception, like IOException, because IOException is a superclass of FileNotFoundException.
    * */
}
