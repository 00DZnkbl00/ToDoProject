package com.testarmy.da_zd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TaskSet {
    private Set<Task> tasksSet = new HashSet<>();
    private final int maxPriority;
    Task selectedTask =null;

    public TaskSet(int maxPriority) {
        this.maxPriority=maxPriority;
    }

    public void addTask(String taskTitle, String taskDescription, int taskPriority) {
        Task newTask=new Task(taskTitle,taskDescription,taskPriority);
        tasksSet.add(newTask);
        saveTask(newTask);
    }

    public void changeTaskCompletion() {
        selectedTask.changeTaskCompletion();
        System.out.println("Zmienono status wykonania na ->"+selectedTask.isTaskComplete());
        saveTask(selectedTask);
    }

    public void setTaskTitle(String taskTitle) {
        selectedTask.setTaskTitle(taskTitle);
        System.out.println("Zmienono tytuł na ->"+selectedTask.getTaskTitle());
        saveTask(selectedTask);
    }

    public void setTaskDescription(String taskDescription) {
        selectedTask.setTaskDescription(taskDescription);
        System.out.println("Zmienono opis na ->"+selectedTask.getTaskDescription());
        saveTask(selectedTask);
    }

    public void setTaskPriority(int taskPriority) {
        selectedTask.setTaskPriority(taskPriority);
        System.out.println("Zmienono wage zadania na ->"+selectedTask.getTaskPriority());
        saveTask(selectedTask);
    }

    public void deleteTask() {
        if (selectedTask!=null) {
            tasksSet.remove(selectedTask);
            File file=selectedTask.getFileName();
            file.delete();
            selectedTask=null;
            System.out.println("Usuniento zadanie");
        }
        else {
            System.out.println("Błąd usuwania");
        }
    }

    public void printTasks(Filter filter){
        for (Task task:tasksSet) {
            if (filter==null||filter.check(task)) {
                System.out.println(task);
            }
        }
    }

    public void printTasks(Filter filter, Comparator comparator){
        List<Task> tempList=new ArrayList<>();
        for (Task task:tasksSet) {
            if (filter==null||filter.check(task)) {
                if (tempList.isEmpty()) {
                    tempList.add(task);
                    continue;
                }
                for (int i=0;i< tempList.size();i++)
                {
                    System.out.println(i+" + "+ tempList.size());
                    if(comparator.compare(task,tempList.get(i))) {
                        System.out.println(i+" = "+ tempList.size());
                        tempList.add(i,task);
                        break;
                    }
                    if (i+1>= tempList.size()) {
                        tempList.add(task);
                        break;
                    }
                }
            }
        }
        for (Task task:tempList) {
            System.out.println(task);
        }
    }

    public boolean haveSelectedTask() {
        return !(selectedTask==null);
    }

    public boolean selectTask(String input) {
        if (input==null) {
            selectedTask=null;
            return false;
        }
        for (Task task:tasksSet)
        {
            if(input.equals(task.getTaskTitle())) {
                selectedTask=task;
                return true;
            }
        }
        System.out.println("Nie ma takiego Zadania");
        return false;
    }

    public void saveTask(Task task)
    {
        try (FileWriter fileWriter=new FileWriter(task.getFileName())){
            fileWriter.write(task.getTaskTitle()+"\n");
            fileWriter.write(task.getTaskDescription()+"\n");
            fileWriter.write(task.getTaskPriority()+"\n");
            fileWriter.write(task.isTaskComplete()+"");
        }
        catch (IOException exception)
        {
            System.out.println("Błąd wczytywania pliku");
        }
    }
    public void loadAll(){
        File fileFolder=new File("src/com/testarmy/da_zd/files");
        File[] files= fileFolder.listFiles();
        for (File file:files)
        {
            loadFromFile(file);
        }
    }
    private void loadFromFile(File file)
    {
        String[] inputs=new String[4];

        try (Scanner scanner=new Scanner(file)){
            for (int i = 0; i < inputs.length; i++) {
                if (scanner.hasNextLine()) {
                    inputs[i]=scanner.nextLine();
                    if (inputs[i].isBlank()||inputs[i].isEmpty())
                        throw new IndexOutOfBoundsException();
                }
                else {
                    throw new IndexOutOfBoundsException();
                }
            }
        }
        catch (FileNotFoundException exception){
            System.out.println("Brak pliku: "+file.getPath());
        }
        catch (IndexOutOfBoundsException exception) {
            System.out.println("Plik uszkodzony");
        }

        try {
            Task newTask=new Task(inputs[0],inputs[1],Integer.parseInt(inputs[2]),Boolean.getBoolean(inputs[3]));
            tasksSet.add(newTask);
            saveTask(newTask);
        }
        catch (InputMismatchException exception)
        {
            System.out.println("Nieprawidłowe dane");
        }

    }

    public int getMaxPriority() {
        return maxPriority;
    }
}
