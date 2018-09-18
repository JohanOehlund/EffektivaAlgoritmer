import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="baaba";
        String wordRules3="ab";
        int numberOfTests=16;
        long[] naiveRes=new long[numberOfTests];
        long[] topDownRes=new long[numberOfTests];
        long[] bottomUpRes=new long[numberOfTests];
        int[] numberOfChars=new int[numberOfTests];
        System.out.println("Path to Rules: "+args[0]);

        ResultMatrix resultMatrix=new ResultMatrix("result2");

        TimerClass timerClass=new TimerClass();
        Enumeration enumeration=new Enumeration(wordRules,20);
        Enumeration enumeration2=new Enumeration(wordRules,1);

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
            String nextString2=enumeration2.nextElement2('a');


            numberOfChars[i]=enumeration.getNumberOfChars();

            System.gc(); //Call to garbage collector...
            timerClass.startTimer();
            naive.parse(nextString2);
            timerClass.stopTimer();
            naiveRes[i]=timerClass.getTotalRunTime();

            System.gc(); //Call to garbage collector...
            timerClass.startTimer();
            topDown.parse(nextString);
            timerClass.stopTimer();
            topDownRes[i]=timerClass.getTotalRunTime();

            System.gc(); //Call to garbage collector...
            timerClass.startTimer();
            bottomUp.parse(nextString);
            timerClass.stopTimer();
            bottomUpRes[i]=timerClass.getTotalRunTime();

        }
        System.out.print("\n");

        resultMatrix.addToMatrix(numberOfTests,numberOfChars,naiveRes,topDownRes,bottomUpRes);
        resultMatrix.closeWriter();

        //########################################################################


        System.out.println("##########Naive##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(naiveRes[i]*0.000001+" ms");
        }
        System.out.println("#########################");

        System.out.println("##########TopDown##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(topDownRes[i]*0.000001+" ms");
        }
        System.out.println("#########################");

        System.out.println("##########BottomUp##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(bottomUpRes[i]*0.000001+" ms");
        }
        System.out.println("#########################");

    }
}
