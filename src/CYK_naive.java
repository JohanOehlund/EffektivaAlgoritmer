public class CYK_naive extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLen;
    private long operations;

    public CYK_naive(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable) {
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
    }

    @Override
    public void init(String word){
        operations=0L;
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
                        boolean b = parse_naive(nonTerminalRulesTable[nonTerminal][z][0],i,k);
                        boolean c = parse_naive(nonTerminalRulesTable[nonTerminal][z][1],k,j);
                        if (b && c) {
                            return true;
                        }
                        /*boolean bool1=parse_naive(nonTerminalRulesTable[nonTerminal][z][0], i, k);
                        boolean bool2=parse_naive(nonTerminalRulesTable[nonTerminal][z][1], k, j);
                        if(bool1&&bool2)
                            return true;
                           */
                    }
                }else{
                    return false;
                }
            }
        }
    }

    public long getOperations(){
        return operations;
    }


}
