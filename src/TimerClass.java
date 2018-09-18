public class TimerClass {
    private long startTime;
    private long stopTime;
    public TimerClass(){

    }

    public void startTimer(){
        startTime= System.nanoTime();
    }

    public void stopTimer(){
        stopTime=System.nanoTime();
    }

    public long getTotalRunTime(){
        return stopTime-startTime;
    }
}
