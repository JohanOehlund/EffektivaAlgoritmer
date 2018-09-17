public class TimerClass {
    private long startTime;
    private long stopTime;
    public TimerClass(){

    }

    public void startTimer(){
        startTime= System.currentTimeMillis();
    }

    public void stopTimer(){
        stopTime=System.currentTimeMillis();
    }

    public long getTotalRunTime(){
        return stopTime-startTime;
    }
}
