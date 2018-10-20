import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ResultMatrix {
    private int currentRow;
    private PrintWriter writer;

    public ResultMatrix(String fileName){
        currentRow=0;
        try {
            writer=new PrintWriter("Matlab_plots/"+fileName+".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void addToMatrixTime(int numberOfTests,int[] steps,int[] naiveSteps,long[] naiveRes,long[] topDownRes,long[] bottomUpRes){
        if(writer!=null){
            for (int i = 0; i < numberOfTests; i++) {
                writer.println(steps[i]+" "+naiveSteps[i]+" "+naiveRes[i]+" "+topDownRes[i]+" "+bottomUpRes[i]);
            }
        }
    }

    public void addToMatrixOps(int numberOfTests,int[] steps,int[] naiveSteps,int[] naiveOps,int[] topDownOps,int[] bottomUpOps){
        if(writer!=null){
            for (int i = 0; i < numberOfTests; i++) {
                writer.println(steps[i]+" "+naiveSteps[i]+" "+naiveOps[i]+" "+topDownOps[i]+" "+bottomUpOps[i]);
            }
        }
    }

    public void closeWriter(){
        writer.close();
    }
}
