public class CYK_BottomUp extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;

    public CYK_BottomUp(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;

    }

    @Override
    boolean parse(String word) {
        return false;
    }
}
