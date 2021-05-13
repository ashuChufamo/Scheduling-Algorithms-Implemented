package scedulingalgorithms;
public class SJFProcess {
    public int brustTime;
    public int arrivalTime;//attributes
    public int finishTime;
    public int hasRunTime;
    
    public SJFProcess(int brustTime, int arrivalTime) {//constructor
        this.brustTime = brustTime;
        this.arrivalTime = arrivalTime;
        this.finishTime = 0;
        this.hasRunTime= 0;
    }  //constructor
    
}
