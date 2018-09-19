public class CYK_TopDown extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLength;
    private Boolean[][][] table;
    private int numOfNonTerms;

    public CYK_TopDown(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable,
                       int numOfNonTerms){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
        this.numOfNonTerms=numOfNonTerms;
    }

    @Override
    public void init(String word){
        wordLength=word.length();
        this.table=new Boolean[numOfNonTerms][wordLength+1][wordLength+1];
        this.word=word.toCharArray();
    }

    @Override
    boolean parse() {

        return parse_TopDown(0,0,wordLength);
    }


    private boolean parse_TopDown(int nonTermRule,int i,int j){
        if(table[nonTermRule][i][j]!=null){
            return table[nonTermRule][i][j];
        }else{
            if(i==j-1){
                for (int k = 0;true; k++) {
                    if(terminalRulesTable[nonTermRule][k]==null){
                        table[nonTermRule][i][j]=false;
                        return false;
                    }else if(terminalRulesTable[nonTermRule][k]==word[i]){
                        table[nonTermRule][i][j]=true;
                        return true;
                    }
                }
            }else{
                for(int z =0;true;z++){
                    for (int k = i+1; k < j ; k++) {
                        if(nonTerminalRulesTable[nonTermRule][z][0]!=null){
                            if(parse_TopDown(nonTerminalRulesTable[nonTermRule][z][0],i,k)&&
                                    parse_TopDown(nonTerminalRulesTable[nonTermRule][z][1],k,j)){
                                table[nonTermRule][i][j]=true;
                                return true;
                            }
                        }else{
                            table[nonTermRule][i][j]=false;
                            return false;
                        }
                    }
                }

            }
        }
    }

    public Boolean[][][] getTable(){
        return table;
    }
}
