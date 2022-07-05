package com.testarmy.da_zd;

import java.util.Scanner;

public class ToDo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskSet taskSet = new TaskSet(10);
        String userInput;
        taskSet.loadAll();
        do {
            if (!taskSet.haveSelectedTask()) {
                System.out.println("""
                        1. Pokaż zadania
                        2. Dodaj zadanie
                        3. Wybierz zadanie
                        X. Zamknij program
                        """);
                userInput = scanner.nextLine();
                switch (userInput) {
                    case "1":
                        showTasks(scanner,taskSet);
                        break;
                    case "2":
                        String taskTitle, taskDescription;
                        int taskPriority;
                        System.out.println("Podaj nazwe zadania");
                        taskTitle = scanner.nextLine();
                        System.out.println("Podaj opis zadania");
                        taskDescription = scanner.nextLine();
                        System.out.println("Podaj wage zadania");
                        taskPriority = ScannerHelper.getInt(scanner, 0, taskSet.getMaxPriority());
                        scanner.nextLine();
                        taskSet.addTask(taskTitle, taskDescription, taskPriority);
                        break;
                    case "3":
                        do{
                            System.out.println("Podaj nazwe zadania");
                            userInput=scanner.nextLine();
                        }while (!taskSet.selectTask(userInput));
                        break;
                    case "X","x":
                        System.out.println("Dziekujemy za korzystanie");
                        break;
                    default:
                        System.out.println("---ZŁA WARTOŚĆ---");
                        break;
                }
            } else {
                System.out.println("""
                        1. Edytuj zadanie
                        2. Usuń zadanie
                        3. Powrót
                        """);
                userInput = scanner.nextLine();
                switch (userInput) {
                    case "1":
                        edditTask(scanner, taskSet);
                        break;
                    case "2":
                        taskSet.deleteTask();
                        break;
                    case "3":
                        taskSet.selectTask(null);
                        break;
                    default:
                        System.out.println("---ZŁA WARTOŚĆ---");
                        break;
                }
            }


        } while (!"X".equals(userInput)&&!"x".equals(userInput));
    }


    static private void showTasks(Scanner scanner, TaskSet taskSet) {
        Filter filter = null;
        Comparator comparator = null;
        String userInput;
        do {
            System.out.println("""
                    1. Pokaż zadania
                    2. Ustaw filtr
                    3. Ustaw sortowanie
                    4. Powrót
                    """);
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    if ( comparator != null)
                        taskSet.printTasks(filter,comparator);
                    else taskSet.printTasks(filter);
                    break;
                case "2":
                    System.out.println("""
                            1. O wadze powyżej 5
                            2. O wadze poniżej 5
                            3. Zadania do wykonania
                            4. Zadania wykonane
                            5. Brak filtru
                            6. Powrót
                            """);
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            filter=value -> value.getTaskPriority() > 5;
                            System.out.println("Dodano filtr");
                            break;
                        case "2":
                            filter=value -> value.getTaskPriority() < 5;
                            System.out.println("Dodano filtr");
                            break;
                        case "3":
                            filter=value -> !value.isTaskComplete();
                            System.out.println("Dodano filtr");
                            break;
                        case "4":
                            filter=value -> value.isTaskComplete();
                            System.out.println("Dodano filtr");
                            break;
                        case "5":
                            filter=null;
                            System.out.println("Dodano filtr");
                            break;
                    }
                    break;
                case "3":
                    System.out.println("""
                            1. Według wagi rosnąco
                            2. Według wagi malejąco
                            3. Brak filtra
                            6. Powrót
                            """);
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            comparator = ((task1, task2) -> task1.getTaskPriority() < task2.getTaskPriority());
                            System.out.println("Dodano filtr");
                            break;
                        case "2":
                            comparator = ((task1, task2) -> task1.getTaskPriority() > task2.getTaskPriority());
                            System.out.println("Dodano filtr");
                            break;

                        case "3":
                            comparator = null;
                            System.out.println("Dodano filtr");
                            break;
                    }
            }
        } while (!"4".equals(userInput));
    }
    static private void edditTask(Scanner scanner, TaskSet taskSet){
        int userInput;
        System.out.println("""
                            1. Zmień nazwe
                            2. Zmień opis
                            3. Zmień wage
                            4. Zmień status wykonania
                            5. Nic nie zmieniaj (powrót)
                            """);
        userInput=ScannerHelper.getInt(scanner,1,5);
        scanner.nextLine();
        switch (userInput) {
            case 1:
                System.out.println("Podaj nazwe zadania");
                String input=scanner.nextLine();
                taskSet.setTaskTitle(input);
                break;
            case 2:
                System.out.println("Podaj opis zadania");
                String input2=scanner.nextLine();
                taskSet.setTaskDescription(input2);
                break;
            case 3:
                System.out.println("Podaj wage zadania");
                int input3=ScannerHelper.getInt(scanner,0,taskSet.getMaxPriority());
                scanner.nextLine();
                taskSet.setTaskPriority(input3);
                break;
            case 4:
                taskSet.changeTaskCompletion();
                break;
        }
    }
}
