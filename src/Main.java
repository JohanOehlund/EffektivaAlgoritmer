import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="baaba";
        String wordRules3="ab";
        int numberOfTests=9;
        long[][] naiveRes=new long[numberOfTests][5];
        long[][] topDownRes=new long[numberOfTests][5];
        long[][] bottomUpRes=new long[numberOfTests][5];
        int[] steps=new int[numberOfTests];
        int[] naiveSteps=new int[numberOfTests];


        boolean[] naiveBools=new boolean[numberOfTests];
        boolean[] topDownBools=new boolean[numberOfTests];
        boolean[] bottomUpBools=new boolean[numberOfTests];
        System.out.println("Path to Rules: "+args[0]);

        ResultMatrix resultMatrix=new ResultMatrix("result5");

        TimerClass timerClass=new TimerClass();
        Enumeration enumeration=new Enumeration(wordRules,20);
        Enumeration enumeration2=new Enumeration(wordRules,3);

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
        //System.out.print("Test in progress");
        for (int i = 0; i < numberOfTests; i++) {
            for (int j = 0; j < 5; j++) {
                //System.out.print(".");
                /*String nextString=enumeration.nextElement();
                String nextString2=enumeration2.nextElement2('a');
                System.out.println(nextString2+" ,String len: "+nextString2.length());

                naiveSteps[i]=enumeration2.getStringLengthNaive();
                steps[i]=enumeration2.getStringLengthNaive();

                naive.init(nextString2);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                naiveBools[i]=naive.parse();
                timerClass.stopTimer();
                naiveRes[i]=timerClass.getTotalRunTime();

                topDown.init(nextString2);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                topDownBools[i]=topDown.parse();
                timerClass.stopTimer();
                topDownRes[i]=timerClass.getTotalRunTime();

                bottomUp.init(nextString2);
                bottomUp.init(nextString2);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                bottomUpBools[i]=bottomUp.parse();
                timerClass.stopTimer();
                bottomUpRes[i]=timerClass.getTotalRunTime();
                   */
            }

        }
        System.out.print("\n");

        //resultMatrix.addToMatrix(numberOfTests,steps,naiveSteps,naiveRes,topDownRes,bottomUpRes);
        //resultMatrix.closeWriter();

        //########################################################################


        System.out.println("##########Naive##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println("Result: "+naiveBools[i]+" Time: "+naiveRes[i]*0.000001+" ms");
        }
        System.out.println("#########################");

        System.out.println("##########TopDown##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println("Result: "+topDownBools[i]+" Time: "+topDownRes[i]*0.000001+" ms");
        }
        System.out.println("#########################");

        System.out.println("##########BottomUp##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println("Result: "+bottomUpBools[i]+" Time: "+bottomUpRes[i]*0.000001+" ms");
        }
        System.out.println("#########################");

    }
}
