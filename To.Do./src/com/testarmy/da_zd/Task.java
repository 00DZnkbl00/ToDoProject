package com.testarmy.da_zd;

import java.io.File;

public class Task {
    private String taskTitle, taskDescription;
    private boolean taskCompletion;
    private int taskPriority;
    private File fileName;

    public Task(String taskTitle, String taskDescription, int taskPriority) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskCompletion = false;
        this.taskPriority = taskPriority;
        this.fileName=new File("src/com/testarmy/da_zd/files/"+taskTitle+".txt");
    }
    public Task(String taskTitle, String taskDescription, int taskPriority,boolean taskCompletion) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskCompletion = taskCompletion;
        this.taskPriority = taskPriority;
        this.fileName=new File("src/com/testarmy/da_zd/files/"+taskTitle+".txt");
    }

    @Override
    public String toString()
    {
        return taskTitle+",waga = " +taskPriority+", ukończone = "+ taskPriority +"\n"+taskDescription;
    }
    public void changeTaskCompletion() {
        taskCompletion = !taskCompletion;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isTaskComplete() {
        return taskCompletion;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }
}
