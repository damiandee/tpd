import java.util.Arrays;

public class SPT {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }

        // sort jobs in ascending order of processing time
        Arrays.sort(jobs);

        // print out schedule
        for (int i = 0; i < N; i++)
            StdOut.println(jobs[i]);
    }
}

class Job {
    private String name;
    private double time;

    Job(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}