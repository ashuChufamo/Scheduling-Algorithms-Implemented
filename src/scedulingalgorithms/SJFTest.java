package scedulingalgorithms;

import java.util.*;

public class SJFTest {
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

    public class SJFProcess {

        public int brustTime;
        public int arrivalTime;//attributes
        public int finishTime;
        public int hasRunTime;

        public SJFProcess(int brustTime, int arrivalTime) {//constructor
            this.brustTime = brustTime;
            this.arrivalTime = arrivalTime;
            this.finishTime = 0;
            this.hasRunTime = 0;
        }  //constructor

    }

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
    
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nThis is a sceduling algorithm");
            System.out.println("Which one do you prefer to check");
            System.out.println("FCFS........................1");
            System.out.println("SJF Non-Premtive............2");
            System.out.println("SJF Premtive................3");
            System.out.println("Round Robin.................4");
            System.out.println("Priority Sceduling..........5");            //Menu
            System.out.print("Command: ");
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    FCFS();
                    break;
                case 2:
                    sJFNonPremitiveScheduling();
                    break;
                case 3:
                    sJFPremitiveScheduling();
                    break;
                case 4:
                    RRSceduling();
                    break;
                case 5:
                    PrioritySceduling();
                    break;
                default:
                    System.out.println("Incorrect input, Please try again!");
                    break;
            }
        }
    }

    public static SJFProcess[] SJFInput() { //input function for the SJF algorithms
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how much processes are there:  ");
        int numberOfProcesses = scanner.nextInt();
        SJFProcess listOfProcesses[] = new SJFProcess[numberOfProcesses];
        System.out.println();
        for (int i = 0; i < numberOfProcesses; i++) {//to fill out array by input of processes
            System.out.print("Enter the brust time of process number " + (i + 1) + ":  ");
            int brustTime = scanner.nextInt();
            System.out.print("Enter the arrival time of process number " + (i + 1) + ":  ");
            int arrivalTime = scanner.nextInt();
            listOfProcesses[i] = new SJFProcess(brustTime, arrivalTime);
            System.out.println();
        }
        return listOfProcesses; //returns array of processes for them to work with
    }

    public static void resultPrinter(SJFProcess[] listOfProcesses) {  //output function which displays the required aributes
        double averageWaitingTime = 0;
        double averageTurnaroudTime = 0;
        System.out.println();
        for (int i = 0; i < listOfProcesses.length; i++) {
            averageWaitingTime += (listOfProcesses[i].finishTime - listOfProcesses[i].brustTime) - listOfProcesses[i].arrivalTime;
            averageTurnaroudTime += listOfProcesses[i].finishTime - listOfProcesses[i].arrivalTime;
            System.out.println("Process " + (i + 1) + " finished with turnaround time of " + (listOfProcesses[i].finishTime - listOfProcesses[i].arrivalTime) + " and waiting time of " + ((listOfProcesses[i].finishTime - listOfProcesses[i].brustTime) - listOfProcesses[i].arrivalTime) + ".");
        }
        System.out.println("The average waiting time is " + (averageWaitingTime) / listOfProcesses.length);
        System.out.println("The average turnaround time is " + (averageTurnaroudTime) / listOfProcesses.length);
    }

    public static void FCFS() {//First come first served algorithm function
        int timeElapsed = 0;
        SJFProcess listOfProcesses[] = SJFInput(); //inputs
        ArrayList<SJFProcess> processesNow = new ArrayList<>(); //a list of current unfinished processes
        while (true) {
            if (listOfProcesses.length == 0) {//checks if for zero processes
                break;
            }
            processesNow.clear(); //clears the unfinished processes in each loop
            for (SJFProcess process : listOfProcesses) {
                if (process.finishTime == 0) {
                    processesNow.add(process);
                }
            }
            if (processesNow.isEmpty()) {
                int sumOfDifference = 0;
                for (SJFProcess process : listOfProcesses) {
                    if (process.brustTime == process.finishTime) {          //breaks the process
                        sumOfDifference++;
                    }
                }
                if (sumOfDifference != 0) {
                    break;
                }
                timeElapsed++;
                continue;
            }
            SJFProcess smallestPriorityProcess = processesNow.get(0);
            for (SJFProcess process : processesNow) {
                if (process.arrivalTime < smallestPriorityProcess.arrivalTime) {//check for the first process
                    smallestPriorityProcess = process;
                }
            }
            smallestPriorityProcess.finishTime = timeElapsed;
            for (int i = 0; i < smallestPriorityProcess.brustTime; i++) {       //loop until finished
                timeElapsed++;
                smallestPriorityProcess.finishTime++;
            }
        }

        double averageTurnaroudTime = 0;
        for (int i = 0; i < listOfProcesses.length; i++) {      //return output
            averageTurnaroudTime += listOfProcesses[i].finishTime;
            System.out.println("Process " + (i + 1) + " finished with turnaround time of " + listOfProcesses[i].finishTime + ".");
        }
        System.out.println("The average turnaround time is " + (averageTurnaroudTime) / listOfProcesses.length);

    }

    public static void sJFNonPremitiveScheduling() { //SJF function for the non premitive
        int timeElapsed = 0;
        SJFProcess listOfProcesses[] = SJFInput();//get input
        ArrayList<SJFProcess> processesNow = new ArrayList<>();
        while (true) {
            if (listOfProcesses.length == 0) {//check for empty process
                break;
            }
            processesNow.clear();
            for (SJFProcess process : listOfProcesses) {
                if (process.finishTime == 0) {
                    if (timeElapsed >= process.arrivalTime) {//add every unprocessed process to the list
                        processesNow.add(process);
                    }
                }
            }
            if (processesNow.isEmpty()) {
                int sumOfDifference = 0;
                for (SJFProcess process : listOfProcesses) {
                    if (process.brustTime == process.hasRunTime) { //check if every process is over
                        sumOfDifference++;
                    }
                }
                if (sumOfDifference != 0) {
                    break;
                }
                timeElapsed++;
                continue;
            }
            SJFProcess shortestBrustTimeProcess = processesNow.get(0);
            for (SJFProcess process : processesNow) {
                if (process.brustTime < shortestBrustTimeProcess.brustTime) { //check for the shortest job
                    shortestBrustTimeProcess = process;
                }
            }
            shortestBrustTimeProcess.finishTime = timeElapsed;
            for (int i = 0; i < shortestBrustTimeProcess.brustTime; i++) {
                timeElapsed++;
                shortestBrustTimeProcess.hasRunTime++;      //loop until finished
                shortestBrustTimeProcess.finishTime++;
            }
        }

        resultPrinter(listOfProcesses);         //give the output
    }

    public static void sJFPremitiveScheduling() { //SJF function for the premitive
        int timeElapsed = 0;
        SJFProcess listOfProcesses[] = SJFInput();//get input

        ArrayList<SJFProcess> processesNow = new ArrayList<>();
        while (true) {
            if (listOfProcesses.length == 0) {//check for noprocess array
                break;
            }
            processesNow.clear();
            for (SJFProcess process : listOfProcesses) {
                if (process.hasRunTime != process.brustTime) {
                    if (timeElapsed >= process.arrivalTime) {//add every unfinished process
                        processesNow.add(process);
                    }
                }
            }
            for (SJFProcess sJFPremitiveProcess : listOfProcesses) {
                if (sJFPremitiveProcess.brustTime != sJFPremitiveProcess.hasRunTime) {//increment time on every process after every loop 
                    sJFPremitiveProcess.finishTime++;
                }
            }
            if (processesNow.isEmpty()) {
                int sumOfDifference = 0;
                for (SJFProcess process : listOfProcesses) {
                    if (process.brustTime == process.hasRunTime) {//check if every process is over
                        sumOfDifference++;
                    }
                }
                if (sumOfDifference != 0) {
                    break;
                }
                timeElapsed++;
                continue;
            }
            SJFProcess shortestBrustTimeProcess = processesNow.get(0);
            for (SJFProcess process : processesNow) {
                if (process.brustTime < shortestBrustTimeProcess.brustTime) {//check for every process in each loop
                    shortestBrustTimeProcess = process;
                }
            }
            timeElapsed++;
            shortestBrustTimeProcess.hasRunTime++;//incriment the shortest loop
        }

        resultPrinter(listOfProcesses);//display the result 

    }

    public static void RRSceduling() {      //function for Round Robin Sceduling Algorithm
        int timeElapsed = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Round Robin Quantum Time:  ");
        int roundRobinQuantumTime = scanner.nextInt();
        System.out.print("Enter how much processes are there:  ");
        int numberOfProcesses = scanner.nextInt();
        RRProcess listOfProcesses[] = new RRProcess[numberOfProcesses]; //get input of processes
        System.out.println();
        for (int i = 0; i < numberOfProcesses; i++) {//to fill out array by input of processes
            System.out.println("Enter the brust time of process number " + (i + 1) + ":  ");
            int brustTime = scanner.nextInt();
            listOfProcesses[i] = new RRProcess(brustTime);
        }

        ArrayList<RRProcess> processesNow = new ArrayList<>();
        while (true) {
            if (listOfProcesses.length == 0) { //check for empty array
                break;
            }
            processesNow.clear();
            for (RRProcess process : listOfProcesses) {
                if (process.hasRunTime != process.brustTime) {      //add every unfinished array
                    processesNow.add(process);
                }
            }
            if (processesNow.isEmpty()) {
                int sumOfDifference = 0;
                for (RRProcess process : listOfProcesses) {
                    if (process.brustTime == process.hasRunTime) {      //end the function if every process is over
                        sumOfDifference++;
                    }
                }
                if (sumOfDifference != 0) {
                    for (RRProcess sJFPremitiveProcess : listOfProcesses) {
                        sJFPremitiveProcess.finishTime++;
                    }
                    break;
                }
                timeElapsed++;
                continue;
            }
            timeElapsed++;
            for (RRProcess process : processesNow) {
                for (int i = 0; i < roundRobinQuantumTime; i++) {
                    if (process.brustTime > process.hasRunTime) {
                        process.hasRunTime++;
                        for (RRProcess sJFPremitiveProcess : listOfProcesses) {      //loop for quantum time for every process until finished
                            if (sJFPremitiveProcess.brustTime != sJFPremitiveProcess.hasRunTime) {
                                sJFPremitiveProcess.finishTime++;
                            }
                        }
                    }
                }

            }

        }

        double averageTurnaroudTime = 0;
        System.out.println();
        for (int i = 0; i < listOfProcesses.length; i++) {      //display the result
            averageTurnaroudTime += listOfProcesses[i].finishTime;
            System.out.println("Process " + (i + 1) + " finished with turnaround time of " + listOfProcesses[i].finishTime + ".");
        }
        System.out.println("The average turnaround time is " + (averageTurnaroudTime) / listOfProcesses.length);
    }

    public static void PrioritySceduling() {//function for Priority Sceduling
        int timeElapsed = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how much processes are there:  ");      //take input
        int numberOfProcesses = scanner.nextInt();
        PriorityProcess listOfProcesses[] = new PriorityProcess[numberOfProcesses];
        System.out.println();
        for (int i = 0; i < numberOfProcesses; i++) {//to fill out array by input of processes
            System.out.print("Enter the brust time of process number " + (i + 1) + ":  ");
            int brustTime = scanner.nextInt();
            System.out.print("Enter the priority of process number " + (i + 1) + ":  ");
            int priority = scanner.nextInt();
            listOfProcesses[i] = new PriorityProcess(brustTime, priority);
            System.out.println();
        }

        ArrayList<PriorityProcess> processesNow = new ArrayList<>();
        while (true) {
            if (listOfProcesses.length == 0) {      //check for empty array
                break;
            }
            processesNow.clear();
            for (PriorityProcess process : listOfProcesses) {       //add every unfinished process
                if (process.finishTime == 0) {
                    processesNow.add(process);
                }
            }
            if (processesNow.isEmpty()) {
                int sumOfDifference = 0;
                for (PriorityProcess process : listOfProcesses) {
                    if (process.brustTime == process.finishTime) {  //check if every process has finished
                        sumOfDifference++;
                    }
                }
                if (sumOfDifference != 0) {
                    break;
                }
                timeElapsed++;
                continue;
            }
            PriorityProcess smallestPriorityProcess = processesNow.get(0);
            for (PriorityProcess process : processesNow) {
                if (process.priority < smallestPriorityProcess.priority) { //Check for the highes priority(i.e smallest integer)
                    smallestPriorityProcess = process;
                }
            }
            smallestPriorityProcess.finishTime = timeElapsed;
            for (int i = 0; i < smallestPriorityProcess.brustTime; i++) {
                timeElapsed++;
                smallestPriorityProcess.finishTime++;
            }
        }

        double averageTurnaroudTime = 0;
        for (int i = 0; i < listOfProcesses.length; i++) {      //display the result
            averageTurnaroudTime += listOfProcesses[i].finishTime;
            System.out.println("Process " + (i + 1) + " finished with turnaround time of " + listOfProcesses[i].finishTime + ".");
        }
        System.out.println("The average turnaround time is " + (averageTurnaroudTime) / listOfProcesses.length);

    }



}
