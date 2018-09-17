import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="baaba";
        String wordRules3="ab";
        System.out.println("Path to Rules: "+args[0]);

        TimerClass timerClass=new TimerClass();
        Enumeration enumeration=new Enumeration(wordRules3,20);

        Grammar grammar = new Grammar();

        try {
            grammar.readRules(new File(args[0]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        CYK_Naive naive=new CYK_Naive(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable());
        CYK_TopDown topDown=new CYK_TopDown(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable(),
                grammar.getNumOfNonTerms());
        CYK_BottomUp bottomUp=new CYK_BottomUp(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable(),
                grammar.getNumOfNonTerms());

        for (int i = 0; i < 10; i++) {
            String nextString=enumeration.nextElement();
            //System.out.println("Word: "+nextString);
            timerClass.startTimer();
            //naive.parse(nextString);
            timerClass.stopTimer();
            System.out.println("Naive: "+timerClass.getTotalRunTime()+" ms");

            timerClass.startTimer();
            topDown.parse(nextString);
            timerClass.stopTimer();
            System.out.println("topDown: "+timerClass.getTotalRunTime()+" ms");

            timerClass.startTimer();
            bottomUp.parse(nextString);
            timerClass.stopTimer();
            System.out.println("bottomUp: "+timerClass.getTotalRunTime()+" ms");
        }



    }
}
