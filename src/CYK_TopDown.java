public class CYK_TopDown extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLength;
    private Boolean[][][] table;

    public CYK_TopDown(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;

    }

    @Override
    boolean parse(String word) {
        wordLength=word.length();
        this.word=word.toCharArray();
        init_table();
        return parse_TopDown(0,0,word.length());
    }

    private void init_table(){
        this.table=new Boolean[wordLength][wordLength][wordLength];
        /*for (int i = 0; i <wordLength ; i++) {
            for (int j = 0; j <wordLength ; j++) {
                for (int k = 0; k <wordLength ; k++) {
                    table[i][j][k]=null;
                }
            }
        }*/
    }

    private boolean parse_TopDown(int nonTermRule,int i,int j){
        if()
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
                        if(parse_TopDown(nonTerminalRulesTable[nonTermRule][z][0],i,k)&&
                                parse_TopDown(nonTerminalRulesTable[nonTermRule][z][1],k,j)){
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            }
        }
    }

    public Boolean[][][] getTable(){
        return table;
    }
}
