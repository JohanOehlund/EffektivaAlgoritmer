public class CYK_BottomUp extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLength;
    private boolean[][][] table;

    public CYK_BottomUp(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;

    }

    @Override
    boolean parse(String word) {
        wordLength=word.length();
        this.word=word.toCharArray();
        table=new boolean[wordLength][wordLength][26];
        return parse_bottomUp(0,0,wordLength-1);
    }

    private boolean parse_bottomUp(int nonTermRule,int i, int j){
        for (Character c:word) {
            for (int k = 0; k < 26; k++) {
                for (int l = 0;true; l++) {
                    if(terminalRulesTable[k][l]==null){
                        break;
                    }
                    else if(c==terminalRulesTable[k][l]){
                        table[0][l][k]=true;
                        break;
                    }
                }
            }
        }



        return false;
    }

    /*
    let the input be a string I consisting of n characters: a1 ... an.
    let the grammar contain r nonterminal symbols R1 ... Rr, with start symbol R1.
    let P[n,n,r] be an array of booleans. Initialize all elements of P to false.
    for each s = 1 to n
      for each unit production Rv -> as
        set P[1,s,v] = true
    for each l = 2 to n -- Length of span
      for each s = 1 to n-l+1 -- Start of span
        for each p = 1 to l-1 -- Partition of span
          for each production Ra -> Rb Rc
            if P[p,s,b] and P[l-p,s+p,c] then set P[l,s,a] = true
    if P[n,1,1] is true then
      I is member of language
    else
      I is not member of language
     */

    public boolean[][][] getTable(){
        return table;
    }
}
