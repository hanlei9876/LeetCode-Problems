package java_practice.Java_Exceptions;

import java.io.FileReader;

public class try_catch_blocks {

    public static void main(String[] args) {

        try {
            // Code that throws FileNotFoundException
            FileReader fr = new FileReader("myfile.txt");
        }
        catch (Exception e1) {
            // handle e1
        }
        /*
        catch (IOException e2) { // !!! compiler will complain: Exception 'java.io.IOException' has already been caught
            // !!! code here is unreachable
            // handle e2
        }
        */
    }
}
