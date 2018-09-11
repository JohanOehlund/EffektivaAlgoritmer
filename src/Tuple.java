public class Tuple {
    private int nonTerminalLetter1;
    private int nonTerminalLetter2;

    public Tuple(int nonTerminalLetter1,int nonTerminalLetter2){
        this.nonTerminalLetter1=nonTerminalLetter1;
        this.nonTerminalLetter2 = nonTerminalLetter2;
    }

    public int getNonTerminalLetter1(){
        return nonTerminalLetter1;
    }

    public int getNonTerminalLetter2(){
        return nonTerminalLetter2;
    }

}

