package scedulingalgorithms;
public class RRProcess {
    public int brustTime;
    public int finishTime;//attributes
    public int hasRunTime;

    public RRProcess(int brustTime) {//constructor
        this.brustTime = brustTime;
        this.finishTime = 0;
        this.hasRunTime = 0;
    }

}
