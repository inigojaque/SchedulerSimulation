import java.util.Scanner;
import java.lang.Math;
public class Scheduler {
    public static void schedulerMain(){
        Scanner scanner = new Scanner(System.in);
        String userInput1, userInput2, userInput3;
        userInput1 = scanner.nextLine();
        userInput2 = scanner.nextLine();
        userInput3 = scanner.nextLine();
        fcfs(userInput1, userInput2, userInput3);
        hpf(userInput1, userInput2, userInput3);
        //rr();
    }
    public static void fcfs(String in1, String in2, String in3){
        //Introduction of inputs
        System.out.println("Process list in FCFS order as entered: ");
        System.out.println(in1);
        System.out.println(in2);
        System.out.println(in3);
        System.out.println("End of list.");

        //Transforming inputs into integers
        int in1Id, in1Time, in1Priority;
        int in2Id, in2Time, in2Priority;
        int in3Id, in3Time, in3Priority;

        in1Id = getValue(in1, 1);
        in2Id = getValue(in2, 1);
        in3Id = getValue(in3, 1);

        in1Time = getValue(in1, 2);
        in2Time = getValue(in2, 2);
        in3Time = getValue(in3, 2);

        in1Priority = getValue(in1, 3);
        in2Priority = getValue(in2, 3);
        in3Priority = getValue(in3, 3);

        //System.out.println("" + in1Id + in1Time + in1Priority);
        //System.out.println("" + in2Id + in2Time + in2Priority);
        //System.out.println("" + in3Id + in3Time + in3Priority);

        System.out.println();

        //Calculations for wait time and avg wait time
        int waitOfp1 = 0;
        int waitOfp2 = in1Time;
        int waitOfp3 = waitOfp2 + in2Time;
        System.out.println("fcfs wait of p1 = " + waitOfp1);
        System.out.println("fcfs wait of p2 = " + waitOfp2);
        System.out.println("fcfs wait of p3 = " + waitOfp3);
        float avgWaitTime = (float)(((float)waitOfp2 + (float)waitOfp3)/3.0);
        System.out.println("average wait time for 3 procs = " + avgWaitTime);

        //Calculations for turn-around time and avg turn-around time
        int turnAroundTimeOfp1 = in1Time;
        int turnAroundTimeOfp2 = turnAroundTimeOfp1 + in2Time;
        int turnAroundTimeOfp3 = turnAroundTimeOfp2 + in3Time;
        System.out.println("fcfs turn-around time for p1 = " + turnAroundTimeOfp1);
        System.out.println("fcfs turn-around time for p2 = " + turnAroundTimeOfp2);
        System.out.println("fcfs turn-around time for p3 = " + turnAroundTimeOfp3);
        float avgTurnAroundTime = (float)(((float)turnAroundTimeOfp1 + (float)turnAroundTimeOfp2 + (float)turnAroundTimeOfp3)/3.0);
        System.out.println("average turn-around for 3 procs = " + avgTurnAroundTime);

        //Calculations for throughput
        float throughput = (float)3.0 / (float)turnAroundTimeOfp3; 
        System.out.println("fcfs throughput for 3 procs = " + throughput);
        System.out.println(" <><> end FCFS <><> ");
        System.out.println();
    }

