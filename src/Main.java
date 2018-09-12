import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String [ ] args) {
        System.out.println("Path to Rules: "+args[0]);
        Grammar grammar = new Grammar();

        String word="baaba";
        try {
            grammar.readRules(new File(args[0]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
       // Parser parser=new Parser(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesHM());

        //boolean test=parser.parse(word);

        //System.out.println("Test: "+test);

        HashMap<Integer,ArrayList<Tuple>> testNonTerm=grammar.getNonTerminalRulesHM();

        for (int i =0;i<26;i++) {
            System.out.println("I:e NonTerm-rule: "+i);
            ArrayList<Tuple> tempArr=testNonTerm.get(i);
            if(tempArr!=null){
                for (Tuple tempTuple:tempArr) {
                    System.out.println("Rule: {"+tempTuple.getNonTerminalLetter1()+","+tempTuple.getNonTerminalLetter2()+"}");
                }
            }

        }

        HashMap<Integer,Character[]> testTerm=grammar.getTerminalRulesHM();

        for(Integer tempKey:testTerm.keySet()) {
            Character[] tempCharArr=testTerm.get(tempKey);
            if(tempCharArr!=null){
                System.out.println("Term-rule: "+tempKey);
                for (int i = 0; i < tempCharArr.length; i++) {
                    if(tempCharArr[i]!=null)
                        System.out.println("Rule: "+tempCharArr[i]);
                }
            }

        }

    }
}
