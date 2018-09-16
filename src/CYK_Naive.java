public class CYK_Naive extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;

    public CYK_Naive(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable) {
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
    }

    @Override
    boolean parse(String word) {
        this.word=word.toCharArray();
        return parse_naive(0,0,word.length());
    }

    public boolean parse_naive(int nonTermRule,int i,int j){

        if(i==j-1){
            for (int k = 0;true; k++) {
                if(terminalRulesTable[nonTermRule][k]==null){
                    return false;
                }else if(terminalRulesTable[nonTermRule][k]==word[i]){
                    return true;
                }
            }
        }else{
            for(int z =0;true;z++){
                for (int k = i+1; k < j ; k++) {
                    if(nonTerminalRulesTable[nonTermRule][z][0]!=null){
                        if(parse_naive(nonTerminalRulesTable[nonTermRule][z][0],i,k)&&
                                parse_naive(nonTerminalRulesTable[nonTermRule][z][1],k,j)){
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
