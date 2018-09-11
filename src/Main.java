import com.sun.media.sound.InvalidFormatException;

import java.io.File;

public class Main {

    public static void main(String [ ] args) {
        System.out.println("Path to Rules: "+args[0]);
        Grammar grammar = new Grammar();

        try {
            grammar.readRules(new File(args[0]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
