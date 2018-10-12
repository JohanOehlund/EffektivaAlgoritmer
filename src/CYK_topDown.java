public class CYK_topDown extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLength;
    private Boolean[][][] table;
    private int numOfNonTerms;
    private int operations;

    public CYK_topDown(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable,
                       int numOfNonTerms){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
        this.numOfNonTerms=numOfNonTerms;
    }

    @Override
    public void init(String word){
        operations=0;
        wordLength=word.length();
        this.table=new Boolean[wordLength][wordLength][numOfNonTerms];
        this.word=word.toCharArray();
    }

    @Override
    boolean parse() {

        return parse_topDown(0, 0,wordLength);
    }


    private boolean parse_topDown(int nonTerminal,int i,int j){
        //operations++;
        if(table[i][j-1][nonTerminal]!=null){
            operations++;
            return table[i][j-1][nonTerminal];
        }else{
            if(i==j-1){
                for (int k = 0;true; k++) {
                    operations++;
                    if(terminalRulesTable[nonTerminal][k]==null){
                        table[i][j-1][nonTerminal]=false;
                        return false;
                    }else if(terminalRulesTable[nonTerminal][k]==word[i]){
                        table[i][j-1][nonTerminal]=true;
                        return true;
                    }
                }
            }else{
                for(int z =0;true;z++){

                    if(nonTerminalRulesTable[nonTerminal][z][0]!=null){
                        for (int k = i+1; k < j ; k++) {
                            operations++;
                            if(parse_topDown(nonTerminalRulesTable[nonTerminal][z][0],i,k)&&
                                    parse_topDown(nonTerminalRulesTable[nonTerminal][z][1],k,j)){
                                table[i][j-1][nonTerminal]=true;
                                return true;
                            }
                        }
                    }else{
                        operations++;
                        table[i][j-1][nonTerminal]=false;
                        return false;
                    }
                }
            }
        }
    }

    public Boolean[][][] getTable(){
        return table;
    }

    public int getOperations(){
        return operations;
    }
}