    public static void hpf(String in1, String in2, String in3){
        //Transforming inputs into integers
        int in1Id, in1Time, in1Priority;
        int in2Id, in2Time, in2Priority;
        int in3Id, in3Time, in3Priority;

        in1Id = getValue(in1, 1);
        in2Id = getValue(in2, 1);
        in3Id = getValue(in3, 1);

        in1Time = getValue(in1, 2);
        in2Time = getValue(in2, 2);
        in3Time = getValue(in3, 2);

        in1Priority = getValue(in1, 3);
        in2Priority = getValue(in2, 3);
        in3Priority = getValue(in3, 3);

        int greatest = findMax(in1Priority, in2Priority, in3Priority);
        int least = findMin(in1Priority, in2Priority, in3Priority);

        //Introduction 
        System.out.println("Process list in HPF order as entered: ");
        if(greatest == 2 && least == 1){
            System.out.println(in1);
            System.out.println(in3);
            System.out.println(in2);
        }else if(greatest == 3 && least == 2){
            System.out.println(in2);
            System.out.println(in1);
            System.out.println(in3);
        }else{
            System.out.println(in3);
            System.out.println(in2);
            System.out.println(in1);
        }
        System.out.println("End of list.");

        //Calculations for wait time and avg wait time
        if(greatest == 3 && least == 1){
            int waitOfp1 = 0;
            int waitOfp2 = in1Time;
            int waitOfp3 = waitOfp2 + in2Time;
            System.out.println("hpf wait of p1 = " + waitOfp1);
            System.out.println("hpf wait of p2 = " + waitOfp2);
            System.out.println("hpf wait of p3 = " + waitOfp3);
            float avgWaitTime = (float)(((float)waitOfp2 + (float)waitOfp3)/3.0);
            System.out.println("average wait time for 3 procs = " + avgWaitTime);
        }else if(greatest == 2 && least == 3){
            int waitOfp3 = 0;
            int waitOfp1 = in3Time;
            int waitOfp2 = waitOfp1 + in1Time;
            System.out.println("hpf wait of p3 = " + waitOfp3);
            System.out.println("hpf wait of p1 = " + waitOfp1);
            System.out.println("hpf wait of p2 = " + waitOfp2);
            float avgWaitTime = (float)(((float)waitOfp1 + (float)waitOfp2)/3.0);
            System.out.println("average wait time for 3 procs = " + avgWaitTime);
        }else if(greatest == 3 && least == 2){
            int waitOfp2 = 0;
            int waitOfp1 = in2Time;
            int waitOfp3 = waitOfp2 + in1Time;
            System.out.println("hpf wait of p2 = " + waitOfp2);
            System.out.println("hpf wait of p1 = " + waitOfp1);
            System.out.println("hpf wait of p3 = " + waitOfp3);
            float avgWaitTime = (float)(((float)waitOfp1 + (float)waitOfp3)/3.0);
            System.out.println("average wait time for 3 procs = " + avgWaitTime);
        }else if(greatest == 2 && least == 1){
            int waitOfp1 = 0;
            int waitOfp2 = in1Time;
            int waitOfp3 = waitOfp2 + in2Time;
            System.out.println("hpf wait of p1 = " + waitOfp1);
            System.out.println("hpf wait of p3 = " + waitOfp3);
            System.out.println("hpf wait of p2 = " + waitOfp2);
            float avgWaitTime = (float)(((float)waitOfp2 + (float)waitOfp3)/3.0);
            System.out.println("average wait time for 3 procs = " + avgWaitTime);
        }else if(greatest == 1 && least == 2){
            int waitOfp2 = 0;
            int waitOfp3 = in2Time;
            int waitOfp1 = waitOfp3 + in3Time;
            System.out.println("hpf wait of p2 = " + waitOfp2);
            System.out.println("hpf wait of p3 = " + waitOfp3);
            System.out.println("hpf wait of p1 = " + waitOfp1);
            float avgWaitTime = (float)(((float)waitOfp3 + (float)waitOfp1)/3.0);
            System.out.println("average wait time for 3 procs = " + avgWaitTime);
        }else{
            int waitOfp1 = 0;
            int waitOfp2 = in1Time;
            int waitOfp3 = waitOfp2 + in2Time;
            System.out.println("hpf wait of p1 = " + waitOfp1);
            System.out.println("hpf wait of p3 = " + waitOfp3);
            System.out.println("hpf wait of p2 = " + waitOfp2);
            float avgWaitTime = (float)(((float)waitOfp2 + (float)waitOfp3)/3.0);
            System.out.println("average wait time for 3 procs = " + avgWaitTime);
        }
{

}        
        //Calculations for turn-around time and avg turn-around time
        if(greatest == 3 && least == 1){
            int turnAroundTimeOfp1 = in1Time;
            int turnAroundTimeOfp2 = turnAroundTimeOfp1 + in2Time;
            int turnAroundTimeOfp3 = turnAroundTimeOfp2 + in3Time;
            System.out.println("hpf turn-around time for p1 = " + turnAroundTimeOfp1);
            System.out.println("hpf turn-around time for p2 = " + turnAroundTimeOfp2);
            System.out.println("hpf turn-around time for p3 = " + turnAroundTimeOfp3);
            float avgTurnAroundTime = (float)(((float)turnAroundTimeOfp1 + (float)turnAroundTimeOfp2 + (float)turnAroundTimeOfp3)/3.0);
            System.out.println("average turn-around for 3 procs = " + avgTurnAroundTime);
        }else if(greatest == 2 && least == 3){
            int turnAroundTimeOfp3 = in3Time;
            int turnAroundTimeOfp1 = turnAroundTimeOfp3 + in1Time;
            int turnAroundTimeOfp2 = turnAroundTimeOfp1 + in2Time;
            System.out.println("hpf turn-around time for p3 = " + turnAroundTimeOfp3);
            System.out.println("hpf turn-around time for p1 = " + turnAroundTimeOfp1);
            System.out.println("hpf turn-around time for p2 = " + turnAroundTimeOfp2);
            float avgTurnAroundTime = (float)(((float)turnAroundTimeOfp1 + (float)turnAroundTimeOfp2 + (float)turnAroundTimeOfp3)/3.0);
            System.out.println("average turn-around for 3 procs = " + avgTurnAroundTime);
        }else{
            int turnAroundTimeOfp2 = in2Time;
            int turnAroundTimeOfp3 = turnAroundTimeOfp2 + in3Time;
            int turnAroundTimeOfp1 = turnAroundTimeOfp3 + in1Time;
            System.out.println("hpf turn-around time for p2 = " + turnAroundTimeOfp2);
            System.out.println("hpf turn-around time for p3 = " + turnAroundTimeOfp3);
            System.out.println("hpf turn-around time for p1 = " + turnAroundTimeOfp1);
            float avgTurnAroundTime = (float)(((float)turnAroundTimeOfp1 + (float)turnAroundTimeOfp2 + (float)turnAroundTimeOfp3)/3.0);
            System.out.println("average turn-around for 3 procs = " + avgTurnAroundTime);
        }
        System.out.println(" <><> end HPF schedule <><> ");
        System.out.println();
    }
    

    public static int getValue(String x, int i){
        x = x.replaceAll("\\s", "");
        int result = Integer.parseInt(x);
        if(x.length() > 3){
            if(i == 1){
                result /= 1000;
            }else if(i == 2){
                result = (result % 1000) / 10;
            }else if(i == 3){
                result %= 10;
            }
        }else{
            if(i == 1){
                result /= 100;
            }else if(i == 2){
                result = (result % 100) / 10;
            }else if(i == 3){
                result %= 10;
            }
        }
        return result;
    }
    public static int findMax(int p1, int p2, int p3){
        if(p1 >= p2 && p1 >= p3){
            return 1;
        }else if(p2 >= p1 && p2 >= p3){
            return 2;
        }
        return 3;
    } 
    public static int findMin(int p1, int p2, int p3){
        if(p1 <= p2 && p1 <= p3){
            return 1;
        }else if(p2 <= p1 && p2 <= p3){
            return 2;
        }
        return 3;
    }
}
