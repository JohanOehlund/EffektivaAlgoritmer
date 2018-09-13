public class CYK_Naive extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private String word;

    public CYK_Naive(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable) {
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
    }

    @Override
    boolean parse(String word) {
        this.word=word;
        return parse_naive(0,0,word.length());
    }

    public boolean parse_naive(int initNonTermRule,int i,int j){

        if(i==j-1){
            for (int k = 0;true; k++) {
                if(terminalRulesTable[initNonTermRule][k]==null){
                    return false;
                }else if(terminalRulesTable[initNonTermRule][k]==word.charAt(i)){
                    return true;
                }
            }
        }else{
            for(int z =0;true;z++){
                for (int k = i+1; k < j ; k++) {
                    if(nonTerminalRulesTable[initNonTermRule][z][0]!=null){
                        boolean bool1=parse_naive(nonTerminalRulesTable[initNonTermRule][z][0],i,k);
                        boolean bool2=parse_naive(nonTerminalRulesTable[initNonTermRule][z][1],k,j);
                        if(bool1&&bool2){
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            }
        }
    }


}
