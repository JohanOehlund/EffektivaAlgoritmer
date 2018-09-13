
public class Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    String word;

    public Parser(Integer[][][] nonTerminalRulesTable,Character[][] terminalRulesTable){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
    }

    public boolean init_Parse(String word){
        this.word=word;
        return parse(0,0,word.length());
    }

    public boolean parse(int initNonTermRule,int i,int j){

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
                        boolean bool1=parse(nonTerminalRulesTable[initNonTermRule][z][0],i,k);
                        boolean bool2=parse(nonTerminalRulesTable[initNonTermRule][z][1],k,j);
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
