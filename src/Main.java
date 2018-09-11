import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String [ ] args) {
        System.out.println("Path to Rules: "+args[0]);
        Grammar grammar = new Grammar();

        try {
            grammar.readRules(new File(args[0]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        HashMap<Integer,ArrayList<Tuple>> testNonTerm=grammar.getNonTerminalRulesHM();
        HashMap<Integer,Character> testTerm=grammar.getTerminalRulesHM();
        for (Integer tempKey:testNonTerm.keySet()) {
            for (Tuple tempTuple:testNonTerm.get(tempKey)) {
                System.out.println("NonTermRules: "+tempKey+" -> {"+tempTuple.getNonTerminalLetter1()+
                    ","+tempTuple.getNonTerminalLetter2()+"}");
            }
        }

        for (Integer tempKey:testTerm.keySet()) {
            Character tempChar=testTerm.get(tempKey);
            System.out.println("TermRules: "+tempKey+"-> "+tempChar);
        }
    }
}
