package tpd;

/**
 * Created by Damian Deska on 2018-01-19.
 */
public class Processor {

    private boolean available = true;
    private Task task;

    public void startTask(Task _task){
        this.available = false;
        this.task = _task;
        this.task.setPending(false);
    }

    public void endTask(){
        this.available = true;
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
