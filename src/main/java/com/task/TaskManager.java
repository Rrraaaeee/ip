package com.task;

import java.util.ArrayList;

import com.command.Command;

public class TaskManager {

    private ArrayList<TaskBase> taskList;
    private TaskFactory taskFactory;


    /**
     * Constructor
     **/
    public TaskManager() {
        this.taskList = new ArrayList<TaskBase>();
        this.taskFactory = new TaskFactory();
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
    public TaskBase createTask(TaskType taskType, String taskDescription, String timeInfo) {
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
    public void listAllTasks() {
        int label = 1;
        for (TaskBase task : taskList) {
            System.out.print(label++);
            System.out.print(".");
            System.out.print(task);
            System.out.println("-------");
        }
    }

    /**
     * Mark task as done
     **/
    public void markTaskDone(int taskId) {
        taskList.get(taskId - 1).markDone();
        System.out.println(taskList.get(taskId - 1));
    }

    /**
     * Delete a task
     **/
    public void deleteTask(int taskId) {
        // data structure is 0-indexed
        System.out.println(taskList.get(taskId - 1));
        taskList.remove(taskId - 1);
    }

    /**
     * Get full tasklist
     * Used by storage unit to save tasks
     */
    public ArrayList<TaskBase> getTaskList() {
        return taskList;
    }


}

