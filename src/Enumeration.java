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

    public String nextElement2(Character addChar){
        for(int i=0; i<nextStep;i++){
            //testString=addChar+testString;
            testString+=addChar;
        }
        return testString;
    }

    public String nextElement3(String addString){
        for(int i=0; i<nextStep;i++){
            //testString=addChar+testString;
            testString=testString+addString;
        }
        return testString;
    }

    public int getNumberOfChars(){
        return nextString.length();
    }

    public int getStringLengthNaive(){
        return testString.length();
    }

    public void setCounter(int val){
        counter=val;
    }

}
