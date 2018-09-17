import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="baaba";
        String wordRules3="ab";
        int numberOfTests=5;
        long[] naiveRes=new long[numberOfTests];
        long[] topDownRes=new long[numberOfTests];
        long[] bottomUpRes=new long[numberOfTests];
        System.out.println("Path to Rules: "+args[0]);

        TimerClass timerClass=new TimerClass();
        Enumeration enumeration=new Enumeration(wordRules3,10);

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

        System.out.print("Test in progress");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.print(".");
            String nextString=enumeration.nextElement();

            timerClass.startTimer();
            naive.parse(nextString);
            timerClass.stopTimer();
            naiveRes[i]=timerClass.getTotalRunTime();

            timerClass.startTimer();
            topDown.parse(nextString);
            timerClass.stopTimer();
            topDownRes[i]=timerClass.getTotalRunTime();

            timerClass.startTimer();
            bottomUp.parse(nextString);
            timerClass.stopTimer();
            bottomUpRes[i]=timerClass.getTotalRunTime();
        }
        System.out.print("\n");



        //########################################################################


        System.out.println("##########Naive##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(naiveRes[i]+" ms");
        }
        System.out.println("#########################");

        System.out.println("##########TopDown##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(topDownRes[i]+" ms");
        }
        System.out.println("#########################");

        System.out.println("##########BottomUp##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(bottomUpRes[i]+" ms");
        }
        System.out.println("#########################");

    }
}
