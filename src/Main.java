import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="baaba";
        String wordRules3="ab";
        String andersson="aaabbb";
        String wordRulesP="()";
        int numberOfTests=5;
        int nrOfSameTest=10;


        long[][] naiveRes=new long[numberOfTests][nrOfSameTest];
        long[][] topDownRes=new long[numberOfTests][nrOfSameTest];
        long[][] bottomUpRes=new long[numberOfTests][nrOfSameTest];
        int[] steps=new int[numberOfTests];
        int[] naiveSteps=new int[numberOfTests];


        boolean[] naiveBools=new boolean[numberOfTests];
        boolean[] topDownBools=new boolean[numberOfTests];
        boolean[] bottomUpBools=new boolean[numberOfTests];
        System.out.println("Path to Rules: "+args[6]);

        ResultMatrix resultMatrix=new ResultMatrix("result6");

        TimerClass timerClass=new TimerClass();
        Enumeration enumeration_naive=new Enumeration(wordRulesP,1);
        Enumeration enumeration=new Enumeration(wordRulesP,20);

        Grammar grammar = new Grammar();

        try {
            grammar.readRules(new File(args[6]));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        CYK_Naive naive=new CYK_Naive(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable());
        CYK_TopDown topDown=new CYK_TopDown(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable(),
                grammar.getNumOfNonTerms());
        CYK_BottomUp bottomUp=new CYK_BottomUp(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable(),
                grammar.getNumOfNonTerms());
        //System.out.print("Test in progress");
        String nextString_naive=wordRulesP;
        for (int i = 0; i < numberOfTests; i++) {
            //System.out.print(".");
            //String nextString=enumeration.nextElement();
            //String nextString_naive=enumeration_naive.nextElement2('(');
            for (int j = 0; j < 100; j++) {
                nextString_naive=nextString_naive+"()";
            }
            System.out.println("Naive: "+nextString_naive.length()+": "+nextString_naive);
            //System.out.println("Other: "+nextString.length()+": "+nextString);
            //steps[i]=nextString.length();
            naiveSteps[i]=nextString_naive.length();
            steps[i]=nextString_naive.length();
            for (int j = 0; j < nrOfSameTest; j++) {

                /*String nextString=enumeration.nextElement();
                String nextString2=enumeration2.nextElement2('a');
                System.out.println(nextString2+" ,String len: "+nextString2.length());
                */


                naive.init(nextString_naive);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                naiveBools[i]=naive.parse();
                timerClass.stopTimer();
                naiveRes[i][j]=timerClass.getTotalRunTime();

                topDown.init(nextString_naive);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                topDownBools[i]=topDown.parse();
                timerClass.stopTimer();
                topDownRes[i][j]=timerClass.getTotalRunTime();

                /*bottomUp.init(nextString_naive);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                bottomUpBools[i]=bottomUp.parse();
                timerClass.stopTimer();
                bottomUpRes[i][j]=timerClass.getTotalRunTime();*/
               // System.out.println("Time: "+timerClass.getTotalRunTime());

            }


        }
        System.out.print("\n");

        long[] naiveCalc= new long[numberOfTests];
        long[] topDownCalc= new long[numberOfTests];
        long[] bottomUpCalc= new long[numberOfTests];
        int removeRes=2;
        long tempRes;
        for (int i = 0; i < numberOfTests; i++) {
            tempRes=0;
            for (int j = removeRes; j < nrOfSameTest ; j++) {
                tempRes+=naiveRes[i][j];
            }
            naiveCalc[i]=tempRes/(nrOfSameTest-removeRes);
        }

        for (int i = 0; i < numberOfTests; i++) {
            tempRes=0;
            for (int j = 1; j < nrOfSameTest ; j++) {
                tempRes+=topDownRes[i][j];
            }
            topDownCalc[i]=tempRes/(nrOfSameTest-removeRes);

        }
        for (int i = 0; i < numberOfTests; i++) {
            tempRes=0;
            for (int j = 1; j < nrOfSameTest ; j++) {
                tempRes+=bottomUpRes[i][j];
            }
            bottomUpCalc[i]=tempRes/(nrOfSameTest-removeRes);
        }

        resultMatrix.addToMatrix(numberOfTests,steps,naiveSteps,naiveCalc,topDownCalc,bottomUpCalc);
        resultMatrix.closeWriter();

        //########################################################################

        System.out.println("##########Naive##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println("Result: "+naiveBools[i]+" Time: "+naiveCalc[i]*0.000001+" ms, "+"Length: "+naiveSteps[i]);

        }
        System.out.println("#########################");

        System.out.println("##########TopDown##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println("Result: "+topDownBools[i]+" Time: "+topDownCalc[i]*0.000001+" ms, "+"Length: "+steps[i]);
        }
        System.out.println("#########################");

        System.out.println("##########BottomUp##########");
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println("Result: "+bottomUpBools[i]+" Time: "+bottomUpCalc[i]*0.000001+" ms, "+"Length: "+steps[i]);
        }
        System.out.println("#########################");

    }
}
