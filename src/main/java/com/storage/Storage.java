package com.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.task.TaskBase;
import com.task.TaskFactory;
import com.task.TaskManager;
import com.task.TaskType;
import com.time.Time;
import com.ui.Ui;


public class Storage {
    private String storagePath;
    private TaskManager taskManager;
    private Ui ui;

    /**
     * Constructor
     * pass storage path for subsequent directory lookup
     * commandhandler and taskfactory to rebuild tasks from storage
     **/
    public Storage(String storagePath, Ui ui) {
        this.storagePath = storagePath;
        this.ui = ui;
        this.taskManager = null;
    }

    /**
     * Constructor
     * pass storage path for subsequent directory lookup
     * taskManager and taskfactory to rebuild tasks from storage
     **/
    public Storage(String storagePath, TaskManager taskManager, TaskFactory taskFactory) {
        this.storagePath = storagePath;
        this.taskManager = taskManager;
    }

    public void assignTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Load tasks
     **/
    public boolean loadTasks() {
        try {
            taskManager.clearTasks();
            File savedFile = new File(storagePath);
            Scanner reader = new Scanner(savedFile);
            while (reader.hasNextLine()) {
                // parse string
                String [] words = reader.nextLine().split(" ");

                // 1. get task type
                TaskType taskType = TaskType.getTaskTypebySymbol(words[0].substring(1, 2));

                // 2. get isDone
                boolean isDone = false;
                if (words[1].substring(1, 2).equals("X")) {
                    isDone = true;
                }

                // 3. get description
                String taskDescription = "";
                for (int i = 2; i < words.length; i++) {
                    if (words[i].equals("@")) {
                        break;
                    }
                    taskDescription += words[i] + " ";
                }
                taskDescription = taskDescription.trim();

                // 4. get time
                String taskTimeInfo = "";
                boolean isTimeString = false;
                for (int i = 2; i < words.length; i++) {
                    if (words[i].equals("@")) {
                        isTimeString = true;
                        continue;
                    }
                    if (isTimeString) {
                        taskTimeInfo += words[i];
                        taskTimeInfo += " ";
                    }
                }
                taskTimeInfo = taskTimeInfo.trim();
                Time time = null;
                if (!taskTimeInfo.equals("")) {
                    assert (taskType != TaskType.TODO);
                    LocalDateTime localTime = LocalDateTime.parse(taskTimeInfo,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    time = new Time(localTime);
                }

                // 5. create task
                TaskBase task = taskManager.createTask(taskType, taskDescription, time);
                if (isDone) {
                    task.markDone();
                }
                taskManager.addTask(task);
                ui.showText(task.toString());
            }
            reader.close();
            return true;
        } catch (FileNotFoundException e) {
            ui.showText("No saved file found");
            return false;
        } catch (Exception e) {
            ui.showText("Error loading file! No data is loaded");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Save tasks
     **/
    public boolean saveTasks() {
        try {
            PrintWriter writer = new PrintWriter(storagePath, "UTF-8");
            for (TaskBase task : taskManager.getTaskList()) {
                writer.println(task);
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error encountered while saving data!");
            return false;
        }
    }


}
