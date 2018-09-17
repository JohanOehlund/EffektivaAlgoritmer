public class CYK_BottomUp extends Parser {
    private Integer[][][] nonTerminalRulesTable;
    private Character[][] terminalRulesTable;
    private char[] word;
    private int wordLength;
    private boolean[][][] table;
    private int numOfNonTerms;

    public CYK_BottomUp(Integer[][][] nonTerminalRulesTable, Character[][] terminalRulesTable,int numOfNonTerms){
        this.nonTerminalRulesTable=nonTerminalRulesTable;
        this.terminalRulesTable=terminalRulesTable;
        this.numOfNonTerms=numOfNonTerms;
    }

    @Override
    boolean parse(String word) {
        wordLength=word.length();
        this.word=word.toCharArray();
        table=new boolean[wordLength][wordLength][numOfNonTerms];
        return parse_bottomUp();
    }

    private boolean parse_bottomUp(){
        for (int s=0;s<wordLength;s++) {
            for (int k = 0; k < numOfNonTerms; k++) {
                for (int l = 0;true; l++) {

                    if(terminalRulesTable[k][l]==null){
                        break;
                    }

                    else if(word[s]==terminalRulesTable[k][l]){
                        table[0][s][k]=true;
                        break;
                    }

                }
            }
        }

        for (int l = 1; l < wordLength; l++) { //Y-led, antal rader i tabell.
            for (int s = 0; s <wordLength-l; s++) {//För varje cell (per rad), blir 1 mindre för varje nivå upp (l++).
                for (int p = 0; p < l; p++) { //
                    for (int k = 0; k<numOfNonTerms; k++) {
                        for (int m = 0; true; m++) {
                            if(nonTerminalRulesTable[k][m][0]==null){
                                break;
                            }else{
                                Integer b=nonTerminalRulesTable[k][m][0];
                                Integer c=nonTerminalRulesTable[k][m][1];
                                if(table[p][s][b] && table[l-p-1][s+p+1][c]){
                                    table[l][s][k]=true;
                                }
                            }

                        }

                    }
                }
            }
        }
        if(table[wordLength-1][0][0]){
            return true;
        }else{
            return false;
        }
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