import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.util.HashMap;


public class Main {


    public static void main(String [ ] args) {
        String wordRules="aabcc";
        String wordRules3="abc";

        String andersson="aaabbb";
        String wordRulesP="()";
        int numberOfTests=10;
        int nrOfSameTest=10;



        long[][] naiveRes=new long[numberOfTests][nrOfSameTest];
        long[][] topDownRes=new long[numberOfTests][nrOfSameTest];
        long[][] bottomUpRes=new long[numberOfTests][nrOfSameTest];
        int[] steps=new int[numberOfTests];
        int[] naiveSteps=new int[numberOfTests];


        boolean[] naiveBools=new boolean[numberOfTests];
        boolean[] topDownBools=new boolean[numberOfTests];
        boolean[] bottomUpBools=new boolean[numberOfTests];




        ResultMatrix resultMatrix=new ResultMatrix("result8");

        TimerClass timerClass=new TimerClass();
        //Enumeration enumeration_naive=new Enumeration(wordRules,2);
        Enumeration enumeration=new Enumeration(wordRules,1);

        Grammar grammar = new Grammar();

        try {
            System.out.println("Path to Rules: "+args[7]);
            grammar.readRules(new File(args[7]));


        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        HashMap<Character,Integer> tempHM=grammar.getConvertedNonTerminalsHM();

        for (Character tempKey:tempHM.keySet()) {
            System.out.println("Key: "+tempKey+" | Value: "+tempHM.get(tempKey));
        }

        Integer[][][] tempNonterms=grammar.getNonTerminalRulesTable();
        Character[][] tempTerms=grammar.getTerminalRulesTable();

        for (int i = 0; i < tempNonterms.length; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    if(tempNonterms[i][j][k]!=null)
                        System.out.println("tableNon["+i+"]["+j+"]["+k+"]: "+tempNonterms[i][j][k]);
                }
            }
        }
        System.out.println("\n");
        for (int i = 0; i < tempTerms.length; i++) {
            for (int j = 0; j < 2; j++) {
                if(tempTerms[i][j]!=null)
                    System.out.println("tableTerm["+i+"]["+j+"]: "+tempTerms[i][j]);
            }
        }


        CYK_naive naive=new CYK_naive(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable());

        naive.init(wordRules);
        boolean test=naive.parse();
        System.out.println("String: "+wordRules+" | result: "+test);

        /*CYK_topDown topDown=new CYK_topDown(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable(),
                grammar.getNumOfNonTerms());
        CYK_bottomUp bottomUp=new CYK_bottomUp(grammar.getNonTerminalRulesTable(),grammar.getTerminalRulesTable(),
                grammar.getNumOfNonTerms());
        //System.out.print("Test in progress");
        for (int i = 0; i < numberOfTests; i++) {
            //System.out.print(".");

            String nextString=enumeration.nextElement2('a');
            //String nextString_naive=enumeration_naive.nextElement2('a');
            //System.out.println("Naive: "+nextString_naive.length()+": "+nextString_naive);
            System.out.println("Other: "+nextString.length()+": "+nextString);

            naiveSteps[i]=nextString.length();
            steps[i]=nextString.length();

            for (int j = 0; j < nrOfSameTest; j++) {

                naive.init(nextString);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                naiveBools[i]=naive.parse();
                timerClass.stopTimer();
                naiveRes[i][j]=timerClass.getTotalRunTime();

                topDown.init(nextString);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                topDownBools[i]=topDown.parse();
                timerClass.stopTimer();
                topDownRes[i][j]=timerClass.getTotalRunTime();

                bottomUp.init(nextString);
                System.gc(); //Call to garbage collector...
                timerClass.startTimer();
                bottomUpBools[i]=bottomUp.parse();
                timerClass.stopTimer();
                bottomUpRes[i][j]=timerClass.getTotalRunTime();

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
    */
    }
}
