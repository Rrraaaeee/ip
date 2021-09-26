package com.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.task.TaskBase;
import com.task.TaskFactory;
import com.task.TaskManager;
import com.task.TaskType;


public class Storage {
    private String storagePath;
    private TaskManager taskManager;
    private TaskFactory taskFactory;

    /**
     * Constructor
     * pass storage path for subsequent directory lookup
     * commandhandler and taskfactory to rebuild tasks from storage
     **/
    public Storage(String storagePath) {
        this.storagePath = storagePath;
        this.taskManager = null;
        this.taskFactory = null;
    }

    /**
     * Constructor
     * pass storage path for subsequent directory lookup
     * taskManager and taskfactory to rebuild tasks from storage
     **/
    public Storage(String storagePath, TaskManager taskManager, TaskFactory taskFactory) {
        this.storagePath = storagePath;
        this.taskManager = taskManager;
        this.taskFactory = taskFactory;
    }

    public void assignTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void assignTaskFactory(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    /**
     * Load tasks
     **/
    public void loadTasks() {
        try {
            File savedFile = new File(storagePath);
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
                taskManager.addTask(task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found");
        } catch (Exception e) {
            System.out.println("Error loading file! No data is loaded");
            e.printStackTrace();
        }
    }

    /**
     * Save tasks
     **/
    public void saveTasks() {
        try {
            PrintWriter writer = new PrintWriter(storagePath, "UTF-8");
            for (TaskBase task : taskManager.getTaskList()) {
                writer.println(task);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error encountered while saving data!");
            return;
        }
    }


}
