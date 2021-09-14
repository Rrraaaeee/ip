package com.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private ArrayList<TaskBase> taskList;
    private TaskFactory taskFactory;

    /**
     * Constructor
     **/
    public TaskManager() {
        this.taskList = new ArrayList<TaskBase>();
        this.taskFactory = null;
    }

    /**
     * Constructor
     **/
    public TaskManager(TaskFactory taskFactory) {
        this.taskList = new ArrayList<TaskBase>();
        this.taskFactory = taskFactory;
    }

    public void addTask(TaskBase task) {
        taskList.add(task);
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
     * Save tasks
     **/
    public void saveTasks() {
        try {
            PrintWriter writer = new PrintWriter("data/save.txt", "UTF-8");
            for (TaskBase task : taskList) {
                writer.println(task);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error encountered while saving data!");
            return;
        }
    }

    /**
     * Load tasks
     **/
    public void loadTasks() {
        try {
            File savedFile = new File("data/save.txt");
            Scanner reader = new Scanner(savedFile);
            while (reader.hasNextLine()) {
                // parse string
                String [] words = reader.nextLine().split(" ");
                TaskType taskType = TaskType.getTaskTypebySymbol(words[0].substring(1, 2));
                boolean isDone = false;
                if (words[1].substring(1, 2) == "X") {
                    isDone = true;
                }
                String taskDescription = words[2];
                String taskTimeInfo = "";
                for (int i = 4; i < words.length; i++) {
                    taskTimeInfo += words[i];
                    taskTimeInfo += " ";
                }
                // create task
                TaskBase task = taskFactory.makeTask(taskType, taskDescription, taskTimeInfo);
                if (isDone) {
                    task.markDone();
                }
                addTask(task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found");
        } catch (Exception e) {
            System.out.println("Error loading file! No data is loaded");
            e.printStackTrace();
        }
    }
}

