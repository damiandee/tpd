package tpd;

/**
 * Created by Damian Deska on 2018-01-19.
 */
public class Processor {

    private int indexNumber;
    private boolean available = true;
    private Task task;

    public Processor(int _indexNumber) {
        this.indexNumber = _indexNumber;
    }

    public void startTask(Task _task){
        this.available = false;
        this.task = _task;
        this.task.setPending(false);
        this.task.setActive(true);
        this.task.setStartTime(System.currentTimeMillis());
    }

    public void endTask(){
        this.available = true;
        this.task.setActive(false);
        this.task.setDone(true);
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
