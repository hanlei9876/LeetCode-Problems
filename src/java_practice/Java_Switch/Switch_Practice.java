package java_practice.Java_Switch;

public class Switch_Practice {

    public static void main(String[] args) {
       // scenario 1:
        int a = 0;

        switch(a) {
            case 3:  System.out.println("level 1");
            case 0:  System.out.println("level 2");
            case 1:  System.out.println("level 3");
            default: System.out.println("level 4");
        }
        // print results:
        //       level 2
        //       level 3
        //       level 4


        // scenario 2:
        int b = 0;

        switch(b) {
            case 3:  System.out.println("level 1");
            case 0:  System.out.println("level 2"); break;
            case 1:  System.out.println("level 3");
            default: System.out.println("level 4");
        }
        // print results:
        //       level 2
    }
}
