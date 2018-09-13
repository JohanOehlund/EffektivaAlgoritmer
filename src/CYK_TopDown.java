public class CYK_TopDown extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private String word;
    private Boolean[][] table;

    public CYK_TopDown(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
    }

    @Override
    boolean parse(String word) {
        init_table();
        return parse_TopDown(0,0,word.length());
    }

    private void init_table(){
        for (int i = 0; i <word.length() ; i++) {
            for (int j = 0; j <word.length() ; j++) {
                table[i][j]=null;
            }
        }
    }

    private boolean parse_TopDown(int initNonTermRule,int i,int j){
        return true;
    }
}
