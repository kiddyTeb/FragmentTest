package util;

/**
 * Created by asus on 2016/7/27.
 */
public abstract class PriorityRunnable implements Runnable , Comparable<PriorityRunnable> {
    private int priority ;

    public PriorityRunnable(int priority){
        if (priority < 0){
            throw new IllegalArgumentException();
        }
        this.priority = priority ;
    }
    @Override
    public int compareTo(PriorityRunnable another) {
        int me = getPriority();
        int other = another.getPriority();
        return me < other ? 1 : me > other ? -1 : 0;
    }

    @Override
    public void run() {
        doWork();
    }

    public abstract void doWork();

    public int getPriority(){
        return this.priority;
    }
}
