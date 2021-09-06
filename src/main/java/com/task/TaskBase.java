package com.task;


// Some funcitionalities
// Time related: (Use time class)
// 1. No start/ end time (todo)
// 2. Has end time (deadline)
// 3. Has start and end time (event)
//
// Time related (extension)
// 1. Tentative time & confirm (time class)
// 2. Snoozing ( postpone/reschedule) (time class)
// 3. Recurring task (time class, checked by task manager)
// 4. Time dependencies (todo + has start time)
// 4.1 Attribute, can be sorted
// 5. Do withim period (todo + has start/end time)
// 6. Fixed duration (todo + fixed duration)
// 7. Reminder (task manager)
// 8. Find free time (task manager)
// 9. View daily schedule (task manager)
// 10. Detect clash (task manager)
// 11. detect duplicate (task manager)
// 12. sort, by different orders (task manager)
// 12.1 each attribute must be comparable
// 13. Reminder
// 14. Find free time
// 15. Update (task manager)
// 16. Priorities ( attribute)
// 17. Archive (task manager)

public abstract class TaskBase {
    private TaskType taskType;
    private String taskTime;
    private String taskDescription;
    private boolean isDone;

    /**
     * Constructor of task that accepts tasktype, time and taskcontent
     **/
    public TaskBase(TaskType taskType, String taskTime, String taskDescription) {
        this.taskType = taskType;
        this.taskTime = taskTime;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Utility to print all info related to task for list command
     **/
    public String toString() {
        String s = new String("");
        s += String.format("[%s] [%c] %s | ",
                    getTaskSymbol(),
                    isDone ? 'X' : ' ',
                    taskDescription);
        s += taskTime.toString();
        return s;
    }

    private String getTaskSymbol() {
        if (taskType == TaskType.TODO) {
            return "T";
        } else if (taskType == TaskType.DEADLINE) {
            return "D";
        } else if (taskType == TaskType.EVENT) {
            return "E";
        } else {
            return "?";
        }

    }
}
