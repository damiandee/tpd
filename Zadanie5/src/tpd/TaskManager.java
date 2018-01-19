package tpd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Damian Deska on 2018-01-19.
 */
public class TaskManager {

    private int PROCESSORS_NUM = 4;
    private int TASK_NUM = 10;
    private List<Processor> processorsList = new ArrayList<>();
    private List<Task> tastkList = new ArrayList<>();
    private Random rand = new Random();
    
    public void manageTasks() {

        boolean allTasksDoneFlag = false;

        makeProcessors();
        makeTasks();

        while(isStillAnyTaskPending()) {
            endTasksIfDone();

            if(isAnyProcessorFree()) {
                Task currentTask = getAnotherTask();
                Processor currentProcessor = findFreeProcessor();

                currentProcessor.startTask(currentTask);
            }
        }

        System.out.println("Done!");
        
    }
    
    private void makeProcessors() {
        for(int i = 0; i < PROCESSORS_NUM; i++) {
            processorsList.add(new Processor());
        }
    }

    private void makeTasks() {
        for(int i = 0; i < TASK_NUM; i++) {
            tastkList.add(new Task(i, generateRandomTime()));
        }
        tastkList = sortTasksFromLongest();
    }

    private List<Task> sortTasksFromLongest() {
        return tastkList.stream().sorted(((o1, o2) -> Integer.compare(o2.getDuration(), o1.getDuration()))).collect(Collectors.toList());
    }

    private int generateRandomTime(){
        return rand.nextInt(1001);
    }

    private boolean isStillAnyTaskPending(){
        for(Task task : tastkList) {
            if(task.isPending()) {
                return true;
            }
        }

        return false;
    }

    private boolean isAnyProcessorFree(){
        for(Processor processor : processorsList) {
            if(processor.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    private Processor findFreeProcessor(){
        Processor freeProcessor = new Processor();
        for(Processor processor : processorsList) {
            if(processor.isAvailable()) {
                freeProcessor = processor;
                break;
            }
        }

        return freeProcessor;
    }

    private Task getAnotherTask() {
        Task anotherTask = new Task(10000000, 0);
        for(Task task : tastkList) {
            if(task.isPending()) {
                anotherTask = task;
                break;
            }
        }

        return anotherTask;
    }

    private void endTasksIfDone(){
        Task processorCurrentTask;
        for(Processor processor : processorsList) {
            if(!processor.isAvailable()) {
                processorCurrentTask = processor.getTask();
                if(System.currentTimeMillis() - processorCurrentTask.getStartTime() > processorCurrentTask.getDuration()) {
                    processor.endTask();
                }
            }
        }
    }
}
