import java.util.Stack;

public class Enumeration {
    private String testString;
    private StringBuilder nextString;
    private Stack<Character> charStack;
    private int counter;
    private int nextStep;

    public Enumeration(String testString,int nextStep){
        this.testString=testString;
        this.nextStep=nextStep;
        counter=1;

    }
    public String nextElement(){
        nextString= new StringBuilder();
        for (int j=0;j<testString.length();j++) {

            for (int i = 0; i < nextStep*counter; i++) {
                nextString.append(testString.charAt(j));
            }
        }
        counter++;
        return nextString.toString();
    }

    public void setCounter(int val){
        counter=val;
    }

}
