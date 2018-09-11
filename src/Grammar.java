import com.sun.media.sound.InvalidFormatException;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Grammar {
    //private HashMap<Integer, String> nonTerminalRulesHM;
    private Integer[][] nonTerminalRulesTable;
    private String rulesRegex = "[\\D][\\s][\\D]+";

    public Grammar() {
        nonTerminalRulesTable=new Integer[26][26];
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
                            System.out.println("Char: "+split[0].charAt(0)+",CharVal: "+Character.getNumericValue(split[0].charAt(0)));
                        } else {
                            throw new InvalidFormatException(st + ": left hand Rule is not in uppercase");
                        }

                        if (Character.isUpperCase(split[1].charAt(0))) {
                            //nonTerminalRulesTable[Character.getNumericValue(split[0].charAt(0)][]
                        }else{

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

}
