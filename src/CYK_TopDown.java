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
        this.word=word;
        init_table();
        return parse_TopDown(0,0,word.length()-1);
    }

    private void init_table(){
        int wordLength=word.length();
        this.table=new Boolean[wordLength][wordLength];
        for (int i = 0; i <word.length() ; i++) {
            for (int j = 0; j <word.length() ; j++) {
                table[i][j]=null;
            }
        }
    }

    private boolean parse_TopDown(int initNonTermRule,int i,int j){
       if(table[i][j]!=null){
           return table[i][j];
       }else{
           table[i][j]=true;
       }
       return false;
    }

    public Boolean[][] getTable(){
        return table;
    }
}
