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
    private List<Task> tasksList = new ArrayList<>();
    private List<Task> pendingTasksList = new ArrayList<>();
    private Random rand = new Random();
    private long startTime;

    public void manageTasks() {

        makeProcessors();
        makeTasks();

        startTime = System.currentTimeMillis();

        while(!allTasksDone()) {
            endTasksIfDone();
            activateTasks();
            getPendingTasks();

            if (pendingTasksList.size() > 0 && isAnyProcessorFree()) {
                Task currentTask = getAnotherTask();
                Processor currentProcessor = getFreeProcessor();

                currentProcessor.startTask(currentTask);
                System.out.println("Procesor " + (currentProcessor.getIndexNumber() + 1) + " rozpoczyna zadanie "
                        + (currentTask.getIndexNumber() + 1) + "\n");
            }

            pendingTasksList.clear();
        }

        System.out.println("\n###################################\nDone!");

    }

    private void makeProcessors() {
        for (int i = 0; i < PROCESSORS_NUM; i++) {
            processorsList.add(new Processor(i));
        }
    }

    private void makeTasks() {
        for (int i = 0; i < TASK_NUM; i++) {
            tasksList.add(new Task(i, generateRandomDurationTime(), generateRandomActivationTime()));
        }
        tasksList = sortTasksFromLongest();
    }

    private List<Task> sortTasksFromLongest() {
        return tasksList.stream().sorted(((o1, o2) -> Integer.compare(o2.getDuration(), o1.getDuration()))).collect(Collectors.toList());

    }

    private List<Task> sortPendingTasksFromLongest() {
        return pendingTasksList.stream().sorted(((o1, o2) -> Integer.compare(o2.getDuration(), o1.getDuration()))).collect(Collectors.toList());
    }

    private int generateRandomDurationTime() {
        return rand.nextInt(5001);
    }

    private int generateRandomActivationTime() {
        return rand.nextInt(2001);
    }

    private boolean isAnyProcessorFree() {
        for (Processor processor : processorsList) {
            if (processor.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    private Processor getFreeProcessor() {
        Processor freeProcessor = new Processor(100);
        for (Processor processor : processorsList) {
            if (processor.isAvailable()) {
                freeProcessor = processor;
                break;
            }
        }

        return freeProcessor;
    }

    private Task getAnotherTask() {
        Task anotherTask = new Task(10000000, 0, 1000000);
        for (Task task : pendingTasksList) {
            if (task.isPending() && !task.isActive()) {
                anotherTask = task;
                break;
            }
        }

        return anotherTask;
    }

    private void endTasksIfDone() {
        Task processorCurrentTask;
        for (Processor processor : processorsList) {
            if (!processor.isAvailable()) {
                processorCurrentTask = processor.getTask();
                if (System.currentTimeMillis() - processorCurrentTask.getStartTime() > processorCurrentTask.getDuration()) {
                    processor.endTask();
                    System.out.println("Procesor " + (processor.getIndexNumber() + 1) +
                            " zakończył zadanie " + (processorCurrentTask.getIndexNumber() + 1) + "\n");
                }
            }
        }
    }

    private void activateTasks() {
        for(Task task : tasksList) {
            if(System.currentTimeMillis() - startTime > task.getActivationTime() && !task.isDone() && !task.isPending() && !task.isActive()) {
                task.setPending(true);
                System.out.println("Zadanie " + (task.getIndexNumber() + 1) + " aktywowane\n");
            }
        }
    }

    private boolean allTasksDone(){
        for(Task task : tasksList) {
            if(!task.isDone()) {
                return false;
            }
        }

        return true;
    }

    private void getPendingTasks(){
        for(Task task : tasksList) {
            if(task.isPending() && !task.isActive() && !task.isDone()) {
                pendingTasksList.add(task);
            }
        }

        pendingTasksList = sortPendingTasksFromLongest();
    }
}