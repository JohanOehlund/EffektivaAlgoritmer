import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="baabaa";
        String wordRules3="aaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
        int nrOfLetters=26;
        System.out.println("Path to Rules: "+args[0]);
        Grammar grammar = new Grammar();


        try {
            grammar.readRules(new File(args[0]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        /*CYK_BottomUp bottomUp=new CYK_BottomUp(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable());
        boolean test_bottomUp=bottomUp.parse(wordRules);
        boolean[][][] test_table_bottomUp=bottomUp.getTable();

        System.out.println("BottomUp: "+wordRules+" is a member -> "+test_bottomUp);*/

        /*for (int i = 0; i <wordRules.length() ; i++) {
            for (int j = 0; j <wordRules.length()-i; j++) {
                for (int k = 0; k < wordRules.length(); k++) {
                    System.out.println("table["+i+"]["+j+"]["+k+"]: "+test_table_bottomUp[i][j][k]);
                }

            }
        }*/

        CYK_TopDown topDown=new CYK_TopDown(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable());
        boolean test_topDown=topDown.parse(wordRules);
        Boolean[][][] test_table_topDown=topDown.getTable();
        System.out.println("TopDown: "+wordRules+" is a member -> "+test_topDown);

        /*for (int i = 0; i <wordRules.length() ; i++) {
            for (int j = 0; j <wordRules.length()-i; j++) {
                for (int k = 0; k < wordRules.length(); k++) {
                    System.out.println("table["+i+"]["+j+"]["+k+"]: "+test_table_topDown[i][j][k]);
                }

            }
        }*/



        /*CYK_Naive naive=new CYK_Naive(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable());
        boolean test_naive=naive.parse(wordRules3);
        System.out.println("The word: "+wordRules3+" is a member -> "+test_naive);
        */



        /*Integer[][][] testNonTerm=grammar.getNonTerminalRulesTable();

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
        }*/

    }
}
