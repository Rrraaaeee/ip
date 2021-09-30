package com.task;

import java.util.ArrayList;

import com.command.Command;
import com.time.Time;
import com.ui.Ui;

public class TaskManager {

    private ArrayList<TaskBase> taskList;
    private TaskFactory taskFactory;
    private Ui ui;


    /**
     * Constructor
     **/
    public TaskManager(Ui ui) {
        this.taskList = new ArrayList<TaskBase>();
        this.taskFactory = new TaskFactory();
        this.ui = ui;
    }

    /**
     * Create task from command and call taskfactory
     */
    public TaskBase createTask(Command command) {
        return taskFactory.makeTask(command);
    }

    /**
     * Create task by specifying all required info and call taskfactory
     */
    public TaskBase createTask(TaskType taskType, String taskDescription, Time timeInfo) {
        return taskFactory.makeTask(taskType, taskDescription, timeInfo);
    }

    /**
     * Add task if it is not a null ptr
     */
    public void addTask(TaskBase task) {
        if (task != null) {
            taskList.add(task);
        }
    }

    /**
     * api for list command
     **/
    public String listAllTasks() {
        String response = "";
        int label = 1;
        for (TaskBase task : taskList) {
            response += label++;
            response += ". ";
            response += task;
            response += "\n";
        }
        if (!response.equals("")) {
            // trailing new line does not render correctly in javafx
            response = response.substring(0, response.length() - 1);
        }
        return response;
    }

    /**
     * Mark task as done. Return the string of the deleted task
     **/
    public String markTaskDone(int taskId) {
        taskList.get(taskId - 1).markDone();
        return taskList.get(taskId - 1).toString();
    }

    /**
     * Delete a task
     **/
    public String deleteTask(int taskId) {
        // data structure is 0-indexed
        String taskString = taskList.get(taskId - 1).toString();
        taskList.remove(taskId - 1);
        return taskString;
    }

    /**
     * Get full tasklist
     * Used by storage unit to save tasks
     */
    public ArrayList<TaskBase> getTaskList() {
        return taskList;
    }

    /**
     * Get number of tasks
     */
    public Integer getTaskListSize() {
        return taskList.size();
    }


    /**
     * backend API to search all tasks by string
     */
    public ArrayList<TaskBase> findTasks(String taskName) {
        ArrayList<TaskBase> outcome = new ArrayList<TaskBase>();
        for (TaskBase task : taskList) {
            if (task.nameContains(taskName)) {
                outcome.add(task);
            }
        }
        return outcome;
    }

    public void clearTasks() {
        taskList.clear();
    }
}

