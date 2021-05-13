package scedulingalgorithms;
public class PriorityProcess {
    public int brustTime;
    public int priority;//attributes
    public int finishTime;

    public PriorityProcess(int brustTime, int priority) {//constructor
        this.brustTime = brustTime;
        this.finishTime = 0;
        this.priority = priority;
    }

}
