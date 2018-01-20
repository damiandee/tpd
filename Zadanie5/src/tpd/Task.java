package tpd;

/**
 * Created by Damian Deska on 2018-01-19.
 */
public class Task {

    private int indexNumber;
    private int duration;
    private long startTime;
    private boolean pending;
    private long activationTime;
    private boolean active;
    private boolean done;

    public Task(int _indexNumber, int _duration, int _activationTime) {
        this.indexNumber = _indexNumber;
        this.duration = _duration;
        this.activationTime = _activationTime;
        this.active = false;
        this.pending = false;
        this.done = false;
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

    public long getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(long activationTime) {
        this.activationTime = activationTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
