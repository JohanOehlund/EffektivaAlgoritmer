import com.sun.media.sound.InvalidFormatException;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Grammar {

    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private HashMap<Character,Integer> convertedNonTerminalsHM;
    private HashMap<Integer,Integer> maxNumOfTermRules;
    private HashMap<Integer,Integer> maxNumOfNonTermRules;
    private int counter;

    private String rulesRegex = "[\\D][\\s][\\D]+";
    private int nrOfLetters=26;
    private int maxTerm;
    private int maxNonTerm;

    public Grammar() {

        convertedNonTerminalsHM=new HashMap<>();
        counter=0;
        initCounterHMs();
    }

    private void initCounterHMs(){
        maxNumOfTermRules=new HashMap<>();
        maxNumOfNonTermRules= new HashMap<>();
        for (int i = 0; i < nrOfLetters; i++) {
            maxNumOfNonTermRules.put(i,0);
            maxNumOfTermRules.put(i,0);
        }
    }

    public void readRules(File rulesFile) throws InvalidFormatException {

        readFromFile(rulesFile,0);
        initAndAddToDTS(rulesFile);
    }
    private void initAndAddToDTS(File rulesFile)throws InvalidFormatException {
        this.nonTerminalRulesTable=new Integer[counter][getMaxNrOfNonTermRules()+1][2];
        this.terminalRulesTable=new Character[counter][getMaxNrOfTermRules()+1];
        readFromFile(rulesFile,1);

    }

    private void readFromFile(File rulesFile,int timeParse) throws InvalidFormatException{
        String st;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(rulesFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (br != null) {
                while ((st = br.readLine()) != null) {
                    st = st.trim();
                    if (Pattern.matches(rulesRegex, st)) {
                        String[] split = st.split("[\\s]");
                        if (!Character.isUpperCase(split[0].charAt(0))) {
                            throw new InvalidFormatException(st + ": left hand Rule is not in uppercase");
                        }
                        if(timeParse==0){
                            convertToInteger(split[0].charAt(0),split[1]);
                        }else if (split[1].trim().length()==2) { //nonTerminalRule
                            addToNonTerminalRulesHM(split[0].charAt(0),split[1]);
                        }else{
                            addToTerminalRulesTable(split[0].charAt(0),split[1].charAt(0));
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

    private int getMaxNrOfTermRules(){

        for (Integer tempKey:maxNumOfTermRules.keySet()) {
            if(maxNumOfTermRules.get(tempKey)>maxTerm){
                maxTerm=maxNumOfTermRules.get(tempKey);
            }
        }
        return maxTerm;
    }

    private int getMaxNrOfNonTermRules(){

        for (Integer tempKey:maxNumOfNonTermRules.keySet()) {
            if(maxNumOfNonTermRules.get(tempKey)>maxNonTerm){
                maxNonTerm=maxNumOfNonTermRules.get(tempKey);
            }
        }
        return maxNonTerm;
    }


    private void addToTerminalRulesTable(Character leftHandSide,char rightHandSide){
        int index=convertedNonTerminalsHM.get(leftHandSide);

        for (int i = 0; i < nrOfLetters; i++) {
            if(terminalRulesTable[index][i]==null){
                terminalRulesTable[index][i]=rightHandSide;
                return;
            }
        }
    }

    private void addToTerminalRulesTableLinear(Integer index,char rightHandSide){
        //for (int i = 0; i < nrOfLetters; i++) {
        if(terminalRulesTable[index][0]==null){
            terminalRulesTable[index][0]=rightHandSide;
        }
        //}
    }

    private void addToNonTerminalRulesHM(Character leftHandSide,String rightHandSide){
        int index=convertedNonTerminalsHM.get(leftHandSide);

        for (int i = 0; i <nrOfLetters ; i++) {
            if(nonTerminalRulesTable[index][i][0]==null){
                char b = rightHandSide.charAt(0);
                char c = rightHandSide.charAt(1);
                Integer map_b=convertedNonTerminalsHM.get(b);
                Integer map_c=convertedNonTerminalsHM.get(c);

                if(Character.isLowerCase(b)){
                    addToTerminalRulesTableLinear(map_b,b);
                }else if(Character.isLowerCase(c)){
                    addToTerminalRulesTableLinear(map_c,c);
                }

                nonTerminalRulesTable[index][i][0]=map_b;
                nonTerminalRulesTable[index][i][1]=map_c;
                return;
            }
        }

    }

    private void convertToInteger(Character leftHandSide,String rightHandSide){
        if(!convertedNonTerminalsHM.containsKey(leftHandSide)){
            convertedNonTerminalsHM.put(leftHandSide,counter);
            counter++;
        }
        if(rightHandSide.trim().length()==2){
            if(!convertedNonTerminalsHM.containsKey(rightHandSide.charAt(0))){
                convertedNonTerminalsHM.put(rightHandSide.charAt(0),counter);
                counter++;
            }
            if(!convertedNonTerminalsHM.containsKey(rightHandSide.charAt(1))){
                convertedNonTerminalsHM.put(rightHandSide.charAt(1),counter);
                counter++;
            }
            addToMaxNonTermRule(convertedNonTerminalsHM.get(leftHandSide));
        }else{
            addToMaxTermRule(convertedNonTerminalsHM.get(leftHandSide));
        }

    }

    /*private void convertToInteger(Character leftHandSide,String rightHandSide){
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
            addToMaxNonTermRule(convertedNonTerminalsHM.get(leftHandSide));
        }else{
            addToMaxTermRule(convertedNonTerminalsHM.get(leftHandSide));
        }

    }*/

    private void addToMaxNonTermRule(int index){
        int tempCounter=maxNumOfNonTermRules.get(index);
        tempCounter=tempCounter+1;
        maxNumOfNonTermRules.put(index,tempCounter);
    }

    private void addToMaxTermRule(int index){
        int tempCounter=maxNumOfTermRules.get(index);
        tempCounter=tempCounter+1;
        maxNumOfTermRules.put(index,tempCounter);
    }

    public Integer[][][] getNonTerminalRulesTable(){
        return nonTerminalRulesTable;
    }

    public Character[][] getTerminalRulesTable(){
        return terminalRulesTable;
    }

    public HashMap<Character,Integer> getConvertedNonTerminalsHM(){
        return convertedNonTerminalsHM;
    }

    public int getNumOfNonTerms(){
        return counter;
    }

}