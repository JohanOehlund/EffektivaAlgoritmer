public class CYK_TopDown extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLength;
    private Boolean[][][] table;
    private int numOfNonTerms;
    private int operations;

    public CYK_TopDown(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable,
                       int numOfNonTerms){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
        this.numOfNonTerms=numOfNonTerms;
    }

    @Override
    public void init(String word){
        operations=0;
        wordLength=word.length();
        this.table=new Boolean[wordLength][wordLength+1][numOfNonTerms];
        this.word=word.toCharArray();
    }

    @Override
    boolean parse() {

        return parse_TopDown(0, 0,wordLength);
    }


    private boolean parse_TopDown(int nonTerminal,int i,int j){
        operations++;
        if(table[i][j][nonTerminal]!=null){
            return table[i][j][nonTerminal];
        }else{
            if(i==j-1){
                for (int k = 0;true; k++) {
                    if(terminalRulesTable[nonTerminal][k]==null){
                        table[i][j][nonTerminal]=false;
                        return false;
                    }else if(terminalRulesTable[nonTerminal][k]==word[i]){
                        table[i][j][nonTerminal]=true;
                        return true;
                    }
                }
            }else{
                for(int z =0;true;z++){
                    if(nonTerminalRulesTable[nonTerminal][z][0]!=null){
                        for (int k = i+1; k < j ; k++) {
                            if(parse_TopDown(nonTerminalRulesTable[nonTerminal][z][0],i,k)&&
                                    parse_TopDown(nonTerminalRulesTable[nonTerminal][z][1],k,j)){
                                table[i][j][nonTerminal]=true;
                                return true;
                            }
                        }
                    }else{
                        table[i][j][nonTerminal]=false;
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
