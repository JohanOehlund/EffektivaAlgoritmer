import com.sun.media.sound.InvalidFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Grammar {
    //private HashMap<Integer, String> nonTerminalRulesHM;
    private HashMap<Integer,ArrayList<Tuple>>nonTerminalRulesHM;
    private HashMap<Integer,Character> terminalRulesHM;
    private String rulesRegex = "[\\D][\\s][\\D]+";
    private int nrOfLetters=26;

    public Grammar() {
        nonTerminalRulesHM=new HashMap<>();
        terminalRulesHM=new HashMap<>();
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
                            addToNonTerminalRulesTable(split[0].charAt(0),split[1]);
                        }else{
                            terminalRulesHM.put(Character.getNumericValue(split[0].charAt(0)),split[1].charAt(0));
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


    private void addToNonTerminalRulesTable(Character leftHandSide,String rightHandSide){
        int convertedChar = (Character.getNumericValue(leftHandSide));
        //System.out.println("lefHandSide: "+convertedChar);
        /*ArrayList<Tuple> tempRules = nonTerminalRulesTable.get(convertedChar);
        tempRules.add(new Tuple(Character.getNumericValue(rightHandSide.charAt(0))-10,
                Character.getNumericValue(rightHandSide.charAt(1))-10));
        */
        if(nonTerminalRulesHM.get(convertedChar)==null){
            ArrayList<Tuple> temp = new ArrayList<>();
            temp.add(new Tuple(Character.getNumericValue(rightHandSide.charAt(0)),
                    Character.getNumericValue(rightHandSide.charAt(1))));
            nonTerminalRulesHM.put(convertedChar,temp);
        }else{
            ArrayList<Tuple>temp=nonTerminalRulesHM.get(convertedChar);
            temp.add(new Tuple(Character.getNumericValue(rightHandSide.charAt(0)),
                    Character.getNumericValue(rightHandSide.charAt(1))));
        }



    }

    public HashMap<Integer,ArrayList<Tuple>> getNonTerminalRulesHM() {
        return nonTerminalRulesHM;
    }

    public HashMap<Integer,Character> getTerminalRulesHM(){
        return terminalRulesHM;
    }
}
