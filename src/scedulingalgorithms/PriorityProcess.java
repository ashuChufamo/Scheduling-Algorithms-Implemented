package scedulingalgorithms;
public class PriorityProcess {
    public int brustTime;
    public int priority;//attributes
    public int finishTime;
    public int finishTime1;
    public int finishTime2;
    public int finishTime3;

    public PriorityProcess(int brustTime, int priority) {//constructor
        this.brustTime = brustTime;
        this.finishTime = 0;
        this.priority = priority;
    }

}
