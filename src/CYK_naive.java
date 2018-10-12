public class CYK_naive extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLen;
    private int operations;

    public CYK_naive(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable) {
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
    }

    @Override
    public void init(String word){
        operations=0;
        wordLen=word.length();
        this.word=word.toCharArray();
    }

    @Override
    public boolean parse() {
        return parse_naive(0,0,wordLen);
    }

    public boolean parse_naive(int nonTerminal,int i,int j){
        operations++;
        if(i==j-1){
            for (int k = 0;true; k++) {
                operations++;
                if(terminalRulesTable[nonTerminal][k]==null){
                    return false;
                }else if(terminalRulesTable[nonTerminal][k]==word[i]){
                    return true;
                }
            }
        }else{
            for(int z =0;true;z++) {
                if (nonTerminalRulesTable[nonTerminal][z][0] != null) {
                    for (int k = i + 1; k < j; k++) {
                        operations++;
                        if (parse_naive(nonTerminalRulesTable[nonTerminal][z][0], i, k) &&
                                parse_naive(nonTerminalRulesTable[nonTerminal][z][1], k, j)) {
                            return true;
                        }
                    }
                }else{
                    return false;
                }
            }
        }
    }

    public int getOperations(){
        return operations;
    }


}
