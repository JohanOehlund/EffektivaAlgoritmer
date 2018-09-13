import com.sun.media.sound.InvalidFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Grammar {

    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private HashMap<Character,Integer> convertedNonTerminalsHM;
    private int counter;

    private String rulesRegex = "[\\D][\\s][\\D]+";
    private int nrOfLetters=26;

    public Grammar() {
        nonTerminalRulesTable=new Integer[26][26][2];
        terminalRulesTable=new Character[26][26];
        convertedNonTerminalsHM=new HashMap<>();
        counter=0;
    }

    public void readFile(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st;
        try {
            if (br != null) {
                while ((st = br.readLine()) != null)
                    System.out.println(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readRules(File rulesFile) throws InvalidFormatException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(rulesFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st;
        try {
            if (br != null) {
                while ((st = br.readLine()) != null) {
                    st = st.trim();
                    if (Pattern.matches(rulesRegex, st)) {
                        String[] split = st.split("[\\s]");
                        if (Character.isUpperCase(split[0].charAt(0))) {
                            //System.out.println(split[0] + ": is uppercase");
                           // System.out.println("Char: "+split[0].charAt(0)+",CharVal: "
                             //       +Character.getNumericValue(split[0].charAt(0)));
                        } else {
                            throw new InvalidFormatException(st + ": left hand Rule is not in uppercase");
                        }

                        if (Character.isUpperCase(split[1].charAt(0))) { //nonTerminalRule
                            addToNonTerminalRulesHM(split[0].charAt(0),split[1]);
                        }else{
                            addToTerminalRulesTable(split[0].charAt(0),split[1]);

                        }
                    } else {
                        throw new InvalidFormatException(st + ": has invalid format...");

                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToTerminalRulesTable(Character leftHandSide,String rightHandSide){
        convertToInteger(leftHandSide,rightHandSide);
        int index=convertedNonTerminalsHM.get(leftHandSide);

        for (int i = 0; i < nrOfLetters; i++) {
            if(terminalRulesTable[index][i]==null){
                terminalRulesTable[index][i]=rightHandSide.charAt(0);
                return;
            }
        }
    }

    private void addToNonTerminalRulesHM(Character leftHandSide,String rightHandSide){
        convertToInteger(leftHandSide,rightHandSide);
        int index=convertedNonTerminalsHM.get(leftHandSide);

        for (int i = 0; i <nrOfLetters ; i++) {
            if(nonTerminalRulesTable[index][i][0]==null){
                nonTerminalRulesTable[index][i][0]=convertedNonTerminalsHM.get(rightHandSide.charAt(0));
                nonTerminalRulesTable[index][i][1]=convertedNonTerminalsHM.get(rightHandSide.charAt(1));
                return;
            }
        }

    }

    private void convertToInteger(Character leftHandSide,String rightHandSide){
        if(!convertedNonTerminalsHM.containsKey(leftHandSide)){
            convertedNonTerminalsHM.put(leftHandSide,counter);
            counter++;
        }
        if(Character.isUpperCase(rightHandSide.charAt(0))){
            if(!convertedNonTerminalsHM.containsKey(rightHandSide.charAt(0))){
                convertedNonTerminalsHM.put(rightHandSide.charAt(0),counter);
                counter++;
            }
            if(!convertedNonTerminalsHM.containsKey(rightHandSide.charAt(1))){
                convertedNonTerminalsHM.put(rightHandSide.charAt(1),counter);
                counter++;
            }
        }

    }

    public Integer[][][] getNonTerminalRulesTable(){
        return nonTerminalRulesTable;
    }

    public Character[][] getTerminalRulesTable(){
        return terminalRulesTable;
    }
}
