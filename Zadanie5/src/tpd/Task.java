package tpd;

/**
 * Created by Damian Deska on 2018-01-19.
 */
public class Task {

    private int indexNumber;
    private int duration;
    private long startTime;
    private boolean pending;

    public Task(int _indexNumber, int _duration) {
        this.indexNumber = _indexNumber;
        this.duration = _duration;
        this.pending = true;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
