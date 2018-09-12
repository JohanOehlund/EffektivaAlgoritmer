import java.util.HashMap;

public class Parser {
    private Integer[][][] nonTerminalRulesTable;
    private HashMap<Integer,Character> terminalRulesHM;

    public Parser(Integer[][][] nonTerminalRulesTable,HashMap<Integer,Character> terminalRulesHM){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesHM=terminalRulesHM;
    }

    public boolean parse(String word){
        return true;
    }
}
