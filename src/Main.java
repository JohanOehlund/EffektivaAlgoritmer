import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String [ ] args) {
        int nrOfLetters=26;
        System.out.println("Path to Rules: "+args[0]);
        Grammar grammar = new Grammar();

        String word="baaba";
        try {
            grammar.readRules(new File(args[2]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
       // Parser parser=new Parser(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesHM());

        //boolean test=parser.parse(word);

        //System.out.println("Test: "+test);

        Integer[][][] testNonTerm=grammar.getNonTerminalRulesTable();

        for (int i =0;i<nrOfLetters;i++) {

            for (int j = 0; j < nrOfLetters ; j++) {
                if(testNonTerm[i][j][0]!=null){
                    System.out.println(i+" -> {"+testNonTerm[i][j][0]+","+
                            testNonTerm[i][j][1]+"}");
                }


            }
        }

        Character[][] testTerm=grammar.getTerminalRulesTable();
        System.out.println("Terminal rules");
        for (int i = 0; i < nrOfLetters ; i++) {
            for (int j = 0; j < nrOfLetters ; j++) {
                if(testTerm[i][j]!=null){
                    System.out.println(i+" -> "+testTerm[i][j]);
                }
            }
        }

    }
}
