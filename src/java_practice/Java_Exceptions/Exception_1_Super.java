package java_practice.Java_Exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Exception_1_Super {

    public void method_throws_exception() throws FileNotFoundException {
        FileReader fr = new FileReader("myfile.txt");
    }
}
